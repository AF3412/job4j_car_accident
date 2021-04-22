<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Edit accident</h1>
<form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td><label for="accident_id">id</label>
                <input id="accident_id" type='number' name='id' value="${accident.id}" disabled>
            </td>
        </tr>
        <tr>
            <td><label for="accident_name">Название</label>
                <input id="accident_name" type='text' name='name' value="${accident.name}">
            </td>
        </tr>
        <tr>
            <td><label for="accident_text">Текст</label>
                <input id="accident_text" type='text' name='text' value="${accident.text}">
            </td>
        </tr>
        <tr>
            <td><label for="accident_address">Адрес</label>
                <input id="accident_address" type='text' name='address' value="${accident.address}">
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>