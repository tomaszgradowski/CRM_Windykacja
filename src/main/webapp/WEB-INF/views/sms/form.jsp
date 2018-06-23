<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Dodawanie nowej sprawy</title>
    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla smsa</h2>

<br><br>

<form:form modelAttribute="sms" method="post">

    <form:hidden path="id"/>
    <form:hidden path="aCase.id"/>
    <table>
        <tr>
            <td>Wybierz numer telefonu:</td>
            <td><form:select items="${telephones}" path="telephone.id" itemValue="id"
                             itemLabel="telNumber"/></td>
        </tr>
        <tr>
            <td>Wpisz treść wiadomości:</td>
            <td><form:textarea path="message" /><form:errors path="message"/></td>
        </tr>
        <tr>
            <td>Czy test:</td>
            <td><form:input path="test" value="1"/><form:errors path="test"/></td>
        </tr>
    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
