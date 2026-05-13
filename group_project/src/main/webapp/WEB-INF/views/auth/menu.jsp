<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
    background:#f5f5f5;
}

/* ヘッダー */
.header{
    background:#e9edf5;
    padding:20px;
    font-size:32px;
    font-weight:bold;
}

/* 全体 */
.container-area{
    display:flex;
}

/* 左メニュー */
.sidebar{
    width:220px;
    background:white;
    min-height:100vh;
    padding:20px;
    border-right:1px solid #ccc;
}

.sidebar a{
    display:block;
    margin-bottom:15px;
    text-decoration:none;
}

/* メイン */
.main{
    flex:1;
    padding:20px;
}

/* タイトル */
.menu-title{
    background:#ddd;
    padding:10px;
    margin-bottom:20px;
    font-size:24px;
    font-weight:bold;
}

/* カード横並び */
.card-area{
    display:flex;
    gap:20px;
}

/* カード */
.menu-card{
    width:220px;
    min-height:120px;
    padding:20px;
    border-radius:8px;
    box-shadow:0 3px 10px rgba(0,0,0,0.15);
}

/* 色 */
.student{
    background:#f8d7da;
}

.score{
    background:#d1e7dd;
}

.subject{
    background:#d6d8f5;
}

/* リンク */
.menu-card a{
    display:block;
    text-decoration:none;
    font-size:20px;
    margin-bottom:10px;
}

</style>

</head>
<body>

<!-- ヘッダー -->
<div class="header">
    得点管理システム
</div>

<div class="container-area">

    <!-- 左メニュー -->
    <div class="sidebar">
        <a href="#">メニュー</a>
        <a href="StudentList.action">学生管理</a>
        <a href="TestList.action">成績参照</a>
        <a href="TestCreate.action">成績登録</a>
        <a href="SubjectList.action">科目管理</a>
    </div>

    <!-- メイン -->
    <div class="main">

        <div class="menu-title">
            メニュー
        </div>

        <div class="card-area">

            <!-- 学生管理 -->
            <div class="menu-card student">
                <a href="StudentList.action">学生管理</a>
            </div>

            <!-- 成績管理 -->
            <div class="menu-card score">
                <a href="TestList.action">成績参照</a>
                <a href="TestCreate.action">成績登録</a>
            </div>

            <!-- 科目管理 -->
            <div class="menu-card subject">
                <a href="SubjectList.action">科目管理</a>
            </div>

        </div>

    </div>

</div>

</body>
</html>