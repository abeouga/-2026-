<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績一覧（学生毎）</title>
</head>
<body>
    <h1>成績一覧（学生毎）</h1>
    <p><a href="testList.action">戻る</a></p>

    <p>
        検索条件: 学生番号=${fStudentNo}
    </p>

    <c:if test="${not empty notFoundMsg}">
        <p style="color:red;">${notFoundMsg}</p>
    </c:if>

    <c:if test="${empty notFoundMsg}">
        <table border="1">
            <tr>
                <th>科目名</th>
                <th>回数</th>
                <th>点数</th>
            </tr>
            <c:forEach var="test" items="${testList}">
                <tr>
                    <td>${test.subjectName}</td>
                    <td>${test.no}</td>
                    <td>${test.point}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
