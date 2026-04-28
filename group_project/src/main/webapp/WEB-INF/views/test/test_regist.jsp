<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">

    <c:param name="title">成績管理一覧</c:param>

    <c:param name="content">

        <div class="container mt-4">

            <!-- タイトル -->
            <h2 class="mb-4 text-center text-primary fw-bold">
                成績登録・更新
            </h2>

            <!-- エラーメッセージ -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center">
                    ${error}
                </div>
            </c:if>

            <!-- 検索フォーム -->
            <form action="TestRegist.action" method="post"
                  class="card shadow-sm p-4 mb-4">

                <div class="row g-3">

                    <div class="col-md-3">
                        <label class="form-label">入学年度</label>
                        <input type="number"
                               name="entYear"
                               class="form-control"
                               value="${param.entYear}"
                               required>
                    </div>

                    <div class="col-md-3">
                        <label class="form-label">クラス</label>
                        <input type="text"
                               name="classNum"
                               class="form-control"
                               value="${param.classNum}"
                               required>
                    </div>

                    <div class="col-md-3">
                        <label class="form-label">科目</label>
                        <select name="subjectCd"
                                class="form-select"
                                required>

                            <option value="">選択してください</option>

                            <c:forEach var="sub" items="${subjectList}">
                                <option value="${sub.cd}"
                                    <c:if test="${param.subjectCd eq sub.cd}">
                                        selected
                                    </c:if>>
                                    ${sub.name}
                                </option>
                            </c:forEach>

                        </select>
                    </div>

                    <div class="col-md-3">
                        <label class="form-label">回数</label>
                        <select name="no"
                                class="form-select"
                                required>

                            <option value="">選択してください</option>

                            <option value="1"
                                <c:if test="${param.no eq '1'}">selected</c:if>>
                                1
                            </option>

                            <option value="2"
                                <c:if test="${param.no eq '2'}">selected</c:if>>
                                2
                            </option>

                        </select>
                    </div>

                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary px-5">
                        検索
                    </button>
                </div>

            </form>

            <!-- 該当データなし -->
            <c:if test="${empty testList and not empty param.subjectCd}">
                <div class="alert alert-warning text-center">
                    該当する学生がいません
                </div>
            </c:if>

            <!-- 成績一覧 -->
            <c:if test="${not empty testList}">

                <form action="TestRegistExecute.action" method="post">

                    <div class="table-responsive">

                        <table class="table table-bordered table-striped table-hover text-center align-middle">

                            <thead class="table-dark">
                                <tr>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th style="width:180px;">点数</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach var="t" items="${testList}">

                                    <tr>

                                        <c:choose>

                                            <c:when test="${not empty t.student}">

                                                <td>
                                                    ${t.student.no}
                                                    <input type="hidden"
                                                           name="studentNoList"
                                                           value="${t.student.no}">
                                                </td>

                                                <td>
                                                    ${t.student.name}
                                                </td>

                                            </c:when>

                                            <c:otherwise>

                                                <td colspan="2" class="text-muted">
                                                    不明
                                                </td>

                                            </c:otherwise>

                                        </c:choose>

                                        <td>

                                            <input type="number"
                                                   name="pointList"
                                                   class="form-control text-center"
                                                   min="0"
                                                   max="100"
                                                   value="${empty t.point ? '' : t.point}"
                                                   placeholder="点数">

                                            <!-- IDあり = 更新、なし = 新規登録 -->
                                            <input type="hidden"
                                                   name="testIdList"
                                                   value="${t.id}">

                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                    </div>

                    <!-- 検索条件保持 -->
                    <input type="hidden" name="subjectCd" value="${param.subjectCd}">
                    <input type="hidden" name="classNum" value="${param.classNum}">
                    <input type="hidden" name="entYear" value="${param.entYear}">
                    <input type="hidden" name="no" value="${param.no}">

                    <div class="text-center mt-4">
                        <button type="submit"
                                class="btn btn-success px-5">
                            登録 / 更新
                        </button>
                    </div>

                </form>

            </c:if>

        </div>

    </c:param>

</c:import>