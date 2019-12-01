<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title><fmt:message key="title.coffee"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aos.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ionicons.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.timepicker.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icomoon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body style="background-image:url(${pageContext.request.contextPath}/pictures/bg/bg_1.jpg);">
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="mainPage.jsp"><fmt:message key="main.brand.coffee"/><small><fmt:message key="main.brand.house"/></small></a>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="main?command=goToMainPage" class="nav-link">
                    <fmt:message key="main.message.home" /></a></li>
                <li class="nav-item "><a href="main?command=showDrinks" class="nav-link">
                    <fmt:message key="main.message.drinks"/></a></li>
                <li class="nav-item"><a href="main?command=showAdditionalIngredients" class="nav-link">
                    <fmt:message key="main.message.additionalIngredients" /></a></li>

                <c:if test="${sessionScope.idUser== null }">
                <li class="nav-item"><a href="main?command=goToRegistrationPage" class="nav-link">
                        <fmt:message key="main.message.registration"/></a>
                    </li>
                    <li class="nav-item"><a href="main?command=goToAuthorizationPage" class="nav-link">
                        <fmt:message key="main.message.authorization" /></a></li>
                </c:if>

                <c:if test="${sessionScope.idUserStatus ==1}"><li class="nav-item"><a href="main?command=showAdminCabinet" class="nav-link">
                    <fmt:message key="main.message.admin_cabinet" /></a>
               </li>
                </c:if>

                <c:if test="${sessionScope.idUserStatus ==2}">
                <li class="nav-item"><a href="main?command=personalCabinet" class="nav-link">
                    <fmt:message key="main.message.personalCabinet" /></a>
                </li>
                </c:if>

                <c:if test="${sessionScope.idUser != null }"><li class="nav-item"><a href="main?command=logOut" class="nav-link">
                    <fmt:message key="locale.drinks.logout" /></a></li>
                </c:if>

                <li class="nav-item cart"><a href="main?command=showCart" class="nav-link"><span class="icon icon-shopping_cart">
                </span><span class="bag d-flex justify-content-center align-items-center"><small>1</small></span></a></li>
            </ul>
            <li class="nav-item">
                <form method="post" action="main">
                    <input type="hidden" name="command" value="changeLocale">
                    <input type="submit" name="locale" value="ru" class="btn btn-white btn-outline-white">

                </form>
            </li>
            <li class="nav-item">
                <form method="post" action="main">
                    <input type="hidden" name="command" value="changeLocale">
                    <input type="submit" name="locale" value="en" class="btn btn-white btn-outline-white">
                </form>
            </li>

                </div>

        </div>
    </div>
</nav>

<section class="home-slider owl-carousel">
    <div class="slider-item" style="background-image: url(${pageContext.request.contextPath}/pictures/bg/bg_1.jpg);">
        <div class="overlay"></div>
        <div class="container">
            <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">
                <div class="col-md-8 col-sm-12 text-center ftco-animate">
                    <span class="subheading"><fmt:message key="main.message.welcome" /></span>
                    <h1 class="mb-4"><fmt:message key="main.message.forYou" /></h1>
                    <p class="mb-4 mb-md-5"><fmt:message key="main.message.goodMood" /></p>
                    <p><a href="main?command=showDrinks" class="btn btn-white btn-outline-white p-3 px-xl-4 py-xl-3"><fmt:message key="main.message.viewDrinks" /></a></p>
                </div>
            </div>
        </div>
    </div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp"/>

<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-migrate-3.0.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.stellar.min.js"></script>
<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.request.contextPath}/js/aos.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.animateNumber.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="${pageContext.request.contextPath}/js/google-map.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>