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

        <h1>USER FORM</h1>
        <f:form action="saveUser" method="post" modelAttribute="user">
            <table>
                <tr>
                    <td>USER-ID</td>
                    <td><f:input path="userId"/></td>
                </tr>
                <tr>
                    <td>USERNAME</td>
                    <td><f:input path="username"/></td>
                </tr>
                <tr>
                    <td>PASSWORD</td>
                    <td><f:input path="password"/></td>
                </tr>
                <tr>
                    <td>EMAIL</td>
                    <td><f:input path="email"/></td>
                </tr>
                <tr>
                    <td>MOBILE-NO</td>
                    <td><f:input path="mobileNo"/></td>
                </tr>
            </table>
            <input type="submit" value="submit">
        </f:form>

    </div>

    <div align="center">
        <h2>USER RECORD</h2>
        <table class="table table-primary table-striped">
            <tr>
                <th>USER-ID</th>
                <th>USERNAME</th>
                <th>PASSWORD</th>
                <th>EMAIL</th>
                <th>MOBILE-NO</th>
                <th colspan="2">ACTION</th>
            </tr>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.getUserId()}</td>
                    <td>${user.getUsername()}</td>
                    <td>${user.getPassword()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getMobileNo()}</td>
                    <td><a href="updateUser?userId=${user.getUserId()}">UPDATE</a></td>
                    <td><a href="deleteUser?userId=${user.getUserId()}">DELETE</a></td>
                </tr>
            </c:forEach>

        </table>
    </div>
</body>
</html>