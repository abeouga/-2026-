<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <c:import url="/WEB-INF/views/common/base.jsp">
            <c:param name="title">
                得点管理システム - CSV読み込み
            </c:param>

            <c:param name="content">
                <section class="me-4">
                    <h2 class="mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                        学生管理 - CSV読み込み
                    </h2>

                    <div class="mx-4 mt-4">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                ${error}
                            </div>
                        </c:if>
                        <c:if test="${not empty message}">
                            <div class="alert alert-success" role="alert">
                                ${message}
                            </div>
                        </c:if>

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">学生データ一括登録</h5>
                                <p class="card-text text-muted small">
                                    フォーマット (UTF-8推奨):<br>
                                    <code>氏名,入学年度(西暦4桁),クラス番号(3桁),在学フラグ(true/false)</code><br>
                                    ※ 1行目はヘッダーとして無視されます。<br>
                                    ※ 学生番号は自動採番されます。
                                </p>

                                <form action="studentCsvImportExecute.action" method="post"
                                    enctype="multipart/form-data">
                                    <div class="mb-3">
                                        <label for="csvFile" class="form-label">CSVファイルを選択</label>
                                        <input class="form-control" type="file" id="csvFile" name="csvFile"
                                            accept=".csv" required>
                                    </div>

                                    <div class="mt-4">
                                        <button type="submit" class="btn btn-primary px-4">読み込み実行</button>
                                        <a href="student.action" class="btn btn-secondary ms-2">戻る</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </c:param>
        </c:import>