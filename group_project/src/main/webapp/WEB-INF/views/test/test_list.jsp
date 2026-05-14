<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>

        <c:import url="../common/base.jsp">

            <c:param name="title">成績参照</c:param>

            <c:param name="content">

                <section class="me-4">

                    <h2 class="mb-4">成績参照</h2>

                    <!-- ========================= -->
                    <!-- 科目別検索 -->
                    <!-- ========================= -->
                    <form action="${pageContext.request.contextPath}/testListSubjectExecute.action" method="post">

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
                                            <option value="${c}">
                                                ${c}
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
                    <form action="${pageContext.request.contextPath}/testListStudentExecute.action" method="post">

                        <div class="border p-3 bg-light">

                            <div class="row align-items-end">

                                <!-- 学生番号 -->
                                <div class="col-md-4">
                                    <label class="form-label">学生番号</label>

                                    <input type="text" name="studentNo" class="form-control"
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
                    <c:if test="${not empty testListst}">

                        <table class="table table-bordered table-striped">

                        <thead class="table-dark">
                            <tr>
                                <th>学生名</th>
                                <th>科目名</th>
                                <th>学生番号</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="t" items="${testListst}">
                                <tr>
                                    <td>${t.studentName}</td>
                                    <td>${t.subjectCd}</td>
                                    <td>${t.studentNo}</td>
                                    <td>${t.no}</td>
                                    <td>${t.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${not empty testListsub}">

                    <table class="table table-bordered table-striped">

                        <thead class="table-dark">
                            <tr>
                                <th>教科名</th>
                                <th>教科コード</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="t" items="${testListsub}">
                                <tr>
                                <td>${t.subjectName}</td>
                                <td>${t.subjectCd}</td>
                                <td>${t.no}</td>
                                <td>${t.point}</td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>

            </c:if>
            <c:if test="${empty testListsub and not empty param.subjectCd}">
                <c:if test="${empty testListsub and not empty param.subjectCd}">
                    <div class="alert alert-warning">
                        成績情報が存在しませんでした
                    </div>
                </c:if>
            </c:if>

                    <!-- メッセージ -->
                    <div class="mt-3 text-info">
                        科目情報を選択または学生情報を入力して検索してください
                    </div>

                </section>

            </c:param>

        </c:import>