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
        <a class="navbar-brand" href="main?command=goToMainPage"><fmt:message key="main.brand.coffee"/><small><fmt:message key="main.brand.house"/></small></a>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item "><a href="main?command=goToMainPage" class="nav-link">
                    <fmt:message key="main.message.home" /></a></li>
                <li class="nav-item "><a href="main?command=showDrinks" class="nav-link">
                    <fmt:message key="main.message.drinks"/></a></li>
                <li class="nav-item"><a href="main?command=showAdditionalIngredients" class="nav-link">
                    <fmt:message key="main.message.additionalIngredients" /></a></li>
                <li class="nav-item active"><a href="main?command=showAdminCabinet" class="nav-link">
                    <fmt:message key="main.message.admin_cabinet" /></a>
                </li>
                  <li class="nav-item"><a href="main?command=logOut" class="nav-link">
                    <fmt:message key="locale.drinks.logout" /></a></li>

                <li class="nav-item cart"><a href="main?command=showCart" class="nav-link"><span class="icon icon-shopping_cart">
                </span><span class="bag d-flex justify-content-center align-items-center"><small>1</small></span></a></li>
            </ul>
            <c:import url="/WEB-INF/jsp/formLanguage.jsp"/>
        </div>
    </div>
</nav>

<section class="ftco-section ftco-cart">
    <div class="container">

        <br>
<div class="align-content-lg-center">
       <a href= "main?command=goToFillingOperationPage" class="btn btn-primary py-3 px-4">
            <fmt:message key="main.message.fillingOperation" /></a>

        <a href="main?command=goToAddNewDrinkPage" class="btn btn-primary py-3 px-4">
            <fmt:message key="main.message.add_drink" /> </a>

        <a href="main?command=goToAddNewAdditionalIngredientPage" class="btn btn-primary py-3 px-4">
            <fmt:message key="main.message.add_additional_ingredient" /></a>

       <a href="main?command=goToDeleteComponentPage" class="btn btn-primary py-3 px-4">
            <fmt:message key="main.message.delete_ingredient" /> </a>
        </div><br>
        <div class="row">
            <div class="col-12">
                <br>
            <table >
                <thead>
                <tr>
                   <th><h6><fmt:message key="admin.table.id_component" /></h6></th>
                    <th><h6><fmt:message key="admin.table.name_component"  /></h6></th>
                    <th><h6><fmt:message key="admin.table.portion" /></h6></th>
                </tr>
                </thead>
                <c:forEach items="${components}" var="component">
                    <tbody><tr>
                        <td><h6>${component.idComponent}</h6></td>
                        <td><h6>${component.nameComponent}</h6></td>
                        <td><h6>${component.portion}</h6></td>
                    </tr></tbody>
                </c:forEach>
            </table>
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
