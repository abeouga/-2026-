<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/common/base.jsp">
    <c:param name="title">アカウント権限管理</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h4 mb-4 fw-normal py-2 px-4" style="background-color: #f0f0f0; border-bottom: 1px solid #dee2e6;">アカウント権限管理</h2>

            <table class="table table-hover align-middle">
                <tr>
                    <th>ユーザーID</th>
                    <th>氏名</th>
                    <th>現在の権限</th>
                    <th>権限の変更</th>
                </tr>

                <c:forEach var="teacher" items="${teacherList}">
                    <tr>
                        <td>${teacher.id}</td>
                        <td>${teacher.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${teacher.role == 'admin'}">
                                    <span class="badge bg-danger">admin</span>
                                </c:when>
                                <c:when test="${teacher.role == 'editor'}">
                                    <span class="badge bg-success">editor</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-secondary">viewer</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${teacher.id != user.id && teacher.role != 'admin'}">
                                <form action="teacherRoleUpdateExecute.action" method="post" class="d-inline m-0 p-0">
                                    <input type="hidden" name="id" value="${teacher.id}">
                                    <select name="role" class="form-select form-select-sm d-inline-block w-auto" onchange="this.form.submit()">
                                        <option value="viewer" <c:if test="${teacher.role == 'viewer'}">selected</c:if>>viewer (閲覧のみ)</option>
                                        <option value="editor" <c:if test="${teacher.role == 'editor'}">selected</c:if>>editor (更新可能)</option>
                                    </select>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <div class="mt-4 ms-2">
                <a href="menu.action" class="text-decoration-none">メニューに戻る</a>
            </div>
        </section>
    </c:param>
</c:import>
