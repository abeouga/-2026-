<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">科目登録完了</c:param>
    <c:param name="content">
<h1>科目登録完了</h1>
    <p>以下の科目を登録しました。</p>
    <ul>
        <li>科目コード: ${subject.cd}</li>
        <li>科目名: ${subject.name}</li>
    </ul>

    <p><a href="subjectCreate.action">戻る（続けて登録）</a></p>
    <p><a href="subjectList.action">科目一覧</a></p>
    </c:param>
</c:import>