<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>GoodAdmin</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

    <script type="text/javascript">

        function add_good() {

            let good = new Object();
            good.title = $("#titleInput").val();
            good.price = $("#priceInput").val();

            let jsonObj = JSON.stringify(good);


            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "post",
                url: "/goodAdmin/add/",
                cache: false,
                data: jsonObj,
                success: function () {
                    alert('Good added.');
                    location.reload();
                },
                error: function () {
                    alert('Good is not added.');
                }
            });
        }

        function delete_good(data) {
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "post",
                url: "/goodAdmin/remove/",
                cache: false,
                data: data,
                success: function () {
                    alert('Good deleted.');
                    location.reload();
                },
                error: function () {
                    alert('Good is not deleted.');
                }
            });
        }


        let good;

        $.getJSON("goodAdminList", function (result) {
            $.each(result, function (i, field) {
                good = JSON.stringify(field);

                let test = document.createElement('td');
                test.innerHTML = "<td><input type='button' value='DELETE'/></td>";

                $("tbody").append("<tr>")
                        .append("<td>" + field.id + "</td>")
                        .append("<td>" + field.title + "</td>")
                        .append("<td>" + field.price + "</td>")
                        .append(test)
                        .append("</tr>");


                test.onclick = function (id) {
                    return function () {
                        delete_good(id);
                    };
                }(good);
            });
        });


    </script>
</head>
<body>

<h1>Good List</h1>

<table class="tg">
    <tr id="tr_head">
        <th id="t_id" width="80">ID</th>
        <th id="t_title" width="120">TITLE</th>
        <th id="t_price" width="120">PRICE</th>
        <th id="t_delete" width="60">Delete</th>
    </tr>
</table>

<br/>
<br/>


<form id="addForm">
    <input id="titleInput" type="text"/><label>Title</label>
    <br/>
    <br/>
    <input id="priceInput" type="text"/><label>Price</label>
    <br/>
    <br/>
    <input type="button" value="Add" onclick="add_good()"/>
</form>

</body>
</html>
