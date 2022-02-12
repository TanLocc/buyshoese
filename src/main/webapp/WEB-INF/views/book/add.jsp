<%@page pageEncoding="UTF-8" contentType="text/html; ISO-8859-1" language="java" %>
<%--mã hóa dạng utf8--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<style>
    .error{
        color: red;
    }
</style>
    <%--    nhúng phần head import css và js--%>
    <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>

    <body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>


    <div class="container">
        <main>
            <div class="py-5 text-center">
                <h2>Thêm sách</h2>
                <br>
<%--                <span style="color: red">${message}</span>--%>
                <c:if test="${message != null}"><span style="color: red">${message}</span></c:if>
            </div>

            <div class="row g-5">
                <div class="col-md-2 col-lg-2"></div>
                <div class="col-md-8 col-lg-8">
                    <form:form modelAttribute="book" enctype="multipart/form-data" class="needs-validation" novalidate="" action="/book/save" method="post">

                        <form:input path="id" type="hidden"></form:input>
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label for="name" class="form-label">Tên sách</label>
                                <form:input type="text" Class="form-control" name="name" id="name" placeholder="" value=""   path="name"/>
                                <form:errors path="name" class="error"/>
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="author" class="form-label">Tác giả</label>
                                <form:input type="text" class="form-control" id="author" name="author" placeholder="" value="" required="" path="author"/>
                                <form:errors path="author" class="error"/>
                                <div class="invalid-feedback">
                                    Valid last name is required.
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="publisher" class="form-label">Nhà xuất bản</label>
                                <div class="input-group has-validation">
                                    <form:input type="text" class="form-control" name="publisher" id="publisher" placeholder="Nhà xuất bản" required="" path="publisher"/>
                                    <form:errors path="publisher" class="error"/>
                                    <div class="invalid-feedback">
                                        Your username is required.
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="numberPage" class="form-label">Số trang </label>
                                <form:input type="number" class="form-control" name="numberPage" id="numberPage" placeholder="Số trang" path="numberPage"/>
                                <form:errors path="numberPage" class="error"/>
                                <div class="invalid-feedback">
                                    Please enter a valid email address for shipping updates.
                                </div>
                            </div>



                            <div class="col-md-5">
                                <label for="language" class="form-label">Ngôn ngữ</label>
                                <form:select class="form-select" name="language" id="language" required="" path="language">
                                    <option value="">Chọn ngôn ngữ</option>
                                    <option value="vi">Tiếng Việt</option>
                                    <option value="en">United States</option>
                                </form:select>
                                <form:errors path="language" class="error"/>
                                <div class="invalid-feedback">
                                    Please select a valid country.
                                </div>
                            </div>


                        </div>

                        <hr class="my-4">


                        <hr class="my-4">

                        <button class="w-100 btn btn-primary btn-lg" type="submit">Thêm sách</button>
                    </form:form>
                </div>
            </div>
        </main>

        <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    </body>

</html>
