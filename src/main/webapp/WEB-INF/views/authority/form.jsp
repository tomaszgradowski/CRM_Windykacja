<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script><html>
<head>
    <title>Dodawanie uprawnień użytkownikowi</title>

    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<h2>Uzupełnij dane dla nowego użytkownika</h2>

<br><br>

<form:form modelAttribute="authority" method="post">

    <form:hidden path="id"/>


    <table>
        <tr>
            <td>Wybierz uprawnienia dla użytkownika:
                <br>Imie: ${user.firstName}
                <br>Nazwisko: ${user.lastName}
                <br>Login: ${user.username}
            </td>
        </tr>
        <tr>
            <td>
               Wybierz uprawnienia: <form:select items="${roles}" path="authority" itemValue="authority" itemLabel="authority"/>
            </td>
            <form:hidden path="userName" value="${user.username}"/>
        </tr>
    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
