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
        <title><fmt:message key="registration.title"/></title>
    </title>
</head>
<body>
<form>
    <input type="hidden" name="command" value="changeLocale" bundle="${loc}"/>
    <label for="locale"></label>
    <select id="locale" name="locale" onchange="submit()">
        <option value="ru" ${locale == 'ru' ? 'selected' : ''}><fmt:message key="local.language.ru"/></option>
        <option value="en" ${locale == 'en' ? 'selected' : ''}><fmt:message key="local.language.en"/></option>
    </select>
</form>
        <h1>
            <fmt:message key="locale.title"/>
        </h1>

        <form class="box" action="home" method="post">
            <fieldset>
                <p>
                    <label>
                        <strong> <fmt:message key="registration.message.personalInfo"/></strong>
                    </label>
        </p>
        <p>

                    <label for="login">
                        <fmt:message key="registration.label.user.login"/>
                    </label>
                    <input id="login" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{3,12}$"
                           required type="text" placeholder="login" name="login">
        </p>
                <p>
                    <label for="password">
                        <fmt:message key="registration.label.user.password"/>
                    </label>
                    <input id="password" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                           required type="password" name="password">
                </p>
                <p>
                    <label for="password_confirm">
                        <fmt:message key="registration.label.user.confirmedPassword"/>
                    </label>
                    <input id="password_confirm" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                           required type="password" name="confirmedPassword">
                </p>
                <p>
                    <label for="name">
                        <fmt:message key="registration.label.user.name"/>
                    </label>
                    <input id="name" pattern=".{2,30}"
                           required type="text" placeholder="name" name="name">
                </p>
                <p>
                    <label for="email">
                        <fmt:message key="registration.label.user.email"/>
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