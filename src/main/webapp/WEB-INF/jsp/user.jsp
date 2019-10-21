<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="Servlet">
    <label>enter login</label>
    <input name="name" value="" type="text" placeholder="name" required>

    <label>enter password</label>
    <input name="password" value="" type="password" required>

    <input type="hidden" name="command" value="signIn">
    <input type="submit"  name="" value="signIn">
</form>
</body>
</html>
