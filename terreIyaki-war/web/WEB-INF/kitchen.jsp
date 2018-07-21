
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" />
        <title>Kichen TerreIyaki</title>
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>
        <c:url value ="FrontController?section=include&action=navbar" var="urlNavbar"/> 
        <c:import url ="${urlNavbar}"/>
        <h1>Interface cuisine</h1>

        <c:forEach var = "kitchenOrderItem" items="${orderItems}">
            <article>
                <div id="order-item-total-display${kitchenOrderItem.id}">
                    <div id="order-item-display${kitchenOrderItem.id}">
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
                    </div>
                    <div id="order-item-customization${kitchenOrderItem.id}" style="display:none"></div>
                </div>
            </article>
        </c:forEach>
        <script src="/terreIyaki-war/js/myJS.js" type="text/javascript"></script>
    </body>
</html>
