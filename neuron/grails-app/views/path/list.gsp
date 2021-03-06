
<%@ page import="com.kkk.neuron.quest.Path" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'path.label', default: 'Path')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-quest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-path" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'quest.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="content" title="${message(code: 'quest.content.label', default: 'Content')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'quest.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'quest.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="periodFrom" title="${message(code: 'quest.periodFrom.label', default: 'Period From')}" />
					
						<th><g:message code="quest.owner.label" default="Owner" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${pathInstanceList}" status="i" var="pathInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pathInstance.id}">${fieldValue(bean: pathInstance.quest, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: pathInstance.quest, field: "content")}</td>
					
						<td><g:formatDate date="${pathInstance.quest.dateCreated}" /></td>
					
						<td><g:formatDate date="${pathInstance.quest.lastUpdated}" /></td>
					
						<td><g:formatDate date="${pathInstance.quest.periodFrom}" /></td>
						
						<td>${fieldValue(bean: pathInstance.quest, field: "owner.name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pathInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
