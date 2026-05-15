<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <c:import url="/WEB-INF/views/common/base.jsp">
            <c:param name="title">エラー</c:param>
            <c:param name="content">
                <h1>エラーが発生しました</h1>
                <p>${error}</p>
                <p><a href="menu.action">メニューに戻る</a></p>
            </c:param>
        </c:import>