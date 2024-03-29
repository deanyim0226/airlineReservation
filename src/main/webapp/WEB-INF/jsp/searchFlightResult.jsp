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

        .modal-dialog{
            overflow-y: initial !important
        }
        .modal-body{
            height: 500px;
            overflow-y: auto;
        }

    </style>
    <script>
        $(document).ready(function(){

            $("#book").click(function(){
                let flightId = $("#book").attr("data-flight-id")
                $("#flight-id").val(flightId);
                $("#myModal").toggle()
            })

            $(".close").click(function(){
                $("#myModal").hide()
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
<br>

<!--// Form starts here  check in page-->
<div id="searchResult" class="container-xxl" align="center">

    <h1>SEARCH RESULT</h1>
    <table class="table table-dark table-striped">

        <tr>
            <th>FLIGHT-NUMBER</th>
            <th>AIRLINE</th>
            <th>DEPARTURE</th>
            <th>ARRIVAL</th>
            <th>DEPARTURE-DATE</th>
            <th>DEPARTURE-TIME</th>
            <th>ARRIVAL-DATE</th>
            <th>ARRIVAL-TIME</th>
            <th>PRICE</th>
            <th>CAPACITY</th>
            <th>BOOKED-SEATS</th>

            <th colspan="2">ACTION</th>
        </tr>

        <c:forEach items="${flights}" var="flight">
            <tr>

                <td class="w-auto">${flight.getFlightNumber()}</td>
                <td>${flight.getFlightAirline().getAirlineName()}</td>
                <td>${flight.getDepartureCity()}</td>
                <td>${flight.getArrivalCity()}</td>
                <td>${flight.getDepartureDate()}</td>
                <td>${flight.getDepartureTime()}</td>
                <td>${flight.getArrivalDate()}</td>
                <td>${flight.getArrivalTime()}</td>
                <td>$${flight.getFlightPrice()}</td>
                <td>${flight.getFlightCapacity()}</td>
                <td>${flight.getFlightSeatsBooked()}</td>

                <td><button data-flight-id="${flight.getFlightId()}" id="book" class="btn btn-primary" >Book</button></td>
            </tr>
        </c:forEach>

    </table>
</div>

<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header" >
                <h4 class="modal-title">PASSENGER INFO</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <f:form action="saveReservation" method="POST" modelAttribute="reservation">
                    <div class="row g-3">
                        <div class="col-12">
                            Flight-ID
                            <f:input readonly="true" id="flight-id" class="form-control" path="flight.flightId"/>
                        </div>
                        <div class="col-md-6">
                            FirstName<f:input class="form-control" path="passenger.firstName"/>
                        </div>

                        <div class="col-md-6">
                            LASTNAME<f:input class="form-control" path="passenger.lastName"/>
                        </div>
                        <div class="col-12">
                            EMAIL
                            <f:input class="form-control" path="passenger.email"/>
                        </div>
                        <div class="col-md-6">
                            GENDER
                            <f:select class="form-control" path="passenger.gender">
                                <c:forEach items="${genders}" var="gender">
                                    <f:option class="form-control"  value="${gender}"></f:option>
                                </c:forEach>
                            </f:select>
                        </div>
                        <div class="col-md-6">
                            DOB
                            <f:input class="form-control" type="date" path="passenger.DOB"/>
                        </div>

                    </div>
                    <br>
                    <div class="row g-3">

                        <div class="col-12">
                            <h5 class="text-center">PASSENGER ADDRESS</h5>
                        </div>

                        <div class="col-12">
                            ADDRESS LINE1 <f:input class="form-control" path="passenger.address.addressLine1" id="modal_address1" />
                        </div>
                        <div class="col-12">
                            ADDRESS LINE2 <f:input class="form-control" path="passenger.address.addressLine2"  id="modal_address2"/>
                        </div>
                        <div class="col-md-6">
                            CITY <f:input class="form-control" path="passenger.address.city"  id="modal_city"/>
                        </div>
                        <div class="col-md-6">
                            STATE <f:input class="form-control" path="passenger.address.state"  id="modal_state"/>
                        </div>
                        <div class="col-md-6">
                            ZIP CODE <f:input class="form-control" path="passenger.address.zipcode" id="modal_zipcode"/>
                        </div>
                        <div class="col-md-6">
                            COUNTRY <f:input class="form-control" path="passenger.address.country" id="modal_country"/>
                        </div>
                    </div>
                    <input style="margin-top:25px" class="btn form-control btn-primary" type="submit" id="" value="Confirm"/>

                </f:form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="close" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>