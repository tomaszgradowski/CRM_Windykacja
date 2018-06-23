<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Dodawanie nowej sprawy</title>
    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla nowej sprawy</h2>

<br><br>

<form:form modelAttribute="case" method="post">

    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Data rozpoczęcia obsługi:</td>
            <td><form:input path="startDate" type="date"/> <form:errors path="startDate" cssClass="inputStyle" onclick="v" /></td>
        </tr>
        <tr>
            <td> Data zakończenia obsługi:</td>
            <td><form:input path="endDate" type="date"/><form:errors path="endDate"/></td>
        </tr>
        <%--<tr>--%>
            <%--<td>Numer konta:</td>--%>
            <%--<td><form:input path="accountNumber"/><form:errors path="accountNumber"/></td>--%>
        <%--</tr>--%>
        <tr>
            <td>Pracownik Call Center:</td>
            <td><form:select items="${ccEmployees}" path="user.id" itemValue="id"
                             itemLabel="username"/></td>
        </tr>
        <tr>
            <td>Prawnik:</td>
            <td><form:select items="${lawyers}" path="user1.id" itemValue="id" itemLabel="username"/></td>
        </tr>
        <tr>
            <td>Kierownik:</td>
            <td><form:select items="${supervisors}" path="user2.id" itemValue="id" itemLabel="username"/></td>
        </tr>
        <tr>

        </tr>
    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
