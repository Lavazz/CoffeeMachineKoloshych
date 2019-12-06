<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="http://trjava.by/kaloshych" %>
<%@ taglib prefix="ctg" uri="http://trjava.by/kaloshych" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title><fmt:message key="title.drinks"/></title>
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
        <c:import url="/WEB-INF/jsp/navBrand.jsp"/>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item "><a href="main?command=goToMainPage" class="nav-link">
                    <fmt:message key="main.message.home" /></a></li>
                <li class="nav-item active"><a href="main?command=showDrinks" class="nav-link">
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
                <c:import url="/WEB-INF/jsp/nawCabinet.jsp"/>
                <c:import url="/WEB-INF/jsp/nawCartAndLogout.jsp"/>
            </ul>
            <c:import url="/WEB-INF/jsp/formLanguage.jsp"/>
        </div>
    </div>
</nav>

<section class="ftco-section">
    <div class="container">
        <c:if test="${sessionScope.messageDrinks!=null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong><fmt:message key="${sessionScope.messageDrinks}"/></strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:remove var="messageDrinks"/>
        </c:if>
        <c:if test="${sessionScope.idUser == null}">
            <h2><fmt:message key="drinks.guest.recommendation" /></h2>
        </c:if>
        <c:if test="${sessionScope.idUserStatus==1}">
            <h2><fmt:message key="drinks.admin.recommendation" /></h2>
        </c:if>
        <form action="main" method="post">
            <c:forEach items="${requestScope.drinks}" var="drink">
            <div class="row">
                <div class="col-lg-6 mb-5 ftco-animate">
                    <div class="form-group d-flex">
                        <label>
                            <input type="radio" name="radioButtonDrink" value="${drink.idComponent}">
                        </label>
                    </div>
                    <a href="${drink.picturePath}" class="image-popup">
                        <img src="${drink.picturePath}" class="img-fluid" alt="Colorlib Template"></a>
                </div>
                <div class="col-lg-6 product-details pl-md-5 ftco-animate">
                    <h3> <ctg:outDrink drink="${drink}"/></h3>
<%--                    ${drink.nameComponent}--%>
                    <p class="price"><span>${drink.price}</span></p>
                    <p><h5><ctg:outDescription drink="${drink}"/></h5>
                    <div class="row mt-4">
                        <div class="col-md-6">
                        </div>
                    </div>
                </div>
                <br><br>
                </c:forEach>

                <c:forEach items="${requestScope.additionalIngredients}" var="additionalIngredient">
                 <p><label>
                     <input type="checkbox" name="idAdditionalIngredient"
                               value="${additionalIngredient.idComponent}" />
                 </label> <ctg:outAdditionalIngredient additionalIngredient="${additionalIngredient}"/></p>

                </c:forEach>
                <div class="w-100"></div>

                <label for="portion">
                    <input type="text" id="portion" name="portion" value="" placeholder="portion: 1-99" required >
                </label>
            </div>
            <c:if test="${sessionScope.idUserStatus==2}">
                <input type="hidden" name="command" value="addToCart">
                <input type="submit" name="submit" value="<fmt:message key='drinks.button.addToCart'/>  ">
            </c:if>
        </form>

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