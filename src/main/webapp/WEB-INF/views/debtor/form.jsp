<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<body><%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla nowego dłużnika</h2>


<form:form modelAttribute="debtor" method="post">

    <form:hidden path="id"/>
    <form:hidden path="aCase.id"/>
    <table>
        <tr>
            <td>Imie:</td>
            <td><form:input path="firstName"/> <form:errors path="firstName" cssClass="inputStyle" onclick="v"/></td>
        </tr>
        <tr>
            <td>Nazwisko:</td>
            <td><form:input path="lastName"/><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td>PESEL:</td>
            <td><form:input path="pesel"/><form:errors path="pesel"/></td>
        </tr>
        <tr>
            <td>Numer dowodu:</td>
            <td><form:input path="identityNumber"/><form:errors path="identityNumber"/></td>
        </tr>
    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>

</form:form>
<div class="container">
    <button class="btn btn-primary" onClick="javascript:location.href='/case/allinfo?newCaseId= ${debtor.aCase.id}'">
        Powrót do okna sprawy numer ${debtor.aCase.id}
    </button>
</div>
</body>
</html>
