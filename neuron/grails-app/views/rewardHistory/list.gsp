
<%@ page import="com.kkk.neuron.quest.RewardHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rewardHistory.label', default: 'RewardHistory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rewardHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rewardHistory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="date" title="${message(code: 'rewardHistory.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'rewardHistory.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="note" title="${message(code: 'rewardHistory.note.label', default: 'Note')}" />
					
						<g:sortableColumn property="amount" title="${message(code: 'rewardHistory.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="balance" title="${message(code: 'rewardHistory.balance.label', default: 'Balance')}" />
					
						<th><g:message code="rewardHistory.reward.label" default="Reward" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rewardHistoryInstanceList}" status="i" var="rewardHistoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rewardHistoryInstance.id}">${fieldValue(bean: rewardHistoryInstance, field: "date")}</g:link></td>
					
						<td>${fieldValue(bean: rewardHistoryInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: rewardHistoryInstance, field: "note")}</td>
					
						<td>${fieldValue(bean: rewardHistoryInstance, field: "amount")}</td>
					
						<td>${fieldValue(bean: rewardHistoryInstance, field: "balance")}</td>
					
						<td>${fieldValue(bean: rewardHistoryInstance, field: "reward")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rewardHistoryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
