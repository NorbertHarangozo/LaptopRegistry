<%--
  Created by IntelliJ IDEA.
  User: norbi
  Date: 2020. 12. 05.
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>New laptop</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div style="margin: auto; max-width: 800px;">
        <f:form method="post" action="NewLaptop" modelAttribute="laptop">
            <table class="table table-bordered table-striped">
                <tr>
                    <td><f:label path="manufacturer">Manufacturer</f:label></td>
                    <td>
                        <f:select path="manufacturer">
                            <f:options items="${manufacturers}"/>
                        </f:select>
                    </td>
                </tr>
                <tr><td><f:label path="name">Name</f:label></td><td><f:input path="name" required="required"/></td></tr>
                <tr>
                    <td><f:label path="color">Color</f:label></td>
                    <td>
                        <f:select path="color">
                            <f:options items="${colors}"/>
                        </f:select>
                    </td>
                </tr>
                <tr><td><f:label path="screenSize">Screen size</f:label></td><td><f:input path="screenSize" type="number" step="0.1" required="required"/></td></tr>
                <tr>
                    <td><f:label path="panelType">Panel type</f:label></td>
                    <td>
                        <f:select path="panelType">
                            <f:options items="${panelTypes}"/>
                        </f:select>
                    </td>
                </tr>
                <tr><td><f:label path="resolutionX">Resolution</f:label></td><td><f:input path="resolutionX" type="number" size="7" required="required"/>x<f:input path="resolutionY" type="number" size="7" required="required"/></td></tr>
                <tr><td><f:label path="refreshRate">Refresh rate (Hz)</f:label></td><td><f:input path="refreshRate" type="number" required="required"/></td></tr>
                <tr><td><f:label path="cpu">CPU</f:label></td><td><f:input path="cpu" required="required"/></td></tr>
                <tr>
                    <td><f:label path="memoryType">Memory type</f:label></td>
                    <td>
                        <f:select path="memoryType">
                            <f:options items="${memoryTypes}"/>
                        </f:select>
                    </td>
                </tr>
                <tr><td><f:label path="memorySize">Memory size (MB)</f:label></td><td><f:input path="memorySize" type="number" required="required"/></td></tr>
                <tr><td><f:label path="gpu">GPU</f:label></td><td><f:input path="gpu" required="required"/></td></tr>
                <tr>
                    <td><f:label path="storageType">Storage type</f:label></td>
                    <td>
                        <f:select path="storageType">
                            <f:options items="${storageTypes}"/>
                        </f:select>
                    </td>
                </tr>
                <tr><td><f:label path="StorageSize">Storage size (GB)</f:label></td><td><f:input path="storageSize" type="number" required="required"/></td></tr>
                <tr>
                    <td><f:label path="opticalDrive">Optical drive</f:label></td>
                    <td>
                       <f:checkbox path="opticalDrive"/>
                    </td>
                </tr>
                <tr><td><f:label path="numberOfUSBPorts">Number of USB ports</f:label></td><td><f:input path="numberOfUSBPorts" type="number" required="required"/></td></tr>
                <tr><td><f:label path="manufactureDate">Manufacturing date</f:label></td><td><f:input path="manufactureDate" type="date" required="required"/></td></tr>
            </table>
            <div style="text-align: center;">
                <input type="submit" class="btn btn-primary btn-lg" value="Add new laptop"/>
                <a href="${pageContext.request.contextPath}/Laptops"><button class="btn btn-primary btn-lg">Go back</button></a>
            </div>
        </f:form>
    </div>
</body>
</html>
