<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <c:url value="FrontController?section=home" var="url33" />
    <a class="button" href="${url33}">Accueil</a>
    <c:if test="${user!=null}" >
    <c:url value="FrontController?section=versLogin&action=logout" var="url01" />
    <a class="button" href="${url01}">Se déconnecter</a>
    </c:if>  
 
   
</nav>