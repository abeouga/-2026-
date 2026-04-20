<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">科目管理</c:param>
    <c:param name="content">
<h1>科目一覧</h1>
    <p><a href="menu.action">戻る</a></p>
    <p><a href="subjectCreate.action">新規登録</a></p>

    <table border="1">
        <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th>操作</th>
        </tr>
        <c:forEach var="subject" items="${subjects}">
            <tr>
                <td>${subject.cd}</td>
                <td>${subject.name}</td>
                <td>
                    <form action="subjectUpdate.action" method="get" style="display:inline;">
                        <input type="hidden" name="cd" value="${subject.cd}">
                        <button type="submit">変更</button>
                    </form>
                    <form action="subjectDelete.action" method="get" style="display:inline;">
                        <input type="hidden" name="cd" value="${subject.cd}">
                        <button type="submit">削除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:param>
</c:import>