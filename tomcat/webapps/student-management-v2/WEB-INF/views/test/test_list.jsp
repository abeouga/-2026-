<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績参照</title>
</head>
<body>
    <h1>成績参照</h1>
    <p><a href="menu.action">戻る</a></p>

    <h2>科目・クラス毎一覧検索</h2>
    <c:if test="${not empty searchError}">
        <p style="color:red;">${searchError}</p>
    </c:if>
    <form action="testListSubjectExecute.action" method="post">
        入学年度: <input type="number" name="fEntYear" value="${fEntYear}">
        クラス: 
        <select name="fClassNum">
            <option value="0">--------</option>
            <c:forEach var="cn" items="${classNumList}">
                <option value="${cn.classNum}" <c:if test="${fClassNum == cn.classNum}">selected</c:if>>${cn.classNum}</option>
            </c:forEach>
        </select>
        科目: 
        <select name="fSubjectCd">
            <option value="0">--------</option>
            <c:forEach var="sub" items="${subjectList}">
                <option value="${sub.cd}" <c:if test="${fSubjectCd == sub.cd}">selected</c:if>>${sub.name}</option>
            </c:forEach>
        </select>
        <button type="submit">検索</button>
    </form>

    <hr>
    <h2>学生毎一覧検索</h2>
    <c:if test="${not empty studentNoError}">
        <p style="color:red;">${studentNoError}</p>
    </c:if>
    <form action="testListStudentExecute.action" method="post">
        学生番号: <input type="text" name="fStudentNo" value="${fStudentNo}">
        <button type="submit">検索</button>
    </form>
</body>
</html>
