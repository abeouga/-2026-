<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>${param.title}</title>

            <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/icon.png">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

            <style>
                body {
                    background: #f8f9fa;
                }

                .header {
                    background: #dfeaf6;
                    padding: 15px 25px;
                    margin-bottom: 15px;
                }

                .sidebar {
                    min-height: 600px;
                    border-right: 1px solid #ddd;
                }

                .sidebar a {
                    display: block;
                    padding: 4px 0;
                    text-decoration: none;
                    color: #0d6efd;
                }

                .sidebar a:hover {
                    color: #0d6efd;
                    padding-left: 5px;
                }

                .footer {
                    background: #e9ecef;
                    text-align: center;
                    font-size: 12px;
                    color: #666;
                    padding: 10px;
                    margin-top: 30px;
                }
            </style>

        </head>

        <body>

            <div class="container mt-3">

                <div class="header d-flex justify-content-between align-items-center">
                    <h2 class="mb-0 fw-bold">得点管理システム</h2>

                    <c:if test="${not empty user}">
                        <div class="d-flex align-items-center">
                            ${user.name} 様　
                            <a href="Logout.action" class="btn btn-sm btn-outline-danger">ログアウト</a>
                        </div>
                    </c:if>
                </div>

                <div class="row">

                    <c:if test="${not empty user}">
                        <div class="col-2 sidebar pt-2">

                            <a href="Menu.action" class="mb-3">メニュー</a>

                            <div class="fw-bold small text-muted mb-1">学生管理</div>
                            <a href="student.action" class="mb-1">学生一覧</a>

                            <div class="fw-bold small text-muted mt-3 mb-1">成績管理</div>
                            <a href="testRegist.action" class="mb-1">成績登録</a>
                            <a href="testList.action" class="mb-1">成績参照</a>

                            <div class="fw-bold small text-muted mt-3 mb-1">科目管理</div>
                            <a href="subjectList.action" class="mb-1">科目一覧</a>

                            <a href="schoolclassList.action" class="fw-bold small mt-3 mb-1">クラス管理</a>

                        </div>

                        <div class="col-10">
                            <c:out value="${param.content}" escapeXml="false" />
                        </div>
                    </c:if>

                    <c:if test="${empty user}">
                        <div class="col-12">
                            <c:out value="${param.content}" escapeXml="false" />
                        </div>
                    </c:if>

                </div>

                <div class="footer">
                    © 2023 TIC<br>
                    大原学園
                </div>

            </div>

        </body>

        </html>