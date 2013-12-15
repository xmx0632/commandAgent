
<%@ page import="commandagent.Command" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'command.label', default: 'Command')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-command" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-command" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list command">
			
				<g:if test="${commandInstance?.command}">
				<li class="fieldcontain">
					<span id="command-label" class="property-label"><g:message code="command.command.label" default="Command" /></span>
					
						<span class="property-value" aria-labelledby="command-label"><g:fieldValue bean="${commandInstance}" field="command"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${commandInstance?.commandParam}">
				<li class="fieldcontain">
					<span id="commandParam-label" class="property-label"><g:message code="command.commandParam.label" default="Command Param" /></span>
					
						<span class="property-value" aria-labelledby="commandParam-label"><g:fieldValue bean="${commandInstance}" field="commandParam"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${commandInstance?.id}" />
					<g:link class="edit" action="edit" id="${commandInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
