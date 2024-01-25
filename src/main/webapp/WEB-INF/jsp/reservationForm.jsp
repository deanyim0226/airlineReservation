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
            <li > <a  class="btn btn-primary dropdown"  href="reservationForm">RESERVATION FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="searchForm">SEARCH FLIGHT</a></li>
        </ul>
    </nav>
</header>


<div class="container-fluid" align="center">
    <h2>RESERVATION RECORD</h2>
    <table class="table table-primary table-striped">
        <tr>
            <th>RESERVATION-ID</th>
            <th>PASSENGER-INFO</th>
            <th>FLIGHT-INFO</th>
            <th>CHECKED BAGS</th>
            <th>CHECKED IN</th>
            <th align="2">ACTION</th>
        </tr>
        <c:forEach items="${reservations}" var="reservation">
            <tr>

                <td>${reservation.getReservationNumber()}</td>
                <td>${reservation.getPassenger().getFirstName()} ${reservation.getPassenger().getLastName()}, ${reservation.getPassenger().getGender()} </td>
                <td>${reservation.getFlight().getDepartureDate()} ${reservation.getFlight().getDepartureCity()} -> ${reservation.getFlight().getArrivalCity()} at ${reservation.getFlight().getDepartureTime()}</td>
                <td>${reservation.getCheckedBags()}</td>
                <td>${reservation.isCheckedIn()}</td>
                <td><a href="updateReservation?reservationId=${reservation.getReservationNumber()}">UPDATE</a></td>
                <td><a href="deleteReservation?reservationId=${reservation.getReservationNumber()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>