<%@ page import="smoothingmethod.example.Example" %>



<div class="fieldcontain ${hasErrors(bean: exampleInstance, field: 'stringVal', 'error')} ">
	<label for="stringVal">
		<g:message code="example.stringVal.label" default="String Val" />
		
	</label>
	<g:textField name="stringVal" value="${exampleInstance?.stringVal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: exampleInstance, field: 'longVal', 'error')} required">
	<label for="longVal">
		<g:message code="example.longVal.label" default="Long Val" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="longVal" type="number" value="${exampleInstance.longVal}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: exampleInstance, field: 'doubleVal', 'error')} required">
	<label for="doubleVal">
		<g:message code="example.doubleVal.label" default="Double Val" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="doubleVal" value="${fieldValue(bean: exampleInstance, field: 'doubleVal')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: exampleInstance, field: 'dateVal', 'error')} required">
	<label for="dateVal">
		<g:message code="example.dateVal.label" default="Date Val" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateVal" precision="day"  value="${exampleInstance?.dateVal}"  />
</div>

