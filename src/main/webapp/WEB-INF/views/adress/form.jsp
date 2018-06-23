<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script><html>
<head>
    <title>Dodawanie nowego adresu </title>
    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla nowego adresu</h2>

<br><br>

<form:form modelAttribute="adress" method="post">

    <form:hidden path="id"/>
    <form:hidden path="debtor.id"/>
    <%--<form:hidden path="debtor.firstName"/>--%>
    <%--<form:hidden path="debtor.lastName"/>--%>
    <%--<form:hidden path="debtor.pesel"/>--%>
    <form:hidden path="debtor.aCase.id"/>
    <%--<form:hidden path="debtor.user"/>--%>
    <%--<form:hidden path="debtor.created"/>--%>
    <%--<form:hidden path="debtor.identityNumber"/>--%>
    <table>
        <tr>
            <td>Miasto:</td>
            <td><form:input path="city"/> <form:errors path="city" cssClass="inputStyle" /></td>
        </tr>
        <tr>
            <td>Numer domu:</td>
            <td><form:input path="houseNumber"/><form:errors path="houseNumber"/></td>
        </tr>
        <tr>
            <td>Numer mieszkania:</td>
            <td><form:input path="localNumber"/><form:errors path="localNumber"/></td>
        </tr>
        <tr>
            <td>Ulica:</td>
            <td><form:input path="street"/><form:errors path="street"/></td>
        </tr>
        <tr>
            <td>Kod pocztowy:</td>
            <td><form:input path="postcode"/><form:errors path="postcode"/></td>
        </tr>
    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
