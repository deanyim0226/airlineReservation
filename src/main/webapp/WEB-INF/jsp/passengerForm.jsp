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

        </ul>
    </nav>
</header>

    <div align="center">
        <h1>PASSENGER FORM</h1>
        <f:form modelAttribute="passenger" method="post" action="savePassenger">
        <table>
            <tr>
                <td>PASSENGER-ID</td>
                <td><f:input path="passengerId"/></td>
            </tr>
            <tr>
                <td>FIRSTNAME</td>
                <td><f:input path="firstName"/></td>
            </tr>
            <tr>
                <td>LASTNAME</td>
                <td><f:input path="lastName"/></td>
            </tr>
            <tr>
                <td>EMAIL</td>
                <td><f:input path="email"/></td>
            </tr>
            <tr>
                <td>GENDER</td>
                <td>
                <f:select path="gender">
                <c:forEach items="${genders}" var="gender">
                    <f:option value="${gender}"></f:option>
                </c:forEach>
                </f:select>
                </td>
            </tr>
            <tr>
                <td>DOB</td>
                <td><f:input type="date" path="DOB"/></td>
            </tr>

            <tr>
                <td>ADDRESS LINE1</td>
                <td><f:input path="address.addressLine1"/></td>
            </tr>
            <tr>
                <td>ADDRESS LINE2</td>
                <td><f:input path="address.addressLine2"/></td>
            </tr>
            <tr>
                <td>ADDRESS LINE1</td>
                <td><f:input path="address.city"/></td>
            </tr>
            <tr>
                <td>ADDRESS LINE1</td>
                <td><f:input path="address.state"/></td>
            </tr>
            <tr>
                <td>ADDRESS LINE1</td>
                <td><f:input path="address.zipcode"/></td>
            </tr>
            <tr>
                <td>ADDRESS LINE1</td>
                <td><f:input path="address.country"/></td>
            </tr>
        </table>
            <input type="submit" value="submit">
        </f:form>
    </div>

    <div align="center">
        <table class="table table-primary table-striped">
            <tr>
                <th>PASSENGER-ID</th>
                <th>FIRSTNAME</th>
                <th>LASTNAME</th>
                <th>EMAIL</th>
                <th>GENDER</th>
                <th>DOB</th>
                <th>ADDRESS</th>
            </tr>

            <c:forEach items="${passengers}" var="passenger">
                <tr>
                    <td>${passenger.getPassengerId()}</td>
                    <td>${passenger.getFirstName()}</td>
                    <td>${passenger.getLastName()}</td>
                    <td>${passenger.getEmail()}</td>
                    <td>${passenger.getGender()}</td>
                    <td>${passenger.getDOB()}</td>
                    <td>                        ${passenger.getCustomerAddress().getAddressLine1()} ${passenger.getCustomerAddress().getAddressLine2()},
                            ${passenger.getCustomerAddress().getCity()}, ${passenger.getCustomerAddress().getState()}, ${passenger.getCustomerAddress().getZipcode()}, ${passenger.getCustomerAddress().getCountry()}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>