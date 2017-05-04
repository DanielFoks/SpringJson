<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CustomerAdmin</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

    <script type="text/javascript">

        function add_customer() {

            let customer = new Object();
            customer.fio = $("#loginInput").val();
            customer.password = $("#passwordInput").val();

            let jsonObj = JSON.stringify(customer);


            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "post",
                url: "/customerAdmin/add/",
                cache: false,
                data: jsonObj,
                success: function () {
                    alert('Customer added.');
                    location.reload();
                },
                error: function () {
                    alert('Customer is not added.');
                }
            });
        }

        function delete_customer(data) {
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "post",
                url: "/customerAdmin/remove/",
                cache: false,
                data: data,
                success: function () {
                    alert('Customer Deleted.');
                    location.reload();
                },
                error: function () {
                    alert('Customer is not deleted.');
                }
            });
        }


        let customer;

        $.getJSON("customerAdminList", function (result) {
            $.each(result, function (i, field) {
                customer = JSON.stringify(field);
                let test = document.createElement('td');
                test.innerHTML = "<td><input type='button' value='DELETE'/></td>";

                $("tbody").append("<tr>")
                        .append("<td>" + field.id + "</td>")
                        .append("<td>" + field.fio + "</td>")
                        .append(test)
                        .append("</tr>");

                test.onclick = function (id) {
                    return function () {
                        delete_customer(id);
                    };
                }(customer);
            });
        });

    </script>
</head>
<body>

<h1>Customer List</h1>

<table class="tg">
    <tr id="tr_head">
        <th id="t_id" width="80">ID</th>
        <th id="t_fio" width="120">FIO</th>
        <th id="t_delete" width="60">Delete</th>
    </tr>
</table>

<br/>
<br/>


<form id="addForm">
    <input id="loginInput" type="text"/><label>Login</label>
    <br/>
    <br/>
    <input id="passwordInput" type="text"/><label>Password</label>
    <br/>
    <br/>
    <input type="button" value="Add" onclick="add_customer()"/>
</form>

</body>
</html>
