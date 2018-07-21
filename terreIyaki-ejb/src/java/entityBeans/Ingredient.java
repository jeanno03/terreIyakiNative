
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

/**
 *
 * @author samira
 */
@Entity
@NamedQueries({
    @NamedQuery(name ="entityBeans.Ingredient.selectAll", 
            query = "select i from Ingredient i")
})
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String Name; 
    @Column(nullable = false)
    private String Description; 

    
    @ManyToMany (mappedBy = "ingredients")
    private Collection <OrderItem> orderItems; 
    
    
    @ManyToMany(mappedBy = "ingredients")
    private Collection <Product> products; 
    
    // add status : removable - not removable
    
    
    public Ingredient() {
        orderItems = new ArrayList();
        products = new ArrayList(); 
        
    }

    public Ingredient(String Name, String Description) {
        this(); 
        this.Name = Name;
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

   

    
    
    
   
    @Override
    public String toString() {
          return "Ingredient [ Name= " + Name +" Description= "+Description +" ]";
    }
    
}
