<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

	<div class="dataFrame largeFrame">
		<h1>
			<fmt:message key="userListPage.header.title" bundle="${msgs}" />
		</h1>

		<table border="1">
			<tr>
				<th>
					<fmt:message key="userListPage.userGrid.id.header" bundle="${msgs}" />
				</th>
				<th>
					<fmt:message key="userListPage.userGrid.userName.header" bundle="${msgs}" />
				</th>
				<th>
					<fmt:message key="userListPage.userGrid.password.header" bundle="${msgs}" />
				</th>
				<th>
					<fmt:message key="userListPage.userGrid.actions.header" bundle="${msgs}" />
				</th>
			</tr>

			<c:forEach items="${all_users_attr}" var="user">
				<tr>
					<td>
						<c:out value="${user.id}" />
					</td>
					<td>
						<c:out value="${user.userName}" />
					</td>
					<td>
						<c:out value="${user.password}" />
					</td>

					<td class="actionsColl">
						<c:url value="userList.html" var="update_url">
							<c:param name="action" value="update_user" />
							<c:param name="user_id" value="${user.id}" />
						</c:url>

						<a href="${update_url}">
							<fmt:message key="userListPage.userGrid.actions.updateAction" bundle="${msgs}" />
						</a> 

						&nbsp;&nbsp;

						<c:url value="userList.html" var="delete_url">
							<c:param name="action" value="delete_user" />
							<c:param name="user_id" value="${user.id}" />
						</c:url>

						<a href="${delete_url}">
							<fmt:message key="userListPage.userGrid.actions.deleteAction" bundle="${msgs}" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<br />
		<hr />
		<br />

		<div class="dataFrame shortFrame">
			<h1>
				<fmt:message key="userListPage.saveUserForm.title" bundle="${msgs}" />
			</h1>

			<form method="post" action="userList.html" >
				<input type="hidden" name="action" value="save_user" />

				<input type="hidden" name="hdn_user_id" value="<c:out value='${current_user.id}' default=''  />" />

				<fmt:message key="userListPage.saveUserForm.userName.label" bundle="${msgs}" />
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

				<fmt:message key="userListPage.saveUserForm.password.label" bundle="${msgs}" />
				<br />
				<input type="text" id="txt_password" name="txt_password" value="<c:out value='${current_user.password}' default=''/>" />

				<c:if test="${validation_errors != null}">
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
				<input type="submit" name="login_btn" value="<fmt:message key='userListPage.saveUserForm.save.button' bundle='${msgs}' />" />
			</form>
		</div>
	</div>
</body>
</html>
