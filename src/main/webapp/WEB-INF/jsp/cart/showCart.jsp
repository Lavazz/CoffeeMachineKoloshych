<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://trjava.by/kaloshych" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title><fmt:message key="title.cart"/></title>
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

<c:import url="/WEB-INF/jsp/form/naw.jsp"/>

<section class="ftco-section ftco-cart">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 ftco-animate">
                <c:if test="${sessionScope.messageCart!=null}">
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong><fmt:message key="${sessionScope.messageCart}"/></strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <c:remove var="messageCart"/>
                </c:if>
                <c:choose>
                <c:when test="${not empty requestScope.carts}">
                <div class="cart-list">
                    <table class="table">
                        <thead class="thead-primary">
                        <tr class="text-center">
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                            <th><fmt:message key="edit.option.drink"/></th>
                            <th><fmt:message key="edit.option.additional_ingredient"/></th>
                            <th><fmt:message key="edit.label.component.price"/></th>
                            <th><fmt:message key="admin.table.portion"/></th>
                            <th><fmt:message key="cart.total"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.carts}" var="cart">
                            <tr class="text-center">
                                <td class="product-remove">
                                    <a href="main?command=deleteCartFromOrder&idCart=${cart.idCart}">
                                        <span class="icon-close"></span></a></td>

                                <td class="image-prod">
                                    <div class="img" style="background-image:url(${cart.drink.picturePath});"></div>
                                </td>

                                <td class="product-name">
                                    <h3><ctg:outDrink drink="${cart.drink}"/></h3>
                                        <%--                                    <h3>${cart.drink.nameComponent}</h3>--%>
                                </td>

                                <td class="product-name">
                                    <c:forEach items="${requestScope.cartAdditionalIngredients}"
                                               var="cartAdditionalIngredient">
                                        <c:if test="${cartAdditionalIngredient.cart.idCart==cart.idCart}">
                                            <h3><ctg:outAdditionalIngredient
                                                    additionalIngredient="${cartAdditionalIngredient.additionalIngredient}"/></h3>
                                            <%--                                            <h3>${cartAdditionalIngredient.additionalIngredient.nameComponent}</h3>--%>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td class="price"><h5>${cart.drink.price}</h5></td>
                                <td class="portion">
                                    <div class="input-group mb-3">
                                        <a href="main?command=changePortion&sign=minus&idCart=${cart.idCart}">
                                            <h2><fmt:message key='cart.minus'/></h2></a>
                                        &ensp; <h4> ${cart.portion}</h4> &ensp;
                                        <a href="main?command=changePortion&sign=plus&idCart=${cart.idCart}">
                                            <h2><fmt:message key='cart.plus'/></h2></a>
                                    </div>
                                </td>
                                <td class="total"><h5>${cart.drink.price*cart.portion}</h5></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3><fmt:message key="cart.totals"/></h3>
                    <hr>
                    <p class="d-flex total-price">
                        <span><fmt:message key="cart.total"/></span>
                        <span>${requestScope.totalCost}</span>
                    </p>
                </div>
                <p class="text-center"><a href="main?command=addOrder" class="btn btn-primary py-3 px-4"><fmt:message
                        key="cart.make_order"/></a></p>
                <c:if test="${requestScope.carts!=null}">
                    <p class="text-center"><a href="main?command=cancelOrder"
                                              class="btn btn-primary py-3 px-4"><fmt:message
                            key="cart.cancel_order"/></a></p>
                </c:if>
            </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong><fmt:message key="cart.empty"/></strong>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:otherwise>
            </c:choose>
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