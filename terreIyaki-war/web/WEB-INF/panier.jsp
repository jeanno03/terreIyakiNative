
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
    Document   : panier
    Created on : 29 mars 2018, 00:11:35
    Author     : jeanno
--%>




  <%-- non fonctionnel--%>
<c:forEach var="order" items="${panierListComboItem}">
   
    <br/>Quantité : 1 - Produit : ${order.product.name} - Prix : ${order.product.priceWithVAT} TTC - ${status.Name} 
 
    
                <c:url value="FrontController?section=home&action=validerPlat&orderItem=${order.id}" var="url33" />
            - <a href="${url33}">Valider</a> 
</c:forEach>
   

