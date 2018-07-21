
package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
    @NamedQuery(name ="entityBeans.Category.selectAll", 
            query = "select c from Category c"),
    @NamedQuery(name = "entityBeans.Category.selectCategoryById", 
            query = "select c from Category c where c.id = :paramId")
})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
       
    private String name;
    
    @OneToMany(mappedBy = "category")
    private Collection<ComboCategory> comboCategories;
    
    @OneToMany(mappedBy = "category")
    private Collection<Product> products;

    @ManyToOne
    private Menu menu;
    
    public Category() {
        comboCategories = new ArrayList();
        products = new ArrayList();
    }
    

    public Category(String name) {
        this();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ComboCategory> getComboCategories() {
        return comboCategories;
    }

    public void setComboCategories(Collection<ComboCategory> comboCategories) {
        this.comboCategories = comboCategories;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
    
    
    @Override
    public String toString() {
        return "Category : " + name;
    }
    
}
