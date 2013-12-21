<%@ page import="smoothingmethod.method.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="task.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${taskInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'titleTitle', 'error')} required">
    <label for="titleTitle">
        <g:message code="task.titleTitle.label" default="Title Title"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="titleTitle" required="" value="${taskInstance?.titleTitle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'titleValue', 'error')} required">
    <label for="titleValue">
        <g:message code="task.titleValue.label" default="Title Value"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="titleValue" required="" value="${taskInstance?.titleValue}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'titleForecast', 'error')} required">
    <label for="titleForecast">
        <g:message code="task.titleForecast.label" default="Title Forecast"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="titleForecast" required="" value="${taskInstance?.titleForecast}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'smoothingParameter', 'error')} required">
    <label for="smoothingParameter">
        <g:message code="task.smoothingParameter.label" default="Smoothing Parameter"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="smoothingParameter" value="${fieldValue(bean: taskInstance, field: 'smoothingParameter')}"
             required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'data', 'error')} ">
    <label for="data">
        <g:message code="task.data.label" default="Data"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${taskInstance?.data ?}" var="d">
            <li><g:link controller="taskData" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="taskData" action="create"
                    params="['task.id': taskInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'taskData.label', default: 'TaskData')])}</g:link>
        </li>
    </ul>

</div>

