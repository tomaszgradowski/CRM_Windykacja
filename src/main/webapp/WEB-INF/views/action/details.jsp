
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Lista akcji</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>


<table>
    <tr>
        <th>Id</th>
        <th>Data dodania</th>
        <th>Kod akcji</th>
        <th>Opis</th>
        <th>Utworzono przez:</th>
     </tr>
        <tr>
            <td><c:out value="${action.id}" /></td>
            <td><c:out value="${action.created}"/></td>
            <td><c:out value="${action.actionType.code}"/></td>
            <td><c:out value="${action.actionType.description}"/></td>
            <td><c:out value="${action.user.username}"/></td>
            <td><a href="/result/form?actionId=${action.id}&caseId=${caseId}">Przypisz rezultat</a></td>
        </tr>
    <br>
</table>
<br>
Rezultaty:
<table>
    <tr>
        <th>Id</th>
        <th>Data dodania</th>
        <th>Kod rezultatu</th>
        <th>Opis rezultatu</th>
        <th>Komentarz</th>
        <th>Dodany przez</th>
        <th>Usuń</th>
        <th>Edytuj</th>
    </tr>
<c:forEach items="${results}" var="result">
<tr>
    <td><c:out value="${result.id}" /></td>
    <td><c:out value="${result.created}"/></td>
    <td><c:out value="${result.resultType.code}"/></td>
    <td><c:out value="${result.resultType.description}"/></td>
    <td><c:out value="${result.comment}"/></td>
    <td><c:out value="${result.user.username}"/></td>
    <td><a href="/result/delete?resultId=${result.id}">Usuń</a></td>
    <td><a href="/result/form?resultId=${result.id}">Edytuj</a></td>
</tr>
</c:forEach>

<a href="/action/list?caseId=${caseId}">Powrót do listy akcji sprawy numer ${caseId}</a>
<br>
<a href="/case/allinfo?newCaseId=${caseId}">Powrót do okna sprawy numer ${caseId}</a>
</body>
</html>
