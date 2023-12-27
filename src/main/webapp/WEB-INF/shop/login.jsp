<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<script>
  // form 안에 있는 데이터를 json으로 변경해서 post로 보내기
  function login() {
    var formData = $("#login").serializeArray().reduce(function(obj, item) {
      obj[item.name] = item.value;
      return obj;
    }, {});

    // ajax로 json 파싱해서 보내기
    $.ajax({
      url:'login',
      type:'POST',
      contentType:'application/json; charset=UTF-8',
      data:JSON.stringify(formData),
      success:function(result) {
        if(result===1) {
          console.log(result);
          alert("로그인 성공! 메인화면으로 이동합니다.");
          location.href="/";
        } else if(result===0){
          alert("아이디나 비밀번호가 맞지 않습니다. 다시 시도해주세요.");
        }
      }
    });
  }
</script>
<body>

<div class="container mt-3">
  <h2>Stacked form</h2>
  <form id="login" method="post">
    <div class="mb-3 mt-3">
      <label for="id">ID:</label>
      <input type="email" class="form-control" id="id" placeholder="Enter email" name="id">
    </div>
    <div class="mb-3">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
    </div>
    <div class="form-check mb-3">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Remember me
      </label>
    </div>
    <button type="button" onclick="login()" class="btn btn-primary">Submit</button>
  </form>
</div>

</body>
</html>
