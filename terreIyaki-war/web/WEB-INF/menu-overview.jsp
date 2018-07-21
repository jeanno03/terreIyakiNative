<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TerreIyaki</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>
        <c:url value ="FrontController?section=include&action=navbar" var="urlNavbar"/> 
        <c:import url ="${urlNavbar}"/>        
        <main>
            <h1>A la carte</h1>
            <section>
                <c:forEach var = "cat" items="${categories}">
                    <c:url value="FrontController?section=overview&category-id=${cat.id}" var="url03" />
                    <a class="button1" href="${url03}">${cat.name}</a>
                </c:forEach>
            </section>
        </main>
    </body>
</html>
