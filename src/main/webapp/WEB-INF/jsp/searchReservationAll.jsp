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
    <title>RESERVATION Form</title>
    <style>

        body{
            background-image: url("https://media.cntraveler.com/photos/607f3c487774091e06dd5d21/16:9/w_2560%2Cc_limit/Breeze%2520Airways_166655077_303814634409055_8038496796049085212_n.jpeg");
            width: 100%;
            height: 100%;

        }
    </style>
    <script>
        $(document).ready(function(){

            $(".update").each(function(index, element){
                $(element).click(function(){
                    let reservationNumber = $(this).attr("data-id")
                    $("#modal_reservationNumber").val(reservationNumber)
                    $("#myModal").toggle();



                    //make a request call to retrieve reservation with id
                })
            });

            $(".checkIn").each(function(index, element){

                $(element).click(function(){
                    let reservationNumber = $(this).attr("data-id")
                })
            })

            $(".close").click(function(){

                $("#myModal").hide();
            })

            $("#close").click(function(){
                $("#myModal").hide()
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

<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header" >
                <h4 class="modal-title">RESERVATION INFO</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->

            <div class="modal-body">
                <f:form action="updateReservation" method="POST" modelAttribute="reservation">
                    <div class="col">
                        Reservation-Number <f:input path="reservationNumber" class="form-control" type="text" readonly="true"  id="modal_reservationNumber"/>
                        Passenger <f:input path="passenger" class="form-control" type="hidden" readonly="true"  id="modal_passenger"/>
                        Flight <f:input path="flight" class="form-control" type="hidden" readonly="true"  id="modal_flight"/>
                        Checked-Bags <f:input path="checkedBags" class="form-control" type="text" id="modal_checkedBags"/>
                        Checked-IN <f:input path="checkedIn" class="form-control" type="text" id="modal_checkedIn"/>
                        <input style="margin-top:25px" class="btn form-control btn-primary" type="submit" id="" value="CheckIn"/>
                    </div>
                </f:form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="close" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<div class="container-fluid" align="center">
    <h1>RESERVATION RECORD</h1>
    <table class="table table-dark table-striped">
        <tr>
            <th>RESERVATION-ID</th>
            <th>PASSENGER-INFO</th>
            <th>FLIGHT-INFO</th>
            <th>CHECKED BAGS</th>
            <th>CHECKED IN</th>
            <s:authorize access="hasAuthority('Admin')">
                <th>ACTION</th>
            </s:authorize>
            <th><a href="searchReservationForm">Back</a></th>
        </tr>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.getReservationNumber()}</td>
                <td>
                    <s:authorize access="hasAuthority('Admin')">
                        <a href="passengerForm?passengerId=${reservation.getPassenger().getPassengerId()}
                &firstName=${reservation.getPassenger().getFirstName()}&email=${reservation.getPassenger().getEmail()}
                &Gender=${reservation.getPassenger().getGender()}&lastName=${reservation.getPassenger().getLastName()}
                &DOB=${reservation.getPassenger().getDOB()}&address.addressLine1=${reservation.getPassenger().getAddress().getAddressLine1()}
                &address.addressLine2=${reservation.getPassenger().getAddress().getAddressLine2()}&address.city=${reservation.getPassenger().getAddress().getCity()}
                &address.state=${reservation.getPassenger().getAddress().getState()}&address.zipcode=${reservation.getPassenger().getAddress().getZipcode()}&address.country=${reservation.getPassenger().getAddress().getCountry()}">
                                ${reservation.getPassenger().getFirstName()} ${reservation.getPassenger().getLastName()}, ${reservation.getPassenger().getGender()} </a>
                    </s:authorize>
                    <s:authorize access="hasAuthority('Customer')">
                        ${reservation.getPassenger().getFirstName()} ${reservation.getPassenger().getLastName()}, ${reservation.getPassenger().getGender()}
                    </s:authorize>
                </td>
                <td>
                    <s:authorize access="hasAuthority('Admin')">
                        <a href="flightForm?flightId=${reservation.getFlight().getFlightId()}&flightNumber=${reservation.getFlight().getFlightNumber()}
                            &departureCity=${reservation.getFlight().getDepartureCity()}&departureDate=${reservation.getFlight().getDepartureDate()}&departureTime=${reservation.getFlight().getDepartureTime()}&arrivalCity=${reservation.getFlight().getArrivalCity()}
                            &arrivalDate=${reservation.getFlight().getArrivalDate()}&arrivalTime=${reservation.getFlight().getArrivalTime()}&flightCapacity=${reservation.getFlight().getFlightCapacity()}&flightPrice=${reservation.getFlight().getFlightPrice()}
                            &flightSeatsBooked=${reservation.getFlight().getFlightSeatsBooked()}&flightAirline.airlineId=${reservation.getFlight().getFlightAirline().getAirlineId()}">
                                ${reservation.getFlight().getDepartureDate()} ${reservation.getFlight().getDepartureCity()} -> ${reservation.getFlight().getArrivalCity()} at ${reservation.getFlight().getDepartureTime()}</a>
                    </s:authorize>
                    <s:authorize access="hasAuthority('Customer')">
                        ${reservation.getFlight().getDepartureDate()} ${reservation.getFlight().getDepartureCity()} -> ${reservation.getFlight().getArrivalCity()} at ${reservation.getFlight().getDepartureTime()}
                    </s:authorize>
                </td>
                <td>${reservation.getCheckedBags()}</td>
                <td>${reservation.isCheckedIn()}</td>

                <td>
                    <s:authorize access="hasAuthority('Admin')">
                        <a class="update" href="#" data-id="${reservation.getReservationNumber()}">CHECK-IN</a>
                    </s:authorize>
                </td>
                <td>
                    <s:authorize access="hasAuthority('Admin')">
                        <a href="deleteReservation?reservationId=${reservation.getReservationNumber()}">CANCEL</a>
                    </s:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>