%{--
 * @author phpusr
 * Date: 20.12.13
 * Time: 12:34
--}%

<%@ page import="smoothingmethod.constants.AppConst" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="title" value="Задача: ${taskInstance.name}" />
    <title>${title}</title>
    <r:require module="angularApp" />
</head>

<body>

<g:set var="toFixed" value="toFixed(2)" />

<div ng-app="app" ng-controller="TaskShowController" style="padding: 20px;">

    <h1>${title}</h1>

    <div ng-show="hasErrors" class="alert alert-danger">{{errorMessage}}</div>

    <div style="margin: 20px 0">
        <label>a = <input type="text" ng-model="data.a" ng-change="updateView(data.a)" /></label>
    </div>

    <div>
        <table class="table table-striped table-bordered table-hover">
            <tr>
                <th rowspan="2">№</th>
                <th rowspan="2">{{data.task.titleTitle}}</th>
                <th rowspan="2">{{data.task.titleValue}}, Yt, %</th>
                <th colspan="2">Экспоненциально взвешенная средняя, Ut</th>
                <th colspan="2">Расчет средней относительной ошибки,<br/> |Yф-Yр| / Yф * 100, %</th>
            </tr>
            <tr>
                <th><g:message code="default.way.one.label" /></th>
                <th><g:message code="default.way.two.label" /></th>
                <th><g:message code="default.way.one.label" /></th>
                <th><g:message code="default.way.two.label" /></th>
            </tr>
            <tr ng-repeat="d in data.taskData">
                <td>{{$index+1}}</td>
                <td>{{d.title}}</td>
                <td>{{d.value}}</td>
                <td>{{d.expAvg1.${toFixed}}}</td>
                <td>{{d.expAvg2.${toFixed}}}</td>
                <td>{{d.relativeError1.${toFixed}}}</td>
                <td>{{d.relativeError2.${toFixed}}}</td>
            </tr>
            <tr>
                <td colspan="5" class="text-right">Итого:</td>
                <td>{{data.sumRelativeError1.${toFixed}}}</td>
                <td>{{data.sumRelativeError2.${toFixed}}}</td>
            </tr>
            <tr class="text-success">
                <td></td>
                <td>{{data.forecast.title}}</td>
                <td></td>
                <td>{{data.forecast.expAvg1.${toFixed}}}</td>
                <td>{{data.forecast.expAvg2.${toFixed}}}</td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>

    <div>
        <p><b>&epsilon;1 =</b> {{data.epsilon1.${toFixed}}}</p>
        <p><b>&epsilon;2 =</b> {{data.epsilon2.${toFixed}}}</p>
    </div>

    <div style="width: 600px; margin: 10px 0;">
        <div ng-show="data.forecastAccuracy" class="alert alert-success">
            <g:message code="task.forecast.satisfactory.message" />
            <b>${AppConst.FORECAST_ACCURACY_VALUE1}-${AppConst.FORECAST_ACCURACY_VALUE2}%</b>.
        </div>
        <div ng-hide="data.forecastAccuracy" class="alert alert-warning">
            <g:message code="task.forecast.not.satisfactory.message" />
            <b>${AppConst.FORECAST_ACCURACY_VALUE1}-${AppConst.FORECAST_ACCURACY_VALUE2}%</b>.
        </div>
    </div>

</div>

<g:form url="[resource:taskInstance, action:'delete']" method="DELETE">
    <fieldset class="buttons">
        <g:link class="edit" action="edit" resource="${taskInstance}"><g:message code="default.button.edit.label" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message')}');" />
    </fieldset>
</g:form>

</body>
</html>