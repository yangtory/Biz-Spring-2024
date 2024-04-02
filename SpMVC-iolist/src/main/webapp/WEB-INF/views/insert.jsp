<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/views/include/head.jspf"%>
<style>
	h1 {
		text-align: center;
	}
	div {
		margin: 4px;
	}
	label {
		margin-right: 8px;
	}
	input {
		padding: 6px 12px;
	}
</style>
<script>
	const rootPath = "${rootPath}"
	const serverDate = "${serverDate}"
	const serverTime = "${serverTime}"
	const io_date = "${item.io_date}"
	const io_time = "${item.io_time}"
</script>
<script src="${rootPath}/static/js/iolist.js?2024-04-01-0010"></script>
<body>
	<%@ include file="/WEB-INF/views/include/header.jspf"%>
<div class="w3-container w3-center">
	<h1>매입매출</h1>
	<form method="POST" >
		<div><label>거래날짜</label><input class="date" placeholder="거래날짜" name="io_date" value="${serverDate}" /></div>
		<div><label>거래시각</label><input class="time" placeholder="거래시각" name="io_time" value="${serverTime}" /></div>
		<div><label>상품이름</label><input placeholder="상품이름" name="io_pname" value="${item.io_pname}" /></div>
		<div><label>거래구분</label><input class="io_input" placeholder="거래구분" name="io_input" value="${item.io_input}" /></div>		
		<div><label>단가　　</label><input placeholder="단가" name="io_price" value="${item.io_price}" /></div>
		<div><label>수량　　</label><input placeholder="수량" name="io_quan" value="${item.io_quan}" /></div>
		<div><input class="w3-button w3-green" type="submit" value="저장"/></div>
	</form>
</div>
</body>
</html>