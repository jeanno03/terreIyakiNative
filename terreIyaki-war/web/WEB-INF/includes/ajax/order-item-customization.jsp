<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${options != null}">
    <p>Options :</p>
    <c:forEach var = "opt" items="${options}">
        <p>${opt.name}</p>
    </c:forEach>
</c:if>
<br />

<c:if test="${ingredients != null}">
    <p>Ingrédients à retirer :</p>
    <c:forEach var = "ing" items="${ingredients}">
         <p>${ing.name}</p>
    </c:forEach>
</c:if>