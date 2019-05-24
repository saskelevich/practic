<%@page import="by.practic.datalayer.db.BrandDBDaoImpl"%>
<%@page import="by.practic.datalayer.entity.Brand"%>
<%@page import="by.practic.datalayer.IDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Brands</title>
</head>
<body bgcolor="#FFDEAD" link="#30D5C8" vlink="#990066">
<jsp:include page="/practic7/menu.jsp"></jsp:include>
<h1 align="center">Brands update</h1>

<%!IDao<Brand, Brand> dao = BrandDBDaoImpl.getInstance();%>

<%
    String id = request.getParameter("id");
    if (id != null && id != "") {
        Brand brand = dao.get(Integer.valueOf(id));
        request.setAttribute("idValue", brand.getId());
        request.setAttribute("nameValue", brand.getName());
    }
%>

<form action="/servlet/brands" method="post">
	<input type="hidden" name="id" value="${idValue}">
	<table>
		<tr>
			<td>NAME</td>
			<td><input type="text" name="name" value="${nameValue}"></td>
		</tr>
	</table>
	<input type="submit" value="SAVE">
</form>
</body>
</html>