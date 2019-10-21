Сделала не много, так как повторяла пройденный материал, чтобы подготовится к тестам.
1.Добавила методы в валидацию:
я в методах сервиса вызывала валидацию, куда передавала полностью все параметры,
и для них делала перегруженные методы validate, а оттуда вызывала проверку 
для каждого параметра в отдельности,  например validateLogin, validatePassword,
так же можно было делать?
2.Немного улучшила ДАО
3.В ServiceFactory заполнила методы, а то там был только пустой каркас.
3.Добавила несколько методов в commandImpl: Drink, User, AdditionalIngredient
4.Распределила jsp и добавила их в index

Самое главное, что web все таки заработал.

Нужно дописать классы Command, jsp, locale.

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<c:set var="locale"--%>
<%--       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"--%>
<%--       scope="session"/>--%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc"/>
<html lang="en">
<head>
    <title><fmt:message key="locale.title.index" bundle="${loc}"/></title>
</head>
<body>
<h1><fmt:message key="locale.title" bundle="${loc}"/></h1>

<a href="${pageContext.request.contextPath}/home?command=changeLocale&locale=en">en</a>
<a href="${pageContext.request.contextPath}/home?command=changeLocale&locale=ru">ru</a>
<form action="home">
    <input type="hidden" name="command" value="changeLocale" />
    <label for="locale"></label>
    <select id="locale" name="locale" onchange="submit()">
        <option value="en" ${locale == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${locale == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>

<c:if test="${sessionScope.userId != null && sessionScope.userStatusId != null}">

    <a href="home?command=logout">
        <fmt:message key="button.logout" bundle="${loc}"/>
    </a>
    <br>
    <a href="home?command=viewUserCabinet">
        <fmt:message key="button.personal_cabinet" bundle="${loc}"/>
    </a>
    <input type="hidden" name="userId" value="${sessionScope.userId}">
    <input type="hidden" name="userStatus" value="${sessionScope.userStatusId}">
</c:if>
<p>
<c:if test="${sessionScope.userId == null}">
    <a href="home?command=goToRegistrationPage">
        <fmt:message key="button.signUp"/>
    </a>
</p>
<p>
    <a href="home?command=authorization">
        <fmt:message key="button.signIn"/>
    </a>
</c:if>
</p>
<p>
<a href="home?command=showAllDrinks">
    drinks
</a>
</p>
<c:if test="${sessionScope.userId != null && sessionScope.userStatusId != null}">

    <a href="home?command=makeOrder">
        order
    </a>
</c:if>

</body>
</html>

<%--<a href="${pageContext.request.contextPath}/home?command=changeLocale&locale=en">en</a>--%>
<%--<a href="${pageContext.request.contextPath}/home?command=changeLocale&locale=ru">ru</a>--%>