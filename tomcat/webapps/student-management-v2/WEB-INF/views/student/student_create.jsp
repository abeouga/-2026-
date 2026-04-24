<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <c:import url="/WEB-INF/views/common/base.jsp">

            <c:param name="title">得点管理システム</c:param>

            <c:param name="content">

                <section class="me-4">

    <!-- タイトル -->
    <h2 class="mb-4 fw-normal
        bg-secondary bg-opacity-10
        py-2 px-4">
                        学生情報登録
                    </h2>

                    <form action="StudentCreateExecute.action" method="post">

        <!-- 入学年度 -->
        <div class="mb-3">
            <label class="form-label">入学年度</label>

            <select name="entYear" class="form-select">
                <option value="">------</option>

                <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}">
                        ${year}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- 学生番号 -->
        <div class="mb-3">
            <label class="form-label">学生番号</label>

                            <input type="text" name="no" class="form-control" placeholder="学生番号を入力してください">
                        </div>

        <!-- 氏名 -->
        <div class="mb-3">
            <label class="form-label">氏名</label>

                            <input type="text" name="name" class="form-control" placeholder="氏名を入力してください">
                        </div>

        <!-- クラス -->
        <div class="mb-3">
            <label class="form-label">クラス</label>

            <select name="classNum" class="form-select">
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}">
                        ${num}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- ★ 在学（追加部分） -->
        <div class="mb-3">
            <label class="form-label">在学</label><br>

            <input type="checkbox"
                name="isAttend"
                value="true">

            在学中
        </div>

        <!-- ボタン -->
        <button class="btn btn-secondary">
            登録して終了
        </button>

                    </form>

    <!-- 戻る -->
    <div class="mt-3">
        <a href="StudentList.action">戻る</a>
    </div>
                    <!-- ⑪ -->
                    <div class="mt-3">
                        <a href="student.action">戻る</a>
                    </div>

                </section>

            </c:param>
        </c:import>