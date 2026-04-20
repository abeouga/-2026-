<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="sidebar">
        <h2>メニュー</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/student.action">学生管理</a></li>
            <li><a href="${pageContext.request.contextPath}/testRegist.action">成績登録</a></li>
            <li><a href="${pageContext.request.contextPath}/testList.action">成績参照</a></li>
            <li><a href="${pageContext.request.contextPath}/subjectList.action">科目管理</a></li>
        </ul>
        <div style="flex:1;"></div>
        <hr>
        <ul>
            <li><a href="${pageContext.request.contextPath}/logout.action">ログアウト</a></li>
        </ul>
    </div>
    
    <div class="main-content">
        <div class="header">
            <div class="title">${param.title}</div>
            <div class="user-info">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        ようこそ、${sessionScope.user.name} 先生
                    </c:when>
                    <c:otherwise>
                        ゲスト
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
        <div class="content">
            ${param.content}
        </div>
    </div>
</body>
</html>
