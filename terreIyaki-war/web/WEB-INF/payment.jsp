<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : payment
    Created on : 24 mars 2018, 18:26:22
    Author     : jeanno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" />
        <title>payment</title>
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>
        <c:url value ="FrontController?section=include&action=navbar" var="urlNavbar"/> 
        <c:import url ="${urlNavbar}"/>
        <main>             
            ${message}

            <c:if test="${commandeToPayHash==null}" >
            <c:url value="FrontController?section=payment&action=enCours&detection=suppSesion" var="url55" />
            <br/>Retour liste facture en attente  : <a href="${url55}"  >Ici</a> 
            <br/>                 
            </c:if>

            

            <c:if test="${commandeToPayHash!=null}" >
<br/>Commande en attente de règlement :<br/>
                <c:forEach var="com" items="${commandeToPayHash}">
                    <br/>Commande n° ${com.key} -- Net à payer : ${com.value.montantTTC}€ TTC
                     -- <a href="FrontController?section=payment&action=versPayer&id=${com.key}">Régler</a>
                </c:forEach>

            </c:if>

            <c:if test="${price!=null}" >

                    <br/> 

                <c:if test="${montantRestantTTCV2>0}" > 
<div id="conteneur">
<div class="element">

                    <br/>Moyen de paiement :   <br/> 

                    <ul>
                    <c:forEach var="paymentOp" items="${paymentOption}">

                        <li><a href="FrontController?section=payment&action=versPayer&id=${id}&detection=payer&vaction=prise&type=${paymentOp.name}">${paymentOp.name}</a></li>


                    </c:forEach>
                    </ul>
                    
                    </div> 
    <div class="element">
                    <c:if test="${typeChoisi!=null&&montantRestantTTCV2>0}">
                        <br/><br/> <br/>Type : ${typeChoisi}
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="section" value="payment" />
                            <input type="hidden" name="action" value="versPayer" />
                            <input type="hidden" name="id" value="${id}" />
                            <input type="hidden" name="detection" value="payer" />
                            <input type="hidden" name="vaction" value="prise" />
                            <input type="hidden" name="type" value="${typeChoisi}" />                                 
                            <input type="hidden" name="faction" value="encaisse" />
                            Montant encaissé : <br/> <input type="text" name="montantEncaisse" value="" />

                            <input type="submit" value="valider" name="doIt" />


                        </form>

                    </c:if> 
                        
                        </div> 
    </div> 
                </c:if>
                    
                 
                <c:if test="${montantRestantTTCV2<=0}">  
                 <div id="conteneur"><div class="element">      
                       
                    <br/><br/><a href="FrontController?section=payment&action=versPayer&id=${id}&detection=payerOK&choix=papier"/>Editer facture</a>
<br/><br/><br/><br/><br/>
</div> 
                <c:if test="${factureEdite!=null}">
                 <div class="element">   
                    <form action="FrontController" method="POST">
                      <br/><br/>  - Recevoir par mail
                        <input type="hidden" name="section" value="payment" />
                        <input type="hidden" name="action" value="versPayer" />
                        <input type="hidden" name="id" value="${id}" />
                        <input type="hidden" name="detection" value="payerOK" />
                        <input type="hidden" name="choix" value="mail" />
                        <input type="text" name="email" value="" />
                        <input type="submit" value="Envoyer" name="doIt" />

                    </form>   
                     </div>   
                </c:if>       
</div>
            </c:if>

            <c:forEach var="pri" items="${price}">
                <br/> <br/>Montant total : ${pri.value.montantHT}€ HT 
                - ${pri.value.montantTTC}€ TTC 
                <br/>
            </c:forEach>
        </c:if>

        <c:if test="${montantRestantTTCV2!=null}">
            <strong>Restant à payer :  ${montantRestantTTCV2}€ TTC</strong><br/>
        </c:if>    



        <c:if test="${listItem!=null}" >
            <br/>Détail commande :<br/>
            <c:forEach var="list" items="${listItem}">
                <c:if test="${list.combo.name!=null}" >
                    <br/>--Menu--  ${list.combo.name}
                </c:if>                 

            </c:forEach>            

        </main>

        <c:forEach var="list" items="${listItem}">


            <c:if test="${list.product.name!=null}" >

                <br/>-Produit-  qté : 1 - prix : ${list.product.price}€ HT - taux tva : ${list.product.vat.rate}% : ${list.product.name}
            </c:if> 

        </c:forEach>


    </c:if> 


</body>
</html>
