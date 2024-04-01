<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/views/include/head.jspf"%>
<script>
	const rootPath = "${rootPath}"
</script>
<script src="${rootPath}/static/js/iolist.js?2024-04-01-001"></script>
<body>
	<%@ include file="/WEB-INF/views/include/header.jspf"%>
	<main class="w3-container">
		<div class="w3-card-4 w3-dark-gray">
			<div class="w3-container w3-center">
				<h3> ${item.io_pname} </h3>
				<h5>거래날짜 ${item.io_date} </h5>
				<h5>거래시간 ${item.io_time} </h5>
				<h5>거래구분 ${item.io_input} </h5>
				<h5>단가 ${item.io_price} </h5>
				<h5>수량 ${item.io_quan} </h5>
				<h5>합계 ${item.io_total} </h5>
				<div class="w3-section">
					<input data-num="${item.io_seq}" class="btn_update w3-button w3-green" type="button" value="수정"/>
					<input data-num="${item.io_seq}" class="btn_delete w3-button w3-red" type="button" value="삭제"/>
				</div>
			</div>
		</div>
	</main>

</body>
</html>