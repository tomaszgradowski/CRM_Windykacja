
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Dane dłużnika</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<div class="page-header">
    <h1><p class="bg-primary  text-center">Dane dłużnika o id: ${debtor.id} w sprawie numer:  ${caseId}</p></h1>
</div>
<div class="container">
    <table class="table table-bordered">
    <tr>
        <th>Id</th>
        <th>Imie:</th>
        <th>Nazwisko</th>
        <th>PESEL</th>
        <th>Numer dowodu</th>
        <th>Dodaj adres dłużńika</th>
        <th>Dodaj telefon dłużnika</th>
        <th>Dodaj maila dłużnika</th>

    </tr>
    <%--<c:forEach items="${debtorsByCaseId}" var="debtor">--%>
        <tr>
            <td><c:out value="${debtor.id}" /></td>
            <td><c:out value="${debtor.firstName}"/></td>
            <td><c:out value="${debtor.lastName}"/></td>
            <td><c:out value="${debtor.pesel}"/></td>
            <td><c:out value="${debtor.identityNumber}"/></td>

            <td><a href="/adress/form?debtorId=${debtor.id}">Dodaj adres dłużńika</a></td>
            <td><a href="/tel/form?debtorId=${debtor.id}">Dodaj telefon dłużnika</a></td>
            <td><a href="/email/form?debtorId=${debtor.id}">Dodaj maila dłużnika</a></td>

                <%--<td><button onClick="javascript:location.href='form?id=${book.id}'">Edit</button></td>--%>
        </tr>
    <%--</c:forEach>--%>
    <br>
</table>
</div>
<br>
<c:if test="${adresses.size() > 0}">
    <<h3><p class="bg-primary  text-center">Adresy dłużnika o id: ${debtor.id} w sprawie numer:  ${caseId}</p></h3>
<div class="container">
    <table class="table table-bordered">
    <tr>
        <th>Miasto</th>
        <th>Numer domu:</th>
        <th>Numer mieszkania</th>
        <th>Ulica</th>
        <th>Kod pocztowy</th>
        <%--<th>Data dodania</th>--%>
        <th>Dodany przez</th>
        <th>Usuń</th>
        <th>Edytuj</th>
    </tr>
<c:forEach items="${adresses}" var="adress">
<tr>
    <td><c:out value="${adress.city}" /></td>
    <td><c:out value="${adress.houseNumber}"/></td>
    <td><c:out value="${adress.localNumber}"/></td>
    <td><c:out value="${adress.street}"/></td>
    <td><c:out value="${adress.postcode}"/></td>
    <%--<td><c:out value="${adress.created}"/></td>--%>
    <td><c:out value="${adress.user.username}"/></td>
    <td><a href="/adress/delete?adressId=${adress.id}">Usuń</a></td>
    <td><a href="/adress/form?adressId=${adress.id}">Edytuj</a></td>
</tr>
</c:forEach>
</table>
</div>
<br>
</c:if>

<c:if test="${tel.size() > 0}">
    <<h3><p class="bg-primary  text-center">Telefony dłużnika o id: ${debtor.id} w sprawie numer:  ${caseId}</p></h3>
<br>
<div class="container">
    <table class="table table-bordered">
    <tr>
        <th>NUmer telefonu</th>
        <th>Data dodania</th>
        <th>Dodany przez</th>
        <th>Usuń</th>
        <th>Edytuj</th>
    </tr>
    <c:forEach items="${tel}" var="telephone">
        <tr>
            <td><c:out value="${telephone.telNumber}" /></td>
            <td><c:out value="${telephone.created}"/></td>
            <td><c:out value="${telephone.user.username}"/></td>
            <td><a href="/tel/delete?telId=${telephone.id}">Usuń</a></td>
            <td><a href="/tel/form?telId=${telephone.id}">Edytuj</a></td>
        </tr>
    </c:forEach>
</table>
</div>
</c:if>

<c:if test="${emails.size() > 0}">
    <<h3><p class="bg-primary  text-center">Emaile dłużnika o id: ${debtor.id} w sprawie numer:  ${caseId}</p></h3>
<br>
<div class="container">
    <table class="table table-bordered">
    <tr>
        <th>Email</th>
        <th>Data dodania</th>
        <th>Dodany przez</th>
        <th>Usuń</th>
        <th>Edytuj</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
            <td><c:out value="${email.email}" /></td>
            <td><c:out value="${email.created}"/></td>
            <td><c:out value="${telephone.user.username}"/></td>
            <td><a href="/tel/delete?emailId=${email.id}">Usuń</a></td>
            <td><a href="/tel/form?emailId=${email.id}">Edytuj</a></td>
        </tr>
    </c:forEach>
</table>
</div>
</c:if>


<div class="container">
    <button  class="btn btn-primary" onClick="javascript:location.href='/debtor/list?caseId=${caseId}'">
        Powrót do listy dłużników sprawy numer ${caseId}
    </button>
    <br><br>

    <button  class="btn btn-primary" onClick="javascript:location.href='/case/allinfo?newCaseId=${caseId}'">
        Powrót do okna sprawy numer ${caseId}
    </button>
</div>


</body>
</html>
