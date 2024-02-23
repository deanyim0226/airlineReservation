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
        div, h2, h3, h6, form, label {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            font: inherit;
            vertical-align: baseline;
        }

        ul {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .clear {
            clear: both;
        }

        .booking-form-w3layouts {
            box-sizing: border-box;
            padding: 3em;
            background: rgba(0, 0, 0, 0.78);
            width: 50%;
            margin: 0 auto;
        }

        h2.sub-heading-agileits,
        h3.sub-heading-agileits {
            display: inline-block;
            text-align: left;
            font-size: 24px;
            color: #fff;
            text-transform: capitalize;
            margin-bottom: 0.4em;
            padding: 0px 25px 10px 0px;
            font-weight: 400;
            letter-spacing: 2px;
            border-bottom: 2px solid #0091cd;
            font-family: 'Raleway', sans-serif;
        }

        .radio-section {
            text-align: left;
            margin: 0.7em 0;
        }

        .radio-section h6 {
            display: inline;
            margin-top: 10px;
            color: #0091cd;
            font-size: 19px;
            text-transform: capitalize;
            margin-bottom: 0.7em;
            font-weight: 400;
            letter-spacing: 2px;
            font-family: 'Raleway', sans-serif;
        }

        .radio-section ul {
            display: inline;
        }

        .radio-buttons-w3-agileits li input[type="radio"] {
            cursor: pointer;
        }

        .radio-buttons-w3-agileits li label {
            color: #fff;
            font-size: 13.5px;
            font-weight: 400;
            letter-spacing: 1px;
            font-family: 'Raleway', sans-serif;
        }

        .booking-form-w3layouts input[type="text"],
        .booking-form-w3layouts input[type="email"],
        .booking-form-w3layouts textarea,
        select.form-control,
        input#datepicker {
            width: 100%;
            font-weight: 300;
            color: #fff;
            font-size: 14px;
            letter-spacing: 1.2px;
            padding: 10px;
            outline: none;
            background: rgba(255, 255, 255, 0);
            border: none;
            border-bottom: 1px solid rgba(255, 255, 255, 0.27);
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }

        .booking-form-w3layouts textarea {
            resize: none;
            height: 80px;
        }

        .field-agileinfo-spc {
            margin-bottom: 1em;
        }

        .form-w3-agile-text {
            width: 100%;
        }

        select.form-control option {
            background: #000;
        }

        .booking-form-w3layouts input[type="submit"],
        .booking-form-w3layouts input[type="reset"] {
            text-transform: capitalize;
            background: #0091cd;
            color: #fff;
            padding: 0.5em 4em;
            border: none;
            font-weight: 500;
            font-size: 1.2em;
            margin-top: 1em;
            float: left;
            outline: none;
            letter-spacing: 1px;
            transition: 0.5s all;
        }
        .booking-form-w3layouts input[type="submit"] {
            margin-right: 1.5em;
            background: #d2741c;
        }

        .booking-form-w3layouts input[type="submit"]:hover {
            background: #0091cd;
            color: #fff;
        }

        .booking-form-w3layouts input[type="reset"]:hover {
            background: #d2741c;
            color: #fff;
        }

        .booking-form-w3layouts ::-webkit-input-placeholder {
            color: #fff;
        }

        .booking-form-w3layouts :-moz-placeholder {
            color: #fff;
        }

        .booking-form-w3layouts ::-moz-placeholder {
            color: #fff;
        }

        .booking-form-w3layouts :-ms-input-placeholder {
            color: #fff;
        }

        .booking-form-w3layouts label {
            font-size: 13.5px;
            color: rgba(255, 255, 255, 0.83);
            letter-spacing: 2px;
            font-weight: 400;
            position: relative;
            margin-bottom: 5px;
            display: inline-block;
            text-transform: capitalize;
        }

        ul.radio-buttons-w3-agileits li {
            display: inline-block;
            margin: 0em 2em;
        }

        @media (max-width: 1440px) {
            .booking-form-w3layouts {
                width: 73%;
            }
        }

        @media (max-width: 1366px) {
            .booking-form-w3layouts {
                width: 75%;
            }
        }

        @media (max-width: 1280px) {
            .booking-form-w3layouts {
                width: 80%;
            }
        }

        @media (max-width: 1080px) {
            .booking-form-w3layouts {
                width: 83%;
                padding: 2em 2.2em;
            }
        }

        @media (max-width: 1024px) {
            h2.sub-heading-agileits,
            h3.sub-heading-agileits {
                font-size: 22px;
                padding: 0px 20px 7px 0px;
            }
            .field-agileinfo-spc {
                margin-bottom: 0.8em;
            }
            .booking-form-w3layouts input[type="submit"],
            .booking-form-w3layouts input[type="reset"] {
                padding: 0.5em 3em;
                font-size: 1.1em;
            }
        }

        @media (max-width: 768px) {
            h2.sub-heading-agileits,
            h3.sub-heading-agileits {
                font-size: 21px;
                padding: 0px 15px 7px 0px;
            }
            .radio-section h6 {
                font-size: 17px;
                letter-spacing: 1.5px;
            }
            ul.radio-buttons-w3-agileits li {
                margin: 0em 1em;
            }
        }

        @media (max-width: 667px) {
            h2.sub-heading-agileits,
            h3.sub-heading-agileits {
                font-size: 19px;
                letter-spacing: 1.5px;
            }
            .booking-form-w3layouts {
                width: 86%;
                padding: 2em 2em;
            }
            .booking-form-w3layouts input[type="text"],
            .booking-form-w3layouts input[type="email"],
            .booking-form-w3layouts textarea,
            select.form-control,
            input#datepicker {
                padding: 8px 10px;
            }
            .field-agileinfo-spc {
                margin-bottom: 0.9em;
            }
            h2.sub-heading-agileits,
            h3.sub-heading-agileits {
                margin-bottom: 0.6em;
            }
        }

        @media (max-width: 640px) {
            .booking-form-w3layouts input[type="submit"] {
                margin-right: 0.7em;
            }
        }

        @media (max-width: 600px) {
            .booking-form-w3layouts {
                width: 90%;
            }
            .booking-form-w3layouts input[type="submit"],
            .booking-form-w3layouts input[type="reset"] {
                padding: 0.5em 2em;
            }
        }

        @media (min-width: 481px) {
            .main-flex-w3ls-sectns {
                display: -webkit-flex;
                display: flex;
                -webkit-justify-content: space-between;
                justify-content: space-between;
            }
            .form-w3-agile-text1,
            .form-w3-agile-text2 {
                flex-basis: 48.5%;
                -webkit-flex-basis: 48.5%;
            }
        }

        @media (min-width: 737px) {
            .triple-wthree {
                display: -webkit-flex;
                display: flex;
                -webkit-justify-content: space-between;
                justify-content: space-between;
            }
            .form-w3-agile-text11,
            .form-w3-agile-text22,
            .form-w3-agile-text33 {
                flex-basis: 32%;
                -webkit-flex-basis: 32%;
            }
        }

        @media (max-width: 480px) {
            ul.radio-buttons-w3-agileits li {
                margin: 0em 0.5em;
            }
            .radio-section {
                margin: 1.3em 0 .7em;
            }
            .booking-form-w3layouts {
                width: 85%;
            }
            ul.radio-buttons-w3-agileits li {
                margin: 1em 0em 0em 0.2em;
                display: block;
            }
            .booking-form-w3layouts input[type="submit"],
            .booking-form-w3layouts input[type="reset"] {
                font-size: 1em;
            }
            .booking-form-w3layouts textarea {
                height: 60px;
            }
            .radio-section {
                margin: 1em 0 .7em;
            }
        }

        @media (max-width: 414px) {
            .booking-form-w3layouts {
                width: 90%;
            }
            .booking-form-w3layouts {
                width: 90%;
                padding: 1.5em 1.7em;
            }
        }

        @media (max-width: 384px) {
            .booking-form-w3layouts input[type="submit"],
            .booking-form-w3layouts input[type="reset"] {
                float: none;
            }
            .booking-form-w3layouts input[type="submit"] {
                margin-right: 0em;
            }
        }

        @media (max-width: 320px) {
            .booking-form-w3layouts {
                width: 93%;
                padding: 1.3em 1.5em;
            }
            h2.sub-heading-agileits,
            h3.sub-heading-agileits {
                font-size: 18px;
                letter-spacing: 1.4px;
                padding: 0px 12px 5px 0px;
            }
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
<div class="booking-form-w3layouts">
    <!-- Form starts here -->
    <f:form action="searchReservation" method="POST" modelAttribute="search">
        <h2 class="sub-heading-agileits">Search Reservation</h2>

        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>Reservation Number</label>
                <f:input type="text" path="reservationNumber" class="form-control" id="reservationNumber"/>
                <c:if test="${hasError}">
                    <f:errors path="reservationNumber"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>Passenger Email</label>
                <f:input type="text" path="passengerEmail" class="form-control" id="passengerEmail"/>
                <c:if test="${hasError}">
                    <f:errors path="passengerEmail"></f:errors>
                </c:if>
            </div>

        </div>
        <div class="main-flex-w3ls-sectns">
            <input id="search-flight" type="submit" value="Find Reservation">
        </div>



    </f:form>
</div>
<!--// Form starts here  check in page-->

</body>
</html>