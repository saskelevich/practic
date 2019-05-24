<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table align="left">
		<tr>
			<td><a href="/practic7/update/brand/brands.jsp">Brands</a></td>
			<td><a href="/practic7/update/model/models.jsp">Models</a></td>
			<td><a href="/practic7/update/car/cars.jsp">Cars</a></td>
		</tr>	
	</table>
	
	<table align="right">
		<tr>
			<td>BRAND</td>
			<td>	
				<form action="/servlet/brands" method="get">
					<input type="text" name="brand" title="brand">
					<input type="submit" value="SEARCH">
				</form>
			</td>
		</tr>
		<tr>
			<td>MODEL</td>
			<td>	
				<form action="/servlet/models" method="get">
					<input type="text" name="model" title="model">
					<input type="submit" value="SEARCH">
				</form>
			</td>
		</tr>
		<tr>
			<td>CAR</td>
			<td>	
				<form action="/servlet/cars" method="get">
					<input type="text" name="car" title="car">
					<input type="submit" value="SEARCH">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>