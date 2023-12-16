<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
  request.setCharacterEncoding("utf-8");
  response.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>회원가입</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

  <script>
    // form 안에 있는 데이터를 json으로 변경해서 post로 보내기
    function signUp() {
      var formData = $("#join").serializeArray().reduce(function(obj, item) {
        obj[item.name] = item.value;
        return obj;
      }, {});

      // ajax로 json 파싱해서 보내기
      $.ajax({
        url:'join',
        type:'POST',
        contentType:'application/json; charset=UTF-8',
        data:JSON.stringify(formData),
        success:function() {
          alert("회원가입 성공! 로그인 화면으로 이동합니다.");
          location.href="login.jsp";
        }
      });
    }
  </script>
</head>
<body>

<div class="container mt-3">
  <h3>JPA SHOP 회원가입</h3>
  <p>아래 정보를 모두 작성해주세요.</p>

  <form id="join" class="was-validated">
    <div class="mb-3 mt-3">
      <label for="name" class="form-label">이름:</label>
      <input type="text" class="form-control" id="name" placeholder="예 ) 홍길동" name="name" required>
      <div class="valid-feedback"></div>
      <div class="invalid-feedback">아이디를 입력해주세요.</div>
    </div>
    <div class="mb-3 mt-3">
      <label for="id" class="form-label">아이디:</label>
      <input type="text" class="form-control" id="id" placeholder="예 ) example" name="id" required>
      <div class="valid-feedback"></div>
      <div class="invalid-feedback">아이디를 입력해주세요.</div>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">비밀번호:</label>
      <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요" name="password" required>
      <div class="valid-feedback"></div>
      <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
    </div>
    <div class="mb-3">
      <label for="phone" class="form-label">휴대폰:</label>
      <input type="tel" class="form-control" id="phone" placeholder="01012345678" name="phone" required>
      <div class="valid-feedback"></div>
      <div class="invalid-feedback">-없이 휴대폰 번호를 입력해주세요.</div>
    </div>
    <div class="mb-3">
      <label for="address" class="form-label">상세주소:</label>
      <input type="text" class="form-control" id="address" placeholder="예 ) ㅁㅁ아파트 105동" name="address" required>
      <div class="valid-feedback"></div>
      <div class="invalid-feedback">상세 주소를 입력해주세요.</div>
    </div>
    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" id="myCheck" required>
      <label class="form-check-label" for="myCheck">이용약관에 동의합니다.</label>
      <div class="valid-feedback">동의</div>
      <div class="invalid-feedback">탈퇴 시 모든 정보가 파기됩니다.</div>
    </div>
    <button type="button" onclick="signUp();" class="btn btn-primary">회원가입</button>
  </form>
</div>

</body>
</html>
