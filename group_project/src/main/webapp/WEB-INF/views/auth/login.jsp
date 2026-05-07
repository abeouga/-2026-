<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>ログイン</title>

    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/icon.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f2f2f2;
        }
        .header {
            background-color: #cfd8dc;
            padding: 15px;
            font-size: 24px;
            font-weight: bold;
        }
        .box {
            margin: 80px auto;
            width: 400px;
            padding: 30px;
            background: white;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .footer {
            margin-top: 100px;
            padding: 20px;
            background-color: #ddd;
            text-align: center;
        }
    </style>
</head>

<body>

<!-- ヘッダー -->
<div class="header">
    得点管理システム
</div>

<div class="box text-center">

    <c:if test="${not empty message}">

    <!-- タイトルバー -->
    <div style="
        background-color:#e0e0e0;
        padding:10px;
        font-weight:bold;
        text-align:left;
        border:1px solid #ccc;
    ">
        ログアウト
    </div>

    <!-- メッセージ -->
    <div style="
        background-color:#c8e6c9;
        padding:10px;
        margin-top:5px;
        border:1px solid #a5d6a7;
        color:#2e7d32;
        text-align:left;
    ">
        ${message}
    </div>

    <!-- リンク -->
    <div style="margin-top:10px; text-align:left;">
        <a href="Login.action" style="
            color:#1976d2;
            font-size:14px;
            text-decoration:none;
        ">
            ログイン
        </a>
    </div>


    </c:if>

    <!-- 通常ログイン画面 -->
    <c:if test="${empty message}">
        <h3>ログイン</h3>

        <form action="LoginExecute.action" method="post">
            <div class="mb-3">
                <input type="text" name="id" class="form-control" placeholder="ユーザーID">
            </div>

            <div class="mb-3">
                <input type="password" name="password" class="form-control" placeholder="パスワード" id="password">
            </div>

            <div class="mb-3">
                <input type="checkbox" onclick="togglePassword()"> パスワードを表示
            </div>

            <button type="submit" class="btn btn-primary w-100">ログイン</button>

            <div class="mt-3 text-center">
                <a href="teacherRegister.action" style="text-decoration: none;">新規登録はこちら</a>
            </div>
        </form>
    </c:if>

</div>

<!-- フッター -->
<div class="footer">
    © 2023 TIC<br>
    大原学園
</div>

<script>
function togglePassword() {
    var x = document.getElementById("password");
    x.type = (x.type === "password") ? "text" : "password";
}
</script>

</body>
</html>