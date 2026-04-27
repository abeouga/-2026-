<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <c:import url="/WEB-INF/views/common/base.jsp">

            <c:param name="title">
                得点管理システム
            </c:param>

            <c:param name="scripts"></c:param>

            <c:param name="content">

                <section class="me-4">

                    <h2 class="mb-3 fw-normal
                bg-secondary bg-opacity-10
                py-2 px-4">
                        学生管理
                    </h2>

                    <div class="my-2 text-end px-4">
                        <a href="studentCreate.action">
                            新規登録
                        </a>
                    </div>

                    <form method="get">

                        <div class="row border mx-3 mb-3 py-2
                    align-items-center rounded">

                            <!-- 入学年度 -->
                            <div class="col-4">
                                <label class="form-label">
                                    入学年度
                                </label>

                                <select class="form-select" name="f1">

                                    <option value="">
                                        ------
                                    </option>

                                    <c:forEach var="year" items="${ent_year_set}">

                                        <option value="${year}" <c:if test="${year == f1}">
                                            selected
                                            </c:if>>

                                            ${year}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- クラス -->
                            <div class="col-4">
                                <label class="form-label">
                                    クラス
                                </label>

                                <select class="form-select" name="f2">

                                    <option value="">
                                        ------
                                    </option>

                                    <c:forEach var="num" items="${class_num_set}">

                                        <option value="${num}" <c:if test="${num == f2}">
                                            selected
                                            </c:if>>

                                            ${num}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- 在学 -->
                            <div class="col-2 text-center">
                                <label class="form-check-label">
                                    在学中
                                </label>

                                <input class="form-check-input" type="checkbox" name="f3" value="t" <c:if
                                    test="${not empty f3}">
                                checked
                                </c:if> />
                            </div>

                            <!-- ボタン -->
                            <div class="col-2 text-center">
                                <button class="btn btn-secondary">
                                    絞込み
                                </button>
                            </div>

                            <!-- エラー -->
                            <div class="mt-2 text-danger">
                                ${errors.f1}
                            </div>

                        </div>
                    </form>

                    <!-- 一覧 -->
                    <c:choose>

                        <c:when test="${not empty students}">

                            <div class="mb-2">
                                検索結果：
                                ${students.size()}件
                            </div>

                            <table class="table table-hover">

                                <tr>
                                    <th>入学年度</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>クラス</th>
                                    <th class="text-center">
                                        在学
                                    </th>
                                    <th></th>
                                </tr>

                                <c:forEach var="student" items="${students}">

                                    <tr>
                                        <td>${student.entYear}</td>
                                        <td>${student.no}</td>
                                        <td>${student.name}</td>
                                        <td>${student.classNum}</td>

                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${student.isAttend}">
                                                    ○
                                                </c:when>
                                                <c:otherwise>
                                                    ×
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td>
                                            <a href="studentUpdate.action?no=${student.no}">
                                                変更
                                            </a>
                                        </td>
                                    </tr>

                                </c:forEach>

                            </table>

                        </c:when>

                        <c:otherwise>
                            学生情報が存在しませんでした。
                        </c:otherwise>

                    </c:choose>

                </section>

            </c:param>
        </c:import>