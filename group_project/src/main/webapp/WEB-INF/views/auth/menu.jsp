<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <c:import url="/WEB-INF/views/common/base.jsp">
            <c:param name="title">メニュー</c:param>
            <c:param name="content">
                <style>
                    /* メニュー画面独自のスタイル */
                    .menu-title {
                        background: #ddd;
                        padding: 10px;
                        margin-bottom: 20px;
                        font-size: 24px;
                        font-weight: bold;
                    }

                    .card-area {
                        display: flex;
                        gap: 20px;
                        flex-wrap: wrap;
                    }

                    .menu-card {
                        width: 220px;
                        min-height: 120px;
                        padding: 20px;
                        border-radius: 8px;
                        box-shadow: 0 3px 10px rgba(0, 0, 0, 0.15);
                    }

                    .student {
                        background: #f8d7da;
                    }

                    .score {
                        background: #d1e7dd;
                    }

                    .subject {
                        background: #d6d8f5;
                    }
 
                    .role {
                        background: #fff3cd;
                    }

                    .menu-card a {
                        display: block;
                        text-decoration: none;
                        font-size: 20px;
                        margin-bottom: 10px;
                        color: #0d6efd;
                    }

                    .menu-card a:hover {
                        opacity: 0.8;
                    }
                </style>

                <div class="menu-title">メニュー</div>

                <div class="card-area">
                    <!-- 学生管理 -->
                    <div class="menu-card student">
                        <a href="student.action">学生管理</a>
                    </div>

                    <!-- 成績管理 -->
                    <div class="menu-card score">
                        <div>成績管理</div>
                        <a href="testRegist.action">成績登録</a>
                        <a href="testList.action">成績参照</a>
                    </div>

                    <!-- 科目管理 -->
                    <div class="menu-card subject">
                        <a href="subjectList.action">科目管理</a>
                    </div>
 
                    <!-- ロール管理 -->
                    <div class="menu-card role">
                        <a href="teacherRoleList.action">ロール管理</a>
                    </div>
                </div>
            </c:param>
        </c:import>