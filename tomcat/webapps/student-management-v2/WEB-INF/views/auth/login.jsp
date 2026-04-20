<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン - 学生管理システム</title>
</head>
<body>
    <h1>学生管理システム ログイン</h1>
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
    <form action="loginExecute.action" method="post">
        <div>
            <label>ID:</label>
            <input type="text" name="id" required>
        </div>
        <div>
            <label>パスワード:</label>
            <input type="password" name="password" required>
        </div>
        <div>
            <button type="submit">ログイン</button>
        </div>
    </form>
</body>
</html>
