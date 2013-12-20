%{--
 * @author phpusr
 * Date: 20.12.13
 * Time: 12:34
--}%

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>%{--TODO--}%</title>
    <r:require module="angularApp" />
</head>

<body>
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
                <td>{{d.expAvg1.toFixed(2)}}</td>
                <td>{{d.expAvg2.toFixed(2)}}</td>
                <td>{{d.avgError1.toFixed(2)}}</td>
                <td>{{d.avgError2.toFixed(2)}}</td>
            </tr>
            <tr>
                <td></td>
                <td>{{data.last.title}}</td>
                <td>{{data.last.value}}</td>
                <td>{{data.last.expAvg1.toFixed(2)}}</td>
                <td>{{data.last.expAvg2.toFixed(2)}}</td>
            </tr>
        </table>
    </div>
</body>
</html>