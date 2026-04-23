<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<c:import url="../common/base.jsp">

    <c:param name="title">科目別成績参照</c:param>

    <c:param name="content">

        <h2 class="mb-4">成績参照（科目・クラス別）</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- 検索 -->
        <form action="TestListSubjectExecute.action" method="post" class="card p-3 mb-4">

            <div class="row g-3">

                <div class="col-md-3">
                    <label class="form-label">入学年度</label>
                    <input type="number" name="entYear" class="form-control" value="${param.entYear}">
                </div>

                <div class="col-md-3">
                    <label class="form-label">クラス</label>
                    <select name="classNum" class="form-select">
                        <option value="">選択してください</option>
                        <c:forEach var="c" items="${classList}">
                            <option value="${c}">
                                ${c}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label">科目</label>
                    <select name="subjectCd" class="form-select">
                        <option value="">選択してください</option>
                        <c:forEach var="sub" items="${subjectList}">
                            <option value="${sub.cd}"
                            <c:if test="${param.subjectCd == sub.cd}">selected</c:if>>
                            ${sub.cd} : ${sub.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

            </div>

            <button type="submit" class="btn btn-primary mt-3">検索</button>

        </form>

        <!-- 結果 -->
        <c:if test="${not empty testList}">

            <table class="table table-bordered table-striped">

                <thead class="table-dark">
                    <tr>
                        <th>教科名</th>
                        <th>教科コード</th>
                        <th>クラス番号</th>
                        <th>点数</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="t" items="${testList}">
                        <tr>
                            <td>${t.subjectName}</td>
                            <td>${t.subjectCd}</td>
                            <td>${t.num}</td>
                            <td>${t.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        </c:if>

    </c:param>

</c:import>