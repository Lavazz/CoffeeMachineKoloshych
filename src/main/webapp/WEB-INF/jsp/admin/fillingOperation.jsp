<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="locale"/>
<html>
<html lang="ru">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message key="locale.drinks.title" bundle="${locale}"/>
    </title>
</head>
<body>
<fmt:message key="locale.drinks.welcome" bundle="${locale}"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<form metod="post" action="home">
    <table border="=1">
        <c:forEach items="${ingredients}" var="ingredient">
            <tr>
                <td>${ingredient.idIngredient}</td>
                <td>${ingredient.nameIngredient}</td>
                <td>${ingredient.portion}</td>
                <td>
                    <input type="checkbox" name="ingredient" value="${ingredient.idIngredient}">
                  </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit"  class="submit" value="FillToMax">
    <input type="hidden" name="command" value="fillToMax" >
</form>
<c:out value="${message}"/>
</body>
</html>
