<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績一覧（科目・クラス毎）</title>
</head>
<body>
    <h1>成績一覧（科目・クラス毎）</h1>
    <p><a href="testList.action">戻る</a></p>

    <p>
        検索条件: 入学年度=${fEntYear}, クラス=${fClassNum}, 科目コード=${fSubjectCd}
    </p>

    <c:if test="${not empty notFoundMsg}">
        <p style="color:red;">${notFoundMsg}</p>
    </c:if>

    <c:if test="${empty notFoundMsg}">
        <table border="1">
            <tr>
                <th>学生番号</th>
                <th>氏名</th>
                <th>点数</th>
            </tr>
            <c:forEach var="test" items="${testList}">
                <tr>
                    <td>${test.studentNo}</td>
                    <td>${test.studentName}</td>
                    <td>${test.point}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
