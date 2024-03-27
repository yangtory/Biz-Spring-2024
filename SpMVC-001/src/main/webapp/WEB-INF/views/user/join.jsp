<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 제목</title>
</head>
<body>
	<form method="POST">
		<div><input placeholder="USERNAME" name="username"/></div>
		<div><input placeholder="비밀번호" name="password"/></div>
		<div><input placeholder="이름" name="name"></div>
		<div><input placeholder="이메일" name="email"/></div>
		<div><input placeholder="전화번호" name="tel"/></div>
		<div><input type="submit" value="회원가입"/></div>
	</form>
	<p>USERNAME : ${USER.username}
	<p>비밀번호 : ${USER.password}
	<p>이름 : ${USER.name}
	<p>이메일 : ${USER.email}
	<p>전화번호 : ${USER.tel}
</body>
</html>