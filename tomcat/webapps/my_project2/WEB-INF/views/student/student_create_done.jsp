<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">学生登録完了</c:param>
    <c:param name="content">
<h1>学生登録完了</h1>
    <p>以下の内容で登録しました。</p>
    <ul>
        <li>学生番号: ${student.no}</li>
        <li>氏名: ${student.name}</li>
        <li>入学年度: ${student.entYear}</li>
        <li>クラス: ${student.classNum}</li>
    </ul>

    <p><a href="studentCreate.action">戻る（続けて登録）</a></p>
    <p><a href="student.action">学生一覧</a></p>
    </c:param>
</c:import>