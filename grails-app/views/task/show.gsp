%{--
 * @author phpusr
 * Date: 20.12.13
 * Time: 12:34
--}%

<%@ page import="smoothingmethod.constants.AppConst" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="title" value="Задача: ${task.name}" />
    <title>${title}</title>
    <r:require module="angularApp" />
</head>

<body>

<g:set var="toFixed" value="toFixed(2)" />

<div ng-app="app" ng-controller="TaskShowController">
    <h1>${title}</h1>

    <label>a = <input type="text" ng-model="data.a" ng-change="updateView(data.a)" /></label>

    <table>
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
            <td colspan="5">Итого:</td>
            <td>{{data.sumRelativeError1.${toFixed}}}</td>
            <td>{{data.sumRelativeError2.${toFixed}}}</td>
        </tr>
        <tr>
            <td></td>
            <td>{{data.forecast.title}}</td>
            <td></td>
            <td>{{data.forecast.expAvg1.${toFixed}}}</td>
            <td>{{data.forecast.expAvg2.${toFixed}}}</td>
        </tr>
    </table>

    <h3>&epsilon;1 = {{data.epsilon1.${toFixed}}}</h3>
    <h3>&epsilon;2 = {{data.epsilon2.${toFixed}}}</h3>

    <h4 ng-show="data.forecastAccuracy"><g:message code="task.forecast.satisfactory.message" /> ${AppConst.FORECAST_ACCURACY_VALUE1}-${AppConst.FORECAST_ACCURACY_VALUE2}%.</h4>
    <h4 ng-hide="data.forecastAccuracy"><g:message code="task.forecast.not.satisfactory.message" /> ${AppConst.FORECAST_ACCURACY_VALUE1}-${AppConst.FORECAST_ACCURACY_VALUE2}%.</h4>
</div>

</body>
</html>