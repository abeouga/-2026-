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
                        <h2 class="h4 mb-4 fw-normal py-2 px-4" style="background-color: #f0f0f0; border-bottom: 1px solid #dee2e6;">科目情報削除</h2>

                        <form action="subjectDeleteExecute.action" method="post" class="mx-4">

                            <p>「${subject.name}(${subject.cd})」を削除してもよろしいですか</p>

                            <input type="hidden" name="cd" value="${subject.cd}">

                            <div class="mt-4">
                                <button type="submit" class="btn btn-secondary px-5">削除</button>
                            </div>
                        </form>

        
                            <a href="menu.action" class="text-decoration-none small">戻る</a>
                        </div>
                    </section>
                </main>
            </div>
        </div>
    </c:param>
</c:import>