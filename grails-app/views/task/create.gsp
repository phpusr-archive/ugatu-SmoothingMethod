<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'task.create.label')}"/>
    <title>${entityName}</title>
    <r:require module="angularApp" />
</head>

<body>
<a href="#create-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div ng-app="app" id="create-task" class="content scaffold-create" role="main" ng-controller="TaskCreateController">
    <h1>${entityName}</h1>

    <fieldset class="form">
        <g:render template="form"/>
    </fieldset>
    <fieldset class="buttons">
        <input type="button" class="save"
               ng-click="save('${g.createLink(action: 'save')}', '${g.createLink(action: 'list')}')"
               value="${message(code: 'default.button.create.label')}" />
    </fieldset>
</div>

</body>
</html>
