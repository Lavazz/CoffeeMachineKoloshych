<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>

<c:if test="${sessionScope.idUserStatus ==1}">
    <li class="nav-item"><a href="main?command=showAdminCabinet" class="nav-link">
        <fmt:message key="main.message.admin_cabinet"/></a>
    </li>
</c:if>
<c:if test="${sessionScope.idUserStatus ==2}">
    <li class="nav-item"><a href="main?command=personalCabinet" class="nav-link">
        <fmt:message key="main.message.personalCabinet"/></a>
    </li>
</c:if>