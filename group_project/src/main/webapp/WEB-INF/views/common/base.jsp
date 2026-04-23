<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body{
    background:#f8f9fa;
}

.header{
    background:#dfeaf6;
    padding:15px 25px;
    margin-bottom:15px;
}

.sidebar{
    min-height:600px;
    border-right:1px solid #ddd;
}

.sidebar a{
    display:block;
    padding:4px 0;
    text-decoration:none;
}

.sidebar a:hover{
    color:#0d6efd;
    padding-left:5px;
}

.footer{
    background:#e9ecef;
    text-align:center;
    font-size:12px;
    color:#666;
    padding:10px;
    margin-top:30px;
}
</style>

</head>
<body>

<div class="container mt-3">

    <!-- ヘッダー -->
    <div class="header d-flex justify-content-between align-items-center">
        <h2 class="mb-0 fw-bold">得点管理システム</h2>

        <c:if test="${not empty user}">
            <div>
                ${user.name} 様　
                <a href="Logout.action">ログアウト</a>
            </div>
        </c:if>
    </div>

    <div class="row">

        <!-- 左メニュー -->
        <c:if test="${not empty user}">
            <div class="col-2 sidebar">

                <a href="Menu.action">メニュー</a>

                <div class="mt-3 fw-bold">
                    学生管理
                </div>
                <a href="StudentList.action">学生一覧</a>

                <div class="mt-3 fw-bold">
                    成績管理
                </div>
                <a href="TestCreate.action">成績登録</a>
                <a href="TestList.action">成績参照</a>

                <div class="mt-3 fw-bold">
                    科目管理
                </div>
                <a href="SubjectList.action">科目一覧</a>

            </div>

            <!-- 右コンテンツ -->
            <div class="col-10">
                <c:out value="${param.content}" escapeXml="false"/>
            </div>
        </c:if>

        <!-- ログイン画面など -->
        <c:if test="${empty user}">
            <div class="col-12">
                <c:out value="${param.content}" escapeXml="false"/>
            </div>
        </c:if>

    </div>

    <!-- フッター -->
    <div class="footer">
        © 2023 TIC<br>
        大原学園
    </div>

</div>

</body>
</html>