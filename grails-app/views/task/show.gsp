%{--
 * @author phpusr
 * Date: 20.12.13
 * Time: 12:34
--}%

<%@ page import="smoothingmethod.constants.AppConst" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>%{--TODO--}%</title>
    <r:require module="angularApp" />
</head>

<body>
    <g:set var="toFixed" value="toFixed(2)" />
    <div ng-app="app" ng-controller="TaskShowController">
        <h1>Задача: {{data.task.name}}</h1>

        <h3>a = {{data.a}}</h3>

        <table>
            <tr>
                <th>№</th>
                <th>Заголовок</th> %{--TODO вынести в Task--}%
                <th>Значение</th>
                <th>U1</th>
                <th>U2</th>
            </tr>
            <tr ng-repeat="d in data.taskData">
                <td>{{$index+1}}</td>
                <td>{{d.title}}</td>
                <td>{{d.value}}</td>
                <td>{{d.expAvg1.${toFixed}}}</td>
                <td>{{d.expAvg2.${toFixed}}}</td>
                <td>{{d.avgError1.${toFixed}}}</td>
                <td>{{d.avgError2.${toFixed}}}</td>
            </tr>
            <tr>
                <td colspan="5">Итого:</td>
                <td>{{data.sumAvgError1.${toFixed}}}</td>
                <td>{{data.sumAvgError2.${toFixed}}}</td>
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

        <h4 ng-show="data.forecastAccuracy">В каждом случае точность прогноза является удовлетворительной, поскольку средняя относительная ошибка попадает в пределы ${AppConst.FORECAST_ACCURACY_VALUE1}-${AppConst.FORECAST_ACCURACY_VALUE2}%.</h4>
        <h4 ng-hide="data.forecastAccuracy">Не в каждом случае точность прогноза является удовлетворительной, поскольку средняя относительная ошибка не попадает в пределы ${AppConst.FORECAST_ACCURACY_VALUE1}-${AppConst.FORECAST_ACCURACY_VALUE2}%.</h4>
    </div>
</body>
</html>