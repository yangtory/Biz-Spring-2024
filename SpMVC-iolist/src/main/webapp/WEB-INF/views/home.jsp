<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/views/include/head.jspf"%>
<script>
	const rootPath = "${rootPath}"
</script>
<script src="${rootPath}/static/js/iolist.js?2024-04-01-003"></script>
<body>
	<%@ include file="/WEB-INF/views/include/header.jspf"%>
	<div class="w3-container btn_box">
		<a href="${rootPath}/insert"
		class="w3-button w3-blue w3-round-large">매입매출추가</a>
	</div>

	<table class="w3-table-all w3-hoverable iolist">
		<tr>
			<th>No.</th>
			<th>거래일자</th>
			<th>거래시각</th>
			<th>상품명</th>
			<th>매출구분</th>
			<th>매입단가</th>
			<th>매출단가</th>
			<th>수량</th>
			<th>합계</th>
		</tr>
		<c:forEach items="${IOLIST}" var="IO" varStatus="VAR">
			<tr data-num="${IO.io_seq}">
				<td>${VAR.count}</td>
				<td>${IO.io_date}</td>
				<td>${IO.io_time}</td>
				<td>${IO.io_pname}</td>
				<td>${IO.io_div}</td>
				<td>${IO.purchase_price}</td>
				<td>${IO.sale_price}</td>
				<td>${IO.io_quan}</td>
				<td>${IO.io_total}</td>
			</tr>
		</c:forEach>
		<c:forEach items="${SUM}" var="SUM">
			<tr>
				<td></td>
				<td>합계</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${SUM.total_purchase}</td>
				<td>${SUM.total_sale}</td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
