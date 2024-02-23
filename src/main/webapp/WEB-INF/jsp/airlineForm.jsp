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

<br>
<div class="booking-form-w3layouts">
    <!-- Form starts here -->
    <f:form modelAttribute="airline" method="post" action="saveAirline">
        <h2 class="sub-heading-agileits">AIRLINE FORM</h2>

        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>AIRLINE-ID</label>
                <f:input path="airlineId"/>
                <c:if test="${hasError}">
                    <f:errors path="airlineId"></f:errors>
                </c:if>
            </div>


        </div>
        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>AIRLINE-CODE</label>
                <f:input path="airlineCode"/>
                <c:if test="${hasError}">
                    <f:errors path="airlineCode"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>NAME</label>
                <f:input path="airlineName"/>
                <c:if test="${hasError}">
                    <f:errors path="airlineName"></f:errors>
                </c:if>
            </div>

        </div>
        <div class="clear"></div>
        <input id="save-airport" type="submit" value="Submit">
        <input formaction="updateAirline" type="submit" value="Update">
        <div class="clear"></div>

    </f:form>
</div>
<br>
<div class="container-fluid" align="center">
    <table class="table table-dark table-striped">
        <tr>
            <th>AIRLINE-ID</th>
            <th>NAME</th>
            <th>AIRLINE-CODE</th>

            <th colspan="2">
                ACTION
            </th>
        </tr>
        <c:forEach items="${airlines}" var="airline">

            <tr>
                <td>${airline.getAirlineId()}</td>
                <td>${airline.getAirlineName()}</td>
                <td>${airline.getAirlineCode()}</td>

                <td><a href="generateAirline?airlineId=${airline.getAirlineId()}">UPDATE</a></td>
                <td><a href="deleteAirline?airlineId=${airline.getAirlineId()}">DELETE</a></td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>