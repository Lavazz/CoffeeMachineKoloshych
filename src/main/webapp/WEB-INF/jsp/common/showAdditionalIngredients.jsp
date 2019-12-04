<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://trjava.by/kaloshych" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title><fmt:message key="title.additionalIngredients"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet">

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
        <a class="navbar-brand" href="main?command=goToMainPage"><fmt:message key="main.brand.coffee"/><small><fmt:message key="main.brand.house"/></small></a>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item "><a href="main?command=goToMainPage" class="nav-link">
                    <fmt:message key="main.message.home" /></a></li>
                <li class="nav-item "><a href="main?command=showDrinks" class="nav-link">
                    <fmt:message key="main.message.drinks"/></a></li>
                <li class="nav-item active"><a href="main?command=showAdditionalIngredients" class="nav-link">
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
            <c:import url="/WEB-INF/jsp/formLanguage.jsp"/>
        </div>
    </div>
</nav>

<section class="ftco-section">
    <div class="container">
        <div class="row">
<div class="col-md-6 mb-5 pb-3">
    <h3 class="mb-5 heading-pricing ftco-animate"><fmt:message key="main.message.additionalIngredients"/> </h3>
    <c:forEach items="${additionalIngredients}" var="additionalIngredient">
        <div class="pricing-entry d-flex ftco-animate">
            <div class="img" style="background-image: url(${additionalIngredient.picturePath});"></div>
            <div class="desc pl-3">
                <h3> <ctg:outAdditionalIngredient additionalIngredient="${additionalIngredient}"/></h3>
<%--                    <h3><span>${additionalIngredient.nameComponent}</span></h3>--%>
                <div class="d-block">
                    <p><fmt:message key="additional_ingredient.calories"/>${additionalIngredient.calories}<fmt:message key="additional_ingredient.kcal"/></p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
        </div>
    </div>
</section>

<c:import url="/WEB-INF/jsp/footer.jsp"/>

<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"></svg></div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-migrate-3.0.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
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