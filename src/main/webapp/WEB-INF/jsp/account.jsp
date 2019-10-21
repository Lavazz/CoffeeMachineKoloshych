<%@ page contentType='text/html;charset=windows-1251' language='java' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text"/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<html lang="en">
<head>
    <meta charset="UTF-8">


<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<form>
        <input type="hidden" name="action" value="signUpWindow">
        <label for="locale"></label>
        <select id="locale" name="locale" onchange="submit()">
            <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
            <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Ðóññêèé</option>
        </select>
</form>
<form method="post" action="home">
    <label>
    <select>
        <option><fmt:message key="account.payment.method"/></option>
        <option>ЕРИП</option>
        <option>карта</option>
        <option>QR-код</option>
        <option>webMoney</option>
    </select>
<p>
    </label>
    <label>enter sum</label>
    <input name="sum" value="" type="text" placeholder="sum" required>

    <input type="submit"  name="" value="make a deposit">
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
</body>
</html>