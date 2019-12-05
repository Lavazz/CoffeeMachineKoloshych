<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>

<a class="navbar-brand" href="main?command=goToMainPage"><fmt:message key="main.brand.coffee"/>
    <small><fmt:message key="main.brand.house"/></small></a>


