<%@ page contentType='text/html;charset=windows-1251' language='java' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.registration"/></title>
</head>
<body>

        <h1>
            <fmt:message key="locale.title"/>
        </h1>

        <form>
            <input type="hidden" name="action" value="main"/>
            <label for="locale"></label>
            <select id="locale" name="locale" onchange="submit()">
                <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
            </select>
        </form>

        <form class="box" action="home" method="post">
            <fieldset>
                <p>
                    <label>
                        <strong> <fmt:message key="table.message.personalInfo"/></strong>
                    </label>
        </p>
        <p>

                    <label for="login">
                        <fmt:message key="table.message.user.login"/>
                    </label>
                    <input id="login" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{3,12}$"
                           required type="text" placeholder="login" name="login">
        </p>
                <p>
                    <label for="password">
                        <fmt:message key="table.message.user.password"/>
                    </label>
                    <input id="password" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                           required type="password" name="password">
                </p>
                <p>
                    <label for="password_confirm">
                        <fmt:message key="table.message.user.confirmedPassword"/>
                    </label>
                    <input id="password_confirm" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                           required type="password" name="confirmedPassword">
                </p>
                <p>
                    <label for="name">
                        <fmt:message key="table.message.user.name"/>
                    </label>
                    <input id="name" pattern=".{2,30}"
                           required type="text" placeholder="name" name="name">
                </p>
                <p>
                    <label for="email">
                        <fmt:message key="table.message.user.email"/>
                    </label>
                    <input id="email"
                           pattern="[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}"
                           required type="email" placeholder="email" name="email">
                </p>
    <input type="submit" class="submit" value="<fmt:message key="button.signUp"/>"/>
                <input type="hidden" name="command" value="registration"/>
    </fieldset>
</form>
    </body>
</html>