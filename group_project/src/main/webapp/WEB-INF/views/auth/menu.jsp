<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>メニュー</title>

            <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/icon.png">
            <!-- Bootstrap -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

            <style>
                body {
                    background-color: #f8f9fa;
                }

                .header {
                    background: #dfeaf6;
                    padding: 15px 25px;
                    margin-bottom: 15px;
                }

                .footer {
                    background: #e9ecef;
                    text-align: center;
                    font-size: 12px;
                    color: #666;
                    padding: 10px;
                    margin-top: 30px;
                }

                .menu-title {
                    background-color: #dee2e6;
                    padding: 20px;
                    border-radius: 12px;
                    margin-bottom: 30px;
                    font-weight: bold;
                }

                p {
                    margin-bottom: 20px;
                    border-radius: 12px;
                    overflow: hidden;
                    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.08);
                    transition: 0.3s;
                }

                p:hover {
                    transform: translateY(-3px);
                    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.12);
                }

                p a {
                    display: block;
                    padding: 20px;
                    text-decoration: none;
                    font-size: 18px;
                    font-weight: bold;
                }

                .logout-btn {
                    position: absolute;
                    top: 20px;
                    right: 30px;
                }

                /* 色分け（HTMLそのまま） */
                p:nth-of-type(1) {
                    background-color: #f8d7da;
                }

                p:nth-of-type(2),
                p:nth-of-type(3) {
                    background-color: #d1e7dd;
                }

                p:nth-of-type(4) {
                    background-color: #d6d8f5;
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
                            <a href="LogoutConfirmation.action" class="btn btn-sm btn-outline-danger">ログアウト</a>
                        </div>
                    </c:if>
                </div>

                <p><a href="student.action">学生管理</a></p>
                <p><a href="testList.action">成績参照</a></p>
                <p><a href="testRegist.action">成績登録</a></p>
                <p><a href="subjectList.action">科目管理</a></p>
                <p><a href="schoolclassList.action">クラス管理</a></p>
                <c:if test="${user.role == 'admin'}">
                    <p><a href="teacherRoleList.action">アカウント権限管理</a></p>
                </c:if>
                <div class="footer">
                    © 2023 TIC<br>
                    大原学園
                </div>
            </div>

        </body>

        </html>