<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <c:import url="/WEB-INF/views/common/base.jsp">
            <c:param name="title">ログイン - 学生管理システム</c:param>
            <c:param name="content">
                <h1>学生管理システム ログイン</h1>
                <!--認証ロジックのLoginExecuteAction.javaの処理でログインがはじかれ、stateにエラーメッセージがあれば表示する。-->
                <c:if test="${not empty errorMessage}">
                    <p style="color:red;">${errorMessage}</p>
                </c:if>
                <form action="LoginExecute.action" method="post">
                    <div>
                        <label>ID:</label>
                        <input type="text" name="id" required>
                    </div>
                    <div>
                        <label>パスワード:</label>
                        <input type="password" name="password" required>
                    </div>
                    <div>
                        <button type="submit">ログイン</button>
                    </div>
                </form>
            </c:param>
        </c:import>