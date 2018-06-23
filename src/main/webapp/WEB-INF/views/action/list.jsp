<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista akcji</title>
	<link href="style.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<h2>Lista akcji:</h2>
<br>


<table>
	<tr>
        <th>Id</th>
		<th>Kod</th>
		<th>Opis</th>
		<th>Data utworzenia</th>
		<th>Dodane przez:</th>

	</tr>
<c:forEach items="${actionsByCaseId}" var="action">
	<tr>
		<td><c:out value="${action.id}" /></td>
		<td><c:out value="${action.actionType.code}"/></td>
		<td><c:out value="${action.actionType.description}"/></td>
		<td><c:out value="${action.created}"/></td>
		<td><c:out value="${action.user.username}"/></td>

        <td><a href="/action/delete?actionId=${action.id}">Usuń</a></td>
        <td><a href="/action/form?actionId=${action.id}">Edytuj</a></td>
		<td><a href="/result/form?actionId=${action.id}&caseId=${caseId}">Przypisz rezultat</a></td>
		<td><a href="/action/details?actionId=${action.id}&caseId=${caseId}">Szczegóły</a></td>

	</tr>
</c:forEach>
<br>
</table>
<br>
<a href="/user/form">Dodaj użytkownika</a><br>
<a href="/case/allinfo?newCaseId=${caseId}">Powrót do okna sprawy numer ${caseId}</a>

</body>
</html>