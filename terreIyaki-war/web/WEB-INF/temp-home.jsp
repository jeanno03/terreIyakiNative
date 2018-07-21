<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" />
        <title>TerreIyaki</title>
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>
        <main>           
<%--
<div>
 <c:url value="FrontController?section=dataTestJeannory" var="url01" />
<a class ="button" href="${url01}">jeu essai</a>
            </div>
--%>

            <div>

<c:url value="FrontController?section=versLogin" var="url02" />
<a class ="button" href="${url02}">Login</a> 

            </div>
<%--
            <div>
                <c:url value="FrontController?section=backoffice" var="url03" />
                <a class ="button" href="${url03}">Back office provisoire</a>
            </div>
--%>
        </main>
    </body>
</html>
