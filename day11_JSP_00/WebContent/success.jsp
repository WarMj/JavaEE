<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	欢迎你：<%
		String name = request.getParameter("userName");
		out.print(name);
	%>
</body>
</html>