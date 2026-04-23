<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.title}</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .sidebar {
            width: 250px;
            min-height: 100vh;
        }
    </style>
</head>

<body>

<div class="d-flex">

    <!-- サイドバー -->
    <div class="sidebar bg-dark text-white p-3 d-flex flex-column">
        <h4 class="mb-4">メニュー</h4>

        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/menu.action">メニュー</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/student.action">学生管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/testRegist.action">成績登録</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/testList.action">成績参照</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/subjectList.action">科目管理</a>
            </li>
        </ul>

        <div class="mt-auto">
            <hr class="text-white">
            <a class="nav-link text-warning"
               href="logout.action"
               onclick="return confirm('ログアウトしますか？');">
                ログアウト
            </a>
        </div>
    </div>

    <!-- メイン -->
    <div class="flex-grow-1">

        <!-- ヘッダー -->
        <div class="d-flex justify-content-between align-items-center bg-light border-bottom p-3">
            <h5 class="mb-0">${param.title}</h5>

            <div>
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

        <!-- コンテンツ -->
        <div class="p-4">
            ${param.content}
        </div>

    </div>
</div>

</body>
</html>