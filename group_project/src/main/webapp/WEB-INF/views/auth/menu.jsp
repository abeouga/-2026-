<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
    body {
        background-color: #f8f9fa;
        padding: 30px;
    }

    h2 {
        background-color: #dee2e6;
        padding: 20px;
        border-radius: 12px;
        margin-bottom: 30px;
        font-weight: bold;
    }

    p {
        margin-bottom: 20px;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0 4px 10px rgba(0,0,0,0.08);
        transition: 0.3s;
    }

    p:hover {
        transform: translateY(-3px);
        box-shadow: 0 8px 16px rgba(0,0,0,0.12);
    }

    p a {
        display: block;
        padding: 20px;
        text-decoration: none;
        font-size: 18px;
        font-weight: bold;
    }
    .logout-btn {
        position: absolute;
        top: 20px;
        right: 30px;
    }

    /* 色分け（HTMLそのまま） */
    p:nth-of-type(1) {
        background-color: #f8d7da;
    }

    p:nth-of-type(2),
    p:nth-of-type(3) {
        background-color: #d1e7dd;
    }

    p:nth-of-type(4) {
        background-color: #d6d8f5;
    }
</style>
</head>
<body>

<h2>メニュー画面</h2>
<div class="text-end mb-3">
    <a class="btn btn-warning"
       href="Logout.action"
       onclick="return confirm('ログアウトしますか？');">
       ログアウト
    </a>
</div>
<p><a href="student.action">学生管理</a></p>
<p><a href="testRegist.action">成績参照</a></p>
<p><a href="testList.action">成績登録</a></p>
<p><a href="subjectList.action">科目管理</a></p>

</body>
</html>