<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/views/includes/head.jspf" %>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf" %>
	<h1>상품정보 등록</h1>
	<form method="POST">
		<div><input placeholder="상품코드" name="p_code" value="${PRODUCT.p_code}" /></div>
		<div><input placeholder="상품이름" name="p_name" value="${PRODUCT.p_name}"/></div>
		<div><input placeholder="카테고리" name="p_item" value="${PRODUCT.p_item}"/></div>
		<div><input placeholder="가격" name="p_price" value="${PRODUCT.p_price}" /></div>
		<div><input type="submit" value="저장"/></div>
	</form>
</body>
</html>