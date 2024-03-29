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
    <script>
        $(document).ready(function(){
            $("#selection").change(function(){

                let selected = $(this).val()
                let pageNo = 0
                let pageSize = 5

                window.location.replace("pagedFlight?pageNo="+pageNo+"&pageSize="+pageSize +"&sortedBy="+selected)

            })
        })
    </script>
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
                <label>BOOKED SEATS</label>
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
        <div class="row">
            <div class="col-6">
                <input id="save-passenger" type="submit" value="SAVE">
                <input formaction="updateFlight" type="submit" value="UPDATE">

            </div>
            <div class="col-6">
                <label>sortedBy</label>
                <select id="selection">
                    <c:forEach items="${sortedBy}" var="item">
                        <option>
                            ${item}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="clear"></div>
    </f:form>

</div>

<br>
<div class="container-fluid" align="center">
    <h1>FLIGHT RECORD</h1>
    <table class="table table-dark table-striped">
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
                <td><a href="writeFlight?flightId=${flight.getFlightId()}">UPDATE</a></td>
                <td><a href="deleteFlight?flightId=${flight.getFlightId()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>