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
    @NamedQuery(name = "entityBeans.Product.selectAll", 
            query = "select p from Product p"),
    @NamedQuery(name = "entityBeans.Product.selectProductById", 
            query = "select p from Product p where p.id = :paramId"),
    @NamedQuery(name = "entityBeans.Product.selectProductsByCategory", 
            query = "select p from Product p where p.category = :paramCat"),
    @NamedQuery(name = "entityBeans.Product.selectProductProperties", 
            query = "select p.properties from Product p where p = :paramProd"),
    @NamedQuery(name = "entityBeans.Product.selectProductOptions", 
            query = "select p.options from Product p where p = :paramProd"),
    @NamedQuery(name = "entityBeans.Product.selectProductIngredients", 
            query = "select p.ingredients from Product p where p = :paramProd"),
    @NamedQuery(name = "entityBeans.Product.selectProductSides", 
            query = "select p.sides from Product p where p = :paramProd")
})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private Float price;
    private String picture;
    private String description;
    
    @ManyToMany
    private Collection<Property> properties;
    @ManyToOne
    private VAT vat; 
    @ManyToMany(mappedBy = "sides")
    private Collection<Product> mainCourses;
    @ManyToMany
    private Collection<Product> sides;
    @ManyToOne
    private Category category;
    @ManyToMany
    private Collection<ComboCategory> comboCategories;
    @OneToMany(mappedBy = "product")
    private Collection<OrderItem> orderItems;
    @ManyToMany
    private Collection<Option> options;
    @ManyToMany
    private Collection<Ingredient> ingredients;
    @ManyToOne
    private Status status;

    public Product() {
        properties = new ArrayList();
        sides = new ArrayList();
        mainCourses = new ArrayList();
        comboCategories = new ArrayList();
        orderItems = new ArrayList();
        options = new ArrayList();
        ingredients = new ArrayList();
    }

    public Product(String name, Float price, String picture, String description) {
        this();
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.description = description;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Collection<Option> getOptions() {
        return options;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }   

    public Collection<ComboCategory> getComboCategories() {
        return comboCategories;
    }

    public void setComboCategories(Collection<ComboCategory> comboCategories) {
        this.comboCategories = comboCategories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    } 

    public Collection<Product> getSides() {
        return sides;
    }

    public void setSides(Collection<Product> sides) {
        this.sides = sides;
    }

    public Collection<Product> getMainCourses() {
        return mainCourses;
    }

    public void setMainCourses(Collection<Product> mainCourses) {
        this.mainCourses = mainCourses;
    }

    public VAT getVat() {
        return vat;
    }

    public void setVat(VAT vat) {
        this.vat = vat;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }
    
    public Float getPriceWithVAT() {
        Float priceWithVAT = price;
        if (vat != null) {
            priceWithVAT += price * vat.getRate() / 100;
        }
        return priceWithVAT;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name +" "+description ;
    }
    
}
