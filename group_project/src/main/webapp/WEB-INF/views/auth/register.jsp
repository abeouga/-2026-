<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>新規登録</title>
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
            margin: 50px auto;
            width: 450px;
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

<div class="header">
    得点管理システム
</div>

<div class="box text-center">
    <h3>教師新規登録</h3>

    <c:if test="${not empty error}">
        <div class="alert alert-danger p-2 mt-2" role="alert">
            ${error}
        </div>
    </c:if>

    <form action="teacherRegisterExecute.action" method="post" class="text-start mt-4">
        <div class="mb-3">
            <label class="form-label">学校コード</label>
            <select name="schoolCd" class="form-select" required>
                <option value="">選択してください</option>
                <option value="001">001 (テスト校)</option>
                <option value="002">002 (東京校)</option>
                <option value="003">003 (大阪校)</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">ユーザーID</label>
            <input type="text" name="id" class="form-control" placeholder="ユーザーID" required>
        </div>

        <div class="mb-3">
            <label class="form-label">パスワード</label>
            <input type="password" name="password" class="form-control" placeholder="パスワード" id="password" required>
            <div class="form-check mt-1">
                <input class="form-check-input" type="checkbox" id="showPassword" onclick="togglePassword()">
                <label class="form-check-label small" for="showPassword">パスワードを表示</label>
            </div>
        </div>

        <div class="mb-4">
            <label class="form-label">氏名</label>
            <input type="text" name="name" class="form-control" placeholder="氏名" required>
        </div>

        <button type="submit" class="btn btn-success w-100">登録</button>

        <div class="mt-3 text-center">
            <a href="Login.action" style="text-decoration: none;">ログイン画面に戻る</a>
        </div>
    </form>
</div>

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
