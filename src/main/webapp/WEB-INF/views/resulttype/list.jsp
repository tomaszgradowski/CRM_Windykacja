<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista rezultatów</title>
	<link href="style.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<div class="page-header">
	<h1><p class="bg-primary  text-center">Lista rezultatów: </p></h1>
</div>


<br>

<div class="container">
	<table class="table table-bordered">
	<tr>
        <th>Kod akcji</th>
		<th>Opis</th>
		<th>Usuń</th>
		<th>Edytuj</th>
	</tr>
<c:forEach items="${resulttypes}" var="resulttype">
	<tr>
		<td><c:out value="${resulttype.code}" /></td>
		<td><c:out value="${resulttype.description}"/></td>
		<td><a href="/resulttype/delete?resultTypeId=${resulttype.id}">Usuń</a></td>
        <td><a href="/resulttype/form?resultTypeId=${resulttype.id}">Edytuj</a></td>
	</tr>
</c:forEach>
<br>
</table>
</div>


<div class="container">
	<button  class="btn btn-primary" onClick="javascript:location.href='/resulttype/form'">
		Dodaj kolejny rezultat
	</button>
</div>
</body>
</html>