<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">

    <c:param name="title">成績管理一覧</c:param>

    <c:param name="content">

        <h2 class="mb-4 text-center">成績管理</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>
        <form action="testRegist.action" method="post" class="card p-4 mb-4 shadow-sm">

            <div class="row g-3">

                <div class="col-md-3">
                    <label class="form-label">入学年度</label>
                    <input type="number" name="entYear" class="form-control"
                           value="${param.entYear}" required>
                </div>

                <div class="col-md-3">
                    <label class="form-label">クラス</label>
                    <select name="classNum" class="form-select" required>
                        <option value="">選択してください</option>
                        <c:forEach var="c" items="${classList}">
                            <option value="${c}"
                                <c:if test="${param.classNum eq c}">selected</c:if>>
                                ${c}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label">科目</label>
                    <select name="subjectCd" class="form-select" required>
                        <option value="">選択してください</option>
                        <c:forEach var="sub" items="${subjectList}">
                            <option value="${sub.cd}"
                                <c:if test="${param.subjectCd eq sub.cd}">selected</c:if>>
                                ${sub.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label">回数</label>
                    <select name="no" class="form-select" required>
                        <option value="">選択してください</option>
                        <option value="1" <c:if test="${param.no eq '1'}">selected</c:if>>1</option>
                        <option value="2" <c:if test="${param.no eq '2'}">selected</c:if>>2</option>
                    </select>
                </div>

            </div>

            <div class="text-end">
                <button type="submit" class="btn btn-primary mt-3 px-4">検索</button>
            </div>

        </form>

        <!-- データなし -->
        <c:if test="${empty testList and not empty param.subjectCd}">
            <div class="alert alert-warning text-center">
                該当する学生がいません
            </div>
        </c:if>
        <c:if test="${not empty testList}">

            <form action="testRegistExecute.action" method="post">

                <div class="table-responsive">
                    <div class="d-flex justify-content-end mb-2">
                        <div class="fw-bold">
                            科目：
                            <c:forEach var="sub" items="${subjectList}">
                                <c:if test="${sub.cd eq param.subjectCd}">
                                ${sub.name}
                            </c:if>
                            </c:forEach>
                            （${param.no}回）
                        </div>
                    </div>
                    <table class="table table-bordered table-striped text-center align-middle">

                        <thead class="table-dark">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="t" items="${testList}">
                                <tr>
                                    <td>${param.entYear}</td>
                                    <td>${param.classNum}</td>
                                            <td>
                                                ${t.studentNo}
                                                <input type="hidden" name="studentNoList" value="${t.studentNo}">
                                            </td>
                                            <td>${t.studentName}</td>
                                    <td style="width:150px;">
                                        <input type="number"
                                            name="pointList"
                                            class="form-control"
                                            min="0"
                                            max="100"
                                            required
                                            value="${t.point}"

                                            oninvalid="
                                                if(this.value==''){
                                                    this.setCustomValidity('0〜100の数字を入力してください');
                                                } else {
                                                    this.setCustomValidity('0〜100の数字を入力してください');
                                                }
                                            "

                                            oninput="this.setCustomValidity('')">
                                        </td>
                                    <input type="hidden" name="noList" value="${t.no}">
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
                <input type="hidden" name="subjectCd" value="${param.subjectCd}">
                <input type="hidden" name="classNum" value="${param.classNum}">
                <input type="hidden" name="entYear" value="${param.entYear}">
                <input type="hidden" name="no" value="${param.no}">

                <div class="text-end">
                    <button type="submit" class="btn btn-success px-4">
                        登録 / 更新
                    </button>
                </div>

            </form>

        </c:if>

    </c:param>

</c:import>