<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <c:import url="/WEB-INF/jsp/form/navBrand.jsp"/>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="main?command=goToMainPage" class="nav-link">
                        <fmt:message key="main.message.home"/></a>
                </li>
                <li class="nav-item ">
                    <a href="main?command=showDrinks" class="nav-link">
                        <fmt:message key="main.message.drinks"/></a>
                </li>
                <li class="nav-item">
                    <a href="main?command=showAdditionalIngredients" class="nav-link">
                        <fmt:message key="main.message.additionalIngredients"/></a>
                </li>
                <c:if test="${sessionScope.idUser== null }">
                    <li class="nav-item"><a href="main?command=goToRegistrationPage" class="nav-link">
                        <fmt:message key="main.message.registration"/></a>
                    </li>
                    <li class="nav-item"><a href="main?command=goToAuthorizationPage" class="nav-link">
                        <fmt:message key="main.message.authorization"/></a></li>
                </c:if>
                <c:if test="${sessionScope.idUserStatus ==1}">
                    <li class="nav-item"><a href="main?command=showAdminCabinet" class="nav-link">
                        <fmt:message key="main.message.admin_cabinet"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.idUserStatus ==2}">
                    <li class="nav-item"><a href="main?command=personalCabinet" class="nav-link">
                        <fmt:message key="main.message.personalCabinet"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.idUser != null }">
                    <c:import url="/WEB-INF/jsp/form/nawLogout.jsp"/>
                </c:if>
                <li class="nav-item cart"><a href="main?command=showCart" class="nav-link"><span
                        class="icon icon-shopping_cart">
                </span><span class="bag d-flex justify-content-center align-items-center"><small>1</small></span></a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <form method="post" action="main">
                        <input type="hidden" name="command" value="changeLocale">
                        <input type="hidden" name="locale" value="ru_RU">
                        <input type="submit" name="locale" value="ru" class="btn btn-white btn-outline-white">
                    </form>
                </li>
                <li class="nav-item">
                    <form method="post" action="main">
                        <input type="hidden" name="command" value="changeLocale">
                        <input type="hidden" name="locale" value="en_EN">
                        <input type="submit" name="locale" value="en" class="btn btn-white btn-outline-white">
                    </form>
                </li>
            </ul>
        </div>
    </div>
    </div>
</nav>