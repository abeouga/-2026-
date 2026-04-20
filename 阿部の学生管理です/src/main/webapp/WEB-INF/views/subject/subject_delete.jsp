<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">科目削除</c:param>
    <c:param name="content">
<h1>科目削除確認</h1>
    <p>以下の科目を削除してもよろしいですか？</p>
    <ul>
        <li>科目コード: ${subject.cd}</li>
        <li>科目名: ${subject.name}</li>
    </ul>

    <form action="subjectDeleteExecute.action" method="post">
        <input type="hidden" name="cd" value="${subject.cd}">
        <button type="submit">削除</button>
    </form>

    <p><a href="subjectList.action">戻る</a></p>
    </c:param>
</c:import>