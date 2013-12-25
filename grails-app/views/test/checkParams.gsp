%{--
 * @author phpusr
 * Date: 19.12.13
 * Time: 15:42
--}%

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <g:form action="checkParams">
        <input type="text" name="val" value="test1" />
        <input type="text" name="val" value="test2" />

        <g:submitButton name="submit" />
    </g:form>
    <p>Check console after submit!</p>
</body>
</html>