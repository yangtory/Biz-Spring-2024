<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html>
<html>
	<%@ include file="/WEB-INF/views/includes/head.jspf" %>
<script>
	const msg = "${MSG}"
	if(msg === "NOT"){
		alert("삭제할수 없음 관리자에게 문의")
	} else if(msg === "FK"){
		alert("주문이 이루어진 상품은 삭제할수 없음")
	}
	const rootPath = "${rootPath}"
</script>
<script src="${rootPath}/static/js/product.js?2024-03-29-004"></script>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf" %>
	<h1>상품 정보</h1>
	<h3>${PRODUCT.p_code}</h3>
	<h3>${PRODUCT.p_name}</h3>
	<h3>${PRODUCT.p_item}</h3>
	<h3>${PRODUCT.p_price}</h3>
	<input data-pcode="${PRODUCT.p_code}" class="btn_update" type="button" value="수정"/>
	<input data-pcode="${PRODUCT.p_code}" class="btn_delete" type="button" value="삭제"/>

</body>
</html>