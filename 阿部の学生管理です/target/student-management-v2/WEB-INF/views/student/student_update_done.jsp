<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">学生変更完了</c:param>
    <c:param name="content">
<h1>学生変更完了</h1>
    <p>学生の情報を変更しました。</p>
    <ul>
        <li>学生番号: ${student.no}</li>
        <li>氏名: ${student.name}</li>
        <li>入学年度: ${student.entYear}</li>
        <li>クラス: ${student.classNum}</li>
        <li>在学中: ${student.isAttend ? '○' : '×'}</li>
    </ul>

    <p><a href="studentUpdate.action?no=${student.no}">戻る</a></p>
    <p><a href="student.action">学生一覧</a></p>
    </c:param>
</c:import>