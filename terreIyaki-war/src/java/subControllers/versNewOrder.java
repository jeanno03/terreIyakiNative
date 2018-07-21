/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subControllers;

import entityBeans.Account;
import entityBeans.MyOrder;
import entityBeans.MyTable;
import entityBeans.OrderItem;
import entityBeans.Product;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBeans.OrderTreatmentLocal;
import sessionBeans.TableTreatmentLocal;
import sessionBeans.newOrderTreatmentSamLocal;
import tools.CustomException;

/**
 *
 * @author samira
 */
public class versNewOrder implements ControllerInterface, Serializable {

        // test if sur création d'une nouvelle commande 
    // ou creation d'un nouveau controleur verscontroleurs 
    // submit vers le nouveau controleur 
    // FrontController?section=table&action=valide
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        OrderTreatmentLocal gestionCommande = lookupOrderTreatmentLocal();
        HttpSession session = request.getSession();
        newOrderTreatmentSamLocal gestionTable = lookupNewOrderTreatmentSamLocal();
        
        
        
        
        //**************************************affichage panier *****début****************************


              
        
        
        
        try{
            if((session.getAttribute("panierListOrderItem()"))!=null){
             try{
               
    MyOrder myOrder = (MyOrder) session.getAttribute("newOrder"); 

          int numeroTable=0;
     for(MyTable my : myOrder.getMyTables()){
      numeroTable =  my.getTableNumber();   
     }
     MyOrder myOrderPersist = gestionCommande.getLastOrderbyTableNumber(numeroTable); 
     
   //  Product po01 =   gestionCommande.persistProductPanier(stringIdProduit,myOrderPersist );
     
//System.out.println("existe tu myOrderPersist *****************" + myOrderPersist.toString());
             List<OrderItem> listOI = gestionCommande.getOrderItemByOrder(myOrder.getId());  

             
             session.setAttribute("panierListOrderItem", listOI); 
             
        
            } catch (CustomException ex1) {
                    Logger.getLogger(versNewOrder.class.getName()).log(Level.SEVERE, null, ex1);
                }
       }
       
       }
    catch(NullPointerException ne){
    
}
        
              
        
        
 //**************************************affichage panier *****fin****************************         
        
        
        
        
        
        
        
        
        
        

        if (request.getParameter("action").equals("valide")) {

            int numeroTable = Integer.valueOf(request.getParameter("table"));

            try {
                Account monCompte = (Account) session.getAttribute("user");
                int monCode = monCompte.getCode();
                Account monCompteObject = gestionTable.getAccountByCode(monCode);
                MyTable mytable = gestionTable.getSeletedTableNumber(numeroTable);
                MyOrder newOrder = gestionTable.newOrder(mytable, monCompteObject);
                session.setAttribute("newOrder", newOrder);
                
               System.out.println("*****ligne 52 52 52 newOrder************"+newOrder.toString());
              

            } catch (CustomException ex) {
                CustomException ce = new CustomException(CustomException.USER_ERR, "Pas de Table");
                try {
                    throw ce;
                } catch (CustomException ex1) {
                    Logger.getLogger(versNewOrder.class.getName()).log(Level.SEVERE, null, ex1);
                }

            }

        }
        
        
        //on choisit produit
        
       if(request.getParameter("action").equals("choixProduit")) {
           String stringIdProduit = request.getParameter("produit");
           System.out.println(" test achat produit idLong Produit "+ stringIdProduit);
           
          // on recupere la derniere commande 
           try{
               
    MyOrder myOrder = (MyOrder) session.getAttribute("newOrder"); 

          int numeroTable=0;
     for(MyTable my : myOrder.getMyTables()){
      numeroTable =  my.getTableNumber();   
     }
     MyOrder myOrderPersist = gestionCommande.getLastOrderbyTableNumber(numeroTable); 
     
     Product po01 =   gestionCommande.persistProductPanier(stringIdProduit,myOrderPersist );
     

             List<OrderItem> listOI = gestionCommande.getOrderItemByOrder(myOrder.getId());  

             
             session.setAttribute("panierListOrderItem", listOI); 
             
        
            } catch (CustomException ex1) {
                    Logger.getLogger(versNewOrder.class.getName()).log(Level.SEVERE, null, ex1);
                }
       }
        
        
        
        
        
        return "home";
    }
    
    

    

    private newOrderTreatmentSamLocal lookupNewOrderTreatmentSamLocal() {
        try {
            Context c = new InitialContext();
            return (newOrderTreatmentSamLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/newOrderTreatmentSam!sessionBeans.newOrderTreatmentSamLocal");

        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);

        }
    }
    
        private OrderTreatmentLocal lookupOrderTreatmentLocal() {
        try {
            Context c = new InitialContext();
            return (OrderTreatmentLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/OrderTreatment!sessionBeans.OrderTreatmentLocal");

        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);

        }

    }

}
