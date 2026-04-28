<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts">
        <style>
            .create {
                margin-left: 500px;
            }
            .table-hover {
                margin: auto;
            }
            .border-list {
                border: 1px solid #ccc;
            }
        </style>
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <div>
                <h2 class="h4 mb-4 fw-normal py-2 px-4" style="background-color: #f0f0f0; border-bottom: 1px solid #dee2e6;">科目管理</h2>
                <a class="create" href="subjectCreate.action">新規登録</a>
            </div>

            

            <table class="table table-hover">
                <tr>
                    <th>科目コード</th>
                    <th>科目名</th>
                    <th></th>
                    <th></th>
                </tr>

                <c:forEach var="subjects" items="${list}">
                    <div class="border-list">

                        <tr>
                            <td>${error}${subjects.cd}</td>
                            <td>${subjects.name}</td>
                            <td><a href="subjectUpdate.action?cd=${subjects.cd}">変更</a></td>
                            <td><a href="subjectDelete.action?cd=${subjects.cd}">削除</a></td>
                        </tr>
                    </div>
                </c:forEach>
            </table>
        </section>
    </c:param>
</c:import>