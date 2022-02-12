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
                    <form:form modelAttribute="user" enctype="multipart/form-data"  class="needs-validation" novalidate="" action="/user/save" method="post">

                        <form:input path="id" type="hidden"></form:input>
                        <div class="row g-3">
                            <div>
                                <label  for="avatar" class="form-label">Avatar</label>
                                <form:input type="file" Class="form-control" name="avatar" path="avatar"  id="avatar" value=""/>
                                <form:errors path="avatar" class="error"/>
                                <div class="invalid-feedback">
                                    Valid avatar required.
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <label for="username" class="form-label">UserName</label>
                                <form:input type="text" Class="form-control" name="username" id="username" placeholder="" value=""   path="username"/>
                                <form:errors path="username" class="error"/>
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="password" class="form-label">Password</label>
                                <form:input type="password" class="form-control" id="password" name="password" placeholder="" value="" required="" path="password"/>
                                <form:errors path="password" class="error"/>
                                <div class="invalid-feedback">
                                    Valid last name is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="phone" class="form-label">Phone</label>
                                <form:input type="text" Class="form-control" name="phone" id="phone" placeholder="" value=""   path="phone"/>
                                <form:errors path="phone" class="error"/>
                                <div class="invalid-feedback">
                                    Valid phone is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="address" class="form-label">Address</label>
                                <form:input type="text" Class="form-control" name="address" id="address" placeholder="" value=""   path="address"/>
                                <form:errors path="address" class="error"/>
                                <div class="invalid-feedback">
                                    Valid address is required.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="email" class="form-label">Email</label>
                                <div class="input-group has-validation">
                                    <form:input type="email" class="form-control" name="email" id="email" placeholder="" required="" path="email"/>
                                    <form:errors path="email" class="error"/>
                                    <div class="invalid-feedback">
                                        Your username is required.
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-5">
                                <label for="role" class="form-label">Role</label>
                                <form:select class="form-select" name="role" id="role" required="" path="role">
                                    <option value="ADMIN">ADMIN</option>
                                    <option value="USER">USER</option>
                                </form:select>
                                <form:errors path="role" class="error"/>
                                <div class="invalid-feedback">
                                    Please select a valid role.
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
