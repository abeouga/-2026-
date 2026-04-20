<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目削除</title>
</head>
<body>
    <h1>科目削除確認</h1>
    <p>以下の科目を削除してもよろしいですか？</p>
    <ul>
        <li>科目コード: ${subject.cd}</li>
        <li>科目名: ${subject.name}</li>
    </ul>

    <form action="subjectDeleteExecute.action" method="post">
        <input type="hidden" name="cd" value="${subject.cd}">
        <button type="submit">削除</button>
    </form>

    <p><a href="subjectList.action">戻る</a></p>
</body>
</html>
