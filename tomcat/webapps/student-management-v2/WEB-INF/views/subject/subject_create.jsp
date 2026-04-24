<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <div class="container-fluid">
            <div class="row">
                <main class="col-md-10 p-4">
                    <section>
                        <h2 class="h4 mb-4 fw-normal py-2 px-4" style="background-color: #f0f0f0; border-bottom: 1px solid #dee2e6;">科目情報登録</h2>

                        <form action="subjectCreateExecute.action" method="post" class="mx-4">

                            <div class="mb-3 col-md-4">
                                <label class="form-label" for="student-no-input">科目コード</label>
                                <br>
                                <input class="form-input form-control" type="text" id="student-no-input" name="cd" value="${cd}" placeholder="科目コードを入力してください" required>
                            <div class="text-danger small">${errors.get("cd")}</div> </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label" for="student-name-input">科目名</label>
                                <br>
                                <input class="form-input form-control" type="text" id="student-name-input" name="name" value="${name}" placeholder="科目名を入力してください" required>
                            </div>

                            <div class="mt-4">
                                <button type="submit" class="btn btn-secondary px-5">登録</button>
                            </div>
                        </form>

                        <div class="mt-4 ms-4">
                            <a href="subjectList.action" class="text-decoration-none small">戻る</a>
                        </div>
                    </section>
                </main>
            </div>
        </div>
    </c:param>
</c:import>