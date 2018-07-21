
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

@Entity
@NamedQueries({
    @NamedQuery(name ="entityBeans.VAT.selectAll", 
            query = "select v from VAT v")
})
public class VAT implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private float rate; // in percent
    private String label;
    
    @OneToMany(mappedBy = "vat")
    private Collection<Product> products;
    
    @OneToMany(mappedBy = "vat")
    private Collection<Combo> combos;
    
    @ManyToOne
    private Status status;

    public VAT() {
        products = new ArrayList();
        combos = new ArrayList();
    }

    public VAT(float rate, String label) {
        this();
        this.rate = rate;
        this.label = label;
    }

    public Collection<Combo> getCombos() {
        return combos;
    }

    public void setCombos(Collection<Combo> combos) {
        this.combos = combos;
    }
    
    

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }  

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return label+" : "+rate+"%";
    }
    
}
