<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
    <form action="/login" method="post">
        <label>Email:
            <input type="text" name="email" value="${param.email}">
        </label><br>
        <label>Password:
            <input type="password" name="password">
        </label><br>
        <button type="submit">Login</button>
        <a href="/registration">
            <button type="button">Registration</button>
        </a>
        <c:if test="${param.error != null}">
            <div style="color: crimson">
                <span>Email or password is not correct</span>
            </div>
        </c:if>

    </form>
</body>
</html>
