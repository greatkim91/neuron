
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>[User Registration]</title>
	</head>
	<body>
		<div id="show-register" class="content scaffold-show" role="main">
			<h1>Success registration</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${newUser?.username}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${newUser}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${newUser?.name}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="user.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${newUser}" field="name"/></span>
					
				</li>
				</g:if>
			
		</div>
		
		<div>
		<g:link controller="path" action="show" id="${path?.id}">Go Quest!</g:link>
		</div>
		
	</body>
</html>
