<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>

            <div class="mx-4">
                <div class="alert alert-success py-2" role="alert">
                    変更が完了しました
                </div>

                <div class="mt-4 d-flex gap-5">
                    <a href="student.action">学生一覧</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>
