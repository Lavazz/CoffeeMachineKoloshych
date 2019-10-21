<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.locale" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message key="locale.drinks.title" bundle="${rb}"/>
    </title>
</head>
<body>
<fmt:message key="locale.drinks.welcome" bundle="${rb}"/>
<form>
    <input type="hidden" name="action" value="main"/>
    <label for="locale"></label>
    <select id="locale" name="locale" onchange="submit()">
        <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<p>
    <c:if test="${user == null}">
<p>
    <a href="${pageContext.request.contextPath}${"authorization"}">
        <fmt:message key="locale.authorization" bundle="${rb}"/>
    </a >
        <%--<form method="post" action="home">--%>
        <%--    <label>enter login</label>--%>
        <%--    <input name="name" value="" type="text" placeholder="name" required>--%>

        <%--    <label>enter password</label>--%>
        <%--    <input name="password" value="" type="password" required>--%>

        <%--    <input type="hidden" name="command" value="signIn">--%>
        <%--    <input type="submit"  name="" value="signIn">--%>
        <%--</form>--%>
</p>
<p>
    <a href="${pageContext.request.contextPath}${"registration"}">
        <fmt:message key="locale.registration" bundle="${rb}"/>
    </a >
</p>
</c:if>
<c:if test="${user != null}">
    <p>
        <a href="${pageContext.request.contextPath}/home?command=${"personalCabinet"}">
            <fmt:message key="locale.drinks.cabinet" bundle="${rb}"/>
        </a>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/home?command=${"/index"}">
            <fmt:message key="locale.drinks.logout" bundle="${rb}"/>
        </a>
    </p>
    <%--    <form method="get" action="home">--%>
    <%--        <input type="submit" name="command" value="registration">--%>
    <%--    </form>--%>
</c:if>

<form method="post"  action="home" >
    <p>
    <table border="=1">
        <c:forEach items="${drinks}" var="drink">
            <tr>
                <td>${drink.idDrink}</td>
                <td>${drink.drinkName}</td>
                <td>${drink.price}</td>
                <td> <label>portion</label>
                    <input name="portion" value="" type="number">
                </td>
            </tr>
        </c:forEach>
    </table>
    </p>
    <p>
    <table border="=1">
        <c:forEach items="${additionalIngredients}" var="additionalIngredient">
            <tr>
                <td>${additionalIngredient.idAdditionalIngredient}</td>
                <td>${additionalIngredient.nameIngredient}</td>
                <td>${additionalIngredient.brand}</td>
                <td>${additionalIngredient.price}</td>
                <td><input type="radio" id="radioButton"></td>

            </tr>
        </c:forEach>
    </table>
    </p>
    <c:if test="${user != null}">
        <a href="home?command=makeOrder">
            <input type="submit" name="command" value="makeOrder">
        </a>
    </c:if>
</form>
</body>
</html>