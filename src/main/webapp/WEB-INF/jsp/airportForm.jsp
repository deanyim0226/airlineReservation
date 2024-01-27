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
    <title>AIRPORT Form</title>
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
            <li > <a  class="btn btn-primary dropdown"  href="reservationForm">RESERVATION FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="searchForm">SEARCH FLIGHT</a></li>
        </ul>
    </nav>
</header>

<div align="center">
    <h1>AIRPORT FORM</h1>
    <f:form modelAttribute="airport" action="saveAirport" method="post">
    <table>
        <tr>
            <td>AIRPORT-ID</td>
            <td><f:input path="airportId"/>
                <c:if test="${hasError}" >
                    <f:errors path="airportId"></f:errors>
                </c:if>

            </td>
        </tr>
        <tr>
            <td>AIRPORT-CODE</td>
            <td><f:input path="airportCode"/>
                <c:if test="${hasError}" >
                    <f:errors path="airportCode"></f:errors>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>NAME</td>
            <td><f:input path="airportName"/>
                <c:if test="${hasError}" >
                    <f:errors path="airportName"></f:errors>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>CITY</td>
            <td><f:input path="airportCity"/>
                <c:if test="${hasError}" >
                    <f:errors path="airportCity"></f:errors>
                </c:if>
            </td>
        </tr>
    </table>
        <input type="submit" value="submit">
    </f:form>
</div>
<div class="container-fluid" align="center">
    <h2>AIRPORT RECORD</h2>
    <table class="table table-primary table-striped">
        <tr>
            <th>AIRPORT-ID</th>
            <th>AIRPORT-CODE</th>
            <th>NAME</th>
            <th>CITY</th>
            <th colspan="2">ACTION</th>
        </tr>

        <c:forEach items="${airports}" var="airport">
            <tr>
                <td>${airport.getAirportId()}</td>
                <td>${airport.getAirportCode()}</td>
                <td>${airport.getAirportName()}</td>
                <td>${airport.getAirportCity()}</td>
                <td><a href="updateAirport?airportId=${airport.getAirportId()}">UPDATE</a></td>
                <td><a href="deleteAirport?airportId=${airport.getAirportId()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>