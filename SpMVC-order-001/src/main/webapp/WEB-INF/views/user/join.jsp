<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html>
<html lang="ko">
	<%@ include file="/WEB-INF/views/includes/head.jspf" %>
	<style>
		form.w3-container {
			width:60%;
			margin:10px auto;
			fieldset {
				border-radius: 20px;
				padding: 16px;
			}
			legend {
				text-align: center;
				font-weight: 900;
				font-size: 1.3rem;
			}
			input {
				margin-bottom: 10px;
				border-radius: 10px;
			}
		}	
	</style>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf" %>
	<form method="POST" class="w3-container">
		<fieldset>
			<legend>회원가입</legend>
				<label class="w3-text-teal">USERNAME</label>
				<input class="w3-input w3-border w3-light-gray" placeholder="USERNAME" name="username"/>
		
				<label class="w3-text-teal">비밀번호</label>
				<input class="w3-input w3-border w3-light-gray" placeholder="비밀번호" name="password"/>
		
				<label class="w3-text-teal">이름</label>
				<input class="w3-input w3-border w3-light-gray" placeholder="이름" name="name">
		
				<label class="w3-text-teal">이메일</label>
				<input class="w3-input w3-border w3-light-gray" placeholder="이메일" name="email"/>
		
				<label class="w3-text-teal">전화번호</label>
				<input class="w3-input w3-border w3-light-gray" placeholder="전화번호" name="tel"/>
		
				<input class="w3-btn w3-blue-gray w3-right" type="submit" value="회원가입"/>
		</fieldset>
	</form>
	<p>USERNAME : ${USER.username}
	<p>비밀번호 : ${USER.password}
	<p>이름 : ${USER.name}
	<p>이메일 : ${USER.email}
	<p>전화번호 : ${USER.tel}
</body>
</html>