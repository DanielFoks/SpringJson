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

        function delete_order(data) {
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "post",
                url: "/orderAdmin/remove/",
                cache: false,
                data: data,
                success: function () {
                    alert('Order deleted.');
                    location.reload();
                },
                error: function () {
                    alert('Order is not deleted.');
                }
            });
        }


        let order;

        $.getJSON("orderAdminList", function (result) {
            $.each(result, function (i, field) {
                order = JSON.stringify(field);

                let test = document.createElement('td');
                test.innerHTML = "<td><input type='button' value='DELETE'/></td>";

                $("#mainTable").append("<tr>")
                        .append("<td>" + field.id + "</td>")
                        .append("<td>" + field.customer.fio + "</td>")
                        .append("<td><table id='innerTable' border=0>");
                $.each(field.goods, function (i, good) {

                    $("#innerTable").append("<tr><td>" + good.title + " " + good.price + "\$ </td></tr>");
                });
                $("#mainTable").append($("</table>")).append($("</td>"));
                $("#mainTable").append(test)
                        .append("</tr>");

                test.onclick = function (id) {
                    return function () {
                        delete_order(id);
                    };
                }(order);

            });
        });


    </script>
</head>
<body>

<h1>Order List</h1>

<table id="mainTable" class="tg">
    <tr id="tr_head">
        <th id="t_id" width="80">ID</th>
        <th id="t_customer" width="120">CUSTOMER</th>
        <th id="t_good" width="120">GOODS</th>
        <th id="t_delete" width="60">Delete</th>
    </tr>
</table>

<br/>
<br/>

</body>
</html>
