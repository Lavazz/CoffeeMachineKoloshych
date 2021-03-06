<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title><fmt:message key="title.registration"/></title>
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

<c:import url="/WEB-INF/jsp/form/naw.jsp"/>

<section class="ftco-section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-8 text-center ftco-animate">
                <c:if test="${sessionScope.messageRegistration!=null}">
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <fmt:message key="${sessionScope.messageRegistration}"/>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <c:remove var="messageRegistration"/>
                </c:if>
            </div>
            <div class="col-xl-8 ftco-animate">
                <form action="main" method="post" class="billing-form ftco-bg-dark p-3 p-md-5">
                    <h3 class="mb-4 billing-heading"><fmt:message key="registration.title"/></h3>
                    <div class="row align-items-end">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="email">
                                    <fmt:message key="registration.label.user.email"/>
                                </label>
                                <input id="email" class="form-control"
                                       pattern="[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}"
                                       type="email" placeholder="email" name="email" required>
                            </div>
                        </div>

                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="login">
                                    <fmt:message key="registration.label.user.login"/>
                                </label>
                                <input id="login" type="text" class="form-control"
                                       pattern="^[a-zA-Z][a-zA-Z0-9-_.]{3,12}$"
                                       placeholder="login" name="login" required>
                            </div>
                        </div>

                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name">
                                    <fmt:message key="registration.label.user.name"/>
                                </label>
                                <input id="name" class="form-control"
                                       pattern="^[a-zA-ZА-Яа-я][a-zA-ZА-Яа-я0-9-_.]{3,12}$"
                                       type="text" placeholder="name" name="name" required>
                            </div>
                        </div>

                        <div class="w-100"></div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="password">
                                    <fmt:message key="registration.label.user.password"/>
                                </label>
                                <input id="password" class="form-control" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                                       required type="password" name="password" placeholder="password">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="password_confirm">
                                    <fmt:message key="registration.label.user.confirmedPassword"/>
                                </label>
                                <input id="password_confirm" class="form-control" pattern="^[a-zA-Z0-9-_.]{4,10}$"
                                       required type="password" name="confirmedPassword" placeholder="confirm password">
                            </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="col-md-12">
                            <div class="form-group mt-4">
                                <input type="submit" class="submit" value="<fmt:message key="button.signUp" />"/>
                                <input type="hidden" name="command" value="registration"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<c:import url="/WEB-INF/jsp/form/footer.jsp"/>

<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px"></svg>
</div>
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