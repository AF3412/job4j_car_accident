<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Create accident</h1>
<form  action="<c:url value='/save' />" method='POST'>
    <table>
        <tr>
            <td><label for="accident_name">Название</label></td>
            <td><input id="accident_name" type='text' name='name'></td>
        </tr>
        <tr>
            <td><label for="accident_text">Текст</label></td>
            <td><input id="accident_text" type='text' name='text'></td>
        </tr>
        <tr>
            <td><label for="accident_address">Адрес</label></td>
            <td>    <input id="accident_address" type='text' name='address'></td>
        </tr>
        <tr>
            <td><label for="type_id">Тип:</label></td>
            <td>
                <select id="type_id" name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>

    </table>
</form>
</body>
</html>