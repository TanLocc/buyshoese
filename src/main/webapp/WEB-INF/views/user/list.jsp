<%@page pageEncoding="UTF-8" contentType="text/html; ISO-8859-1" language="java" %>
<%--mã hóa dạng utf8--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/"%>
<html>
    <%--    nhúng phần head import css và js--%>
    <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>

    <body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
    <c:if test="${message != null}">
        <div aria-live="polite" aria-atomic="true" class="d-flex justify-content-center align-items-center w-100">
            <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                    <h5 class="me-auto"></h5>
                    <button type="button" class="btn-close" style="width: 0.5em;height: 0.5em" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body" style="color: mediumblue">
                   ${message}
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function (){
                $('.toast').toast('show');
            });
        </script>
    </c:if>
    <div class="container">
        <main>
            <div class="py-5 text-center">
                <h2>Danh sách book</h2>
                <br>
            </div>
            <a class="btn btn-primary" href="/user/add" role="button"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
            </svg></a>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">STT</th>

                    <th scope="col"><a href="/user/page/${currentPage}?sortField=username&sortDir=${reverseSortDir}">UserName</a></th>
                    <th >Avatar</th>
                    <th scope="col"><a href="/user/page/${currentPage}?sortField=username&sortDir=${reverseSortDir}">Email</a></th>
                    <th scope="col"><a href="/user/page/${currentPage}?sortField=phone&sortDir=${reverseSortDir}">Phone</a></th>
                    <th scope="col"><a href="/user/page/${currentPage}?sortField=address&sortDir=${reverseSortDir}">Address</a></th>
                    <th scope="col"><a href="/user/page/${currentPage}?sortField=role&sortDir=${reverseSortDir}">Role</a></th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="stt" value="1"></c:set>

                <c:forEach items="${listUser}" var="user">
                    <tr>
                        <th scope="row">${stt}</th>
                        <td>${user.username}</td>
                        <td>
                            <img width="100" height="100" src="${pageContext.request.contextPath}/user/getUserPhoto?id=${user.id}">

                        </td>
                        <td>${user.email}</td>

                        <td>${user.phone}</td>
                        <td>${user.address}</td>
                        <td>${user.role}</td>

                        <td>
                            <a href="/user/edit/${user.id}" >Edit</a>|
                            <a href="/user/delete/${user.id}" >Delete</a>
                        </td>
                    </tr>

                    <c:set var="stt" value="${stt+1}"></c:set>
                </c:forEach>

                </tbody>
            </table>
            <c:if test="${currentPage > 1}"><a href="/user/page/1?sortField=${sortField}&sortDir=${sortDir}">First</a> </c:if>
            <c:if test="${currentPage == 1}"><span >First</span> </c:if>

            <c:if test="${currentPage > 1}"><a href="/user/page/${currentPage - 1}?sortField=${sortField}&sortDir=${sortDir}">Previous</a> </c:if>
            <c:if test="${currentPage == 1}"><span >Previous</span> </c:if>


            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:if test="${currentPage != i}"><a class="btn btn-primary" href="/user/page/${i}?sortField=${sortField}&sortDir=${sortDir}">${i}</a></c:if>
                <c:if test="${currentPage == i}"><a class="btn btn-light" >${i}</a></c:if>
            </c:forEach>

            <c:if test="${currentPage < totalPages}"><a href="/user/page/${currentPage + 1}?sortField=${sortField}&sortDir=${sortDir}">Next</a> </c:if>
            <c:if test="${currentPage >= totalPages}"><span >Next</span> </c:if>

            <c:if test="${currentPage < totalPages}"><a href="/user/page/${totalPages}?sortField=${sortField}&sortDir=${sortDir}">Last</a> </c:if>
            <c:if test="${currentPage >= totalPages}"><span >Last</span> </c:if>





        </main>

        <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    </body>

</html>
