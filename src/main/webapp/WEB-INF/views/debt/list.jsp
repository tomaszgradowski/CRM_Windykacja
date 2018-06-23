<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista dłużników</title>
	<link href="style.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>


<h2>Lista dłużników</h2>
<br>
<%--${allCases.endDate}--%>


<table>
	<tr>
        <th>Id</th>
		<th>Numer umowy</th>
		<th>Data zawarcia umowy</th>
		<th>Data wypowiedzenia</th>
		<th>Kapitał</th>
		<th>Koszty</th>
		<th>Odsetki</th>
        <th>Usuń</th>
        <th>Edytuj</th>
	</tr>
<c:forEach items="${debtsByCaseId}" var="debt">
	<tr>
		<td><c:out value="${debt.id}" /></td>
		<td><c:out value="${debt.contractNumber}"/></td>
		<td><c:out value="${debt.contractDate}"/></td>
		<td><c:out value="${debt.terminationDate}"/></td>
		<td><c:out value="${debt.principal}"/></td>
		<td><c:out value="${debt.costs}"/></td>
		<td><c:out value="${debt.interests}"/></td>
        <td><a href="/debt/delete?debtId=${debt.id}">Usuń</a></td>
        <td><a href="/debt/form?debtId=${debt.id}">Edytuj</a></td>
        <%--<td><button onClick="javascript:location.href='form?id=${book.id}'">Edit</button></td>--%>
	</tr>
</c:forEach>
<br>
</table>

<a href="/case/allinfo?newCaseId=${caseId}">Powrót do okna sprawy numer ${caseId}</a>

</body>
</html>