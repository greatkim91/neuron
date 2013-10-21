
<div class="fieldcontain ${hasErrors(bean: newUser, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${newUser?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: newUser, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField name="password" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: newUser, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${newUser?.name}"/>
</div>

<g:hiddenField name="path_id" value="${params.path_id}"/>
<g:hiddenField name="email" value="${params.email}"/>
