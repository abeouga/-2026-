<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<c:import url="../common/base.jsp">

    <c:param name="title">学生別成績参照</c:param>

    <c:param name="content">

        <h2 class="mb-4">成績参照（学生別）</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- 検索 -->
        <form action="testListStudentExecute.action" method="post" class="card p-3 mb-4">

            <div class="row g-3">

                <div class="col-md-4">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="studentNo" class="form-control" value="${param.studentNo}" required>
                </div>

            </div>

            <button type="submit" class="btn btn-primary mt-3">検索</button>

        </form>

        <!-- 結果 -->
        <c:if test="${not empty testList}">

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
                    <c:forEach var="t" items="${testList}">
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
        <!--データの中身が存在しない場合-->
        <c:if test="${empty testList and not empty param.subjectCd}">
            <div class="alert alert-warning">
                学生情報が存在しませんでした
            </div>
        </c:if>

    </c:param>

</c:import>