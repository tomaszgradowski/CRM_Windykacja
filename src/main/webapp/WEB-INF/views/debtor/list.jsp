<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista dłużników</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<br>
<br>

<div class="page-header">
    <h1><p class="bg-primary  text-center">Lista dłużnków w sprawie numer:  ${caseId}</p></h1>
</div>

<div class="container">
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Imie:</th>
            <th>Nazwisko</th>
            <th>PESEL</th>
            <th>Numer dowodu</th>
            <th>Usuń</th>
            <th>Edytuj</th>
            <th>Szczegóły</th>

        </tr>
        <c:forEach items="${debtorsByCaseId}" var="debtor">
            <tr>
                <td><c:out value="${debtor.id}"/></td>
                <td><c:out value="${debtor.firstName}"/></td>
                <td><c:out value="${debtor.lastName}"/></td>
                <td><c:out value="${debtor.pesel}"/></td>
                <td><c:out value="${debtor.identityNumber}"/></td>

                <td><a href="/debtor/delete?debtorId=${debtor.id}">Usuń</a></td>
                <td><a href="/debtor/form?debtorId=${debtor.id}">Edytuj</a></td>
                <td><a href="/debtor/details?debtorId=${debtor.id}&caseId=${caseId}">Szczegóły</a></td>

            </tr>
        </c:forEach>
        <br>
    </table>
    <div class="container">
        <button  class="btn btn-primary" onClick="javascript:location.href='/case/allinfo?newCaseId=${caseId}'">
            Powrót do okna sprawy numer ${caseId}
        </button>
    </div>

        <%--<a href="/case/allinfo?newCaseId=${caseId}">Powrót do okna sprawy numer ${caseId}</a>--%>

</body>
</html>