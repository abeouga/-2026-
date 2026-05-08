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
                <h2 class="h4 mb-4 fw-normal py-2 px-4" style="background-color: #f0f0f0; border-bottom: 1px solid #dee2e6;">クラス管理</h2>
                <a class="btn btn-sm btn-outline-primary" href="schoolclassCreate.action">新規登録</a>
            </div>

            

            <h3 class="h5 mt-4 mb-3">1年生 クラス一覧</h3>
            <table class="table table-hover border-list">
                <tr>
                    <th>クラス</th>
                    <th></th>
                </tr>
                <c:forEach var="schoolClass" items="${list1}">
                    <tr>
                        <td>${error}${schoolClass.classNum}</td>
                        <!-- <td><a href="schoolclassUpdate.action?cd=${schoolClass.classNum}" class="btn btn-sm btn-outline-primary">変更</a></td> -->
                        <td><a href="schoolclassDelete.action?class_num=${schoolClass.classNum}" class="btn btn-sm btn-outline-danger">削除</a></td>
                    </tr>
                </c:forEach>
            </table>

            <h3 class="h5 mt-5 mb-3">2年生 クラス一覧</h3>
            <table class="table table-hover border-list">
                <tr>
                    <th>クラス</th>
                    <th></th>
                </tr>
                <c:forEach var="schoolClass" items="${list2}">
                    <tr>
                        <td>${error}${schoolClass.classNum}</td>
                        <!-- <td><a href="schoolclassUpdate.action?cd=${schoolClass.classNum}" class="btn btn-sm btn-outline-primary">変更</a></td> -->
                        <td><a href="schoolclassDelete.action?class_num=${schoolClass.classNum}" class="btn btn-sm btn-outline-danger">削除</a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </c:param>
</c:import>