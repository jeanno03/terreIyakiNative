
package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author samira
 */
@Entity
@NamedQueries({
   @NamedQuery(name = "entityBeans.PaymentOption.getPaymentType",
           query = "Select p from PaymentOption p") ,
    @NamedQuery(name = "entityBeans.PaymentOption.getObjectPaymentType",
            query="Select p from PaymentOption p where p.Name=:paramName")
})
public class PaymentOption implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Column(nullable = false)
    private String Name; 

    
    
    @ManyToOne
    private Status status;
    
    
    
    
    // associations
    @OneToMany(mappedBy = "PaymentOption")
    private Collection<Payment> payment;

    public PaymentOption() {
        payment = new ArrayList(); 
    }

    public PaymentOption(String Name) {
        this();
        this.Name = Name;
    
    }

    public Collection<Payment> getPayment() {
        return payment;
    }

    public void setPayment(Collection<Payment> payment) {
        this.payment = payment;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

   
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PaymentOption[ Name= " + Name ;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
