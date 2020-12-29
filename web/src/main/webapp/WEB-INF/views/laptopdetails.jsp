<%--
  Created by IntelliJ IDEA.
  User: norbi
  Date: 2020. 12. 03.
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${laptop.manufacturer} ${laptop.name} - ${laptop.serialNumber}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div style="margin: auto; max-width: 550px;">
        <table class="table table-bordered table-hover table-striped">
            <tr><td>Serial number</td><td>${laptop.serialNumber}</td></tr>
            <tr><td>Manufacturer</td><td>${laptop.manufacturer}</td></tr>
            <tr><td>Name</td><td>${laptop.name}</td></tr>
            <tr><td>Color</td><td>${laptop.color}</td></tr>
            <tr><td>Screen size</td><td>${laptop.screenSize}"</td></tr>
            <tr><td>Panel type</td><td>${laptop.panelType}</td></tr>
            <tr><td>Resolution</td><td>${laptop.resolutionX}x${laptop.resolutionY}</td></tr>
            <tr><td>Refresh rate</td><td>${laptop.refreshRate} Hz</td></tr>
            <tr><td>CPU</td><td>${laptop.cpu}</td></tr>
            <tr><td>Memory type</td><td>${laptop.memoryType}</td></tr>
            <tr><td>Memory size</td><td>${laptop.memorySize} MB</td></tr>
            <tr><td>GPU</td><td>${laptop.gpu}</td></tr>
            <tr><td>Storage type</td><td>${laptop.storageType}</td></tr>
            <tr><td>Storage size</td><td>${laptop.storageSize} GB</td></tr>
            <tr><td>Optical drive</td>
                <td>
                    <c:if test="${laptop.opticalDrive}">Yes</c:if>
                    <c:if test="${!laptop.opticalDrive}">No</c:if>
                </td>
            </tr>
            <tr><td>Number of USB ports</td><td>${laptop.numberOfUSBPorts}</td></tr>
            <tr><td>Manufacturing date</td><td>${laptop.manufactureDate}</td></tr>
        </table>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/UpdateLaptop/${laptop.serialNumber}"><button class="btn btn-primary btn-lg">Change details</button></a>
            <a href="${pageContext.request.contextPath}/Laptops"><button class="btn btn-primary btn-lg">Go back</button></a>
        </div>
    </div>
</body>
</html>
