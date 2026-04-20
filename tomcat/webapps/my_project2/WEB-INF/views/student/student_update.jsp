<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">学生変更</c:param>
    <c:param name="content">
<h1>学生変更</h1>

    <form action="studentUpdateExecute.action" method="post">
        <!-- 学生番号はPKなので変更不可（hidden等で保持、表示のみ） -->
        <input type="hidden" name="no" value="${student.no}">
        <div>
            <label>学生番号:</label>
            ${student.no}
        </div>
        <div>
            <label>入学年度:</label>
            <input type="number" name="entYear" value="${student.entYear}">
        </div>
        <div>
            <label>氏名:</label>
            <input type="text" name="name" value="${student.name}">
            <c:if test="${not empty nameError}"><span style="color:red;">${nameError}</span></c:if>
        </div>
        <div>
            <label>クラス:</label>
            <input type="text" name="classNum" value="${student.classNum}">
        </div>
        <div>
            <label>在学中:</label>
            <input type="checkbox" name="isAttend" value="true" <c:if test="${student.isAttend}">checked</c:if>>
        </div>
        <div>
            <button type="submit">変更</button>
        </div>
    </form>

    <p><a href="student.action">戻る</a></p>
    </c:param>
</c:import>