<%@page import="by.practic.datalayer.db.ModelDBDaoImpl"%>
<%@page import="by.practic.datalayer.entity.Model"%>
<%@page import="by.practic.datalayer.IDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Models</title>
</head>
<body bgcolor="#FFDEAD" link="#30D5C8" vlink="#990066">
<jsp:include page="/practic7/menu.jsp"></jsp:include>
<h1 align="center">Models update</h1>

<%!IDao<Model, List<Model>> dao = ModelDBDaoImpl.getInstance();%>

<%
    String id = request.getParameter("id");
    if (id != null && id != "") {
    	Model model = dao.get(Integer.valueOf(id));
        request.setAttribute("idValue", model.getId());
        request.setAttribute("nameValue", model.getName());
        request.setAttribute("brandId", model.getBrandId());
    }
%>

<form action="/servlet/models" method="post">
	<input type="hidden" name="id" value="${idValue}">
	<table>
		<tr>
			<td>NAME</td>
			<td><input type="text" name="name" title="name" value="${nameValue}"></td>
			<td><input type="text" name="brandId" title="brandId" value="${brandId}"></td>
		</tr>
	</table>
	<input type="submit" value="SAVE">
</form>
</body>
</html>