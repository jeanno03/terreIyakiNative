<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>
        <h1>back office provisoire</h1>
        <p>Attention il faut passer en create dans persistence.xml</p>
        <br />
        <p>New Product</p>
        <form action="FrontController" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="section" value="backoffice" />
            <input type="hidden" name="action" value="createproduct" />
            <label>nom</label><br />
            <input type="text" name="name" /><br /><br />
            <label>prix HT</label><br />
            <input type="text" name="price" /><br /><br />
            <label>image</label><br />
            <input type="text" name="picture" /><br /><br />
            <label>description</label><br />
            <input type="text" name="description" /><br /><br />
            <label>categorie</label><br />
            <select name="pcategory">
                <c:forEach var = "cat" items="${categories}">
                    <option value="${cat.name}">${cat}</option>
                </c:forEach>
            </select><br /><br />
            <label>TVA</label><br />
            <select name="pvat">
                <c:forEach var = "vat" items="${vats}">
                    <option value="${vat.id}">${vat}</option>
                </c:forEach>
            </select><br /><br />
            <label>propriétés</label><br />
            <select name="pproperties" multiple>
                <!-- request.getParameterValues("pproperties") returns an array of all submitted values. -->
                <c:forEach var = "prop" items="${properties}">
                    <option value="${prop.id}">${prop}</option>
                </c:forEach>
            </select><br /><br />
            <label>options</label><br />
            <select name="poptions" multiple>
                <c:forEach var = "opt" items="${options}">
                    <option value="${opt.id}">${opt}</option>
                </c:forEach>
            </select><br /><br />
            <label>ingredients</label><br />
            <select name="pingredients" multiple>
                <c:forEach var = "ing" items="${ingredients}">
                    <option value="${ing.id}">${ing}</option>
                </c:forEach>
            </select><br /><br />
            <input type="submit" value="Valider">
        </form>
    </body>
</html>
