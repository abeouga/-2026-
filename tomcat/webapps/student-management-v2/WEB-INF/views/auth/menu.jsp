<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <c:import url="/WEB-INF/views/common/base.jsp">
            <c:param name="title">メインメニュー</c:param>
            <c:param name="content">
                <h1>メインメニュー</h1>
                <p>ようこそ、${sessionScope.user.name} 先生</p>

                <ul>
                    <li><a href="student.action">学生管理</a></li>
                    <li><a href="testRegist.action">成績登録</a></li>
                    <li><a href="testList.action">成績参照</a></li>
                    <li><a href="subjectList.action">科目管理</a></li>
                </ul>

                <p><a href="logout.action" onclick="return confirm('ログアウトしますか？');">ログアウト</a></p>
            </c:param>
        </c:import>