<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>login</title>
</head>
<body>
<h2 style="text-align: center; color: blue;">LOGIN</h2>
<div align="center">
    <form action="/doLogin" method="post">

        <div class="col-sm-6">
            <label for="username" class="form-label">UserName</label>
            <input type="text" Class="form-control" name="username" id="username" placeholder="" value=""/>
        </div>

        <div class="col-sm-6">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="" value=""
                   required=""/>
        </div>

        <button class=" btn btn-primary btn-lg" type="submit">Login</button>

    </form>
</div>
</body>
</html>

<style>

</style>