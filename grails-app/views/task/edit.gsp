<%@ page import="smoothingmethod.method.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'task.edit.label')}"/>
    <title>${entityName}</title>
    <r:require module="angularApp" />
</head>

<body>
<a href="#edit-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div ng-app="app" id="edit-task" class="content scaffold-edit" role="main" ng-controller="TaskEditController">
    <h1>${entityName}</h1>

    <fieldset class="form">
        <g:render template="form"/>
    </fieldset>
    <fieldset class="buttons">
        <input type="button" class="save"
               ng-click="update('${createLink(action: 'update')}', '${createLink(action: 'show')}')"
               value="${message(code: 'default.button.update.label')}" />
    </fieldset>
</div>

</body>
</html>
