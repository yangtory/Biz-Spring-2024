<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html>
<html>

	<%@ include file="/WEB-INF/views/includes/header.jspf" %>
  <body>
	<%@ include file="/WEB-INF/views/list.jsp" %>
  </body>
</html>
