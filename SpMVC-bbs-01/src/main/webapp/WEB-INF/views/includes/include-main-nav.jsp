<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
 <nav class="gallery">
 	<ul>
 		<li><a href="${rootPath }/">Home</a>
 		<li>공지사항
 		<li><a href="${rootPath }/bbs/">자유게시판</a>
 		
 	</ul>
 </nav>
