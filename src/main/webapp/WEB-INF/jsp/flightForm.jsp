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
    <title>Flight Form</title>
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
    <h1>Flight Form</h1>
    <f:form modelAttribute="flight" method="post" action="saveFlight">
        <table>
            <tr>
                <td>FLIGHT-ID</td>
                <td><f:input path="flightId"/></td>
            </tr>
            <tr>
                <td>FLIGHT-NUMBER</td>
                <td><f:input path="flightNumber"/></td>
            </tr>
            <tr>
                <td>AIRLINE</td>
                <td>
                <f:select path="flightAirline">
                <c:forEach items="${availableAirlines}" var="airline">
                    <f:option label="${airline.getAirlineName()}" value="${airline.getAirlineId()}" selected="true"/>
                </c:forEach>

                </f:select>
                </td>
            </tr>
            <tr>
                <td>DEPARTURE</td>
                <td><f:input path="departureCity"/></td>
            </tr>
            <tr>
                <td>ARRIVAL</td>
                <td><f:input path="arrivalCity"/></td>
            </tr>
            <tr>
                <td>DEPARTURE-DATE</td>
                <td><f:input type="date" path="departureDate"/></td>
            </tr>

            <tr>
                <td>DEPARTURE-TIME</td>
                <td><f:input type="time" path="departureTime"/></td>
            </tr>
            <tr>
                <td>ARRIVAL-DATE</td>
                <td><f:input type="date" path="arrivalDate"/></td>
            </tr>
            <tr>
                <td>ARRIVAL-TIME</td>
                <td><f:input type="time" path="arrivalTime"/></td>
            </tr>
            <tr>
                <td>CAPACITY</td>
                <td><f:input path="flightCapacity"/></td>
            </tr>
            <tr>
                <td>PRICE</td>
                <td><f:input path="flightPrice"/></td>
            </tr>
            <tr>
                <td>BOOKED-SEATS</td>
                <td><f:input path="flightSeatsBooked"/></td>
            </tr>
        </table>
        <input type="submit" value="submit">
    </f:form>

</div>
<div class="container-fluid" align="center">
    <h2>FLIGHT RECORD</h2>
    <table class="table table-primary table-striped">
        <tr>
            <th>FLIGHT-ID</th>
            <th>FLIGHT-NUMBER</th>
            <th>AIRLINE</th>
            <th>DEPARTURE</th>
            <th>ARRIVAL</th>
            <th>DEPARTURE-DATE</th>
            <th>DEPARTURE-TIME</th>
            <th>ARRIVAL-DATE</th>
            <th>ARRIVAL-TIME</th>
            <th>CAPACITY</th>
            <th>PRICE</th>
            <th>BOOKED-SEATS</th>
            <th colspan="2">ACTION</th>
        </tr>

        <c:forEach items="${flights}" var="flight">
            <tr>
                <td>${flight.getFlightId()}</td>
                <td>${flight.getFlightNumber()}</td>
                <td>${flight.getFlightAirline().getAirlineName()}</td>
                <td>${flight.getDepartureCity()}</td>
                <td>${flight.getArrivalCity()}</td>
                <td>${flight.getDepartureDate()}</td>
                <td>${flight.getDepartureTime()}</td>
                <td>${flight.getArrivalDate()}</td>
                <td>${flight.getArrivalTime()}</td>
                <td>${flight.getFlightCapacity()}</td>
                <td>${flight.getFlightPrice()}</td>
                <td>${flight.getFlightSeatsBooked()}</td>
                <td><a href="updateFlight?flightId=${flight.getFlightId()}">UPDATE</a></td>
                <td><a href="deleteFlight?flightId=${flight.getFlightId()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>