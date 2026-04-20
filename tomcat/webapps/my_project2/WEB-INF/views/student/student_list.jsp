<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">学生管理</c:param>
    <c:param name="content">
<h1>学生一覧</h1>
    <p><a href="menu.action">戻る</a></p>
    <p><a href="studentCreate.action">新規登録</a></p>

    <form action="student.action" method="get">
        入学年度: <input type="number" name="entYear" value="${fEntYear}">
        クラス: <input type="text" name="classNum" value="${fClassNum}">
        在学中フラグ: 
        <select name="isAttend">
            <option value="">すべて</option>
            <option value="true" <c:if test="${fIsAttend == 'true'}">selected</c:if>>在学中</option>
            <option value="false" <c:if test="${fIsAttend == 'false'}">selected</c:if>>退学・卒業</option>
        </select>
        <button type="submit">絞り込み</button>
    </form>

    <table border="1">
        <tr>
            <th>学生番号</th>
            <th>氏名</th>
            <th>入学年度</th>
            <th>クラス</th>
            <th>在学中</th>
            <th>操作</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.no}</td>
                <td>${student.name}</td>
                <td>${student.entYear}</td>
                <td>${student.classNum}</td>
                <td>${student.isAttend ? '○' : '×'}</td>
                <td>
                    <form action="studentUpdate.action" method="get" style="display:inline;">
                        <input type="hidden" name="no" value="${student.no}">
                        <button type="submit">変更</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:param>
</c:import>