<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Dodawanie nowego kodu akcji</title>
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
<h2>Uzupełnij dane dla nowego kodu akcji</h2>

<br><br>

<form:form modelAttribute="actiontype" method="post">

    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Nazwa kodu:</td>
            <td><form:input path="code"/> <form:errors path="code" cssClass="inputStyle" /></td>
        </tr>
        <tr>
            <td>Opis:</td>
            <td><form:input path="description"/><form:errors path="description"/></td>
        </tr>
    </table>
<div class="container">
    <input class="btn btn-primary" type="submit" value="Dodaj"/>
</div>
</form:form>

</body>
</html>
