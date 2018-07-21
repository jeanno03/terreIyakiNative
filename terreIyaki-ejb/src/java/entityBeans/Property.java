
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name ="entityBeans.Property.selectAll", 
            query = "select p from Property p")
})
public class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name; // peut servir d'id ?
    private String value; // on ne va pas faire des calculs avec les contenances ? ou calories etc
    
    @ManyToOne
    private Unit unit;
    @ManyToMany(mappedBy = "properties")
    private Collection<Product> products;
    

    public Property() {
        products = new ArrayList();       
    }

    public Property(String name, String value) {
        this();
        this.name = name;
        this.value = value;
    } 

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name+" : "+value;
    }
    
}
