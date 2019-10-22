<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc"/>
<html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message key="locale.drinks.title" bundle="${loc}"/>
    </title>
</head>
<body>
<fmt:message key="locale.drinks.welcome" bundle="${loc}"/>

<c:import url="/header.jsp"/>


</body>
</html>
