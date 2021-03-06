<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Dodawanie nowego rezultatu</title>
    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla nowego rezultatu</h2>

<br><br>

<form:form modelAttribute="result" method="post">

    <form:hidden path="id"/>
    <form:hidden path="aCase.id"/>
    <form:hidden path="action.id"/>
    <table>
        <tr>
            <td>Akcja:</td>
            <td><form:input path="action.actionType.code" disabled="true"/> <form:errors path="action.actionType.code" cssClass="inputStyle" onclick="v" /></td>
        </tr>
        <tr>
            <td>Komentarz:</td>
            <td><form:input path="comment"/><form:errors path="comment"/></td>
        </tr>
        <tr>
            <td>Wybierz rezultat:</td>
            <td><form:select items="${actionTypeResultTypes}" path="resultType.id" itemValue="resultType.id"
                         itemLabel="resultType.code"/></td>
        </tr>
    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
