<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生登録</title>
</head>
<body>
    <h1>学生登録</h1>

    <c:if test="${not empty duplicateError}">
        <p style="color:red;">${duplicateError}</p>
    </c:if>

    <form action="studentCreateExecute.action" method="post">
        <div>
            <label>入学年度:</label>
            <input type="number" name="entYear" value="${entYear}">
            <c:if test="${not empty entYearError}"><span style="color:red;">${entYearError}</span></c:if>
        </div>
        <div>
            <label>学生番号:</label>
            <input type="text" name="no" value="${no}">
            <c:if test="${not empty noError}"><span style="color:red;">${noError}</span></c:if>
        </div>
        <div>
            <label>氏名:</label>
            <input type="text" name="name" value="${name}">
            <c:if test="${not empty nameError}"><span style="color:red;">${nameError}</span></c:if>
        </div>
        <div>
            <label>クラス:</label>
            <input type="text" name="classNum" value="${classNum}">
        </div>
        <div>
            <button type="submit">登録</button>
        </div>
    </form>

    <p><a href="student.action">戻る</a></p>
</body>
</html>
