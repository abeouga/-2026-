<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">科目変更</c:param>
    <c:param name="content">
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
    </c:param>
</c:import>