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
    <title>RESERVATION Form</title>
    <script>
        $(document).ready(function(){

            $(".update").each(function(index, element){
                $(element).click(function(){
                    let reservationNumber = $(this).attr("data-id")
                    $("#myModal").toggle();

                    //make a request call to retrieve reservation with id
                })
            });

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
    <nav class="navbar bg-primary">
        <ul class="nav justify-content-end">
            <li > <a  class="btn btn-primary dropdown"  href="home">HOME</a></li>
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
                        Passenger <f:input path="passenger" class="form-control" type="text"  readonly="true"  id="modal_passenger"/>
                        Flight <f:input path="flight" class="form-control" type="text" readonly="true"  id="modal_flight"/>
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
                <td><a class="update" data-id="${reservation.getReservationNumber()}">UPDATE</a></td>
                <td><a href="deleteReservation?reservationId=${reservation.getReservationNumber()}">CANCEL</a></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>