<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc"/>
<h1><fmt:message key="locale.title" bundle="${loc}"/></h1>

<form>
    <input type="hidden" name="command" value="changeLocale" bundle="${loc}"/>
    <label for="locale"></label>
    <select id="locale" name="locale" onchange="submit()">
        <option value="ru" ${locale == 'ru' ? 'selected' : ''}>Русский</option>
        <option value="en" ${locale == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>
<c:if test="${user == null}">
    <p>
    <form method="post" action="home">
        <label><fmt:message key="header.label.login" bundle="${loc}"/></label>
        <input name="login" value="" type="text" placeholder="name" required>

        <label><fmt:message key="header.label.password" bundle="${loc}"/></label>
        <input name="password" value="" type="password" required>

        <input type="hidden" name="command" value="authorization">
        <input type="submit"  name="" value="<fmt:message key='header.input.authorization' bundle='${loc}'/>" >
    <c:out value="${message}"/>
    </form>

    </p>
    <p>
    <form method="get" action="home">
        <input type="submit" name="submit" value="<fmt:message key='header.input.registration' bundle='${loc}'/>">
    <input type="hidden" name="command" value="goToRegistrationPage">
    </form>
    </p>
</c:if>
<c:if test="${user != null}">
    <p>
        <a href="${pageContext.request.contextPath}/home?command=${"logOut"}">
            <fmt:message key="locale.drinks.logout" bundle="${loc}"/>
        </a>
    </p>
</c:if>
