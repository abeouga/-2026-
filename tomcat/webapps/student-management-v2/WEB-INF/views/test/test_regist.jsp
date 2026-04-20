<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績登録</title>
</head>
<body>
    <h1>成績登録</h1>
    <p><a href="menu.action">戻る</a></p>

    <c:if test="${not empty searchError}">
        <p style="color:red;">${searchError}</p>
    </c:if>
    <c:if test="${not empty inputError}">
        <p style="color:red;">${inputError}</p>
    </c:if>

    <form action="testRegist.action" method="get">
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
        回数: <input type="number" name="fNo" value="${fNo}">
        <button type="submit" name="search" value="true">検索</button>
    </form>

    <c:if test="${not empty testList}">
        <hr>
        <form action="testRegistExecute.action" method="post">
            <input type="hidden" name="subjectCd" value="${fSubjectCd}">
            <input type="hidden" name="no" value="${fNo}">
            <input type="hidden" name="classNum" value="${fClassNum}">

            <table border="1">
                <tr>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>点数</th>
                </tr>
                <c:forEach var="test" items="${testList}">
                    <tr>
                        <td>
                            ${test.studentNo}
                            <input type="hidden" name="studentNo_array" value="${test.studentNo}">
                        </td>
                        <td>${test.studentName}</td>
                        <td>
                            <input type="number" name="point_${test.studentNo}" value="${test.point}">
                            <%-- Request scope error messages follow a pattern pointError_studentNo --%>
                            <c:set var="errKey" value="pointError_${test.studentNo}" />
                            <c:if test="${not empty requestScope[errKey]}">
                                <span style="color:red;">${requestScope[errKey]}</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <button type="submit">登録して終了</button>
        </form>
    </c:if>
</body>
</html>
