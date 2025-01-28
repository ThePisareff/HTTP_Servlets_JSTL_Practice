<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="username">Name: <input type="text" name="name" id="username"> </label><br>
    <label>Birthday: <input type="date" name="birthday"> </label><br>
    <label>Email: <input type="text" name="email"> </label><br>
    <label>Password: <input type="password" name="password"> </label><br>
    <select name="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${fn:toLowerCase(role)}</option>
        </c:forEach>
    </select><br>
    <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" value="${gender}"> ${fn:toLowerCase(gender)}<br>
    </c:forEach>
    <button type="submit">SEND</button>
    <button type="reset">RESET</button>
</form>

<c:if test="${not empty requestScope.errors}">
    <div style="color: crimson">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
        </c:forEach>
    </div>
</c:if>

</body>
</html>
