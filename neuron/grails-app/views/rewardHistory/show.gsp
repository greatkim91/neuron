
<%@ page import="com.kkk.neuron.quest.RewardHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rewardHistory.label', default: 'RewardHistory')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rewardHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rewardHistory" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rewardHistory">
			
				<g:if test="${rewardHistoryInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="rewardHistory.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${rewardHistoryInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rewardHistoryInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="rewardHistory.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${rewardHistoryInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rewardHistoryInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="rewardHistory.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${rewardHistoryInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rewardHistoryInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="rewardHistory.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${rewardHistoryInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rewardHistoryInstance?.balance}">
				<li class="fieldcontain">
					<span id="balance-label" class="property-label"><g:message code="rewardHistory.balance.label" default="Balance" /></span>
					
						<span class="property-value" aria-labelledby="balance-label"><g:fieldValue bean="${rewardHistoryInstance}" field="balance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rewardHistoryInstance?.reward}">
				<li class="fieldcontain">
					<span id="reward-label" class="property-label"><g:message code="rewardHistory.reward.label" default="Reward" /></span>
					
						<span class="property-value" aria-labelledby="reward-label"><g:link controller="reward" action="show" id="${rewardHistoryInstance?.reward?.id}">${rewardHistoryInstance?.reward?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rewardHistoryInstance?.id}" />
					<g:link class="edit" action="edit" id="${rewardHistoryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
