<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="http://trjava.by/kaloshych" %>

<fmt:setLocale value="${sessionScope.locale}"/>
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

<c:import url="/WEB-INF/jsp/form/naw.jsp"/>

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
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong><fmt:message key="drinks.guest.recommendation"/></strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${sessionScope.idUser ==1}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong><fmt:message key="drinks.admin.recommendation"/></strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
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
                        <img src="${drink.picturePath}" class="img-fluid" alt="Drink"></a>
                </div>
                <div class="col-lg-6 product-details pl-md-5 ftco-animate">
                    <br>
                    <h3><ctg:outDrink drink="${drink}"/></h3>
                        <%--                    ${drink.nameComponent}--%>
                    <p class="price"><span>${drink.price}</span></p>
                    <p><h5><ctg:outDescription drink="${drink}"/></h5>
                </div>
                <br>
                </c:forEach>
                <br>
                <div class="col-md-4"></div>
                <div class="col-md-6">
                    <c:forEach items="${requestScope.additionalIngredients}" var="additionalIngredient">
                        <h5><p><label>
                            <input type="checkbox" name="idAdditionalIngredient"
                                   value="${additionalIngredient.idComponent}"/>
                        </label><ctg:outAdditionalIngredient
                                additionalIngredient="${additionalIngredient}"/></h5>

                    </c:forEach>
                    <div class="w-100"></div>

                    <label for="portion">
                        <input type="text" id="portion" name="portion" value="" placeholder="portion: 1-99" required>
                    </label>

                    <c:if test="${sessionScope.idUserStatus==2}">
                        <input type="hidden" name="command" value="addToCart">
                        <input type="submit" name="submit" value="<fmt:message key='drinks.button.addToCart'/>  ">
                    </c:if>
                </div>
        </form>
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