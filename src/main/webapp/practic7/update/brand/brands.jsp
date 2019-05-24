<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="by.practic.datalayer.db.BrandDBDaoImpl"%>
<%@page import="by.practic.datalayer.entity.Brand"%>
<%@page import="by.practic.datalayer.IDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Brands</title>
</head>
<body bgcolor="#FFDEAD" link="#30D5C8" vlink="#990066">
	<jsp:include page="/practic7/menu.jsp"></jsp:include>
	<h1 align="center">Brands page</h1>

	<%!IDao<Brand, Brand> dao = BrandDBDaoImpl.getInstance();%>

	<%
	    List<Brand> brands = new ArrayList<>();
	    String name = null;
	    name = request.getParameter("name");
	    if (name != null) {
	        brands.add(dao.findByName(name));
	        request.setAttribute("brands", brands);
	    } else {
	        brands = dao.getAll();
	        request.setAttribute("brands", brands);
	    }
	%>

	<table border="10px" bordercolor="#DDBE8D" bgcolor="#FFF"
		cellpadding="5" align="center">
		<tr bgcolor="#666" align="center">
			<th>ID</th>
			<th>NAME</th>
			<th>CREATED</th>
			<th>UPDATED</th>
			<th>ACTIONS</th>
			<th>MODELS</th>
		</tr>

		<c:forEach items="${brands}" var="item">
			<tr bgcolor="#FFF" align="center">
				<td>#${item.id}</td>
				<td>${item.name}</td>
				<td>${item.created}</td>
				<td>${item.updated}</td>
				<td><a
					href="/practic7/update/brand/brand-edit.jsp?id=${item.id}">edit
						| </a> <a href="/servlet/brands?action=delete&id=${item.id}">delete</a>
				</td>
				<td><a
					href="/practic7/update/model/models.jsp?brandId=${item.id}">models</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	<p align="center">
		<a href="/practic7/update/brand/brand-edit.jsp">ADD</a>
	</p>
</body>
</html>