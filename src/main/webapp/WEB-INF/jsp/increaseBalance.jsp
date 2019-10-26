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
        <fmt:message key="increaseBalance.title" bundle="${loc}"/>
    </title>
</head>
<body>
<fmt:message key="increaseBalance.text.welcome" bundle="${loc}"/>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<c:if test="${user!=null}">
<form method="post" action="home">
    <label>
        <select>
            <option><fmt:message key="account.payment.method" bundle="${loc}"/></option>
            <option><fmt:message key="increaseBalance.label.ERIP" bundle="${loc}"/></option>
            <option><fmt:message key="increaseBalance.label.card" bundle="${loc}"/></option>
            <option><fmt:message key="increaseBalance.label.QR-code" bundle="${loc}"/></option>
            <option><fmt:message key="increaseBalance.label.webMoney" bundle="${loc}"/></option>
        </select>
        <p>
    </label>
    <input type="hidden" name="paymentMethod" value=""/>
    <label><fmt:message key="increaseBalance.label.sum"/> </label>
    <input name="amountOfMoney" value="" type="text" placeholder="sum" required>
    <input type="submit"  name="" value="<fmt:message key='increaseBalance.label.deposit' bundle="${loc}"/> ">
    <input type="hidden" name="command" value="increaseBalance">
    </p>
</form>

<form method="post" action="home">
</form>
<table>
    <tr>
        <td>
            <c:out value="${balance}"/>
        </td>
    </tr>
</table>

</c:if>
</body>
</html>
