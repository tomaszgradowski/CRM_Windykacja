<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista kodów</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<div class="page-header">
	<h1><p class="bg-primary  text-center">Lista kodów: </p></h1>
</div>
<br>

<div class="container">
	<table class="table table-bordered">
	<tr>
        <th>Kod akcji</th>
		<th>Opis</th>
		<th>Usuń</th>
		<th>Dodaj</th>
	</tr>
<c:forEach items="${actiontypes}" var="actiontype">
	<tr>
		<td><c:out value="${actiontype.code}" /></td>
		<td><c:out value="${actiontype.description}"/></td>
		<td><a href="/actiontype/delete?actionTypeId=${actiontype.id}">Usuń</a></td>
        <td><a href="/actiontype/form?actionTypeId=${actiontype.id}">Edytuj</a></td>
	</tr>
</c:forEach>
<br>
</table>
</div>

<%--<a href="/actiontype/form">Dodaj kolejna akcję</a>--%>

<div class="container">
	<button  class="btn btn-primary" onClick="javascript:location.href='/actiontype/form'">
		Dodaj kolejna akcję
	</button>
</div>
</body>
</html>