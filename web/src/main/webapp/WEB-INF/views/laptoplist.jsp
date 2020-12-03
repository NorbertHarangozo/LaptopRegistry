<%--
  Created by IntelliJ IDEA.
  User: norbi
  Date: 2020. 12. 03.
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Laptops</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div style="margin: auto; max-width: 1200px;">
        <c:if test="${!empty laptops}">
            <table class="table table-bordered table-hover table-striped">
                <tr>
                    <th>Serial number</th><th>Manufacturer</th><th>Name</th><th>Color</th>
                    <th>Screen size</th><th>Resolution</th><th>Memory size</th><th>Storage size</th>
                </tr>
                <c:forEach items="${laptops}" var="laptop">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/Laptop/${laptop.serialNumber}">${laptop.serialNumber}</a></td><td>${laptop.manufacturer}</td><td>${laptop.name}</td><td>${laptop.color}</td>
                        <td>${laptop.screenSize}"</td><td>${laptop.resolutionX}x${laptop.resolutionY}</td><td>${laptop.memorySize} MB</td><td>${laptop.storageSize} GB</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty laptops}">
            There aren't any laptops that can be found.
        </c:if>
    </div>>
</body>
</html>
