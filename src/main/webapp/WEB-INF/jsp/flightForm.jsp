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
    <link rel="stylesheet" href="../css/style.css">
    <title>Flight Form</title>
</head>
<body>
<header>
    <nav class="navbar bg-primary">
        <a  class="btn btn-primary dropdown"  href="home">HOME</a>
        <ul class="nav justify-content-end">

            <li > <a  class="btn btn-primary dropdown"  href="userForm">USER FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="roleForm">ROLE FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="passengerForm">PASSENGER FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="airportForm">AIRPORT FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="flightForm">FLIGHT FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="airlineForm">AIRLINE FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="searchReservationForm">RESERVATION FORM</a></li>
            <li > <a  class="btn btn-primary dropdown"  href="searchFlightForm">SEARCH FLIGHT</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="booking-form-w3layouts">
    <f:form modelAttribute="flight" method="post" action="saveFlight">
        <h2 class="sub-heading-agileits">Flight Form</h2>

        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>FLIGHT-ID</label>
                <f:input path="flightId"/>
                <c:if test="${hasError}">
                    <f:errors path="flightId"></f:errors>
                </c:if>

            </div>
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>FLIGHT-NUMBER</label>
                <f:input path="flightNumber"/>
                <c:if test="${hasError}">
                    <f:errors path="flightNumber"></f:errors>
                </c:if>
            </div>

        </div>


        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>AIRLINE</label>
                <f:select path="flightAirline">
                    <c:forEach items="${availableAirlines}" var="airline">
                        <f:option label="${airline.getAirlineName()}" value="${airline.getAirlineId()}" selected="true"/>
                    </c:forEach>
                    <c:if test="${hasError}">
                        <f:errors path="flightAirline"></f:errors>
                    </c:if>
                </f:select>
            </div>


            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>PRICE</label>
                <f:input path="flightPrice"/>
            </div>

        </div>


        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>CAPACITY</label>
                <f:input path="flightCapacity"/>

            </div>
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>AVAILABLE SEATS</label>
                <f:input path="flightSeatsBooked"/>
            </div>
        </div>



        <h3 class="sub-heading-agileits">Departure</h3>
        <div class="main-flex-w3ls-sectns">

            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>CITY</label>
                <f:input path="departureCity"/>
                <c:if test="${hasError}">
                    <f:errors path="departureCity"></f:errors>
                </c:if>
            </div>

            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>DATE</label>
                <f:input type="date" path="departureDate"/>
                <c:if test="${hasError}">
                    <f:errors path="departureDate"></f:errors>
                </c:if>
            </div>

            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>TIME</label>
                <f:input type="time" path="departureTime"/>
            </div>
        </div>

        <h3 class="sub-heading-agileits">Arrival</h3>
        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>CITY</label>
                <f:input path="arrivalCity"/>
                <c:if test="${hasError}">
                    <f:errors path="arrivalCity"></f:errors>
                </c:if>
            </div>

            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>DATE</label>
                <f:input type="date" path="arrivalDate"/>
                <c:if test="${hasError}">
                    <f:errors path="arrivalDate"></f:errors>
                </c:if>
            </div>

            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>TIME</label>
                <f:input type="time" path="arrivalTime"/>
            </div>
        </div>

        <div class="clear"></div>
        <input id="save-passenger" type="submit" value="SAVE">

        <div class="clear"></div>
    </f:form>

</div>

<br>
<div class="container-fluid" align="center">
    <h1>FLIGHT RECORD</h1>
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