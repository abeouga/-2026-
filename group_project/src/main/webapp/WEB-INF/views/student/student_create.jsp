<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <c:import url="/WEB-INF/views/common/base.jsp">

            <c:param name="title">得点管理システム</c:param>

            <c:param name="content">

                <section class="me-4">

                    <!-- タイトル -->
                    <h2 class="mb-4 fw-normal
                bg-secondary bg-opacity-10
                py-2 px-4">
                        学生情報登録
                    </h2>

                    <form action="studentCreateExecute.action" method="post">

                        <!-- 入学年度 -->
                        <div class="mb-3">
                            <label class="form-label">入学年度</label>

                            <select name="entYear" class="form-select">
                                <option value="">------</option>

                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == entYear}">selected</c:if>>
                                        ${year}
                                    </option>
                                </c:forEach>
                            </select>

                            <c:if test="${ent_year_error}">
                                <div class="text-danger mt-1">入学年度を選択してください</div>
                            </c:if>
                        </div>

                        <!-- 学生番号 -->
                        <div class="mb-3">
                            <label class="form-label">学生番号</label>

                            <input type="text" name="no" value="${no}" class="form-control" placeholder="学生番号を入力してください"
                                required>

                            <c:if test="${no_duplicate_error}">
                                <div class="text-danger mt-1">学生番号が重複しています</div>
                            </c:if>
                        </div>

                        <!-- 氏名 -->
                        <div class="mb-3">
                            <label class="form-label">氏名</label>

                            <input type="text" name="name" value="${name}" class="form-control"
                                placeholder="氏名を入力してください" required>
                        </div>

                        <!-- クラス -->
                        <div class="mb-3">
                            <label class="form-label">クラス</label>

                            <select name="classNum" class="form-select" required>
                                <option value="">------</option>

                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num == classNum}">selected</c:if>>
                                        ${num}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>



                        <!-- ボタン -->
                        <button type="submit" class="btn btn-secondary">
                            登録して終了
                        </button>

                    </form>

                    <!-- 戻る -->
                    <div class="mt-3">
                        <a href="student.action">戻る</a>
                    </div>

                </section>

            </c:param>

        </c:import>