/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.MyOrder;
import entityBeans.OrderItem;
import entityBeans.Payment;
import entityBeans.PaymentOption;
import entityBeans.Product;
import entityBeans.Status;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.naming.NamingException;
import tools.CustomException;
import tools.Mail;
import tools.ProductAmount;

/**
 *
 * @author jeanno
 */
@Local
public interface PayementTreatmentLocal {

    public void envoyerMail(String mailDestinataire) throws NamingException, SQLException, EJBException;


//    public Mail getMail() throws NamingException, SQLException;

    public void getBillPdf(String nomMenu);

    public Mail getMail() throws NamingException, SQLException;

    public List<MyOrder> getOrderToPay() throws CustomException;

    public HashMap<Long,ProductAmount> getMontant(Long MyOrderId, HashMap<Long,ProductAmount> ha01) throws CustomException;

    public List<OrderItem> getItemsFromOrder(Long idCommande) throws CustomException;

    public float getMontantRestant(float priceTotal, float payment);

    public List<PaymentOption> getPaymentType() throws CustomException;

    public PaymentOption getObjectPaymentType(String nameChoisi) throws CustomException;

    public MyOrder getOrderById(long id) throws CustomException;

    public void persistPayment(float priceTotal, PaymentOption po02, MyOrder my02, float mt);

    public Status getStatus(String name) throws CustomException;

    public List<Payment> getPaymentbyOrder(Long id) throws CustomException;

    public float getMontantEncaisseTTC(float priceTotal, MyOrder my02);

    public void changeStatutOrder(float priceTotal, float cumulPayment,  long id);

    public void getBillPdfV02(MyOrder my02, List<OrderItem> or01, float priceTotal, List<Payment> lo09);

    public float getMontantRestantTTCV02(long id) throws CustomException;

    



    
}
