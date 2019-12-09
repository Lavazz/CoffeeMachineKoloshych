<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://trjava.by/kaloshych" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="ru">
<head>

    <title><fmt:message key="title.orderHistory"/></title>
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

<section class="ftco-section ftco-cart">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-md-10 ftco-animate align-content-md-center">
            <div class="cart-list">
                <table class="table">
                    <thead class="thead-primary">
                    <tr class="text-center">
                        <th><fmt:message key="orderHistory.table.number"/></th>
                        <th><fmt:message key="edit.option.drink"/></th>
                        <th><fmt:message key="edit.option.additional_ingredient"/></th>
                        <th><fmt:message key="admin.table.portion"/></th>
                        <th><fmt:message key="edit.label.date"/></th>
                        <th><fmt:message key="cart.total"/></th>
                    </tr>
                    </thead>
                    <c:set var="countPage" scope="request" value="${0}"/>
                    <c:forEach varStatus="count" items="${sessionScope.orders}" var="order">
                    <c:forEach items="${sessionScope.carts}" var="cart">
                    <c:if test="${order.cartUser.idCartUser==cart.cartUser.idCartUser}">
                    <c:if test="${countPage >= sessionScope.ordersFirstRow && countPage < sessionScope.ordersLastRow}">
                    <tbody>
                    <tr class="text-center">
                        <th>${count.count}</th>
                        <td class="product-name">
                            <h3> <ctg:outDrink drink="${cart.drink}"/></h3>
<%--                            <h3>${cart.drink.nameComponent}</h3>--%>
                        </td>

                        <td class="product-name">
                            <c:forEach items="${sessionScope.cartAdditionalIngredients}" var="cartAdditionalIngredient">
                                <c:if test="${cartAdditionalIngredient.cart.idCart==cart.idCart}">
                                <h3> <ctg:outAdditionalIngredient additionalIngredient="${cartAdditionalIngredient.additionalIngredient}"/></h3>
<%--                                    <h3>${cartAdditionalIngredient.additionalIngredient.nameComponent}</h3>--%>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td class="product-name"><H5>${cart.portion}</H5></td>
                        <td class="date">${order.dateOrder}</td>
                        <td class="total">${order.totalCost}</td>
                    </tr>
                    </c:if>
                    <c:set var="countPage" value="${countPage + 1}"></c:set></c:if>
                    </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

<div class="row">
    <div class="col-3"></div>
    <div class="col-2">
        <form action="main" method="post">
            <c:if test="${sessionScope.ordersFirstRow > 0}">
                <input type="hidden" name="firstRow" value="ordersFirstRow">
                <input type="hidden" name="lastRow" value="ordersLastRow">
                <input type="hidden" name="numberOfPages" value="ordersNumberOfPages">
                <input type="hidden" name="currentPageNumber" value="ordersCurrentPageNumber">
                <input type="hidden" name="currentPageURL" value="main?command=goToOrderHistoryPage">
                <input type="hidden" name="command" value="showPreviousPage">
                <input type="submit" class="submit" value="<fmt:message key="order.button.PrevPage" />">
            </c:if>
        </form>
    </div>
        <div class="col-2">
        <h4>
            <fmt:message key="orderHistory.message.page"/>
            <c:out value="${sessionScope.ordersCurrentPageNumber}"/>
            <fmt:message key="orderHistory.message.from"/>
            <c:out value=" ${sessionScope.ordersNumberOfPages}"/>
        </h4>
        </div>
    <div class="col-2">
        <form action="main" method="post">
            <c:if test="${countPage > sessionScope.ordersLastRow}">
                <input type="hidden" name="firstRow" value="ordersFirstRow">
                <input type="hidden" name="lastRow" value="ordersLastRow">
                <input type="hidden" name="numberOfPages" value="ordersNumberOfPages">
                <input type="hidden" name="currentPageNumber" value="ordersCurrentPageNumber">
                <input type="hidden" name="currentPageURL" value="main?command=goToOrderHistoryPage">
                <input type="hidden" name="command" value="showNextPage">
                <input type="submit" class="submit" value="<fmt:message key="order.button.NextPage" />">
            </c:if>
        </form>
    </div>
</div>
</section>


<c:import url="/WEB-INF/jsp/form/footer.jsp"/>

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