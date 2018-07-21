/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subControllers;

import entityBeans.MyOrder;
import entityBeans.OrderItem;
import entityBeans.Payment;
import entityBeans.PaymentOption;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBeans.PayementTreatmentLocal;
import tools.CustomException;
import tools.ProductAmount;

/**
 *
 * @author jeanno
 */
public class PaymentCtrl implements ControllerInterface, Serializable {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        PayementTreatmentLocal traiterPaiement = lookupPayementTreatmentLocal();
        
try{
        if(request.getParameter("action").equals("enCours")){
            
            try{
                //on récupère la liste des commabdes non réglés
            List<MyOrder> my01 = traiterPaiement.getOrderToPay();
            request.setAttribute("commandeToPay", my01);
        //   HashMap<Long,ProductAmount>ha01=new HashMap();
           HashMap<Long,ProductAmount>ha02=new HashMap();
            //pour chaque commande on récupère le montant
           //code pas très joli mais je suis fatigué ...
            for (int i=0;i<my01.size();i++){
                traiterPaiement.getMontant(my01.get(i).getId(), ha02);
            }
            request.setAttribute("commandeToPayHash", ha02);
            
            //test

            
                    
            }catch (CustomException ex){
                String text = ex.getMessage();
                request.setAttribute("message",text);
            }
            
                try{    
      if(request.getParameter("detection").equals("suppSesion")){
   //       session.removeAttribute("priceRestant");
          
      }       
         }catch(NullPointerException ne)  {
             
         }      
            
            
            
            
//*************************ici on édite la facture début *********************************            
          //sécurité pour éviter des soucis en cas de retour arriere
            
//   session.removeAttribute("priceRestant");
//   System.out.println("on vire la session +++++++++++++++++++");
// 
//*************************ici on édite la facture fin *********************************             
        }
}catch(NullPointerException ex){
    
    
}




//**************on sélectionne une facture à payer Début *******************
//  section=payment&action=enCours
// &section=payment&action=versPayer&id=7
try{
if(request.getParameter("action").equals("versPayer")){
Long id = Long.valueOf(request.getParameter("id"));

//*****************test pour calcul montant restant rapidos ************début ****                        
                     try{
                     
                      float montantRestantTTCV2 = traiterPaiement.getMontantRestantTTCV02(id);
                     
                     request.setAttribute("montantRestantTTCV2", montantRestantTTCV2);                    
                     }catch (CustomException ex){
      String text = ex.getMessage();
      request.setAttribute("message",text);
  } 
                     
                     
//*****************test pour calcul montant restant rapidos ************fin ****     


//dans ce bloc l id sera toujours renvoyé
request.setAttribute("id", id);
//la hashMap exite dans tout le bloc je peux la réutiliser 
      HashMap<Long,ProductAmount> ha01 = new HashMap(); 
//la priceTotal exite dans tout le bloc je peux la réutiliser 
       float priceTotal = 0f;
       
   //afficher moyen de payment  exite dans tout le bloc je peux la réutiliser 
  List<PaymentOption> po01=new ArrayList();
  try{
  po01 = traiterPaiement.getPaymentType();
  request.setAttribute("paymentOption", po01);
  
  }catch (CustomException ex){
      String text = ex.getMessage();
      request.setAttribute("message",text);
  }      
       //déclarer pour pouvoir l utiliser dans la facturation
       List<OrderItem> or01 = new ArrayList();
             try{
           or01= traiterPaiement.getItemsFromOrder(id);
          
            request.setAttribute("listItem", or01);
            
            
            traiterPaiement.getMontant(id, ha01);
        
            
            request.setAttribute("price", ha01);
            
            priceTotal = ha01.get(id).getMontantTTC();
        
            }catch (CustomException ex){
                String text = ex.getMessage();
                request.setAttribute("message",text);
            }
             
             

             
             
//**************on choisi de payer début ******************************             
             try{
                 
 

             if(request.getParameter("detection").equals("payer")){

                 
                 
                 
//**************montant restant calculé dynamique début ******************************     
//il faut que le règlement soit toujours le dernier enregistré                 
//                 try{
//                     
//                    
//                     
//                     
//       
//            //1************si pas de session on execute sinon on reprend données session
//            if(session.getAttribute("priceRestant")==null){ 
//                //si pas d encaissement on retire 0f
//                 if(request.getParameter("montantEncaisse")!=null){
//            float encaissement = Float.valueOf(request.getParameter("montantEncaisse"));      
//          float montantRestantTTC=  traiterPaiement.getMontantRestant(priceTotal, encaissement);
//            session.setAttribute("priceRestant", montantRestantTTC);
//                 }
//                 else{
//
//          float montantRestantTTC=  traiterPaiement.getMontantRestant(priceTotal, 0f);
//            session.setAttribute("priceRestant", montantRestantTTC);        
//                 }
//            }                                 
//             //2************si pas de session on execute sinon on reprend donner session  
//            else if(session.getAttribute("priceRestant")!=null){  
//                
//                float nouveauMontant = Float.valueOf(String.valueOf(session.getAttribute("priceRestant")));
//                //si pas d encaissement on retire 0f
//                 if(request.getParameter("montantEncaisse")!=null){
//            float encaissement = Float.valueOf(request.getParameter("montantEncaisse"));      
//          float montantRestantTTC=  traiterPaiement.getMontantRestant(nouveauMontant, encaissement);
//            session.setAttribute("priceRestant", montantRestantTTC);
//                 }
//                 else{
//
//          float montantRestantTTC=  traiterPaiement.getMontantRestant(nouveauMontant, 0f);
//            session.setAttribute("priceRestant", montantRestantTTC);        
//                 }
//            }              
//                 
//                 }catch(NullPointerException ne){
//                     
//                     
//                 }
//**************montant restant calculé dynamique fin ******************************       
  
  
  
  
  
  
//*********************on choisi le moyen de paiement ***********début ******************
  try{
      
  if(request.getParameter("vaction").equals("prise")){
    //si je retourne le meme nom j ouvre le formulaire
      String type="";
      //type va reconduit dans la condition moyen de paiement choisi
 type = request.getParameter("type");
 request.setAttribute("typeChoisi", type);
      //*******************************le moyen de paiement choisie on choisi le montant *******début *************
 try{
 if(request.getParameter("faction").equals("encaisse")){
   
     
     
     
     
     
     
     
     
     
     
     
     float mt = Float.valueOf(String.valueOf(request.getParameter("montantEncaisse"))) ;
    //méthode insert à faire 
     //je dois récupérer l objet moyen de payment 
      //je dois récupérer l ojbet MyOrder
     float cumulPayment =0f;
  try{
     PaymentOption po02 = traiterPaiement.getObjectPaymentType(type);
     MyOrder my02 = traiterPaiement.getOrderById(id);
     traiterPaiement.persistPayment(priceTotal, po02,my02, mt);
     System.out.println("objet type de paement récup ++++++++++++++++ : "+po02.toString());
     
    cumulPayment = traiterPaiement.getMontantEncaisseTTC(priceTotal, my02);

//si on a l egalité on change le status
  traiterPaiement.changeStatutOrder(priceTotal, cumulPayment, id);    
    
    
        }catch (CustomException ex){
      String text = ex.getMessage();
      request.setAttribute("message",text);
  }    
  

  
  
  
    
    //*****************test pour calcul montant restant rapidos ************début ****                        
                     try{
                     
                      float montantRestantTTCV2 = traiterPaiement.getMontantRestantTTCV02(id);
                     
                     request.setAttribute("montantRestantTTCV2", montantRestantTTCV2);                    
                     }catch (CustomException ex){
      String text = ex.getMessage();
      request.setAttribute("message",text);
  } 
                     
                     
//*****************test pour calcul montant restant rapidos ************fin ****     
     
     
      
     
 }
 
 }catch(NullPointerException ex){
     
 }     
      //*******************************le moyen de paiement choisie on choisi le montant *******fin *************      
  }
  
  }
  catch(NullPointerException ex){        
          }
  
  
  
//*********************on choisi le moyen de paiement ***********fin ******************                 
}
             }catch(NullPointerException ex)   {
                 
             }
//**************on choisi de payer fin ******************************              
            
             
             
             
//*************************ici on édite la facture début *********************************    
             try{
         if(request.getParameter("detection").equals("payerOK")){
             
             
             
             
             
             //je récupère le Myorder
             MyOrder my02 = new MyOrder();
              List<Payment> lo09 = new ArrayList();
           
          
     try{
         my02 = traiterPaiement.getOrderById(id);
         lo09= traiterPaiement.getPaymentbyOrder(my02.getId());

        }catch (CustomException ex){
      String text = ex.getMessage();
      request.setAttribute("message",text);
  }   
     
             
             
             
             
             
             
    //  session.removeAttribute("priceRestant");       
 //***************facture electronique début ****************************             
             if(request.getParameter("choix").equals("mail")){
                  request.setAttribute("factureEdite","OK");
            String email = request.getParameter("email")  ;
        //    traiterPaiement.getBillPdfV02(my02, or01, priceTotal, lo09);
                 try {
                     traiterPaiement.envoyerMail(email);
                 } catch (NamingException ex) {
                     Logger.getLogger(PaymentCtrl.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (SQLException ex) {
                     Logger.getLogger(PaymentCtrl.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (EJBException ex) {
                     Logger.getLogger(PaymentCtrl.class.getName()).log(Level.SEVERE, null, ex);
                 }
            
            
             }
             
 //***************electronique papier fin ****************************              
             
             
 //***************facture papier début ****************************            
             if(request.getParameter("choix").equals("papier")){
 
   System.out.println("on ne vire pas encore la session +++++++++++++++++++");
   
   System.out.println("montant TTC a éditer µµ**********************"+ priceTotal);
   System.out.println("l id de la commande TTC a éditer µµ**********************"+ id);
 System.out.println("liste des products a éditer µµ**********************"+ or01.toString());
  System.out.println("myOrder à éditer µµ**********************"+ my02.toString());
 System.out.println("List<Payment> lo09 à éditer µµ**********************"+  lo09.toString()); 
 //
 
 traiterPaiement.getBillPdfV02(my02, or01, priceTotal, lo09);
 request.setAttribute("factureEdite","OK");
             }
             
 //***************facture papier début ****************************                 
         }
             }catch (NullPointerException ex){
                 
                 }
//*************************ici on édite la facture fin *********************************              
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
}
}catch(NullPointerException ex){
    
}

//**************on sélectionne une facture à payer Fin *******************

        
        return "payment";
    }
    
    
    
private PayementTreatmentLocal lookupPayementTreatmentLocal(){
    try{
      Context c = new InitialContext();
      return (PayementTreatmentLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/PayementTreatment!sessionBeans.PayementTreatmentLocal");
    }catch(NamingException ne){
        Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught", ne);
        throw new RuntimeException(ne);
    }
    
}
    
}
