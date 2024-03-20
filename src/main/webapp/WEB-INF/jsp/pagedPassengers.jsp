<%@page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../css/style.css">
    <title>User Form</title>

    <style>

        body{
            background-image: url("https://media.cntraveler.com/photos/607f3c487774091e06dd5d21/16:9/w_2560%2Cc_limit/Breeze%2520Airways_166655077_303814634409055_8038496796049085212_n.jpeg");
            width: 100%;
            height: 100%;
        }

    </style>
</head>
<body>
<header>
    <nav class="navbar bg-dark border-bottom border-body">
        <a  class="btn btn-dark dropdown"  href="home">HOME</a>
        <ul class="nav justify-content-end">
            <s:authorize access="hasAuthority('Admin')">
                <li > <a  class="btn btn-dark dropdown"  href="userForm">USER FORM</a></li>
                <li > <a  class="btn btn-dark dropdown"  href="roleForm">ROLE FORM</a></li>
                <li > <a  class="btn btn-dark dropdown"  href="passengerForm">PASSENGER FORM</a></li>
                <li > <a  class="btn btn-dark dropdown"  href="airportForm">AIRPORT FORM</a></li>
                <li > <a  class="btn btn-dark dropdown"  href="flightForm">FLIGHT FORM</a></li>
                <li > <a  class="btn btn-dark dropdown"  href="airlineForm">AIRLINE FORM</a></li>
            </s:authorize>
            <li > <a  class="btn btn-dark dropdown"  href="searchReservationForm">RESERVATION</a></li>
            <li > <a  class="btn btn-dark dropdown"  href="searchFlightForm">SEARCH FLIGHT</a></li>
            <s:authorize access="isAuthenticated()">
                <li class = "nav-item"><a class="btn btn-dark dropdown"  href="/logout">LOGOUT</a></li>
            </s:authorize>
        </ul>
    </nav>
</header>


<div class="container-fluid" align="center">
    <table class="table table-dark table-striped">
        <tr>
            <th>PASSENGER-ID</th>
            <th>FIRSTNAME</th>
            <th>LASTNAME</th>
            <th>EMAIL</th>
            <th>GENDER</th>
            <th>DOB</th>
            <th>ADDRESS</th>
            <th>ACTION</th>
            <th><a href="passengerForm">Back</a></th>
        </tr>

        <c:forEach items="${passengers}" var="passenger">
            <tr>
                <td>${passenger.getPassengerId()}</td>
                <td>${passenger.getFirstName()}</td>
                <td>${passenger.getLastName()}</td>
                <td>${passenger.getEmail()}</td>
                <td>${passenger.getGender()}</td>
                <td>${passenger.getDOB()}</td>
                <td>                        ${passenger.getAddress().getAddressLine1()} ${passenger.getAddress().getAddressLine2()},
                        ${passenger.getAddress().getCity()}, ${passenger.getAddress().getState()}, ${passenger.getAddress().getZipcode()}, ${passenger.getAddress().getCountry()}
                </td>
                <td><a href="generatePassenger?passengerId=${passenger.getPassengerId()}">UPDATE</a></td>
                <td><a href="deletePassenger?passengerId=${passenger.getPassengerId()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>

    <c:set var="noOfPages" value= "${totalPages}"></c:set>
    <c:set var="sortedBy" value= "${sortedBy}"></c:set>
    <c:set var="pageSize" value= "${pageSize}"></c:set>

    <%
        //http://localhost:8081/dinesh/pagedEmployees?pageNo=0&pageSize=5&sortedBy=name
        for(int i=0; i< (int)pageContext.getAttribute("noOfPages"); i++){ // pageContext, out, request, response are some of jsp implict object
            out.println("<a href=\"pagedPassengers?pageNo="+i
                    + "&pageSize="+request.getAttribute("pageSize")
                    +"&sortedBy="+request.getAttribute("sortedBy")
                    +"\">"
                    +i
                    +
                    "</a>");
        }
    %>
</div>

</body>
</html>