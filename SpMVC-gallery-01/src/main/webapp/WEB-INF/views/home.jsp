<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
   
<!DOCTYPE html>
<html>
	<tiles:insertAttribute name="head"/>
  <body>
  	<tiles:insertAttribute name="header"/>
  	<tiles:insertAttribute name="nav"/>
  	<!-- 조건을 바꿔가며 보여줄 자리 -->
  	<tiles:insertAttribute name="content"/>
  </body>
</html>
