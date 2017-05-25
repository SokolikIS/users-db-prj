<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>


<fmt:setBundle basename="com.skillsimprover.usersdb.messages.msgs" var="msgs" />

<html>
<head>
	<meta charset="utf-8">

	<link rel="stylesheet" href="<%=application.getContextPath() %>/css/main.css" type="text/css">

	<title>
		<fmt:message key="application.title" bundle="${msgs}" />
	</title>
</head>
<body>
	<div class="dataFrame shortFrame">
		<h1>
			<fmt:message key="loginPage.header.title" bundle="${msgs}" />
		</h1>

		<sf:form action="auth.html" method="post" modelAttribute="user">
			<sf:input path="userName" />
			<br />
			<br />
			<sf:input path="password" />

			<br />
			<br />
			<input type="submit" name="login_btn" value="<fmt:message key='loginPage.signIn.button' bundle='${msgs}' />" />
		</sf:form>

		<%-- <form method="post" action="auth.html" >

			<c:if test="${validation_errors != null}">
				<c:forEach items="${validation_errors}" var="error">
					<c:if test="${error.errorCode == 'auth.error'}">
						<span class="errorText">
							<fmt:message key="${error.errorText}" bundle="${msgs}" />
						</span>
					</c:if>
				</c:forEach>
			</c:if>

			<br />

			<input type="hidden" name="action" value="login" />

			<fmt:message key="loginPage.userName.label" bundle="${msgs}" />
			<br />
			<input type="text" id="txt_login" name="txt_login" value="<c:out value='${current_user.userName}' default=''  />" />

			<c:if test="${validation_errors != null}">
				<c:forEach items="${validation_errors}" var="error">
					<c:if test="${error.errorCode == 'userName.error'}">
						<span class="errorText">
							<fmt:message key="${error.errorText}" bundle="${msgs}" />
						</span>
					</c:if>
				</c:forEach>
			</c:if>

			<br />
			<br />

			<fmt:message key="loginPage.password.label" bundle="${msgs}" />
			<br />
			<input type="text" id="txt_password" name="txt_password" />

			<c:if test="${not empty validation_errors}">
				<c:forEach items="${validation_errors}" var="error">
					<c:if test="${error.errorCode == 'password.error'}">
						<span class="errorText">
							<fmt:message key="${error.errorText}" bundle="${msgs}" />
						</span>
					</c:if>
				</c:forEach>
			</c:if>

			<br />
			<br />
			<input type="submit" name="login_btn" value="<fmt:message key='loginPage.signIn.button' bundle='${msgs}' />" />
		</form> --%>
	</div>
</body>
</html>