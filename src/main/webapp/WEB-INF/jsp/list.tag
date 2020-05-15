<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="pbArray" items="${pbArray}">
    <tr>
        <td><a href="/playerinfo?name=${pbArray.name}"> <c:out value="${pbArray.name}"/></a></td>
    </tr>
</c:forEach>