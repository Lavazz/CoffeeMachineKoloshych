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
<c:if test="${user != null}">
    <p>
        <a href="${pageContext.request.contextPath}/home?command=${"personalCabinet"}">
            <fmt:message key="locale.drinks.cabinet" bundle="${loc}"/>
        </a>
    </p>
</c:if>
<form method="post"  action="home" >
    <p>
    <table border="=1">
        <c:forEach items="${drinks}" var="drink">
            <tr>
                <td>${drink.idDrink}</td>
                <td>${drink.drinkName}</td>
                <td>${drink.price}</td>
                <td><input type="radio" name="radioButtonDrink" value="${drink.idDrink}"></td>
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
                <td><input type="radio" name="radioButtonIngredient" value="${additionalIngredient.idAdditionalIngredient}"></td>
            </tr>
        </c:forEach>
    </table>
    </p>
    <c:if test="${user != null}">
        <label><fmt:message key="drinks.label.portion" bundle="${loc}"/></label>
        <input name="portion" value="" type="number" required>

        <input type="submit"  name="portion" value="<fmt:message key='drinks.input.addInBasket' bundle='${loc}'/>" >
        <input type="hidden" name="command" value="orderJournal">
    </c:if>
    <p>
        <c:if test="${user == null}">
         <h2><fmt:message key="locale.drinks.recommendation" bundle="${loc}"/></h2>
            </c:if>
    </p>
</form>
</body>
</html>