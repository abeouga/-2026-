<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">科目登録</c:param>
    <c:param name="content">
<h1>科目登録</h1>

    <c:if test="${not empty duplicateError}">
        <p style="color:red;">${duplicateError}</p>
    </c:if>

    <form action="subjectCreateExecute.action" method="post">
        <div>
            <label>科目コード:</label>
            <input type="text" name="cd" value="${cd}" required>
            <c:if test="${not empty cdError}"><span style="color:red;">${cdError}</span></c:if>
        </div>
        <div>
            <label>科目名:</label>
            <input type="text" name="name" value="${name}" required>
        </div>
        <div>
            <button type="submit">登録</button>
        </div>
    </form>

    <p><a href="subjectList.action">戻る</a></p>
    </c:param>
</c:import>