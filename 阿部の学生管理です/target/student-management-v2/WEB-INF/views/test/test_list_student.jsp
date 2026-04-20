<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">成績一覧（学生毎）</c:param>
    <c:param name="content">
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
    </c:param>
</c:import>