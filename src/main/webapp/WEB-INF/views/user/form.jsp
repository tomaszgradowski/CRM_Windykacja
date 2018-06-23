<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Dodawanie nowej użytkownika</title>

    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla nowego użytkownika</h2>

<br><br>

<form:form modelAttribute="user" method="post">

    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Imie:</td>
            <td><form:input path="firstName" /> <form:errors path="firstName" cssClass="inputStyle" onclick="v" /></td>
        </tr>
        <tr>
            <td>Nazwisko:</td>
            <td><form:input path="lastName" /><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td>Login</td>
            <td><form:input path="username"/><form:errors path="username"/></td>
        </tr>
        <tr>
            <td>Hasło:</td>
            <td><form:password path="password"/><form:errors path="password"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email"/><form:errors path="email"/></td>
        </tr>

    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
