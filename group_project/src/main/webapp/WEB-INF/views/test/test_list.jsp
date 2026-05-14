<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">

    <c:param name="title">成績参照</c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="mb-4">成績参照</h2>

            <!-- ========================= -->
            <!-- 科目別検索 -->
            <!-- ========================= -->
            <form action="${pageContext.request.contextPath}/testListSubjectExecute.action"
                  method="post">

                <div class="border p-3 mb-4 bg-light">

                    <div class="row align-items-end">

                        <!-- 入学年度 -->
                        <div class="col-md-2">
                            <label class="form-label">入学年度</label>
                            <select name="entYear" class="form-select">
                                <option value="">------</option>
                                <c:forEach var="year" items="${entYearList}">
                                    <option value="${year}">
                                        ${year}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- クラス -->
                        <div class="col-md-2">
                            <label class="form-label">クラス</label>
                            <select name="classNum" class="form-select">
                                <option value="">------</option>
                                <c:forEach var="c" items="${classList}">
                                    <option value="${c.classNum}">
                                        ${c.classNum}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- 科目 -->
                        <div class="col-md-4">
                            <label class="form-label">科目</label>
                            <select name="subjectCd" class="form-select">
                                <option value="">------</option>
                                <c:forEach var="sub" items="${subjectList}">
                                    <option value="${sub.cd}">
                                        ${sub.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- ボタン -->
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-secondary">
                                検索
                            </button>
                        </div>

                    </div>

                </div>

            </form>


            <!-- ========================= -->
            <!-- 学生別検索 -->
            <!-- ========================= -->
            <form action="${pageContext.request.contextPath}/testListStudentExecute.action"
                  method="post">

                <div class="border p-3 bg-light">

                    <div class="row align-items-end">

                        <!-- 学生番号 -->
                        <div class="col-md-4">
                            <label class="form-label">学生番号</label>

                            <input type="text"
                                   name="studentNo"
                                   class="form-control"
                                   placeholder="学生番号を入力してください">
                        </div>

                        <!-- 検索 -->
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-secondary">
                                検索
                            </button>
                        </div>

                    </div>

                </div>

            </form>


            <!-- メッセージ -->
            <div class="mt-3 text-info">
                科目情報を選択または学生情報を入力して検索してください
            </div>

        </section>

    </c:param>

</c:import>