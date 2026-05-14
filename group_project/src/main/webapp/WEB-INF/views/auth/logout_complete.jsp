<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">ログアウト</c:param>

    <c:param name="content">
        <section class="me-4">
            <!-- ① タイトルバー -->
            <h2 class="mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                ログアウト
            </h2>

            <div class="mx-4">
                <!-- ② メッセージバー -->
                <div class="alert alert-success text-center py-2" role="alert">
                    ログアウトしました
                </div>

                <!-- ③ ログインリンク -->
                <div class="mt-4">
                    <a href="Login.action" style="text-decoration: none;">ログイン</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>
