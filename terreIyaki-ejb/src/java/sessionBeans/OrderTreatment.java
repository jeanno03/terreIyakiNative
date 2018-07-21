/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Account;
import entityBeans.Combo;
import entityBeans.ComboCategory;
import entityBeans.MyOrder;
import entityBeans.MyTable;
import entityBeans.OrderItem;
import entityBeans.Payment;
import entityBeans.Product;
import entityBeans.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
import tools.TriParComboCategory;
//import tools.TriParComboCategory;



/**
 *
 * @author jeanno
 */
@Stateless
public class OrderTreatment implements OrderTreatmentLocal {

    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;
    
CatalogTreatmentLocal catalogTreatment = lookupCatalogTreatmentLocal();
//    private HashMap<String,Long> panier ;
//    
//    @PostConstruct
//    @Override
//    public void init(){
//      HashMap<String,Long>   panier = new HashMap<>();
//    }
//    
    
    
    
//méthode pour afficher produit
  
    @Override
    public List<Product> getProduct() throws CustomException, SecurityException{
     List<Product> p01 = new ArrayList();
     String req01 = "select p from Product p";   
        Query qr01 = em.createQuery(req01);

         try{
 p01= (List<Product>) qr01.getResultList();
 return   p01;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun produit");
            throw ce;         
 }
         
    }
       //méthode pour afficher menu    
    @Override
    public List<Combo> getCombo() throws CustomException, SecurityException{
     List<Combo> c01 = new ArrayList();
     String req02 = "select c from Combo c";   
        Query qr02 = em.createQuery(req02);

         try{
 c01= (List<Combo>)  qr02.getResultList();
 return   c01;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun menu");
            throw ce;         
 }         
        
        
    }
    
//afficher comboCategorie
    @Override
    public List<ComboCategory> getComboCat(String nomMenu) throws CustomException, SecurityException{
        
     List<ComboCategory> c03 = new ArrayList();
     String req03 = "Select c from ComboCategory c where c.combo.name =:paramComboName order by c.number";   
        Query qr03 = em.createQuery(req03);
qr03.setParameter("paramComboName", nomMenu);
         try{
 c03= (List<ComboCategory>)  qr03.getResultList();
 return   c03;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun sous menu");
            throw ce;         
 }         
               
    }    
    
 //afficher produit du sous categorie menus
    @Override
     public List<Product> getComboProduct(String nomCategorie, String nomMenu) throws CustomException, SecurityException{
       
         
         
     List<Product> po04 = new ArrayList();
     String req04 = "Select c.products from  ComboCategory c where c.name= :paramComboCategoryName and c.combo.name =:paramComboName";   
        Query qr04 = em.createQuery(req04);
qr04.setParameter("paramComboCategoryName", nomCategorie);
qr04.setParameter("paramComboName",nomMenu);
         try{
 po04= (List<Product>)  qr04.getResultList();
 return   po04;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun produit dans sous menu");
            throw ce;         
 }         
        
        
    }    
     
     
     //*********************************************************************************
     //on insert un Combo, on index les combo item et en valeur on recupère les products

    /**
     *
     * @param nomMenu
     * @return on récupère une hashMap contenant en clef le sous menu et en valeure la list de produit du sous menu
     * @throws CustomException
     */
        @Override
     public HashMap<String, List<Product>> getHashProduct(String nomMenu) throws CustomException, SecurityException{
         
         HashMap <String, List<Product>> ha01 = new HashMap();
         
          List<ComboCategory> c03 = new ArrayList();
     String req03 = "Select c from ComboCategory c where c.combo.name =:paramComboName order by c.number";   
        Query qr03 = em.createQuery(req03);
qr03.setParameter("paramComboName", nomMenu);
         try{
 c03= (List<ComboCategory>)  qr03.getResultList();
 //return   c03;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun sous menu");
            throw ce;         
 }  
//*********************************************************  
//*********************************************************  
//il faut trier la c03 en fonction de number
 //*********************************************************  
 //*********************************************************   
         List<ComboCategory>c05 = new ArrayList();
         TriParComboCategory c04 = new TriParComboCategory();
       Collections.sort(c03, c04);        
 System.out.println("triiiiiiiiiiiiiiiiiiiiiiiiiii\n"+c03.toString());

 for(int i=0;i<c03.size();i++){
     System.out.println(i+" "+c03.get(i).getNumber()+"*******************************");
     
 }
  //**********insertion hashmap**********début
         for(int i=0;i<c03.size();i++){
       List<Product> po04 = new ArrayList(); 
     String req04 = "Select c.products from  ComboCategory c where c.name= :paramComboCategoryName and c.combo.name =:paramComboName";   
        Query qr04 = em.createQuery(req04);
qr04.setParameter("paramComboCategoryName", c03.get(i).getName());
qr04.setParameter("paramComboName", nomMenu);
         try{
          
 po04= (List<Product>)  qr04.getResultList();
 ha01.put(c03.get(i).getName(), po04);
 System.out.println("+++++++++++++++produit de la hashMap "+po04.toString());
 //return   po04;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun produit dans sous menu");
            throw ce;         
 }
         }
   //**********insertion hashmap**********fin      
       return ha01;  
     }
     //**************************************************************************
     
     

   
     
    
    
    //besoin de tous les produits d'un menu ==> inutile
    @Override
      public List<Product> getComboProductAll (String nomMenu) throws CustomException, SecurityException{
        
     List<Product> po05 = new ArrayList();
     String req05 = "Select c.products from  ComboCategory c where c.combo.name= :paramComboName";   
        Query qr05 = em.createQuery(req05);
qr05.setParameter("paramComboName", nomMenu);
         try{
 po05= (List<Product>)  qr05.getResultList();
 return   po05;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun produit dans menu");
            throw ce;         
 }         
        
        
    }     
     
     
     //a chaque clique on ajoute 1 produit
    @Override
    public HashMap<String,Long> getPanier(String nomComboCat,String nomProduit) throws CustomException {
  //  Product p01 = new Product();
     HashMap<String,Long> panier = new HashMap();
     String req01 = "select p from Product p where  p.name =:paramNameProduct";   
        Query qr01 = em.createQuery(req01);
qr01.setParameter("paramNameProduct", nomProduit);
         try{
             
             
             Product p01 = (Product) qr01.getSingleResult();

panier.put(nomComboCat, p01.getId());
return panier;
 }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR, "aucun produit");
            throw ce;         
 }       
        
      //  HashMap<Long,Integer> panier = new HashMap();
    }
    
    
    
    //on récupère toutes les id produits des menus commandés, ainsi que leur nombre
    @Override
 public HashMap<Long,Integer> getMenuProductCommande (HashMap<String,Long> getPanier) {
    HashMap<Long,Integer> menuProductPaid = new HashMap();
    for(HashMap.Entry<String,Long>entry : getPanier.entrySet()){
       Long idProduct = (Long) entry.getValue();
 
       menuProductPaid.put(idProduct, 1);
       
       
    }
    
    return menuProductPaid;
   
}
 

 public Combo getComboByName(String nameComboChoice) throws CustomException{
     Combo co01;
   TypedQuery<Combo> qr = em.createNamedQuery("entityBeans.Combo.getComboByName",Combo.class);  
     qr.setParameter("paramComboName", nameComboChoice);
     try{
         co01=qr.getSingleResult();
         
     }catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de combo");
         throw ce;
     }       
     return co01;
 }
 
 //dans cette méthode je créé l'objet orderItem et je fais un set avec le combo
 //je créé orderItem et je fais un set avec l'objet product + set avec status commandeEnCours
    @Override
    public void comboPersist(HashMap<String,Long>  hashPanier, String nameComboChoice, MyOrder myOrderPersist) throws CustomException{
 
     //   List <OrderItem> listOrderItem = new ArrayList (); 
        
    try{
    Combo co01 =  getComboByName(nameComboChoice);
    OrderItem oi = new OrderItem (0f, 0f);
  //  listOrderItem.add(oi); 
oi.setCombo(co01);

oi.setMyOrder(myOrderPersist);

em.persist(oi);

  }catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de produit");
         throw ce;
         
     }  

Product po01;

List<Product>li01=new ArrayList();

for(HashMap.Entry<String,Long>entry : hashPanier.entrySet()){
    try{
        
        Status statusOrderItemCommandeEnCours =  getStatusComboItemCommandeEnCours();
    String idString= String.valueOf(entry.getValue());
       po01= catalogTreatment.getProductById(idString);
        li01.add(po01);

       OrderItem oi = new OrderItem (po01.getPrice(), po01.getVat().getRate());
  
       oi.setProduct(po01);
       oi.setMyOrder(myOrderPersist);
       oi.setStatus(statusOrderItemCommandeEnCours);
       em.persist(oi);

    }catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de produit");
         throw ce;
     }       
        
    }

  System.out.println("*************myOrderPersist myOrderPersist myOrderPersist*************   "+  myOrderPersist.toString());

}
    

 
    @Override
    public MyOrder getLastOrderByAccountCode(int accountCode) throws CustomException  {
      MyOrder my01;
         TypedQuery<MyOrder> qr = em.createNamedQuery("entityBeans.MyOrder.getLastAccount",MyOrder.class);  
     qr.setParameter("paramAccountNumber", accountCode);
     qr.setMaxResults(1);
     try{
         my01=qr.getSingleResult();
         return my01;
     }catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de combo");
         throw ce;
     }
      
  }
    
    
    @Override
    public MyOrder getLastOrderbyTableNumber(int tableNumber)throws CustomException  {
          //je change le status de la table a la barbare    
//                Status s14 = new Status(14, "Actif", "My Table ");
//        Status s15 = new Status(15, "Inactif ", "My Table");
        MyTable maTable = em.find(MyTable.class,tableNumber);
        Status status =  em.find(Status.class,15);
        maTable.setStatus(status);
        em.merge(maTable);
        
        MyOrder my01;
         TypedQuery<MyOrder> qr = em.createNamedQuery("entityBeans.MyOrder.getOrderBylastTableNumber",MyOrder.class);  
     qr.setParameter("paramTableNumber", tableNumber);
     qr.setMaxResults(1);
     
     
     
     
     try{
         my01=qr.getSingleResult();
         return my01;
     }catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de combo");
         throw ce;
     }
    }
    
    
    //statut commande en cours
    @Override
        public Status getStatusComboItemCommandeEnCours() throws CustomException{
        
        
        Query qr=em.createNamedQuery("entityBeans.Status.getStatusByNum");
        qr.setParameter("paramNumStatus",0); 
        try {
        Status status = (Status) qr.getSingleResult(); 
        return status;
        } catch (NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de statut");
            throw ce;
            
        
        }
        
    }
    
    //on persist le combo item + le statue + on le produit associe recupere + la comande
        
    @Override
    public Product persistProductPanier(String stringIdProduct, MyOrder my) throws CustomException  {
        //je dois reconstituer le produit 
       
       
       
try{
    
      Product po01= catalogTreatment.getProductById(stringIdProduct);
       
       OrderItem oi = new OrderItem (po01.getPrice(), po01.getVat().getRate());
       Status statusOrderItemCommandeEnCours =  getStatusComboItemCommandeEnCours();
       oi.setStatus(statusOrderItemCommandeEnCours);
       oi.setMyOrder(my);
       oi.setProduct(po01);
       em.persist(oi);
       
       return po01;
       
       }catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de produit");
         throw ce;
         
    
         
         
     }    
        
    }  
        
        
        
             public Status getStatusApayer() throws CustomException{
        
        
        Query qr=em.createNamedQuery("entityBeans.Status.getStatusByNum");
        qr.setParameter("paramNumStatus",5); 
        try {
        Status status = (Status) qr.getSingleResult(); 
        return status;
        } catch (NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de statut");
            throw ce;
            
        
        }
        
    }   
        
    @Override
             public void changeStatusCommandeAPayer(MyOrder my) throws CustomException{
     Status status =     getStatusApayer()  ;      
     my.setStatus(status);
     em.merge(my);
             }
        
        
        
    @Override
     public List<OrderItem> getOrderItemByOrder(long MyOrderId) throws CustomException {
             
             
        TypedQuery<OrderItem> qr = em.createNamedQuery("entityBeans.OrderItem.selectByOrder",OrderItem.class);
        qr.setParameter("paramMyOrderId",MyOrderId);
        try{
            List<OrderItem> or01;
            or01 = qr.getResultList();
            return or01;
                }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR,"commande vide");
            throw ce;
        }
     }  
    
     
     
    @Override
             public OrderItem getOrderItemById(long id) throws CustomException{

        Query qr=em.createNamedQuery("entityBeans.OrderItem.selectOrderItemById");
        qr.setParameter("paramOrderItemId",id); 
        try {
        OrderItem oi = (OrderItem) qr.getSingleResult(); 
        return oi;
        } catch (NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de ligne de commande");
            throw ce;
            
        
        }
        
    }
     
     
     
     
     
     
     
    
    //méthode en private pour utiliser un autre métier
         private CatalogTreatmentLocal lookupCatalogTreatmentLocal() {
        try {
            Context c = new InitialContext();
            return (CatalogTreatmentLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/CatalogTreatment!sessionBeans.CatalogTreatmentLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    @Override
             public Status getStatusAPreparer() throws CustomException{
        
        
        Query qr=em.createNamedQuery("entityBeans.Status.getStatusByNum");
        qr.setParameter("paramNumStatus",1); 
        try {
        Status status = (Status) qr.getSingleResult(); 
        return status;
        } catch (NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de statut");
            throw ce;
            
        
        }
    } 
    
    
    @Override
          public void metOrderItemAPreparer(OrderItem oi) throws CustomException{

              
              
            
           Status status = getStatusAPreparer();
              oi.setStatus(status);
              em.merge(oi);
              
            
        }  
    

 
  }    

 