<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>

    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

    <script type="text/javascript">

        function new_order() {

            let goods = [];

            $("input:checkbox:checked").each(function () {
                goods.push($(this).val());
            });

            let jsonObj = JSON.stringify(goods);

            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "post",
                url: "/customerSuccess/newOrder/",
                cache: false,
                data: jsonObj,
                success: function () {
                    alert('Order was created.');
                    location.reload();
                },
                error: function () {
                    alert('Order was not created.');
                }
            });
        }

        let good;

        $.getJSON("goodAdminList", function (result) {
            $.each(result, function (i, field) {

                $("div").append("<label>")
                        .append("<input type='checkbox' name='goodCheck' value='" + field.id + "'>")
                        .append(field.title + " " + field.price + "\$")
                        .append("</label>")
                        .append("<br><br>");
            });
        });


    </script>

</head>

<body>

<div>

</div>

<input type="button" value="Make an order" onclick="new_order()">

</body>
</html>
