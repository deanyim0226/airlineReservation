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
    <f:form modelAttribute="passenger" method="post" action="savePassengerByAdmin">
        <h2 class="sub-heading-agileits">Passenger Form</h2>

        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>PASSENGER-ID</label>
                <f:input path="passengerId"/>
                <c:if test="${hasError}">
                    <f:errors path="passengerId"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>EMAIL</label>
                <f:input path="email"/>
                <c:if test="${hasError}">
                    <f:errors path="email"></f:errors>
                </c:if>
            </div>
        </div>
        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>FIRSTNAME</label>
                <f:input path="firstName"/>
                <c:if test="${hasError}">
                    <f:errors path="firstName"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>LASTNAME</label>
                <f:input path="lastName"/>
                <c:if test="${hasError}">
                    <f:errors path="lastName"></f:errors>
                </c:if>
            </div>

        </div>

        <div class="main-flex-w3ls-sectns">

            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>GENDER</label>
                <f:select path="gender">
                    <c:forEach items="${genders}" var="gender">
                        <f:option value="${gender}"></f:option>
                    </c:forEach>
                    <c:if test="${hasError}">
                        <f:errors path="gender"></f:errors>
                    </c:if>
                </f:select>
            </div>

            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>DOB</label>
                <f:input type="date" path="DOB"/>
                <c:if test="${hasError}">
                    <f:errors path="DOB"></f:errors>
                </c:if>
            </div>
        </div>

        <h3 class="sub-heading-agileits">Address</h3>
        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>ADDRESS LINE1</label>
                <f:input path="address.addressLine1"/>
                <c:if test="${hasError}">
                    <f:errors path="address.addressLine1"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>ADDRESS LINE2</label>
                <f:input path="address.addressLine2"/>
                <c:if test="${hasError}">
                    <f:errors path="address.addressLine2"></f:errors>
                </c:if>
            </div>

        </div>
        <div class="main-flex-w3ls-sectns">
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>CITY</label>
                <f:input path="address.city"/>
                <c:if test="${hasError}">
                    <f:errors path="address.city"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>STATE</label>
                <f:input path="address.state"/>
                <c:if test="${hasError}">
                    <f:errors path="address.state"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text1">
                <label>ZIPCODE</label>
                <f:input path="address.zipcode"/>
                <c:if test="${hasError}">
                    <f:errors path="address.zipcode"></f:errors>
                </c:if>
            </div>
            <div class="field-agileinfo-spc form-w3-agile-text2">
                <label>COUNTRY</label>
                <f:input path="address.country"/>
                <c:if test="${hasError}">
                    <f:errors path="address.country"></f:errors>
                </c:if>
            </div>

        </div>
        <div class="clear"></div>
        <input id="save-passenger" type="submit" value="SAVE">
        <input formaction="updatePassenger"  type="submit" value="Update">

        <div class="clear"></div>
    </f:form>

</div>

<br>


<div class="container-fluid" align="center">
        <table class="table table-dark table-striped">
            <tr>
                <th>PASSENGER-ID</th>
                <th>FIRSTNAME</th>
                <th>LASTNAME</th>
                <th>EMAIL</th>
                <th>GENDER</th>
                <th>DOB</th>
                <th>ADDRESS</th>
                <th colspan="2">ACTION</th>
            </tr>

            <c:forEach items="${passengers}" var="passenger">
                <tr>
                    <td>${passenger.getPassengerId()}</td>
                    <td>${passenger.getFirstName()}</td>
                    <td>${passenger.getLastName()}</td>
                    <td>${passenger.getEmail()}</td>
                    <td>${passenger.getGender()}</td>
                    <td>${passenger.getDOB()}</td>
                    <td>                        ${passenger.getAddress().getAddressLine1()} ${passenger.getAddress().getAddressLine2()},
                            ${passenger.getAddress().getCity()}, ${passenger.getAddress().getState()}, ${passenger.getAddress().getZipcode()}, ${passenger.getAddress().getCountry()}
                    </td>
                    <td><a href="generatePassenger?passengerId=${passenger.getPassengerId()}">UPDATE</a></td>
                    <td><a href="deletePassenger?passengerId=${passenger.getPassengerId()}">DELETE</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>