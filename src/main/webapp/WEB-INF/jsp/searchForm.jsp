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

        .modal-dialog{
            overflow-y: initial !important
        }
        .modal-body{
            height: 500px;
            overflow-y: auto;
        }

        .booking-form-w3layouts {
            box-sizing: border-box;
            padding: 3em;
            background: rgba(0, 0, 0, 0.78);
            width: 50%;
            margin: 0 auto;
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
    <nav class="navbar bg-primary">
        <a  class="btn btn-primary dropdown"  href="home">HOME</a>
        <ul class="nav justify-content-end">

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
<br>
<div class="booking-form-w3layouts">
    <!-- Form starts here -->
    <f:form action="searchFlight" method="POST" modelAttribute="search">
        <h2 class="sub-heading-agileits">Search</h2>
        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>From*</label>
                <<f:input type="text" path="from" class="form-control" id="from"/>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>To*</label>
                <<f:input type="text" path="to" class="form-control" id="to"/>
            </div>
        </div>
        <div class="main-flex-w3ls-sectns">

            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>Dates*</label>
                <<f:input type="date" path="date" class="form-control" id="date"/>
            </div>

        </div>
        <div class="main-flex-w3ls-sectns">

        </div>

        <div class="radio-section">
            <h6>Select your Fare</h6>
            <ul class="radio-buttons-w3-agileits">
                <li>
                    <input type="radio" id="a-option" name="selector1">
                    <label for="a-option">One Way</label>
                    <div class="check"></div>
                </li>
                <li>
                    <input type="radio" id="b-option" name="selector1">
                    <label for="b-option">Round-Trip</label>
                    <div class="check">
                        <div class="inside"></div>
                    </div>
                </li>
            </ul>
            <div class="clear"></div>
        </div>

        <div class="clear"></div>
        <input id="search-flight" type="submit" value="Find flight">

        <div class="clear"></div>
    </f:form>
</div>
    <!--// Form starts here  check in page-->

<div  id="searchResult" class="container-fluid" align="center">

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
                    <tr>
                        <td>Flight-ID</td>
                        <td><f:input readonly="true" id="flight-id" class="form-control" path="flight.flightId"/></td>
                    </tr>
                    <tr>
                        <td>FIRSTNAME</td>
                        <td><f:input class="form-control" path="passenger.firstName"/></td>
                    </tr>
                    <tr>
                        <td>LASTNAME</td>
                        <td><f:input class="form-control" path="passenger.lastName"/></td>
                    </tr>
                    <tr>
                        <td>EMAIL</td>
                        <td><f:input class="form-control" path="passenger.email"/></td>
                    </tr>
                    <tr>
                        <td>GENDER</td>
                        <td>
                            <f:select class="form-control" path="passenger.gender">
                                <c:forEach items="${genders}" var="gender">
                                    <f:option value="${gender}"></f:option>
                                </c:forEach>
                            </f:select>
                        </td>
                    </tr>
                    <tr>
                        <td>DOB</td>
                        <td><f:input class="form-control" type="date" path="passenger.DOB"/></td>
                    </tr>
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