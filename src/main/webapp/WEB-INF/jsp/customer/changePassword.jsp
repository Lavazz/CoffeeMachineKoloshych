<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title><fmt:message key="title.changePassword"/></title>
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
        <a class="navbar-brand" href="main?command=goToMainPage"><fmt:message key="main.brand.coffee"/><small>
            <fmt:message key="main.brand.house"/></small></a>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item "><a href="main?command=goToMainPage" class="nav-link">
                    <fmt:message key="main.message.home"/></a></li>
                <li class="nav-item "><a href="main?command=showDrinks" class="nav-link">
                    <fmt:message key="main.message.drinks"/></a></li>
                <li class="nav-item"><a href="main?command=showAdditionalIngredients" class="nav-link">
                    <fmt:message key="main.message.additionalIngredients"/></a></li>

                <li class="nav-item ">
                    <a href="main?command=showAdminCabinet" class="nav-link">
                        <fmt:message key="main.message.admin_cabinet"/></a>
                </li>

                <li class="nav-item active">
                    <a href="main?command=goToChangePasswordPage" class="nav-link">
                        <fmt:message key="nav.changePassword"/></a>
                </li>

                <li class="nav-item"><a href="main?command=logOut" class="nav-link">
                    <fmt:message key="locale.drinks.logout"/></a></li>

                <li class="nav-item cart"><a href="main?command=showCart" class="nav-link"><span
                        class="icon icon-shopping_cart">
                </span><span class="bag d-flex justify-content-center align-items-center"><small>1</small></span></a>
                </li>
            </ul>
            <li class="nav-item">
                <a href="main?command=changeLocale&locale=ru">
                    <fmt:message key="locale.language.ru"/>
                </a> |
                <a href="main?command=changeLocale&locale=en">
                    <fmt:message key="locale.language.en"/>
                </a>
            </li>
        </div>
    </div>
</nav>


<section class="ftco-section">
    <div class="container">
        <c:if test="${messageChangePassword!=null}">
            <h3> <fmt:message key="${messageChangePassword}"/></h3>
        </c:if>
        <c:remove var="messageChangePassword"/>
        <div class="row">
            <div class="col-xl-8 ftco-animate">
                <form action="main" method="post" class="billing-form ftco-bg-dark p-3 p-md-5">
                    <h3 class="mb-4 billing-heading"><fmt:message key="changePassword.table.title" /></h3>
                    <div class="row align-items-end">
                        <div class="col-md-6">
                            <div class="form-group">

                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="current_password">
                                    <fmt:message key="changePassword.label.user.currentPassword" />
                                </label>
                                <input id="current_password" class="form-control" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                                       required type="password" name="currentPassword" placeholder="current password">
                            </div>
                        </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="new_password">
                                            <fmt:message key="changePassword.label.user.newPassword" />
                                        </label>
                                        <input id="new_password" class="form-control" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                                               required type="password" name="newPassword" placeholder="new password">
                                    </div>
                                </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="password_confirm">
                                    <fmt:message key="changePassword.label.user.confirmedPassword"/>
                                </label>
                                <input id="password_confirm" class="form-control" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                                       required type="password" name="confirmedPassword" placeholder="confirm password">
                            </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="col-md-12">
                            <div class="form-group mt-4">
                                <input type="submit" class="submit" value="<fmt:message key="changePassword.submit" />"/>
                                <input type="hidden" name="command" value="changePassword"/>
                            </div>
                        </div>
                    </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
<%--        <c:if test="${messageRegistration !=null}">--%>
<%--            <script>--%>
<%--                showAlertMessage("<fmt:message key="${messageRegistration}"/>");--%>
<%--            </script>--%>
<%--        </c:if>--%>

<%--        <c:remove var="messageRegistration"/>--%>
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
