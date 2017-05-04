<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Customer Page</title>
    <link href="../../resources/css/style.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

    <script type="text/javascript">

        function check_customer() {

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
                url: "/customer/check/",
                cache: false,
                data: jsonObj,
                success: function () {
                    alert('You entered.');
                    window.location.replace("http://localhost:8080/customerSuccess");
                },
                error: function () {
                    alert('You did not entered.');
                }
            });
        }
    </script>
</head>
<body>

<form id="checkForm">
    <input id="loginInput" type="text"/><label>Login</label>
    <br/>
    <br/>
    <input id="passwordInput" type="text"/><label>Password</label>
    <br/>
    <br/>
    <input type="button" value="Enter" onclick="check_customer()"/>
</form>

</body>
</html>