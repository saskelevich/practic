<%@page import="java.util.ArrayList"%>
<%@page import="by.practic.datalayer.db.CarDBDaoImpl"%>
<%@page import="by.practic.datalayer.entity.Car"%>
<%@page import="by.practic.datalayer.IDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cars</title>
</head>
<body bgcolor="#FFDEAD" link="#30D5C8" vlink="#990066">
	<jsp:include page="/practic7/menu.jsp"></jsp:include>
	<h1 align="center">Cars page</h1>

	<%!IDao<Car, List<Car>> dao = CarDBDaoImpl.getInstance();%>

	<%
	    List<Car> cars = new ArrayList<>();
	    String name = null;
	    name = request.getParameter("name");
	    if (name != null) {
	        cars = dao.findByName(name);
	        request.setAttribute("cars", cars);
	    } else {
	        Integer modelId = null;
	        try {
	            modelId = Integer.valueOf(request.getParameter("modelId"));
	        } catch (NumberFormatException exc) {

	        }
	        if (modelId != null) {
	            cars = dao.find(modelId);
	            request.setAttribute("cars", cars);
	        } else {
	            cars = dao.getAll();
	            request.setAttribute("cars", cars);
	        }
	    }
	%>

	<table border="10px" bordercolor="#DDBE8D" bgcolor="#FFF"
		cellpadding="5" align="center">
		<tr bgcolor="#666" align="center">
			<th>ID</th>
			<th>VIN</th>
			<th>CREATED</th>
			<th>UPDATED</th>
			<th>MODEL_ID</th>
			<th>ACTIONS</th>
		</tr>

		<c:forEach items="${cars}" var="item">
			<tr bgcolor="#FFF" align="center">
				<td>#${item.id}</td>
				<td>${item.vin}</td>
				<td>${item.created}</td>
				<td>${item.updated}</td>
				<td>${item.modelId}</td>
				<td><a href="/practic7/update/car/car-edit.jsp?id=${item.id}">edit
						| </a> <a href="/servlet/cars?action=delete&id=${item.id}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<p align="center">
		<a href="/practic7/update/car/car-edit.jsp">ADD</a>
	</p>
</body>
</html>