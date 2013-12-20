%{--
 * @author phpusr
 * Date: 20.12.13
 * Time: 10:08
--}%

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="task.title.label" /></title>
</head>

<body>

    <h1><g:message code="task.title.label" /></h1>

    <ul>
        <g:each in="${taskInstanceList}" var="task">
            <li><g:link action="show"><g:fieldValue field="name" bean="${task}"/></g:link></li>
        </g:each>
    </ul>

</body>
</html>