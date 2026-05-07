<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<c:import url="../common/base.jsp">

    <c:param name="title">成績参照検索</c:param>

    <c:param name="content">

        <h2 class="mb-4">成績参照</h2>

        <div class="d-grid gap-3 col-6 mx-auto">

            <a href="testListSubjectExecute.action" class="btn btn-primary">
                 📘 科目・クラス別で参照
            </a>

            <a href="testListStudentExecute.action" class="btn btn-secondary">
                 👤 学生別で参照
            </a>

        </div>

    </c:param>

</c:import>