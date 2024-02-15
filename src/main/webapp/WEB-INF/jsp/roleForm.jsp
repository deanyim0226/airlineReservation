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

            $("#addRole").click(function(){

                $("#modal_roleId").attr("readonly",false)

                $("#modal_roleId").val("")
                $("#modal_roleName").val("")
                $("#modal_roleDescription").val("")

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
                <h4 class="modal-title">ROLE INFO</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <f:form action="saveRole" method="POST" modelAttribute="role">
                    <div class="col">
                        ROLE ID <f:input path="roleId" readonly="true" class="form-control" type="text" id="modal_roleId"/>
                        ROLE <f:input path="roleName" class="form-control" type="text" id="modal_roleName"/>
                        DESCRIPTION <f:input path="description" class="form-control" type="text" id="modal_roleDescription"/>
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

<div class="container-xxl" align="center">
    <h2>ROLE RECORD</h2>
    <table class="table table-primary table-striped">
        <tr>
            <th>ROLE-ID</th>
            <th>NAME</th>
            <th>DESCRIPTION</th>
            <th >OPTION</th>
            <th><button class="btn btn-success" id="addRole">ADD</button></th>
        </tr>
        <c:forEach items="${roles}" var="role">
            <tr>
                <td>${role.getRoleId()}</td>
                <td>${role.getRoleName()}</td>
                <td>${role.getDescription()}</td>
                <td><a href="/updateRole?roleId=${role.getRoleId()}">UPDATE</a></td>
                <td><a href="/deleteRole?roleId=${role.getRoleId()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
