
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>

<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/icon.png">
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
            <div class="d-flex align-items-center">
                ${user.name} 様
            </div>
        </c:if>
    </div>

    <div class="row">
        <div class="col-12">

            <!-- ログアウト済み画面（これが今回の②③） -->
            <c:if test="${empty user}">
                <section>

                    <!-- ① タイトル -->
                    <h2 class="mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                        ログアウト
                    </h2>

                    <!-- ② ログアウトメッセージ -->
                    <div class="alert alert-success text-center">
                        ログアウトしました
                    </div>

                    <!-- ③ ログインリンク -->
                    <div class="mt-3">
                        <a href="Login.action">ログイン</a>
                    </div>

                </section>
            </c:if>

            <!-- まだログイン中なら確認画面 -->
            <c:if test="${not empty user}">
                <section>
                    <h2 class="mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                        ログアウト確認
                    </h2>

                    <div class="card shadow-sm mx-auto" style="max-width: 500px; margin-top: 50px;">
                        <div class="card-body text-center py-5">
                            <h5 class="card-title mb-4">ログアウトしますか？</h5>
                            <div class="d-flex justify-content-center gap-3">
                                <a href="Menu.action" class="btn btn-outline-secondary px-4">戻る</a>
                                <a href="Logout.action" class="btn btn-danger px-4">ログアウト</a>
                            </div>
                        </div>
                    </div>
                </section>
            </c:if>

        </div>
    </div>

    <div class="footer">
        © 2023 TIC<br>
        大原学園
    </div>

</div>

</body>
</html>
