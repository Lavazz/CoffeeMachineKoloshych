<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>

<c:if test="${sessionScope.idUser != null }">
<li class="nav-item"><a href="main?command=logOut" class="nav-link">
    <fmt:message key="locale.drinks.logout"/></a></li>
</c:if>
<li class="nav-item cart"><a href="main?command=showCart" class="nav-link"><span
        class="icon icon-shopping_cart">
                </span><span class="bag d-flex justify-content-center align-items-center"><small>1</small></span></a>
</li>
