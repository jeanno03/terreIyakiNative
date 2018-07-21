<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${kitchenOrderItem.status.num == 1}">
    <c:set var="text" value="Mettre en préparation" scope="page" />
    <c:set var="classname" value="to-process" scope="page" />
</c:if>
<c:if test="${kitchenOrderItem.status.num == 2}">
    <c:set var="text" value="Mettre à servir" scope="page" />
    <c:set var="classname" value="processing" scope="page" />
</c:if>

<p class = "${classname}">${kitchenOrderItem.product.name} : ${kitchenOrderItem.status.name}</p>

<c:if test="${not empty kitchenOrderItem.ingredients || not empty kitchenOrderItem.options}">
    <a class="button" onclick="displayOrderCustomization('${kitchenOrderItem.id}');
            return false;">/!\ Commande personnalisée - voir détails</a>
</c:if>

<a class="button" onclick="setItemStatusForward('${kitchenOrderItem.id}', ${kitchenOrderItem.status.num});
        return false;">${text}</a>