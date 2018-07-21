<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:url value ="FrontController?section=include&action=navbar" var="urlNavbar"/> 
        <c:import url ="${urlNavbar}"/>         
        <article>
            <h1>Fiche produit</h1>
            <img src="${product.picture}" class="meal"/>
            <p class="product-name">${product.name}</p>
            <p>
                ${product.description}<br />
                Prix : ${product.priceWithVAT} €<br />
            </p>
            <c:url value="FrontController?section=product-choice&product-id=${product.id}" var="url04" />
            <a class="button" href="${url04}">Choisir</a><br />
            <h4>
                Propriétés    
            </h4>
            <c:forEach var = "pty" items="${properties}">
                <p>
                    ${pty.name} : ${pty.value} ${pty.unit.abbreviation}
                </p><br />
            </c:forEach>
        </article>
    </body>
</html>
