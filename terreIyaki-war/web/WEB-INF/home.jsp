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
        <c:url value ="FrontController?section=include&action=navbar" var="urlNavbar"/> 
        <c:import url ="${urlNavbar}"/>

        <main> 
            <c:if test="${panierListOrderItem!=null}">
                <br/>Produit : 
                <c:forEach var="orderP" items="${panierListOrderItem}">

                    <br/>Qté : 1 - ${orderP.status} - ${orderP.product.name} 
                    <c:url value="FrontController?section=home&action=validerPlat&orderItem=${orderP.id}" var="url33" />
                    <c:if test="${orderP.status.num==0}">
                        - <a href="${url33}">Envoyer en cuisine</a>     
                    </c:if>
                </c:forEach>
                <c:url value="FrontController?section=home&action=addition" var="url3333" />
                <a class="button" href="${url3333}">L'addition</a>    
            </c:if>
            <%--
        <c:if test="${panierListOrderComboItem!=null}">
            <br/>Menu : 
        <c:forEach var="orderM" items="${panierListOrderComboItem}">
           
            <br/>Qté : 1 - ${orderM.status} - ${orderM.product.name}  
        </c:forEach>          
        </c:if>  
            --%>


            <p class ="home">${message} /
                <c:if test="${user!=null}">
                    Bonjour ${user.firstName} ${user.lastName} /

                    <c:forEach var="my" items="${myGrants}" >
                        Profil : ${my.name}</p>
                        <c:if test="${my.name=='client'}">
                        Bienvenue à la table n° ${user.myTable.tableNumber}   
                    </c:if>
                    <c:if test="${my.name=='client'||my.name=='serveur'}">

                        <c:url value="FrontController?section=overview" var="url10" />
                        <a class="button1" href="${url10}">À la carte</a>
                        <c:url value="FrontController?section=combo&action=allCombo" var="url05" />
                        <a class="button1" href="${url05}">Formules</a> 
                    </c:if>
                    <c:if test="${my.name=='caissier'}">
                        <c:url value="FrontController?section=payment&action=enCours" var="url33" />
                        <a class="button1" href="${url33}"  >Commande en attente de règlement</a>
                    </c:if>
                </c:forEach>
                <div>
                    <c:url value="FrontController?section=table" var="url04" />
                    <a class="button1" href="${url04}">Liste des tables</a>
                </div>
                <div>
                    <c:url value="FrontController?section=kitchen" var="url44" />
                    <a class="button1" href="${url44}">liste des plats à préparer</a>
                </div>

            </c:if>



            <div>
                <c:url value="FrontController?section=backoffice" var="url43" />
                <a class ="button" href="${url43}">Back office provisoire</a>
            </div>
        </main>    
    </body>
</html>
