<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc"/>
<html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="home">
    <label><fmt:message key="header.label.login" bundle="${loc}"/></label>
    <input name="additionalIngredient" value="" type="text" placeholder="name" required>

    <label><fmt:message key="header.label.password" bundle="${loc}"/></label>
    <input name="price" value="" type="number" required>

    <input type="hidden" name="command" value="EditIngredient">
    <input type="submit"  name="" value="<fmt:message key='header.input.authorization' bundle='${loc}'/>" >
</form>
<form method="post" action="home">
    <label><fmt:message key="header.label.login" bundle="${loc}"/></label>
    <input name="idAdditionalIngredient" value="" type="text" placeholder="name" required>

    <input type="hidden" name="command" value="DeleteAdditionalIngredient">
    <input type="submit"  name="" value="<fmt:message key='header.input.authorization' bundle='${loc}'/>" >
</form>


</body>
</html>
