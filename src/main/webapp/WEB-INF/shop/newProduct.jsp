<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>신상품</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </script>
</head>
<body>
<div class="container mt-3">
<button type="button" onclick="location.href='/product/new'" class="btn btn-primary" style="justify-items: right;">상품 등록</button>
<table class="table table-hover">
    <tr>
<%--        <c:if test=""></c:if>--%>
        <th>상품번호</th>
        <th>상품명</th>
        <th>가격</th>
        <th>좋아요</th>
        <th>등록일자</th>
    </tr>
    <c:forEach var="product" items="${productList}" varStatus="i">
    <tr>
        <td>${i.index + 1}</td>
        <td>${product.productName}</td>
        <td>${product.price}</td>
        <td>${product.likes}</td>
        <td>${product.createdAt}</td>
    </tr>
    </c:forEach>
</table>
</div>
</body>
</html>