<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" var="loc"/>

<html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/maim.css">

    <title>
        <title><fmt:message key="registration.title" bundle="${loc}"/></title>
    </title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-5"> <h1>
            <fmt:message key="locale.title" bundle="${loc}"/>
        </h1>

            <form class="box" action="home" method="post">
                <fieldset>
                    <p>
                        <label>
                            <strong> <fmt:message key="registration.message.personalInfo" bundle="${loc}"/></strong>
                        </label>
                    </p>
                    <p>

                        <label for="login">
                            <fmt:message key="registration.label.user.login" bundle="${loc}"/>
                        </label>
                        <input id="login" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{3,12}$"
                               required type="text" placeholder="login" name="login">
                    </p>
                    <p>
                        <label for="password">
                            <fmt:message key="registration.label.user.password" bundle="${loc}"/>
                        </label>
                        <input id="password" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                               required type="password" name="password">
                    </p>
                    <p>
                        <label for="password_confirm">
                            <fmt:message key="registration.label.user.confirmedPassword" bundle="${loc}"/>
                        </label>
                        <input id="password_confirm" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                               required type="password" name="confirmedPassword">
                    </p>
                    <p>
                        <label for="name">
                            <fmt:message key="registration.label.user.name" bundle="${loc}"/>
                        </label>
                        <input id="name" pattern=".{2,30}"
                               required type="text" placeholder="name" name="name">
                    </p>
                    <p>
                        <label for="email">
                            <fmt:message key="registration.label.user.email" bundle="${loc}"/>
                        </label>
                        <input id="email"
                               pattern="[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}"
                               required type="email" placeholder="email" name="email">
                    </p>
                    <input type="submit" class="submit" value="<fmt:message key="button.signUp" bundle="${loc}"/>"/>
                    <input type="hidden" name="command" value="registration"/>
                </fieldset>
            </form>
        </div>
        <div class="col-3">
<form>
    <input type="hidden" name="command" value="changeLocale" bundle="${loc}"/>
    <label for="locale"></label>
    <select id="locale" name="locale" onchange="submit()">
        <option value="ru" ${locale == 'ru' ? 'selected' : ''}>Русский</option>
        <option value="en" ${locale == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>
        </div>

        <div class="col-4">
            <form method="get" action="home">
                <input type="submit" name="submit" value="<fmt:message key='registration.input.drinks' bundle='${loc}'/>">
                <input type="hidden" name="command" value="goToSelectionAllDrinks">
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
    </body>
</html>