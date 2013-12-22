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

    <div class="content">

        <h1><g:message code="task.title.label" /></h1><br/>

        <div class="container">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:link action="create"><button class="btn btn-success"><g:message code="task.create.label" /></button></g:link>
            <br/><br/>

            <table style="width: 600px;">
                <tr>
                    <g:sortableColumn property="name" title="${message(code: 'task.name.label')}" />
                    <th style="width: 160px;"><g:message code="task.taskData.count.label" /></th>
                    <th></th>
                    <th></th>
                </tr>
                <g:each in="${taskInstanceList}" var="task">
                    <tr>
                        <td>
                            <g:link action="show" id="${task.id}"><g:fieldValue field="name" bean="${task}"/></g:link>
                        </td>
                        <td class="text-center">${task.data.size()}</td>
                        <td>
                            <g:link action="edit" id="${task.id}" title="${message(code: 'default.button.edit.label')}">
                                <span class="glyphicon glyphicon-edit"></span>
                            </g:link>
                        </td>
                        <td>
                            <g:link action="delete" id="${task.id}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message')}');" title="${message(code: 'default.button.delete.label')}">
                                <span class="glyphicon glyphicon-trash"></span>
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </table>
        </div>

    </div>

</body>
</html>