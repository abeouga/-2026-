<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生情報変更</title>

<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/icon.png">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body{
    background:#f8f9fa;
}
.main-box{
    width:700px;
    margin:40px auto;
    background:white;
    padding:30px;
    border:1px solid #ddd;
}
.title-box{
    background:#f1f3f5;
    padding:10px 15px;
    margin-bottom:25px;
}
</style>

</head>
<body>

<div class="main-box">

    <!-- ① -->
    <div class="title-box">
        <h4 class="mb-0">学生情報変更</h4>
    </div>

    <form action="studentUpdateExecute.action" method="post">

        <!-- ②③ -->
        <div class="row mb-3">
            <label class="col-sm-3 col-form-label">入学年度</label>
            <div class="col-sm-9 pt-2">
                ${student.entYear}
                <input type="hidden" name="entYear" value="${student.entYear}">
            </div>
        </div>

        <!-- ④⑤ -->
        <div class="row mb-3">
            <label class="col-sm-3 col-form-label">学生番号</label>
            <div class="col-sm-9 pt-2">
                ${student.no}
                <input type="hidden" name="no" value="${student.no}">
            </div>
        </div>

        <!-- ⑥⑦ -->
        <div class="row mb-3">
            <label class="col-sm-3 col-form-label">氏名</label>
            <div class="col-sm-9">
                <input type="text" name="name"
                    value="${student.name}"
                    class="form-control" required>
            </div>
        </div>

        <!-- ⑧⑨ -->
        <div class="row mb-3">
            <label class="col-sm-3 col-form-label">クラス</label>
            <div class="col-sm-9">
                <select name="classNum" class="form-select">
                    <c:forEach var="num" items="${class_num_set}">
                        <option value="${num}"
                            <c:if test="${num == student.classNum}">
                                selected
                            </c:if>>
                            ${num}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- ⑩⑪ -->
        <div class="row mb-4">
            <label class="col-sm-3 col-form-label">在学中</label>
            <div class="col-sm-9 pt-2">
                <input type="checkbox" name="isAttend" value="true"
                    <c:if test="${student.isAttend}">
                        checked
                    </c:if>>
            </div>
        </div>

        <!-- ⑫ -->
        <div class="mb-3">
            <button type="submit" class="btn btn-primary">
                変更
            </button>
        </div>

        <!-- ⑬ -->
        <a href="student.action">戻る</a>

    </form>

</div>

</body>
</html>