
<%@ page import="smoothingmethod.example.Example" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'example.label', default: 'Example')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-example" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-example" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="stringVal" title="${message(code: 'example.stringVal.label', default: 'String Val')}" />
					
						<g:sortableColumn property="longVal" title="${message(code: 'example.longVal.label', default: 'Long Val')}" />
					
						<g:sortableColumn property="doubleVal" title="${message(code: 'example.doubleVal.label', default: 'Double Val')}" />
					
						<g:sortableColumn property="dateVal" title="${message(code: 'example.dateVal.label', default: 'Date Val')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${exampleInstanceList}" status="i" var="exampleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${exampleInstance.id}">${fieldValue(bean: exampleInstance, field: "stringVal")}</g:link></td>
					
						<td>${fieldValue(bean: exampleInstance, field: "longVal")}</td>
					
						<td>${fieldValue(bean: exampleInstance, field: "doubleVal")}</td>
					
						<td><g:formatDate date="${exampleInstance.dateVal}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${exampleInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
