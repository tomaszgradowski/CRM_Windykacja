<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista spraw</title>

	<style>
		*{
			font-size: 20px;
		}
		table{
			align: center;
			border: 1px red;
			width: 100%;
			margin-left: 20%;
		}


		h2{
			font-style: italic;
			font-size: 30px;
			/*text-align: center;*/

		}

		input{
			width: 100%;
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<h2>Lista spraw</h2>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<%--${allCases.endDate}--%>
<table>
	<tr>
		<th>Numer sprawy</th>
		<th>Numer konta</th>
		<th>Data rozpoczęcia obsługi</th>
		<th>Data zakończenia obsługi</th>
		<th>Pracownik CC</th>
		<th>Prawnik</th>
		<th>Kierownik</th>
	</tr>
<%--<c:forEach items="${newCase}" var="cases">--%>
<tr>
	<%--<td>${newCase.id}</td>--%>
	<td><c:out value="${newCase.accountNumber}"/></td>
	<td><c:out value="${newCase.startDate}"/></td>
	<td><c:out value="${newCase.endDate}"/></td>
	<td><c:out value="${newCase.user.username}"/></td>
	<td><c:out value="${newCase.user1.username}"/></td>
	<td><c:out value="${newCase.user2.username}"/></td>
</tr>


<%--</c:forEach>--%>
</table>
</body>
</html>