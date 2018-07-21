/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author samira
 */
@Entity
@NamedQueries({
@NamedQuery(name="entityBeans.Payment.getPaymentByOrder",
      query = "Select p from Payment p where p.myOrder.id = :paramIdMyOrder") 
})
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Date; 

    @ManyToOne
    private Status status; 

    @ManyToOne
    private PaymentOption PaymentOption;
    
    @ManyToOne
    private MyOrder myOrder; 
       private float Amount; 

    public Payment() {
    }

    public Payment(Date Date, float Amount) {
        this.Date = Date;
        this.Amount = Amount;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float Amount) {
        this.Amount = Amount;
    }

    
    
    public PaymentOption getPaymentOption() {
        return PaymentOption;
    }

    public void setPaymentOption(PaymentOption PaymentOption) {
        this.PaymentOption = PaymentOption;
    }
 
    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyOrder getMyOrder() {
        return myOrder;
    }

    public void setMyOrder(MyOrder myOrder) {
        this.myOrder = myOrder;
    }


    @Override
    public String toString() {
        return "Payment[ Date=" + Date + " ]";
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status Status) {
        this.status = Status;
    }
    
}
