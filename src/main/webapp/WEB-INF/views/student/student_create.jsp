<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>

<form action="StudentCreateExecute.action" method="post">

    <p>名前 <input type="text" name="name"></p>
    <p>学生番号 <input type="text" name="studentnum"></p>

    <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="add">

        <!-- 入学年度 -->
        <div class="col-4">
            <label class="form-label" for="student-f1-select">入学年度</label>
            <select class="form-select" id="student-f1-select" name="f1">
                <option value="0">--------</option>
                <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}" 
                        <c:if test="${year == f1}">selected="selected"</c:if>>
                        ${year}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- クラス -->
        <div class="col-4">
            <label class="form-label" for="student-f2-select">クラス</label>
            <select class="form-select" id="student-f2-select" name="f2">
                <option value="0">--------</option>
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}" 
                        <c:if test="${num == f2}">selected="selected"</c:if>>
                        ${num}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- 在学中 -->
        <div class="col-2 form-check text-center">
            <label class="form-check-label" for="student-f3-check">在学中
                <input class="form-check-input" type="checkbox" id="student-f3-check" 
                       name="f3" value="t"
                       <c:if test="${!empty f3}">checked="checked"</c:if>>
            </label>
        </div>

    </div>

    <p><input type="submit" value="登録"></p>

</form>

<%@include file="../footer.html" %>