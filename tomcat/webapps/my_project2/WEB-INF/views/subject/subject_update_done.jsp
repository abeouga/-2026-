<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">科目変更完了</c:param>
    <c:param name="content">
<h1>科目変更完了</h1>
    <p>科目の情報を変更しました。</p>
    <ul>
        <li>科目コード: ${subject.cd}</li>
        <li>科目名: ${subject.name}</li>
    </ul>

    <p><a href="subjectList.action">科目一覧</a></p>
    </c:param>
</c:import>