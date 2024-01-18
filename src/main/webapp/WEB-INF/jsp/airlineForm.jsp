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
            <li > <a  class="btn btn-primary dropdown"  href="airportForm">AIRPORT FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="flightForm">FLIGHT FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="airlineForm">AIRLINE FORM</a></li>
        </ul>
    </nav>
</header>

<div align="center">
    <h1>AIRLINE FORM</h1>
    <f:form modelAttribute="airline" method="post" action="saveAirline">
    <table>
        <tr>
            <td>AIRLINE-ID</td>
            <td><f:input path="airlineId"/></td>
        </tr>
        <tr>
            <td>NAME</td>
            <td><f:input path="airlineName"/></td>
        </tr>
        <tr>
            <td>AIRLINE-CODE</td>
            <td><f:input path="airlineCode"/></td>
        </tr>
        <tr>
            <td>FLIGHT INFO</td>
            <td><f:input path="airlineFlight"/></td>
        </tr>
    </table>
        <input type="submit" value="submit">
    </f:form>
</div>
<div align="center">
    <table class="table table-primary table-striped">
        <tr>
            <th>AIRLINE-ID</th>
            <th>NAME</th>
            <th>AIRLINE-CODE</th>
            <th>FLIGHT-INFO</th>
            <th colspan="2">
                ACTION
            </th>
        </tr>
        <c:forEach items="${airlines}" var="airline">

            <tr>
                <td>${airline.getAirlineId()}</td>
                <td>${airline.getAirlineName()}</td>
                <td>${airline.getAirlineCode()}</td>
                <td>${airline.getAirlineFlight()}</td>
                <td><a href="updateAirline?airlineId=${airline.getAirlineId()}">UPDATE</a></td>
                <td><a href="deleteAirline?airlineId=${airline.getAirlineId()}">DELETE</a></td>
            </tr>
        </c:forEach>


    </table>
</div>
</body>
</html>