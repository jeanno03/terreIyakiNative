<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : myTable
    Created on : 22 mars 2018, 15:55:03
    Author     : samira
-- &action=valide, mettre à la suite de form action

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="css/style.css" />
        <title>Ouvrir Table</title>
    </head>
    <body>
        <c:url value ="FrontController?section=include&action=header" var="urlHeader"/> 
        <c:import url ="${urlHeader}"/>
        <c:url value ="FrontController?section=include&action=navbar" var="urlNavbar"/> 
        <c:import url ="${urlNavbar}"/>        
        <h1>Liste des tables et leurs statuts</h1>
        <form action="FrontController">
            <c:forEach var="table" items="${myTable}">
                <input type ="hidden" name ="section" value ="newOrder" />
                <input type ="hidden" name ="action" value ="valide" />
                
                <p id="mytableStatus">
                    <label >Table : ${table.tableNumber} <br /> ${table.status} </label>  <br />
                <input type="checkbox" name="table" value="${table.tableNumber}"> <br> 
            </p>  
        </c:forEach> <br />
        <input type="submit" value="Submit"> <br />
    </form>
</body>
</html>