%{--
 * @author phpusr
 * Date: 20.12.13
 * Time: 10:08
--}%

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

    <h1>Test <u>respond</u></h1>
    taskInstance: ${taskInstance}<br/>
    stringInstance: ${stringInstance}<br/>
    objectInstance: ${objectInstance}<br/>
    taskDataInstance: ${taskDataInstance}<br/>

    <ul>
        <g:each in="${taskInstanceList}" var="task">
            <li><g:link action="show"><g:fieldValue field="name" bean="${task}"/></g:link></li>
        </g:each>
    </ul>

</body>
</html>