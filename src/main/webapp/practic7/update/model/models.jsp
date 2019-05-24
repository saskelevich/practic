<%@page import="java.util.ArrayList"%>
<%@page import="by.practic.datalayer.db.ModelDBDaoImpl"%>
<%@page import="by.practic.datalayer.entity.Model"%>
<%@page import="by.practic.datalayer.IDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Models</title>
</head>
<body bgcolor="#FFDEAD" link="#30D5C8" vlink="#990066">
	<jsp:include page="/practic7/menu.jsp"></jsp:include>
	<h1 align="center">Models page</h1>

	<%!IDao<Model, List<Model>> dao = ModelDBDaoImpl.getInstance();%>

	<%
	    List<Model> models = new ArrayList<>();
	    String name = null;
	    name = request.getParameter("name");
	    if (name != null) {
	        models = dao.findByName(name);
	        request.setAttribute("models", models);
	    } else {
	        Integer brandId = null;
	        try {
	            brandId = Integer.valueOf(request.getParameter("brandId"));
	        } catch (NumberFormatException exc) {

	        }
	        if (brandId != null) {
	            models = dao.find(brandId);
	            request.setAttribute("models", models);
	        } else {
	            models = dao.getAll();
	            request.setAttribute("models", models);
	        }
	    }
	%>

	<table border="10px" bordercolor="#DDBE8D" bgcolor="#FFF"
		cellpadding="5" align="center">
		<tr bgcolor="#666" align="center">
			<th>ID</th>
			<th>NAME</th>
			<th>CREATED</th>
			<th>UPDATED</th>
			<th>BRAND_ID</th>
			<th>ACTIONS</th>
			<th>CARS</th>
		</tr>

		<c:forEach items="${models}" var="item">
			<tr bgcolor="#FFF" align="center">
				<td>#${item.id}</td>
				<td>${item.name}</td>
				<td>${item.created}</td>
				<td>${item.updated}</td>
				<td>${item.brandId}</td>
				<td><a
					href="/practic7/update/model/model-edit.jsp?id=${item.id}">edit
						| </a> <a href="/servlet/models?action=delete&id=${item.id}">delete</a></td>
				<td><a href="/practic7/update/car/cars.jsp?modelId=${item.id}">cars</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	<p align="center">
		<a href="/practic7/update/model/model-edit.jsp">ADD</a>
	</p>
</body>
</html>