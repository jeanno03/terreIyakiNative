package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String type; // client ou serveur
    
    @OneToMany(mappedBy = "menu")
    private Collection<Category> categories;
    
    @OneToMany(mappedBy = "menu")
    private Collection<Category> combos;
    
    @OneToMany(mappedBy = "menu")
    private Collection<Category> accounts;

    public Menu() {
        categories = new ArrayList();
        combos = new ArrayList();
        accounts = new ArrayList();
    }

    public Menu(String type) {
        this();
        this.type = type;
    }
    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public Collection<Category> getCombos() {
        return combos;
    }

    public void setCombos(Collection<Category> combos) {
        this.combos = combos;
    }

    public Collection<Category> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Category> accounts) {
        this.accounts = accounts;
    }
    
    

    @Override
    public String toString() {
        return "Menu" + type + " ]";
    }
    
}
