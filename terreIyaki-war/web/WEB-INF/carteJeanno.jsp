<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : carteJeanno
    Created on : 19 mars 2018, 10:29:51
    Author     : jeanno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>
        <c:url value ="FrontController?section=include&action=navbar" var="urlNavbar"/> 
        <c:import url ="${urlNavbar}"/>
        <main>
            <div>
                <h3>Nos produits</h3>     

                <c:forEach var="prod" items="${product}"><p>
                        ${prod.name} - ${prod.price}€ - ${prod.description} - Ajouter <a href="FrontController">ici</a>
                    </p>  </c:forEach>

                    <h3>Voici nos menus</h3>

                <c:forEach var="com" items="${combo}">
                    <p>${com.name} - ${com.price}€ - ${com.description}
                        <c:url value="FrontController?section=carteJeannory&action=formule&detection=itemFormul&nameCombo=${com.name}" var="url02" />
                        - <a href="${url02}">détail</a>        
                        <br/>Ajouter <a href="FrontController">ici</a></p>
                    </c:forEach>                  

                <h3>Détail sélection</h3>  


                <c:set var="nameCombo" value="${nameCombo}" scope="request" />


                <c:forEach var="comCat" items="${comboCategory}">
                    ${comCat.name}



                    <c:url value="FrontController?section=carteJeannory&action=formule&detection=itemFormul&faction=produitFormul&nameCombo=${nameCombo}&comboCategory=${comCat.name}" var="url01" />
                    - <a href="${url01}">détail</a> <br/> 
                </c:forEach>  


                <h3>Détail sélection</h3>
                <c:forEach var="comboProduct" items="${comboProduct}">
                    ${comboProduct.name} -  ${comboProduct.description}<br/>
                </c:forEach>

            </div>
        </main>
    </body>
</html>
