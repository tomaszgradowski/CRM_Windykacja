<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@    taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/home">Strona główna</a></li>
            <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Administracja<span
                        class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/actiontype/form">Dodaj nowy kod akcji</a></li>
                        <li><a href="/actiontype/list">Lista kodów akcji</a></li>
                        <li><a href="/resulttype/form">Dodaj nowy rezultat akcji</a></li>
                        <li><a href="/resulttype/list">Lista rezultatów akcji</a></li>
                        <li><a href="/acttyperestype/form">Przypisz rezultat do akcji</a></li>

                    </ul>
                </li>
            </security:authorize>

            <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Zarządzanie
                    użytkownikami<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/form">Dodaj użytkownika</a></li>
                        <li><a href="/user/list">Lista użytkowników</a></li>
                            <%--<li><a href="#">Page 1-3</a></li>--%>
                    </ul>
                </li>
            </security:authorize>
            <security:authorize access="hasAnyRole('ADMIN','SUPERVISOR')">
            <li><a href="/case/form">Nowa sprawa</a></li>
            </security:authorize>
            <%--<li><a href="#">Page 3</a></li>--%>
        </ul>
        <form class="navbar-form navbar-left" action="/case/allinfo">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Numer sprawy" name="newCaseId">
            </div>
            <button type="submit" class="btn btn-default">Otwórz</button>
        </form>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">User: <security:authentication property="principal.username"/></a></li>
            <li class="active"><a>Role: <security:authentication property="principal.authorities"/></a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>--%>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
    </div>

</nav>