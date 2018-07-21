
package subControllers;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBeans.OrderTreatmentLocal;
import tools.CustomException;

public class HomeCtrl implements ControllerInterface, Serializable {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
    
         HttpSession session = request.getSession();
         OrderTreatmentLocal gestionCommande = lookupOrderTreatmentLocal(); 
         
         
// ****************************je remet a jour l affichage *************début **************
          try{
          if(session.getAttribute("panierListOrderItem")!=null){
           try{
               
    MyOrder myOrder = (MyOrder) session.getAttribute("newOrder"); 

          int numeroTable=0;
     for(MyTable my : myOrder.getMyTables()){
      numeroTable =  my.getTableNumber();   
     }
     MyOrder myOrderPersist = gestionCommande.getLastOrderbyTableNumber(numeroTable); 
    

             List<OrderItem> listOI = gestionCommande.getOrderItemByOrder(myOrder.getId());  

             
             session.setAttribute("panierListOrderItem", listOI); 
             
        
            } catch (CustomException ex1) {
                    Logger.getLogger(versNewOrder.class.getName()).log(Level.SEVERE, null, ex1);
                }
          
          }
                 
          }catch(NullPointerException ne){
              
          }
         
// ****************************je remet a jour l affichage *************fin **************    
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
      try   {
if(request.getParameter("action").equals("addition")){
    
    //on change le status de order
    // on recupere order
    //on change le status de table
    try{
   MyOrder myOrder = (MyOrder) session.getAttribute("newOrder"); 
        int numeroTable=0;
     for(MyTable my : myOrder.getMyTables()){
      numeroTable =  my.getTableNumber();   
     }
     MyOrder myOrderPersist = gestionCommande.getLastOrderbyTableNumber(numeroTable); 
     
     gestionCommande.changeStatusCommandeAPayer(myOrderPersist);
     
    session.removeAttribute("newOrder");
     
      session.removeAttribute("panierListOrderItem");
     
     
     
     
    }catch(CustomException ex){
                        String texte = ex.getMessage();
                        request.setAttribute("message", texte);         
     }
    
    
    
}
     
      }catch (NullPointerException ne){
          

        
    }
      
      try{
      if(request.getParameter("action").equals("validerPlat")){
    //rechercher les orders items  //besoin du Lond de orderItem
          //rechercher le status 
          
        //  List<OrderItem> li01 = (List<OrderItem> ) session.getAttribute("panierListOrderItem");
          try{
      long id=  Long.valueOf(request.getParameter("orderItem"));
   OrderItem oi=  gestionCommande. getOrderItemById(id);
   System.out.println(" OrderItem objet de la base a t elle été créé : "+oi.toString());
   gestionCommande.metOrderItemAPreparer(oi);
   
   
     }catch(CustomException ex){
                        String texte = ex.getMessage();
                        request.setAttribute("message", texte);         
     }
          
        
          
          
          
          
          
          
   
          
      }
            }catch (NullPointerException ne){
          

        
    }
    
    return "home";
    
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
