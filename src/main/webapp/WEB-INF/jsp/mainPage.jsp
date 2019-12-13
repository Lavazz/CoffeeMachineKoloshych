<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
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
                    <c:import url="/WEB-INF/jsp/form/naw.jsp"/>

<section class="home-slider owl-carousel">
    <div class="slider-item" style="background-image: url(${pageContext.request.contextPath}/pictures/bg/bg_1.jpg);">
        <div class="overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col">
                    <c:out value=" "/>
                    <br><br><br><br><br>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-sm-10 text-center ftco-animate">
                    <c:if test="${sessionScope.mainMessage!=null}">
                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            <strong> <c:out value="${sessionScope.userName}, "/></strong>
                            <fmt:message key="${sessionScope.mainMessage}"/>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <c:remove var="mainMessage"/>
                    </c:if>
                    <c:if test="${sessionScope.messageAccess!=null}">
                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            <fmt:message key="${sessionScope.messageAccess}"/>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <c:remove var="messageAccess"/>
                    </c:if>
                </div>
            </div>
            <div class="row slider-text justify-content-center " data-scrollax-parent="true">
                <div class="col-md-8 col-sm-12 text-center ftco-animate">
                    <br><br>
                    <span class="subheading"><fmt:message key="main.message.welcome"/></span>
                    <h1 class="mb-4"><fmt:message key="main.message.forYou"/></h1>
                    <p class="mb-4 mb-md-5"><fmt:message key="main.message.goodMood"/></p>
                    <p><a href="main?command=showDrinks"
                          class="btn btn-white btn-outline-white p-3 px-xl-4 py-xl-3"><fmt:message
                            key="main.message.viewDrinks"/></a></p>
                </div>
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