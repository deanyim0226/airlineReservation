<%@page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>User Form</title>
</head>
<body>
<header>
    <nav class="navbar bg-primary">
        <ul class="nav justify-content-end">
            <li > <a  class="btn btn-primary dropdown"  href="home">HOME</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="userForm">USER FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="roleForm">ROLE FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="passengerForm">PASSENGER FORM</a></li>
        </ul>
    </nav>
</header>
<div align="center">

    <h1>ROLE FORM</h1>
    <f:form action="saveRole" method="post" modelAttribute="role">
        <table>
            <tr>
                <td>ROLE-ID</td>
                <td><f:input path="roleId"/></td>
            </tr>
            <tr>
                <td>NAME</td>
                <td><f:input path="roleName"/></td>
            </tr>
            <tr>
                <td>DESCRIPTION</td>
                <td><f:input path="description"/></td>
            </tr>

        </table>
        <input type="submit" value="submit">
    </f:form>
</div>

<div align="center">
    <h2>ROLE RECORD</h2>
    <table class="table table-primary table-striped">
        <tr>
            <th>ROLE-ID</th>
            <th>NAME</th>
            <th>DESCRIPTION</th>
            <th colspan="2">OPTION</th>
        </tr>
        <c:forEach items="${roles}" var="role">
            <tr>
                <td>${role.getRoleId()}</td>
                <td>${role.getRoleName()}</td>
                <td>${role.getDescription()}</td>
                <td><a href="/updateRole?roleId=${role.getRoleId()}">UPDATE</a></td>
                <td><a href="/deleteRole?roleId=${role.getRoleId()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
