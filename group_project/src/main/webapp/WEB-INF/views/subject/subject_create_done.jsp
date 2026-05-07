<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
<style>
.success-message {
    width: 675px;
    margin: auto;
    background: #c0f7c5;
    color: #171817;
    padding: 10px;
    border-radius: 0.5px;
    margin-bottom: 20px;
    text-align: center;
}
</style>
        
        <div class="container-fluid">
            <div class="row">
                <main class="col-md-10 p-4">
                    <section>
                        <h2 class="h4 mb-4 fw-normal py-2 px-4" style="background-color: #f0f0f0; border-bottom: 1px solid #dee2e6;">科目情報登録</h2>

                        <div class="success-message">
                            登録が完了しました
                        </div>

                        
                        <div class="mt-4 ms-4">
                            <a href="subjectList.action" class="btn btn-sm btn-outline-secondary">科目一覧へ</a>
                        </div>
                    </section>
                </main>
            </div>
        </div>
    </c:param>
</c:import>