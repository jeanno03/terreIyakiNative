
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
@NamedQuery(name="entityBeans.Combo.getComboByName",
      query = "Select c from Combo c where c.name = :paramComboName") 
})
public class Combo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String name; // de type A2 B6 etc
    private float price;
    private String description;
    
    @OneToMany(mappedBy="combo")
    private Collection<OrderItem>ordreItems;

   
    

    public Combo(String name, float price, String description, String urlImage) {
        this();
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
    }
    private String urlImage;
    
    @ManyToOne
    private Status status;
    
    @ManyToOne
    private Menu menu;
    
    @ManyToOne
    private VAT vat;
    
    @OneToMany(mappedBy = "combo")
    private Collection<ComboCategory> comboCategories;

    public Combo() {
         ordreItems= new ArrayList();
        comboCategories = new ArrayList();
    }


    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Collection<ComboCategory> getComboCategories() {
        return comboCategories;
    }

    public void setComboCategories(Collection<ComboCategory> comboCategories) {
        this.comboCategories = comboCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VAT getVat() {
        return vat;
    }

    public void setVat(VAT vat) {
        this.vat = vat;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
    
    @Override
    public String toString() {
        return "Combo " + name + " ";
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Collection<OrderItem> getOrdreItems() {
        return ordreItems;
    }

    public void setOrdreItems(Collection<OrderItem> ordreItems) {
        this.ordreItems = ordreItems;
    }
    
}
