<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple View</title>
</head>
<body>
      <h3>simplecontroller에서 forward된 페이지</h3>
      요청한 결과를 출력 <%= request.getAttribute("result") %>
      <br>
      <h3>EL & JSTL 표기 권장</h3>
      EL 요청결과 : ${requestScope.result}<br>
</body>
</html>