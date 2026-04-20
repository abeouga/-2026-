<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目変更</title>
</head>
<body>
    <h1>科目変更</h1>

    <form action="subjectUpdateExecute.action" method="post">
        <input type="hidden" name="cd" value="${subject.cd}">
        <div>
            <label>科目コード:</label>
            ${subject.cd}
        </div>
        <div>
            <label>科目名:</label>
            <input type="text" name="name" value="${subject.name}">
            <c:if test="${not empty nameError}"><span style="color:red;">${nameError}</span></c:if>
        </div>
        <div>
            <button type="submit">変更</button>
        </div>
    </form>

    <p><a href="subjectList.action">戻る</a></p>
</body>
</html>
