
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>[Register]</title>
	</head>
	<body>
		<div id="register" class="register">
			<g:hasErrors bean="${newUser}">
			<ul class="errors" role="alert">
				<g:eachError bean="${newUser}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
			<g:form action="register" method="post">
				<g:render template="form" />
				<g:submitButton name="register" value="Register"/>
			</g:form>
		</div>
	</body>
</html>
