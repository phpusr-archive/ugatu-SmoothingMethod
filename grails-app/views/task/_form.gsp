<%@ page import="smoothingmethod.method.Task" %>

<div ng-show="hasErrors" class="alert alert-danger">Обнаружены ошибки при заполнении</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="task.name.label" default="Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" ng-model="task.name" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'titleTitle', 'error')} required">
    <label for="titleTitle">
        <g:message code="task.titleTitle.label" default="Title Title" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="titleTitle" required="" ng-model="task.titleTitle" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'titleValue', 'error')} required">
    <label for="titleValue">
        <g:message code="task.titleValue.label" default="Title Value" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="titleValue" required="" ng-model="task.titleValue" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'titleForecast', 'error')} required">
    <label for="titleForecast">
        <g:message code="task.titleForecast.label" default="Title Forecast" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="titleForecast" required="" ng-model="task.titleForecast" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'smoothingParameter', 'error')} required">
    <label for="smoothingParameter">
        <g:message code="task.smoothingParameter.label" default="Smoothing Parameter" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="" name="smoothingParameter" ng-model="task.smoothingParameter" required="" />
</div>

<div class="fieldcontain required">
    <label for="countData">
        <g:message code="task.countData.label" default="Count" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="countData" required="" ng-model="countData" ng-change="changeCountData()" />
</div>

<div style="margin: 20px 0; width: 500px;" ng-show="countData > 0">

    <h3>Данные задачи</h3>
    <br/>

    <table>
        <tr>
            <th>№</th> %{--TODO--}%
            <th>Заголовок</th>
            <th>Значение</th>
        </tr>
        <tr ng-repeat="d in taskData ">
            <td>{{$index+1}}</td>
            <td><input type="text" ng-model="d.title" /></td>
            <td><input type="text" ng-model="d.value" /></td>
        </tr>
    </table>

</div>

<g:createLink action="save" />
<button ng-click="save('${g.createLink(action: 'save')}', '${g.createLink(action: 'list')}')" onclick="return false">Save</button>

