%{--
 * @author phpusr
 * Date: 20.12.13
 * Time: 10:08
--}%

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="task.title.label" /></title>
</head>

<body>


    <div style="margin: 20px;">

        <h1><g:message code="task.title.label" /></h1><br/>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <g:link action="create"><button class="btn btn-success"><g:message code="task.create.label" /></button></g:link>
        <br/><br/>

        <table>
            <g:each in="${taskInstanceList}" var="task">
                <tr>
                    <td><g:link action="show" id="${task.id}"><g:fieldValue field="name" bean="${task}"/></g:link></td>
                    <td><g:link action="edit" id="${task.id}"><span class="glyphicon glyphicon-edit"></span></g:link></td>
                    <td><g:link action="delete" id="${task.id}"><span class="glyphicon glyphicon-trash"></span></g:link></td>
                </tr>
            </g:each>
        </table>

    </div>

</body>
</html>