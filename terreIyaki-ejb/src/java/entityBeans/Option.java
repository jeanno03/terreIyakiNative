
package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
    @NamedQuery(name ="entityBeans.Option.selectAll", 
            query = "select o from Option o"),
    @NamedQuery(name ="entityBeans.Option.selectByOrderItem", 
            query = "select o from Option o join o.orderItems i where i = :paramItem")
})
public class Option implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String Name; 
    @Column(nullable = false)
    private String Value; 

    @ManyToMany(mappedBy = "options")
    private Collection <OrderItem> orderItems; 
    
    @ManyToMany(mappedBy = "options")
    private Collection <Product> products; 
    
    
    public Option() {
        orderItems = new ArrayList(); 
        products = new ArrayList(); 
    }

    public Option(String Name, String Value) {
        this(); 
        this.Name = Name;
        this.Value = Value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

 
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Option[ Name= " + Name +" Value= "+Value +" ]";
    }
    
}
