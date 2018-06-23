
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/WEB-INF/resources/css/style.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/resources/css/grid-layout.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<div class="grid-container">
    <div data-area="debtor">
        <p class='p-header'>Dane dłużnika:</p>
        <button class="buttonDetails" onClick="javascript:location.href='/debtor/list?caseId=${newCase.id}'">Szczegóły
        </button>
<security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
        <button class="button" onClick="javascript:location.href='/debtor/form?caseId=${newCase.id}'">Dodaj
        </button>
</security:authorize>
        <form>
            <table>
                <tr>
                    <td><span class='span-header'>Imie:</span></td>
                    <td><c:out value="${debtors.firstName}"/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Nazwisko:</span></td>
                    <td><c:out value="${debtors.lastName}"/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>PESEL:</span></td>
                    <td><c:out value="${debtors.pesel}"/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Numer dowodu osobistego:</span></td>
                    <td><c:out value="${debtors.identityNumber}"/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Numer telefonu:</span></td>
                    <td>
                        <c:forEach items="${telephone}" var="tel">
                            <c:out value="${tel.telNumber}"/>,&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td><span class='span-header'>Email:</span></td>
                    <td>
                        <c:forEach items="${email}" var="mail">
                            <c:out value="${mail.email}"/>,&nbsp;
                        </c:forEach></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Miasto:</span></td>
                    <td>${adress.city}</td>
                </tr>
                <tr>
                    <td><span class='span-header'>Ulica:</span></td>
                    <td>${adress.street}</td>
                </tr>
                <tr>
                    <td><span class='span-header'>Kod pocztowy:</span></td>
                    <td>${adress.postcode}</td>
                </tr>
                <tr>
                    <td><span class='span-header'>Numer domu:</span></td>
                    <td>${adress.houseNumber}</td>
                </tr>
                <tr>
                    <td><span class='span-header'>Numer mieszkania:</span></td>
                    <td>${adress.localNumber}</td>
                </tr>
            </table>
        </form>
    </div>


    <div data-area="action">
        <p class='p-header'>Akcje:</p>
        <button class="button100" onClick="javascript:location.href='/action/form?caseId=${newCase.id}'">Dodaj
        </button>
        <form>
            <table>
                <tr>
                    <th>Kod akcji</th>
                    <th>Opis</th>
                    <th>Data akcji</th>
                    <th>Przypisz rezultat</th>
<security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                    <th>Usuń</th>
                    <th>Edytuj</th>
</security:authorize>
                </tr>
                <c:forEach items="${actions}" var="action">
                    <tr>
                        <td>${action.actionType.code}</td>
                        <td>${action.actionType.description}</td>
                        <td>${action.created}</td>
                        <td><a href="/result/form?actionId=${action.id}&caseId=${newCase.id}">Przypisz
                            rezultat</a></td>
                        <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                        <td><a href="/action/delete?actionId=${action.id}">Usuń</a></td>
                        <td><a href="/action/form?actionId=${action.id}">Edytuj</a></td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>

    <div data-area="sms">
        <p class='p-header'>SMS:</p>
        <button class="button100" onClick="javascript:location.href='/sms/form?caseId=${newCase.id}'">Dodaj
        </button>
        <form>
            <table>
                <tr>
                    <th>Numer telefonu</th>
                    <th>Treść</th>
                    <th>Data wysyłki</th>
                    <th>Koszt</th>
                </tr>
                <c:forEach items="${sms}" var="sms1">
                    <tr>
                        <td>${sms1.telephone.telNumber}</td>
                        <td>${sms1.message}</td>
                        <td>${sms1.created}</td>
                        <td>${sms1.cost}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>


    <div data-area="details">
        <p class='p-header'>Wierzytelności:</p>
<security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
        <button class="button100" onClick="javascript:location.href='/debt/form?caseId=${newCase.id}'">Dodaj
        </button>
</security:authorize>
        <form action="">
            <table>
                <tr>
                    <th>Numer umowy</th>
                    <th>Data zawarcia umowy</th>
                    <th>Data wypowiedzenia</th>
                    <th>Kapitał</th>
                    <th>Koszty</th>
                    <th>Odsetki</th>
                    <th>Spłacony kapitał</th>
                    <th>Spłacone koszty</th>
                    <th>Spłacone odsetki</th>
                    <th>Saldo kapitału</th>
                    <th>Saldo kosztów</th>
                    <th>Saldo odsetek</th>
<security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                    <th>Usuń</th>
                    <th>Edytuj</th>
</security:authorize>
                </tr>
                <c:forEach items="${debts}" var="debt">
                    <tr>
                        <td><c:out value="${debt.contractNumber}"/></td>
                        <td><c:out value="${debt.contractDate}"/></td>
                        <td><c:out value="${debt.terminationDate}"/></td>
                        <td><c:out value="${debt.principal}"/></td>
                        <td><c:out value="${debt.costs}"/></td>
                        <td><c:out value="${debt.interests}"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                        <td><a href="/debt/delete?debtId=${debt.id}">Usuń</a></td>
                        <td><a href="/debt/form?debtId=${debt.id}">Edytuj</a></td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>


    <div data-area="results">
        <p class='p-header'>Rezultat:</p>
        <form>
            <table>
                <tr>
                    <th>Id akcji</th>
                    <th>Kod akcji</th>
                    <th>Opis kodu akcji</th>
                    <th>Data akcji</th>
                    <th>Akcja utworzona przez</th>
                    <th>Id rezultatu</th>
                    <th>Kod rezultatu</th>
                    <th>Opis kodu rezultatu</th>
                    <th>Data rezultatu</th>
                    <th>Komentarz</th>
                    <th>Użytkownik</th>
<security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                    <th>Usuń</th>
                    <th>Edytuj</th>
</security:authorize>
                </tr>
                <c:forEach items="${results}" var="result">
                    <tr>
                        <td>${result.action.id}</td>
                        <td>${result.action.actionType.code}</td>
                        <td>${result.action.actionType.description}</td>
                        <td>${result.action.created}</td>
                        <td>${result.action.user.id}</td>
                        <td>${result.id}</td>
                        <td>${result.resultType.code}</td>
                        <td>${result.resultType.description}</td>
                        <td>${result.created}</td>
                        <td>${result.comment}</td>
                        <td>${result.user.username}</td>
                        <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                        <td><a href="/result/delete?resultId=${result.id}">Usuń</a></td>
                        <td><a href="/result/form?resultId=${result.id}&actionId=${result.action.id}">Edytuj</a></td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>

    <div data-area="financial">
        <p class='p-header'>Dane finansowe:</p>
        <form>
            <table>
                <tr>
                    <td><span class='span-header'>Początkowa wartość
								kapitału:</span></td>
                    <td>${sumPrincipal}</td>
                </tr>
                <tr>
                    <td><span class='span-header'>Początkowa wartość kosztów:</span></td>
                    <td>${sumCosts}</td>
                </tr>
                <tr>
                    <td><span class='span-header'>Początkowa wartość odsetek:</span></td>
                    <td>${sumIntersts}</td>
                </tr>
                <tr>
                    <td><span class='span-header'>Spłacony kapitał:</span></td>
                    <td><input type="number" disabled="disabled" name='principal'/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Spłacone koszty:</span></td>
                    <td><input type="number" disabled="disabled" name='costs'/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Spłacone odsetki:</span></td>
                    <td><input type="number" disabled="disabled" name='interests'/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Aktualna wartość kapitału:</span></td>
                    <td><input type="number" disabled="disabled" name='principal'/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Aktualna wartość kosztów:</span></td>
                    <td><input type="number" disabled="disabled" name='costs'/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Aktualna wartość odsetek:</span></td>
                    <td><input type="number" disabled="disabled" name='interests'/></td>
                </tr>
                <tr>
                    <td><span class='span-header'>Aktualna wartość
								zadłużenia:</span></td>
                    <td><input type="number" disabled="disabled" name=''/></td>
                </tr>
            </table>
        </form>
    </div>


    <div data-area="payment">
        <p class='p-header'>Wpłaty:</p>
<security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
        <button class="button" id='edit'>Edytuj</button>
        <button class="button" id='delete'>Usuń</button>
        <button class="button" id='add'>Dodaj</button>
</security:authorize>
        <form>
            <table>
                <tr>
                    <th>Data wpłaty</th>
                    <th>Data wprowadzenia wpłaty</th>
                    <th>Wartość wpłaty</th>
                    <th>Wpłata na kapitał</th>
                    <th>Wpłata na koszty</th>
                    <th>Wpłata na odsetki</th>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>


    <div data-area="case">
        <p class='p-header'>Dane sprawy:</p>
<security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
        <button class="button100" onClick="javascript:location.href='/case/form?caseId=${newCase.id}'">Edytuj
        </button>
</security:authorize>
        <form>
            <table>
                <tr>
                    <td>Numer sprawy</td>
                    <td><c:out value="${newCase.id}"/></td>
                </tr>
                <tr>
                    <td>Numer konta</td>
                    <td><c:out value="${newCase.accountNumber}"/></td>
                </tr>
                <tr>
                    <td>Data rozpoczęcia obsługi</td>
                    <td><c:out value="${newCase.startDate}"/></td>
                </tr>
                <tr>
                    <td>Data zakończenia obsługi</td>
                    <td><c:out value="${newCase.endDate}"/></td>
                </tr>
                <tr>
                    <td>Pracownik CC</td>
                    <td><c:out value="${newCase.user.username}"/></td>
                </tr>
                <tr>
                    <td>Prawnik</td>
                    <td><c:out value="${newCase.user1.username}"/></td>
                </tr>
                <tr>
                    <td>Kierownik</td>
                    <td><c:out value="${newCase.user2.username}"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
