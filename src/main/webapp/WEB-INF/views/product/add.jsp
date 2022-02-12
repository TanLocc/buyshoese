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
                    <form:form modelAttribute="shoesProduct" enctype="multipart/form-data"  class="needs-validation" novalidate="" action="/product/save" method="post">

                        <form:input path="id" type="hidden"></form:input>
                        <div class="row g-3">
                            <div>
                                <label  for="image" class="form-label">Image</label>
                                <form:input type="file" Class="form-control" name="image" path="image"  id="image" value=""/>
                                <form:errors path="image" class="error"/>
                                <div class="invalid-feedback">
                                    Valid image required.
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <label for="name" class="form-label">Name</label>
                                <form:input type="text" Class="form-control" name="name" id="name" placeholder="" value=""   path="name"/>
                                <form:errors path="name" class="error"/>
                                <div class="invalid-feedback">
                                    Valid name is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="price" class="form-label">Price</label>
                                <form:input type="text" class="form-control" id="price" name="price" placeholder="" value="" required="" path="price"/>
                                <form:errors path="price" class="error"/>
                                <div class="invalid-feedback">
                                    Valid price is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="amount" class="form-label">Amount</label>
                                <form:input type="number" Class="form-control" name="amount" id="amount" placeholder="" value=""   path="amount"/>
                                <form:errors path="amount" class="error"/>
                                <div class="invalid-feedback">
                                    Valid amount is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="detail" class="form-label">Detail</label>
                                <form:input type="text" Class="form-control" name="detail" id="detail" placeholder="" value=""   path="detail"/>
                                <form:errors path="detail" class="error"/>
                                <div class="invalid-feedback">
                                    Valid detail is required.
                                </div>
                            </div>

                            <div class="col-md-5">
                                <label for="categoryName" class="form-label">category</label>
                                <form:select class="form-select" name="categoryName" id="categoryName" required="" path="categoryName">
                                    <c:forEach items="${categorys}" var="category">
                                        <option value="${category}">${category}</option>
                                    </c:forEach>
                                </form:select>
                                <form:errors path="categoryName" class="error"/>
                                <div class="invalid-feedback">
                                    Please select a valid category.
                                </div>
                            </div>
                        </div>

                        <hr class="my-4">


                        <hr class="my-4">

                        <button class="w-100 btn btn-primary btn-lg" type="submit">Save</button>
                    </form:form>
                </div>
            </div>
        </main>

        <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    </body>

</html>
