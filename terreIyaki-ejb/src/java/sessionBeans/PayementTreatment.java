/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;


import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import entityBeans.MyOrder;
import entityBeans.OrderItem;
import entityBeans.Payment;
import entityBeans.PaymentOption;
import entityBeans.Status;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tools.ConnexionBDD;
import tools.CustomException;
import tools.Mail;
import tools.ProductAmount;


/**
 *
 * @author jeanno
 */
@Stateless
public class PayementTreatment implements PayementTreatmentLocal {
    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;


//    public void persist(Object object) {
//        em.persist(object);
//    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
 
    @Override
    public void envoyerMail(String mailDestinataire) throws NamingException, SQLException, EJBException {



        //on utilise une méthode d'un pojo pour récupérer identifiant de connexion à la boite mail
        Mail mail01 = getMail();

        String from = mail01.getMail();//mettre adresse mail
        final String username = mail01.getMail();//mettre adresse mail
        final String password = mail01.getMdp();//mettre mot de passe

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailDestinataire));

            // Set Subject: header field
            message.setSubject("Restaurant Iyaki - Facture");

            // Now set the actual message
            message.setText("Votre Restaurant Iyaki vous remercie de votre visite\nVous trouverez ci-joint votre facture\nEn espérant vous retrouver prochainement");


            
 //***********************PJ****************************************************           
//En local local            
//DataSource source = new FileDataSource("/home/jeanno/Files/facture.pdf");
            
//En local windows
//DataSource source = new FileDataSource("c:/Files/facture.pdf");            
            
//sur serveur distant            
DataSource source = new FileDataSource("/home/jeannory/Files/facture.pdf");
message.setDataHandler(new DataHandler(source));

//En local local  
//message.setFileName("/home/jeanno/Files/facture.pdf");

//En local windows
//message.setFileName("c:/Files/facture.pdf");

//sur serveur distant 
message.setFileName("/home/jeannory/Files/facture.pdf");

 //***********************PJ****************************************************                               
           

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Mail getMail() throws NamingException, SQLException {


        ConnexionBDD b01 = new ConnexionBDD();

        Mail mail01 = new Mail("", "");
        try (Connection cnt = b01.getConnetion();
                java.sql.Statement stm = cnt.createStatement();) {
            String req = "SELECT * FROM mail";
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                String email = rs.getString("email");
                String mdp = rs.getString("mdp");
                mail01.setMail(email);
                mail01.setMdp(mdp);
            }

        }
        return mail01;
    }

//    public void persist(Object object) {
//        em.persist(object);
//    }
    
    
    
    

    @Override
    public void getBillPdf(String nomMenu){
        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);
try{
//en local    
//PdfWriter.getInstance(document,new FileOutputStream("/home/jeanno/Files/facture.pdf"));

//en local windows
//PdfWriter.getInstance(document,new FileOutputStream("c:/Files/facture.pdf"));    
    
//sur serveur distant
PdfWriter.getInstance(document,new FileOutputStream("/home/jeannory/Files/facture.pdf"));
document.open();

//en local
//Image image = Image.getInstance("/home/jeanno/Files/logo.png");

//en local windows
//Image image = Image.getInstance("c:/Files/logo.png");

//sur serveur distant
Image image = Image.getInstance("/home/jeannory/Files/logo.png");
document.add(image);
SimpleDateFormat formater = null;
Date aujourdhui = new Date();
        
formater = new SimpleDateFormat("EEEE d MMM yyyy");      
 
Paragraph paragraph = new Paragraph("\n\n\n");
document.add(paragraph);
        
paragraph = new Paragraph("Facture du "+formater.format(aujourdhui));
paragraph.setIndentationLeft(30f);
document.add(paragraph);

 paragraph = new Paragraph("\n\n");
document.add(paragraph);

paragraph = new Paragraph("Produits commandés :");
document.add(paragraph);

paragraph = new Paragraph("\n");
document.add(paragraph);

paragraph = new Paragraph(nomMenu +" - quantité : 1");
paragraph.setIndentationLeft(15f);
document.add(paragraph);


paragraph = new Paragraph("Plat du jour : 50€ (fictif)");
paragraph.setIndentationLeft(15f);
document.add(paragraph);

paragraph = new Paragraph("Coca cola : 20€ (fictif)");
paragraph.setIndentationLeft(15f);
document.add(paragraph);

paragraph = new Paragraph("Montant HT : 70€ (fictif)");
paragraph.setIndentationLeft(15f);
document.add(paragraph);

paragraph = new Paragraph("TVA : 20% (fictif)");
paragraph.setIndentationLeft(15f);
document.add(paragraph);

paragraph = new Paragraph("Net à payer : 84€ (fictif)");
paragraph.setIndentationLeft(15f);
document.add(paragraph);

paragraph = new Paragraph("\n\n\n");
document.add(paragraph);

paragraph = new Paragraph("Terre Iyaki Restaurant vous remercie de votre visite et vous dit à bientôt");
document.add(paragraph);


} catch (DocumentException de) {
de.printStackTrace();
} catch (IOException ioe) {
ioe.printStackTrace();
}
document.close();
}
        
 
    
 //afficher toutes les commandes en attente de règlement
    @Override
    public List<MyOrder> getOrderToPay() throws CustomException{
        List<MyOrder> my01;
        String parametre = "A payer";
        TypedQuery<MyOrder> qr = em.createNamedQuery("entityBeans.MyOrder.getOrderToPay", MyOrder.class);
        qr.setParameter("paramStatusName", parametre);
        try{
           my01 = qr.getResultList();
        }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de commande en attente de règlement");
            throw ce;
        }
        return my01;
    }
    
    //affiche via hashMap tous les montants des commande (via id)
    @Override
    public HashMap<Long,ProductAmount> getMontant(Long MyOrderId, HashMap<Long,ProductAmount> ha01)throws CustomException{
 //       HashMap<Long,ProductAmount> ha01 = new HashMap();
        List<OrderItem> or01;
        TypedQuery<OrderItem> qr = em.createNamedQuery("entityBeans.OrderItem.selectByOrder",OrderItem.class);
        qr.setParameter("paramMyOrderId",MyOrderId);
        try{
            or01 = qr.getResultList();
                }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR,"commande vide");
            throw ce;
        }
        float ht=0f;
        float ttc = 0f;
        //somme ht
        for(int i=0;i<or01.size();i++){
            ht=ht+or01.get(i).getPrice();
        }
        //somme ttc
        for(int i=0;i<or01.size();i++){
       ttc = ttc+  or01.get(i).getPrice()+ ((or01.get(i).getPrice()*or01.get(i).getTax())/100);
            
        }
        ProductAmount po01= new ProductAmount(ht,ttc);
        ha01.put(MyOrderId, po01);
        return ha01;
    }
    
    //affiche la list des produits de la commande
    
    @Override
    public List<OrderItem> getItemsFromOrder(Long idCommande) throws CustomException{
        List<OrderItem> or01;
        TypedQuery<OrderItem> qr = em.createNamedQuery("entityBeans.MyOrder.getItemsByOrder",OrderItem.class);
        qr.setParameter("paramMyOrderId",idCommande);
        try{
            or01=qr.getResultList();
        }catch (NoResultException ex){
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de produits affectés");
            throw ce;
        }
       return  or01;
    }
    
    //fonction pour avoir le solde restant 
    @Override
    public float getMontantRestant(float priceTotal, float payment){

      float  montantRestantTTC = priceTotal -payment;
        
        System.out.println(" montantRestantTTC dans la méthode ++++++++++++    "+ montantRestantTTC);
        return montantRestantTTC;
        
    }
    
  //disposer de tous les moyens de payment
    @Override
    public List<PaymentOption> getPaymentType() throws CustomException{
      List<PaymentOption> op01;
      TypedQuery<PaymentOption> qr = em.createNamedQuery("entityBeans.PaymentOption.getPaymentType", PaymentOption.class);
     try{
         op01= qr.getResultList();}
     catch (NoResultException ex){
         CustomException ce = new CustomException(CustomException.USER_ERR,"pas de moyen de payment");
         throw ce;
     }
       return  op01;
    }
    
    
  //on récupère l objet pour l associer au montant réglé  
    @Override
    public PaymentOption getObjectPaymentType(String nameChoisi)throws CustomException{
        PaymentOption op01;
            TypedQuery<PaymentOption> qr = em.createNamedQuery("entityBeans.PaymentOption.getObjectPaymentType", PaymentOption.class);
     qr.setParameter("paramName", nameChoisi);
            try{
         op01= qr.getSingleResult();}
     catch (NoResultException ex){
         CustomException ce = new CustomException(CustomException.USER_ERR,"pas de moyen de payment");
         throw ce;
     }
       return  op01;  
        
    }
    
    
    @Override
  public MyOrder getOrderById(long id) throws CustomException{
      MyOrder mo01;
     TypedQuery<MyOrder> qr=em.createNamedQuery("entityBeans.MyOrder.getOrderById",MyOrder.class);
     qr.setParameter("paramMyOrderId", id);
     try{
         mo01=qr.getSingleResult();
     }
        catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de commande");
         throw ce;
     }
     return mo01;
 }   
  
  
  //prix facture TTC, option de payment choisi, n° commande et montant encaissé
    @Override
 public void persistPayment(float priceTotal, PaymentOption po02, MyOrder my02 , float mt){
            
          SimpleDateFormat formater = null;
        Date aujourdhui = new Date();

        formater = new SimpleDateFormat("EEEE d MMM yyyy");

        Date today = aujourdhui;

Payment po01 = new Payment(today,mt);
po01.setPaymentOption(po02);
po01.setMyOrder(my02);

em.persist(po01); 

} 
 
 

 
 
    @Override
    public float getMontantEncaisseTTC(float priceTotal, MyOrder my02) {
        List<Payment> lo09 = new ArrayList();
try{
     lo09= getPaymentbyOrder(my02.getId());
            }catch (CustomException ex){
      String text = ex.getMessage();

  }    
    //si le prix total est inférieur à la somme encaissé on change de statut ==> les montants encaissés sont en TTC
float cumulPayment=0f;
for(int i=0;i<lo09.size();i++){
    cumulPayment = cumulPayment + lo09.get(i).getAmount();
    
}

//System.out.println("++++++++++++++++++++++++++++  cumulPayment cumulPayment "+cumulPayment); 

    return cumulPayment;
   
    
}
 
 
    @Override
 public void changeStatutOrder(float priceTotal, float cumulPayment, long id){

     
     if(cumulPayment>=priceTotal){
           String mot = "Payé";
           
      
           
           
           
           
           
     try{
         MyOrder my02 =getOrderById(id); 
     Status st01 = getStatus(mot);
      my02.setStatus(st01);
      em.persist(my02);
}catch (CustomException ex){
      String text = ex.getMessage();
     
  }  

     
     
     }
     
 }
 
 
 
 
 
    @Override
    public Status getStatus(String name) throws CustomException {
    Status st01;
        TypedQuery<Status> qr = em.createNamedQuery("entityBeans.Status.getStatus",Status.class);
    qr.setParameter("nameStatus", name);
    try{
st01= qr.getSingleResult();
    }
            catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de commande");
         throw ce;
     }
    return st01;
    
}
    
    
    
    @Override
    public List<Payment> getPaymentbyOrder(Long id) throws CustomException{
        List<Payment> lo09;
         TypedQuery<Payment> qr = em.createNamedQuery("entityBeans.Payment.getPaymentByOrder",Payment.class);
    qr.setParameter("paramIdMyOrder", id);
    try{
lo09= qr.getResultList();
    }
            catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"pas de paiement");
         throw ce;
     }       
        return lo09;
    }
 
    
    

//
//    public void envoyerMailV02(String mailDestinataire) throws NamingException, SQLException, EJBException {
//
//
//
//        //on utilise une méthode d'un pojo pour récupérer identifiant de connexion à la boite mail
//        Mail mail01 = getMail();
//
//        String from = mail01.getMail();//mettre adresse mail
//        final String username = mail01.getMail();//mettre adresse mail
//        final String password = mail01.getMdp();//mettre mot de passe
//
//        String host = "smtp.gmail.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//
//        // Get the Session object.
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            // Create a default MimeMessage object.
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(mailDestinataire));
//
//            // Set Subject: header field
//            message.setSubject("Restaurant Iyaki - Facture");
//
//            // Now set the actual message
//            message.setText("Votre Restaurant Iyaki vous remercie de votre visite\nVous trouverez ci-joint votre facture\nEn espérant vous retrouver prochainement");
//
//
//            
// //***********************PJ****************************************************           
////A changer            
////DataSource source = new FileDataSource("/home/jeanno/Files/test.pdf");
//DataSource source = new FileDataSource("/home/jeannory/Files/test.pdf");
//message.setDataHandler(new DataHandler(source));
//
////A changer  
////message.setFileName("/home/jeanno/Files/test.pdf");
//message.setFileName("/home/jeannory/Files/test.pdf");
//
// //***********************PJ****************************************************                               
//           
//
//            // Send message
//            Transport.send(message);
//
//            System.out.println("Sent message successfully....");
////
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//


    

    @Override
    public void getBillPdfV02(MyOrder my02, List<OrderItem> po03, float priceTotal, List<Payment> lo09 ){
        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);
try{
//en local    
//PdfWriter.getInstance(document,new FileOutputStream("/home/jeanno/Files/facture.pdf"));
    
//en local windows
//PdfWriter.getInstance(document,new FileOutputStream("c:/Files/facture.pdf"));      

//sur serveur distant
PdfWriter.getInstance(document,new FileOutputStream("/home/jeannory/Files/facture.pdf"));
document.open();

//en local
//Image image = Image.getInstance("/home/jeanno/Files/logo.png");

//en local windows
//Image image = Image.getInstance("c:/Files/logo.png");

//sur serveur distant
Image image = Image.getInstance("/home/jeannory/Files/logo.png");
document.add(image);
SimpleDateFormat formater = null;
Date aujourdhui = new Date();
        
formater = new SimpleDateFormat("EEEE d MMM yyyy");      
 
Paragraph paragraph = new Paragraph("\n\n\n");
document.add(paragraph);
        
paragraph = new Paragraph("Facture n° "+my02.getId()+ " - "+formater.format(aujourdhui));
paragraph.setIndentationLeft(30f);
document.add(paragraph);


paragraph = new Paragraph("Montant total : "+priceTotal  +"€ TTC");
paragraph.setIndentationLeft(15f);
document.add(paragraph);

 paragraph = new Paragraph("\n\n");
document.add(paragraph);

paragraph = new Paragraph("Produits :");
paragraph.setIndentationLeft(15f);
document.add(paragraph);


//je fais une boucle de tous les produits
for(OrderItem po : po03){
 paragraph = new Paragraph(   
   "Qté : 1 - prix : "+po.getProduct().getPrice()+"€ HT - tva : "+ po.getProduct().getVat().getRate()+ "% - " +po.getProduct().getName());
document.add(paragraph);
}

//for(OrderItem po : po03){
// paragraph = new Paragraph(   
//   "\nMenu - Qté : 1 - "+po.getCombo().getName());
//}

paragraph = new Paragraph("\n");
document.add(paragraph);
paragraph = new Paragraph("\n");
document.add(paragraph);

paragraph = new Paragraph("Règlements :");
paragraph.setIndentationLeft(15f);
document.add(paragraph);


for(Payment pa : lo09 ){
 paragraph = new Paragraph(   
   "Montant : "+pa.getAmount()+"€ -"+ pa.getPaymentOption().getName());
document.add(paragraph);
}


paragraph = new Paragraph("\n\n\n");
document.add(paragraph);

paragraph = new Paragraph("Terre Iyaki Restaurant vous remercie de votre visite et vous dit à bientôt");
document.add(paragraph);


} catch (DocumentException de) {
de.printStackTrace();
} catch (IOException ioe) {
ioe.printStackTrace();
}
document.close();
}
    
    
    
    
    
    @Override
 public float   getMontantRestantTTCV02 (long id) throws CustomException{
     
     float calulMontantRestantTTCV02;
     
     
     //On associe les orderItem pour calculer facture TTC
     
     //on associe les reglements
     
    // on calcul la différence
     try{
         //on cherche l 'objet MyOrder
     MyOrder my01 = getOrderById(id);
     List<OrderItem> lior01 = getItemsFromOrder(id);
     List<Payment> lipa01 = getPaymentbyOrder(id);
     
     float totalTTC=0f;
     for(int i=0;i<lior01.size();i++){
         totalTTC = totalTTC + (lior01.get(i).getPrice() + ((lior01.get(i).getPrice()*lior01.get(i).getTax())/100));
   
     }
    System.out.println("+++++++++++++++++++V2 totalTTC "+totalTTC);
    
    
     float totalpayerTTC = 0f;
         for(int i=0;i<lipa01.size();i++){
         totalpayerTTC = totalpayerTTC + lipa01.get(i).getAmount();
   
     } 
     System.out.println("+++++++++++++++++++V2 totalpayerTTC "+totalpayerTTC);
     
     
     float soldeRestantTTC = totalTTC- totalpayerTTC;
          System.out.println("+++++++++++++++++++V2 soldeRestantTTC "+soldeRestantTTC);
     return soldeRestantTTC;
     
      }
            catch(NoResultException ex) {
            CustomException ce = new CustomException(CustomException.USER_ERR,"error");
         throw ce;
     }   
     
     
     
     
 }
    
          
}




