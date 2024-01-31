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
    <script>
        $(document).ready(function(){
            $("#addUser").click(function(){
                $("#modal_userId").val("")
                $("#modal_email").val("")
                $("#modal_username").val("")
                $("#modal_password").val("")

                $("#modal_userId").removeAttr("readonly")

                $("#myModal").show()
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
                <h4 class="modal-title">USER INFO</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">

                <f:form action="saveUser" method="POST" modelAttribute="user">
                    <c:if test="${hasError}">
                        <tr>
                            <td>Errors</td>
                            <td><f:errors path="*"></f:errors></td>
                        </tr>
                    </c:if>
                    <div class="col">
                        USER ID <f:input path="userId" class="form-control" type="number" id="modal_userId"/>
                        NAME <f:input path="username" class="form-control" type="text" id="modal_username"/>
                        PASSWORD<f:input path="password" class="form-control" type="password" id="modal_password"/>
                        EMAIL <f:input path="email" class="form-control" type="text" id="modal_email"/>
                        MOBILE-NO<<f:input class="form-control" path="mobileNo"  id="modal_mobileNo"/>

                        <c:forEach items="${roles}" var="role" >

                            <c:if test="${retrievedRole.contains(role)}">
                                <f:checkbox class="checkbox1" path="roles" label="${role.getRoleName()}" value="${role.getRoleId()}" checked="ture"/>
                            </c:if>

                            <c:if test="${!retrievedRole.contains(role)}">
                                <f:checkbox class="checkbox2" path="roles" label="${role.getRoleName()}" value="${role.getRoleId()}"/>
                            </c:if>

                        </c:forEach>

                        <input style="margin-top:25px" class="btn form-control btn-primary" type="submit" id="" value="submit"/>
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

<div class="container" align="center">
        <h2>USER RECORD</h2>
        <table class="table table-primary table-striped">
            <tr>
                <th>USER-ID</th>
                <th>USERNAME</th>
                <th>PASSWORD</th>
                <th>EMAIL</th>
                <th>MOBILE-NO</th>
                <th >ACTION</th>
                <th><button class="btn btn-success" id="addUser">ADD</button></th>
            </tr>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.getUserId()}</td>
                    <td>${user.getUsername()}</td>
                    <td>${user.getPassword()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getMobileNo()}</td>
                    <td><a href="updateUser?userId=${user.getUserId()}">UPDATE</a></td>
                    <td><a href="deleteUser?userId=${user.getUserId()}">DELETE</a></td>
                </tr>
            </c:forEach>

        </table>
    </div>
</body>
</html>