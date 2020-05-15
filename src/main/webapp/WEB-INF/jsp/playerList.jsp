<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Person List</title>
</head>
<body>
<h1>Person List</h1>

<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>Tam Name</th>
            <th>Team City</th>
        </tr>
        <c:forEach  items="${list}" var ="player">
            <tr>
                <td>${player.teamName}</td>
                <td>${player.teamCity}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>
