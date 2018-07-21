<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" />
        <title>login</title>
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>        
        
        <h1>Login</h1>
        
<c:if test="${code==null}">
<c:set var="codeNumber" value="1" scope="page" />  
</c:if>        
        
<c:if test="${code=='*'}">
<c:set var="codeNumber" value="2" scope="page" />  
</c:if>
        
<c:if test="${code=='**'}">
<c:set var="codeNumber" value="3" scope="page" />  
</c:if>   
        
<c:if test="${code=='***'}">
<c:set var="codeNumber" value="4" scope="page" />  
</c:if>  
        
        
        
        <div class ="pad" >       
<div class="bouton">
      <a href="FrontController?section=login&bottom${codeNumber}=01"><img src="images/image01.png" width="86" height="94" alt="bouton01"/></a>
      <a href="FrontController?section=login&bottom${codeNumber}=02"><img src="images/image02.png" width="86" height="94" alt="bouton02"/></a>
      <a href="FrontController?section=login&bottom${codeNumber}=03"><img src="images/image03.png" width="86" height="94" alt="bouton03"/></a>
</div>
<div class="bouton">
      <a href="FrontController?section=login&bottom${codeNumber}=04"><img src="images/image04.png" width="86" height="94" alt="bouton04"/></a>
      <a href="FrontController?section=login&bottom${codeNumber}=05"><img src="images/image05.png" width="86" height="94" alt="bouton05"/></a>
      <a href="FrontController?section=login&bottom${codeNumber}=06"><img src="images/image06.png" width="86" height="94" alt="bouton06"/></a>
</div>
<div class="bouton">
      <a href="FrontController?section=login&bottom${codeNumber}=07"><img src="images/image07.png" width="86" height="94" alt="bouton07"/></a>
      <a href="FrontController?section=login&bottom${codeNumber}=08"><img src="images/image08.png" width="86" height="94" alt="bouton08"/></a>
      <a href="FrontController?section=login&bottom${codeNumber}=09"><img src="images/image09.png" width="86" height="94" alt="bouton09"/></a>
</div>
<div class="bouton">      
      <a href="FrontController?section=login&bottom=correction"><img src="images/imageCorrection.png" width="86" height="94" alt="boutonCorrection"/></a>
      <a href="FrontController?section=login&bottom${codeNumber}=00"><img src="images/image00.png" width="86" height="94" alt="bouton00"/></a>
      <c:if test="${code=='****'}">
      <a href="FrontController?section=login&bottom=valider"><img src="images/imageOk.png" width="86" height="94" alt="boutonOk"/></a>
      </c:if>
</div>         
<div class="bouton"> 
<c:if test="${code=='*'}">
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>  
</c:if>    
<c:if test="${code=='**'}">
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
</c:if>
<c:if test="${code=='***'}">
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
</c:if>
<c:if test="${code=='****'}">
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
<img src="images/imageTry.png" width="86" height="94" alt="boutonTry"/>
</c:if> 
</div>          

      </div>
    <p>${message}</p>       

    
<p>${message}</p>
<c:if test="${user!=null}">
    <br/>Bonjour ${user.firstName} ${user.lastName}
<c:forEach var="my" items="${myGrants}">
    <br/> Profil : ${my.name}
</c:forEach>
<c:forEach var="my" items="${myGrants}">
   <br/>
<c:if test="${my.name=='client'}">
    <br/>Bienvenue à la table n° ${user.myTable.tableNumber} <br/>  
</c:if>
<c:if test="${my.name=='client'||my.name=='serveur'}">
<c:url value="FrontController?section=carteJeannory&action=produit" var="url02" />
<br/>A la carte  :  <a href="${url02}">  ici </a>     
<c:url value="FrontController?section=carteJeannory&action=formule" var="url03" />
<br/>vers les formules  :  <a href="${url03}">  ici </a>       

<c:url value="FrontController?section=combo&action=allCombo" var="url04" />
<br/>Formule V2  :  <a href="${url04}">  ici </a>    
</c:if>
<c:if test="${my.name=='caissier'}">
    <c:url value="FrontController?section=payment&action=enCours" var="url33" />
    <br/>Commande en attente de règlement : <a href="${url33}"  >Ici</a>
</c:if>


</c:forEach>

<%--
<c:url value="FrontController?section=home&action=logout" var="url01" />
<p>Se déconnecter :  <a href="${url01}">  ici </a> </p>
--%>
</c:if>       

    </body>
</html>
