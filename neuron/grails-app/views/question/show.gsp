
<%@ page import="com.kkk.neuron.quest.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-question" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list question">
			
				<g:if test="${questionInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="question.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${questionInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="question.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${questionInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.questioner}">
				<li class="fieldcontain">
					<span id="questioner-label" class="property-label"><g:message code="question.questioner.label" default="Questioner" /></span>
					
						<span class="property-value" aria-labelledby="questioner-label"><g:link controller="user" action="show" id="${questionInstance?.questioner?.id}">${questionInstance?.questioner?.name?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="question.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${questionInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="question.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${questionInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.periodFrom}">
				<li class="fieldcontain">
					<span id="periodFrom-label" class="property-label"><g:message code="question.periodFrom.label" default="Period From" /></span>
					
						<span class="property-value" aria-labelledby="periodFrom-label"><g:formatDate date="${questionInstance?.periodFrom}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.periodTo}">
				<li class="fieldcontain">
					<span id="periodTo-label" class="property-label"><g:message code="question.periodTo.label" default="Period To" /></span>
					
						<span class="property-value" aria-labelledby="periodTo-label"><g:formatDate date="${questionInstance?.periodTo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.reward}">
				<li class="fieldcontain">
					<span id="reward-label" class="property-label"><g:message code="question.reward.label" default="Reward" /></span>
					
						<span class="property-value" aria-labelledby="reward-label"><g:fieldValue bean="${questionInstance}" field="reward"/></span>
					
				</li>
				</g:if>
				
				<li class="fieldcontain">
				<span id="delivery-label" class="property-label">Delivery</span>
				<span class="property-value" aria-labelledby="reward-label">
					<g:each in="${pathInstances}" var="pathInstance">
						<g:fieldValue bean="${pathInstance.user}" field="name"/> (<g:fieldValue bean="${pathInstance}" field="reward"/>)
					</g:each>
				</span>
				</li>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${questionInstance?.id}" />
					<g:link class="edit" action="edit" id="${questionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
		
		<div>
			<span>
			<ul>
				<g:each in="${pathInstances}" var="pathInstance">
				<g:if test="${pathInstance.answered == true}">
				<li>
					<g:fieldValue bean="${pathInstance}" field="answer"/> |
					<g:fieldValue bean="${pathInstance.user}" field="name"/> |
					<g:if test="${pathInstance.chosen == true}">★Chosen★</g:if>
					<g:form action="choose" method="post">
						<g:hiddenField name="id" value="${questionInstance?.id}" />
						<g:hiddenField name="path_id" value="${pathInstance.id}" />
						<g:submitButton action="choose" name="choose" value="Choose"/>
					</g:form>
					<hr/>
				</li>
				</g:if>
				</g:each>
			</ul>
			</span>
		</div>
		
		<div>
			<g:form action="deliver" method="post">
				<g:hiddenField name="id" value="${questionInstance?.id}" />
				<g:textField name="next_username"/>
				<g:actionSubmit action="deliver" value="Deliver"/>
			</g:form>
		</div>
		
	</body>
</html>
