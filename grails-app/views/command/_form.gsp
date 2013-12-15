<%@ page import="commandagent.Command" %>



<div class="fieldcontain ${hasErrors(bean: commandInstance, field: 'command', 'error')} ">
	<label for="command">
		<g:message code="command.command.label" default="Command" />
		
	</label>
	<g:select name="command" from="${commandInstance.constraints.command.inList}" value="${commandInstance?.command}" valueMessagePrefix="command.command" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commandInstance, field: 'commandParam', 'error')} ">
	<label for="commandParam">
		<g:message code="command.commandParam.label" default="Command Param" />
		
	</label>
	<g:textField name="commandParam" value="${commandInstance?.commandParam}"/>
</div>

