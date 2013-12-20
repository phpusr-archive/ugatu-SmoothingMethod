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
        <h1>Задача: ${taskInstance?.name}</h1>

        <table>
            <tr>
                <th>Заголовок</th> %{--TODO вынести в Task--}%
                <th>Значение</th>
            </tr>
            <tr ng-repeat="d in data.data">
                <td>{{d.title}}</td>
                <td>{{d.value}}</td>
            </tr>
        </table>
    </div>
</body>
</html>