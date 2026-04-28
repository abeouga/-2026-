<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">

    <c:param name="title">
        完了
    </c:param>

    <c:param name="content">

        <div class="container mt-5">

            <div class="alert alert-success text-center">
                ${message}
            </div>

            <div class="text-center mt-3">
                <a href="TestRegist.action" class="btn btn-primary">
                    戻る
                </a>
            </div>

        </div>

    </c:param>

</c:import>