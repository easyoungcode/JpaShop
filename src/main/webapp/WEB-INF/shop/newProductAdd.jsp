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
<%--    <script src="/js/fileNotice.js"></script>--%>
    <script type="text/javascript">
        $(document).ready(function () {
        var inputFileList = Array();     // 이미지 파일을 담아놓을 배열 (업로드 버튼 누를 때 서버에 전송할 데이터)

        // 파일 선택 이벤트
        $('input[name=productImage]').on('change', function(e) {
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);

            // 업로드 된 파일 유효성 체크
            if (filesArr.length > 3) {
                alert("이미지는 최대 3개까지 업로드 가능합니다.");
                $('input[name=productImage]').val();
                return;
            }

            filesArr.forEach(function(f) {
                inputFileList.push(f);    // 이미지 파일을 배열에 담는다.
            });
        });

        $("#uploadBtn").on('click',function() {
        // function upload() {
            console.log("inputFileList: " + inputFileList);
            // var file = $('#productImage')[0].files[0];
            var formData = new FormData();
            // formData.append('productImg', file);
            for (let i = 0; i < inputFileList.length; i++) {
                formData.append("productImg", inputFileList[i]);  // 배열에서 이미지들을 꺼내 폼 객체에 담는다.
            }

            var data = {
                productName: $('#productName').val(),
                price: $('#price').val(),
                inventory: $('#inventory').val()
            };
            formData.append("productInfo", new Blob([JSON.stringify(data)], {type: "application/json"}));

            $.ajax({
                url: "/product/new",
                method: "post",
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                enctype: 'multipart/form-data',
                dataType: "json",
                success: function(result) {
                    // 성공 시 처리
                },
                error: function (xhr, status, error) {
                    // 실패 시 처리
                }
            });
        // }
        });
        });
    </script>
</head>
<body>
<div class="container mt-3">
    <h3>상품 등록</h3>
    <p>아래 정보를 모두 작성해주세요.</p>

    <form id="product-new" class="was-validated" method="post" enctype="multipart/form-data">
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
            <label for="productImage" class="form-label">상품 사진:</label>
            <input type="file" class="form-control" id="productImage" name="productImage" required multiple>
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
        <button type="button" id="uploadBtn" class="btn btn-primary">상품등록</button>
    </form>
</div>
</body>
</html>