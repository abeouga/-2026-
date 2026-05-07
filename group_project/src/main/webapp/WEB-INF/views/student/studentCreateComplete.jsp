<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                登録完了
            </h2>

            <div class="mx-4">
                <p>学生登録が完了しました。</p>
                <p>正常に登録されました。</p>

                <div class="mt-3">
                    <a href="studentCreate.action">続けて登録</a>
                    <span class="mx-2">|</span>
                    <a href="student.action">一覧へ戻る</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>