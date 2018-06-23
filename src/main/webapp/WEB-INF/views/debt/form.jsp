<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Dodawanie nowej wierzytelności</title>
    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla nowej wierzytelności</h2>

<br><br>

<form:form modelAttribute="debt" method="post">

    <form:hidden path="id"/>
    <form:hidden path="aCase.id"/>
    <table>
        <tr>
            <td>Wartość kapitału:</td>
            <td><form:input path="principal"/> <form:errors path="principal" cssClass="inputStyle" onclick="v" /></td>
        </tr>
        <tr>
            <td>Wartość kosztów:</td>
            <td><form:input path="costs"/><form:errors path="costs"/></td>
        </tr>
        <tr>
            <td>Wartość odsetek:</td>
            <td><form:input path="interests"/><form:errors path="interests"/></td>
        </tr>
        <tr>
            <td>Data zawarcia umowy:</td>
            <td><form:input path="contractDate" type="date"/><form:errors path="contractDate"/></td>
        </tr>
        <tr>
            <td>Data wypowiedzenia umowy:</td>
            <td><form:input path="terminationDate" type="date"/><form:errors path="terminationDate"/></td>
        </tr>
        <tr>
            <td>Numer umowy:</td>
            <td><form:input path="contractNumber"/><form:errors path="contractNumber"/></td>
        </tr>


    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
