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
    <script src="/js/fileNotice.js"></script>
    <script>
        function addProduct() {
            var formData = $("#product-new").serializeArray().reduce(function(obj, item) {
                obj[item.name] = item.value;
                return obj;
            }, {});

            // ajax로 json 파싱해서 보내기
            $.ajax({
                url:'/product/new',
                type:'POST',
                contentType:'application/json; charset=UTF-8',
                data:JSON.stringify(formData),
                success:function() {
                    location.href="/product/list/new";
                }
            });
        }
    </script>
</head>
<body>
<div class="container mt-3">
    <h3>상품 등록</h3>
    <p>아래 정보를 모두 작성해주세요.</p>

    <form id="product-new" class="was-validated">
        <div class="mb-3 mt-3">
            <label for="productName" class="form-label">상품명:</label>
            <input type="text" class="form-control" id="productName" placeholder="예 ) 텀블러" name="productName" required>
            <div class="valid-feedback"></div>
            <div class="invalid-feedback">상품명을 입력해주세요.</div>
        </div>
        <div class="mb-3 mt-3">
            <label for="price" class="form-label">가격:</label>
            <input type="text" class="form-control" id="price" placeholder="예 ) 10000" name="price" required>
            <div class="valid-feedback"></div>
            <div class="invalid-feedback">가격을 입력해주세요.</div>
        </div>
        <div class="mb-3">
            <label for="inventory" class="form-label">개수:</label>
            <input type="text" class="form-control" id="inventory" placeholder="예 ) 120" name="inventory" required>
            <div class="valid-feedback"></div>
            <div class="invalid-feedback">재고 수량을 입력해주세요</div>
        </div>
        <div class="mb-3">
            <label for="productImg" class="form-label">상품 사진:</label>
            <input type="file" class="form-control" id="productImg" name="productImg" required multiple>
            <div class="valid-feedback"></div>
            <div class="invalid-feedback">상품 이미지를 업로드해주세요</div>
        </div>
        <div class="file_drag">
            <div class="file_list_header" style="display: none;">
                <div class="file_list_header_task"><button type="button" class="button_svg_delete"><span class="blind">전체 삭제</span></button></div>
                <div class="file_list_header_title"><span class="text">파일명</span></div>
                <div class="file_list_header_volume"><span class="text">용량</span><span id="fileSize">0</span></div>
            </div>
            <ul id="fileList"></ul>
        </div>
        <button type="button" onclick="addProduct();" class="btn btn-primary">상품등록</button>
    </form>
</div>
</body>
</html>