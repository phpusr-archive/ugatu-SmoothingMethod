
<%@ page import="smoothingmethod.example.Example" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'example.label', default: 'Example')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-example" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-example" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list example">
			
				<g:if test="${exampleInstance?.stringVal}">
				<li class="fieldcontain">
					<span id="stringVal-label" class="property-label"><g:message code="example.stringVal.label" default="String Val" /></span>
					
						<span class="property-value" aria-labelledby="stringVal-label"><g:fieldValue bean="${exampleInstance}" field="stringVal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${exampleInstance?.longVal}">
				<li class="fieldcontain">
					<span id="longVal-label" class="property-label"><g:message code="example.longVal.label" default="Long Val" /></span>
					
						<span class="property-value" aria-labelledby="longVal-label"><g:fieldValue bean="${exampleInstance}" field="longVal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${exampleInstance?.doubleVal}">
				<li class="fieldcontain">
					<span id="doubleVal-label" class="property-label"><g:message code="example.doubleVal.label" default="Double Val" /></span>
					
						<span class="property-value" aria-labelledby="doubleVal-label"><g:fieldValue bean="${exampleInstance}" field="doubleVal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${exampleInstance?.dateVal}">
				<li class="fieldcontain">
					<span id="dateVal-label" class="property-label"><g:message code="example.dateVal.label" default="Date Val" /></span>
					
						<span class="property-value" aria-labelledby="dateVal-label"><g:formatDate date="${exampleInstance?.dateVal}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:exampleInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${exampleInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
