
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script><html>
<head>
    <title>Dodawanie nowego kodu akcji</title>

    <style>
        <%@include file="/WEB-INF/resources/css/form.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>
<br>
<h2>Uzupełnij dane dla nowego kodu akcji</h2>


<br><br>

<form:form modelAttribute="actionTypeResultType" method="post">

    <form:hidden path="id"/>
    <%--<form:hidden path="actionType.id"/>--%>
    <%--<form:hidden path="resultType.id"/>--%>
    <table>
        <tr>
            <td>Nazwa kodu:</td>
            <td><form:select items="${actions}" path="actionType.id" itemValue="id" itemLabel="code"/></td>
        </tr>
        <tr>
            <td>Rezultaty:</td>
            <td><form:select items="${results}" path="resultType.id" itemValue="id" itemLabel="code"/></td>
        </tr>
    </table>
    <div class="container">
        <input class="btn btn-primary" type="submit" value="Dodaj"/>
    </div>
</form:form>

</body>
</html>
