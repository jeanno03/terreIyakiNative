
package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class ComboCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private String name;
    
    @ManyToOne
    private Category category;
    
    @ManyToOne
    private Combo combo;
    
    @ManyToMany(mappedBy = "comboCategories")
    private Collection<Product> products;

    public ComboCategory() {
        products = new ArrayList();
    }
    
    

    public ComboCategory(int number, String name) {
        this();
        this.number = number;
        this.name = name;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return number+" "+name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
}
