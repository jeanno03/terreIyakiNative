/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Account;
import entityBeans.MyOrder;
import entityBeans.MyTable;
import entityBeans.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import tools.CustomException;

/**
 *
 * @author samira
 */
@Stateless
public class newOrderTreatmentSam implements newOrderTreatmentSamLocal {
    OrderTreatmentLocal gestionCommande = lookupOrderTreatmentLocal();  

    PayementTreatmentLocal getMethodPayementTreatment = lookupPayementTreatmentLocal();
    
    
    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;
    
    @Override
    public MyTable getSeletedTableNumber(int numeroTable) throws CustomException {
        
     Query qr = em.createNamedQuery("entityBeans.MyTable.selectTablebyTableNumber");
     qr.setParameter("paramtableNumber", numeroTable); 
     try {
     MyTable table = (MyTable) qr.getSingleResult() ;
     return table;

    } 
     catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "Pas de Table");
            throw ce;     
          
     }
       
    }
    
    public Status getStatusTableActif() throws CustomException{
        
        
        Query qr=em.createNamedQuery("entityBeans.Status.getStatusByNum");
        qr.setParameter("paramNumStatus",14); 
        try {
        Status status = (Status) qr.getSingleResult(); 
        return status;
        } catch (NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de statut");
            throw ce;
            
        
        }
        
    }
    
    public Status getStatusCommandeEnCours() throws CustomException{
        
        
        Query qr=em.createNamedQuery("entityBeans.Status.getStatusByNum");
        qr.setParameter("paramNumStatus",9); 
        try {
        Status status = (Status) qr.getSingleResult(); 
        return status;
        } catch (NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de statut");
            throw ce;
            
        
        }
        
    }
    
    
    //on persist myOrder et on recup l object persisté
    @Override
    public MyOrder newOrder(MyTable table, Account monCompte) throws CustomException{ 

        Date aujourdhui = new Date();
        Status statusTable = getStatusTableActif(); 
        table.setStatus(statusTable);
        Status statusCommande = getStatusCommandeEnCours (); 
        
        ArrayList listeTable = new ArrayList (); 
        listeTable.add(table);
        MyOrder newOrder = new MyOrder(); 
        
       
        newOrder.setStatus(statusCommande);
        newOrder.setMyTables(listeTable);
        newOrder.setAccount(monCompte);
        newOrder.setOrderDate(aujourdhui);
        em.persist(newOrder);
        em.merge(table); 
      //  return newOrder;
   int numerotable = table.getTableNumber();     
 MyOrder newOrder02 =  gestionCommande.getLastOrderbyTableNumber(numerotable);
  return  newOrder02;
    }
    
    
        
    
    
     
//    @Override
//    public MyOrder getLastOrderByTable(int numeroTable) throws CustomException{
//        
// 
//    TypedQuery<MyOrder> qr = em.createNamedQuery("entityBeans.MyOrder.getLastOrderByTableNumber",MyOrder.class);  
//        qr.setParameter("paramTableNumber", numeroTable);
//        
//    try{
//    MyOrder maCommande  =  qr.getSingleResult();
// return maCommande;
//    }catch(NoResultException ex) {
//            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de combo");
//         throw ce;
//
//    
//    
//    }
//        
//    }
    
    
    @Override
    public Account getAccountByCode(int leCode) throws CustomException{
        
        TypedQuery<Account> qr = em.createNamedQuery("entityBeans.Account.getAccountByCode",Account.class);
        qr.setParameter("paramAccountCode", leCode);
        try{
        Account monCompte = qr.getSingleResult();
        return monCompte;
        }catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de combo");
         throw ce;
        
        
    }
    
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
