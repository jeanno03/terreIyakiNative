<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="entityBeans.Product"%>
<%@page import="java.util.Set"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : combo
    Created on : 20 mars 2018, 14:02:24
    Author     : jeanno
--%>

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
            <p>${message}</p>


            <c:forEach var="com" items="${combo}">
                <div id="conteneur">
                    <div class="element">
                        <img src="${com.urlImage}" height="100"/>
                    </div>
                    <div class="element">

                        <c:url value="FrontController?section=combo&action=monCombo&comboName=${com.name}" var="url01"/>
                        ${com.name}
                        <br/>${com.description} - ${com.price} €  
                        <a class="button1" href="${url01}" >Choisir</a>

                    </div>


                </div>     
            </c:forEach>




            <c:if test="${HashProduct!=null}">
                <h3>Menu ${comboName02} </h3>

                <c:if test="${newOrder!=null}">
                    <a href="FrontController?section=combo&action=comboChoice&detection=itemFormul&comboName=${comboName02}"><img src="images/ajouter.png" width="80" alt="ajouter"/></a>
                    </c:if>  



                <%
                    //test
                    //out.print("<br><h3>Code dans la jsp ==> HashMap trié dans la jsp...</h3>");  

                    HashMap<String, List<Product>> ha01 = new HashMap();
                    ha01 = (HashMap<String, List<Product>>) request.getAttribute("HashProduct");
                    HashMap<String, List<Product>> ha02 = new HashMap();
                    List<Product> lp01 = new ArrayList();
                    String myKey = "";

                    Set set = ha01.entrySet();
                    Iterator iterator = set.iterator();
                    //    System.out.println("Avant le tri: ");
                    while (iterator.hasNext()) {
                        Map.Entry me = (Map.Entry) iterator.next();
                        //         System.out.print(me.getKey() + ": ");
                        //        System.out.println(me.getValue());
                    }

                    Map sortedMap = new TreeMap(ha01);

                    Set set2 = sortedMap.entrySet();
                    Iterator iterator2 = set2.iterator();
                    //     System.out.println("Après le tri: ");
                    while (iterator2.hasNext()) {
                        Map.Entry me2 = (Map.Entry) iterator2.next();
                        //         System.out.print(me2.getKey() + ": ");
                        //         System.out.println(me2.getValue());
                        myKey = String.valueOf(me2.getKey());
                        lp01 = (List<Product>) me2.getValue();
                        ha02.put(myKey, lp01);
                        //    out.print("<br/>test");
                        out.print("<br><br/>" + myKey + "<br>");

                        for (int i = 0; i < lp01.size(); i++) {

                            out.print("<br>" + lp01.get(i).getName());
                            out.print(" " + lp01.get(i).getDescription());

                        }

                        //     out.print("<br>"+lp01.toString());        
                    }

                %>

            </c:if>





            <c:if test="${comboCategory!=null}">





                <c:if test="${hashPanier!=null}">


                    <c:if test="${menuRempli!=null}">



                        <a href="FrontController?section=combo&action=validerMenu">ajouter à la commande</a>
                        <br/><br/>

                    </c:if>  


                    <br/>Sous menu choisi <c:forEach var="hashPanr" items="${hashPanier}"> 

                        <br/>${hashPanr.key}
                    </c:forEach>
                </c:if>
                <c:if test="${menuRempli!=null}">

                </c:if>


                <h3>${nameComboChoice}</h3>
                <c:forEach var="comboChoose" items="${comboCategory}">

                    <a href="FrontController?section=combo&action=comboChoice&detection=itemFormul&comboName=${nameComboChoice}&faction=produitFormul&comboCategory=${comboChoose.name}">${comboChoose.name}</a>  

                </c:forEach>
                <hr/>
                <c:forEach var="comboProductToChoose" items="${comboProduct}"> 
                    <div id="conteneur"> 

                        <div class="element">
                            <img src="${comboProductToChoose.picture}" height="90"/>
                        </div>   
                        <div class="element">
                            <a href="FrontController?section=combo&action=comboChoice&detection=itemFormul&comboName=${nameComboChoice}&faction=produitFormul&comboCategory=${nomCategorie}&vaction=choix&productChoose=${comboProductToChoose.name}"><img src="images/ajouter.png" width="80" alt="ajouter"/> </a> 
                            <br/>${comboProductToChoose.name}<br/>     

                            ${comboProductToChoose.description}
                        </div>  


                    </div> 

                </c:forEach>         


            </c:if>     



            <c:if test="${menuCommande!=null}">
                <c:forEach var="achat" items="${menuCommande}">


                    menu commandé : ${achat.key} - quantité : ${achat.value}
                    <c:url value="FrontController?section=versLogin" var="url02" />


                </c:forEach>
            </c:if>

        </main>            
    </body>
</html>
