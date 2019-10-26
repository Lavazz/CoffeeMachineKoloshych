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

<c:import url="/WEB-INF/jsp/header.jsp"/>
<h2>
<fmt:message key="personalCabinet.text" bundle="${loc}"/>
</h2>
<p>

<c:out value="${balance}"/>
</p>
<p>
    <a href="home?command=goToPageIncreaseBalance">
      <fmt:message key="increaseBalance.massage.balance" bundle="${loc}"/>
    </a>

</p>
<p>
    <a href="home?command=goToIndexPage">
        <fmt:message key="increaseBalance.message.order" bundle="${loc}"/>
    </a>

</p>
<p>
<fmt:message key="personalCabinet.text.historyOfOrders" bundle="${loc}"/>
<form method="post"  action="home" >
<table border="=1">
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.idDrink}</td>
            <td>${order.idAdditionalIngredient}</td>
            <td>${order.portion}</td>
<%--            <td>${order.totalCost}</td>--%>
                    </tr>
    </c:forEach>
</table>
</form>
</p>

<p>
    <fmt:message key="personalCabinet.message.changePassword" bundle="${loc}"/>
</p>
<a href="${pageContext.request.contextPath}/home?command=${"fillingOperation"}">
    filling
<%--    <fmt:message key="per.message.order" bundle="${loc}"/>--%>
</a>

</body>
</html>
