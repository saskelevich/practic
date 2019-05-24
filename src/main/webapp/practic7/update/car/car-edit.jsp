<%@page import="by.practic.datalayer.db.CarDBDaoImpl"%>
<%@page import="by.practic.datalayer.entity.Car"%>
<%@page import="by.practic.datalayer.IDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cars</title>
</head>
<body bgcolor="#FFDEAD" link="#30D5C8" vlink="#990066">
<jsp:include page="/practic7/menu.jsp"></jsp:include>
<h1 align="center">Cars update</h1>

<%!IDao<Car, List<Car>> dao = CarDBDaoImpl.getInstance();%>

<%
    String id = request.getParameter("id");
    if (id != null && id != "") {
    	Car car = dao.get(Integer.valueOf(id));
        request.setAttribute("idValue", car.getId());
        request.setAttribute("vin", car.getVin());
        request.setAttribute("modelId", car.getModelId());
    }
%>

<form action="/servlet/cars" method="post">
	<input type="hidden" name="id" value="${idValue}">
	<table>
		<tr>
			<td>NAME</td>
			<td><input type="text" name="vin" title="vin" value="${vin}"></td>
			<td><input type="text" name="modelId" title="modelId" value="${modelId}"></td>
		</tr>
	</table>
	<input type="submit" value="SAVE">
</form>
</body>
</html>