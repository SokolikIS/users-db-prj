<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<fmt:setBundle basename="com.skillsimprover.usersdb.messages.msgs" var="msgs" />

<html>
<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="css/main.css" type="text/css">

	<meta http-equiv="refresh" content="0; url=login.html">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>
<body>

	<h1>Hello</h1>

	<fmt:message key="indexPage.info" bundle="${msgs}" />
</body>
</html>
