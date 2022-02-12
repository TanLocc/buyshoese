<%@page pageEncoding="UTF-8" contentType="text/html; ISO-8859-1" language="java" %>
<%--mã hóa dạng utf8--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags/" %>
<html>
<%--    nhúng phần head import css và js--%>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>

<body>
<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
<c:if test="${username != null}">
    <div aria-live="polite" aria-atomic="true" class="d-flex justify-content-center align-items-center w-100">
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <h5 class="me-auto"></h5>
                <button type="button" class="btn-close" style="width: 0.5em;height: 0.5em" data-bs-dismiss="toast"
                        aria-label="Close"></button>
            </div>
            <div class="toast-body" style="color: mediumblue">
                Hello loc, you logined success
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function () {
            $('.toast').toast('show');
        });
    </script>
</c:if>
<div class="container">


    <div id="myCarousel" class="carousel slide border " data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100 " src="https://dacaocapcyvy.com.vn/uploads/images/news/giay-the-thao.jpg"
                     alt="Panther" height="600">
                <div class="carousel-caption">
                    <h5>GIRL 1</h5>
                    <small>
                        This is image of girl 1.
                    </small>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="http://s1.storage.5giay.vn/image/2016/11/20161110_5833138a073beec28c492a4fb6bf4ce6_1478792091.jpg"
                     alt="Black Cat" height="600">
                <div class="carousel-caption">
                    <h5>GIRL 2</h5>
                    <small>
                        This is image of girl 2.
                    </small>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100"
                     src="http://nguabac.com/wp-content/uploads/2017/10/giay-cong-so-nam-cao-cap-GB-008.jpg"
                     alt="Lion" height="600">
                <div class="carousel-caption">
                    <h5>GIRL 3</h5>
                    <small>
                        This is image of girl 3.
                    </small>
                </div>
            </div>
        </div>

        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


    <h4 class="mb-4 pt-4">TẤT CẢ SẢN PHẨM MỚI CÓ TẠI ĐÂY</h4>

    <sec:authorize access="hasRole('ok')">

        This content will only be visible to users who have the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.

    </sec:authorize>
    <div class="row mb-4" >
        <c:forEach items="${products}" var="product">
            <div class="col-sm-6 col-md-4">
                <div class="card">
                    <img style="height:8rem" class=" w-100 card-img-top"
                         src="/product/productImage?id=${product.id}"/>
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">
                                ${product.detail}
                        </p>
                        <h5 class="card-title"><span>${product.price} $</span></h5>
                        <p class="card-text">
                            <small class="text-muted">Last updated 3 mins ago</small>
                        </p>
                    </div>
                    <div href="#" class="card-footer">
                        <a href="BadView?id=${product.id}" class="btn btn-primary">
                            THÊM VÀO
                            GIỎ HÀNG
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>

    <c:if test="${currentPage > 1}"><a href="/product/customerPage/1?category=${category}">First</a> </c:if>
    <c:if test="${currentPage == 1}"><span >First</span> </c:if>

    <c:if test="${currentPage > 1}"><a href="/product/customerPage/${currentPage - 1}?category=${category}">Previous</a> </c:if>
    <c:if test="${currentPage == 1}"><span >Previous</span> </c:if>


    <c:forEach var="i" begin="1" end="${totalPages}">
        <c:if test="${currentPage != i}"><a class="btn btn-primary" href="/product/customerPage/${i}?category=${category}">${i}</a></c:if>
        <c:if test="${currentPage == i}"><a class="btn btn-light" >${i}</a></c:if>
    </c:forEach>

    <c:if test="${currentPage < totalPages}"><a href="/product/customerPage/${currentPage + 1}?category=${category}">Next</a> </c:if>
    <c:if test="${currentPage >= totalPages}"><span >Next</span> </c:if>

    <c:if test="${currentPage < totalPages}"><a href="/product/customerPage/${totalPages}?category=${category}">Last</a> </c:if>
    <c:if test="${currentPage >= totalPages}"><span >Last</span> </c:if>

</div>



<!-- footer -->


<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>

</html>