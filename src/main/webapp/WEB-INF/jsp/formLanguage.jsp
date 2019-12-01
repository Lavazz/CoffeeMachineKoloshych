<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale"/>

            <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form method="post" action="main">
                    <input type="hidden" name="command" value="changeLocale">
                    <input type="submit" name="locale" value="ru" class="btn btn-white btn-outline-white">

                </form>
            </li>
            <li class="nav-item">
                <form method="post" action="main">
                    <input type="hidden" name="command" value="changeLocale">
                    <input type="submit" name="locale" value="en" class="btn btn-white btn-outline-white">
                </form>
            </li>
            </ul>


