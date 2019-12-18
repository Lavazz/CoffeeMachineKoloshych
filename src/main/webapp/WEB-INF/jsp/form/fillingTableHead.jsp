<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<thead>
<tr>
    <th><fmt:message key="admin.table.id_component"/></th>
    <th><fmt:message key="admin.table.name_component"/></th>
    <th><fmt:message key="admin.table.portion"/></th>
</tr>
</thead>
