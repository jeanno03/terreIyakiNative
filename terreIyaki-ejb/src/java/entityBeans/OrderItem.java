/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author jeanno
 */
@Entity
@NamedQueries({
    @NamedQuery(name ="entityBeans.OrderItem.selectByOrder",
        query="Select o from OrderItem o where o.myOrder.id = :paramMyOrderId"),
    @NamedQuery(name ="entityBeans.OrderItem.selectReadyToCook",
        query="Select o from OrderItem o where o.status = :paramStatus"),
    @NamedQuery(name ="entityBeans.OrderItem.selectByStatus2Params",
        query="Select o from OrderItem o where o.status = :paramStatus1 or o.status = :paramStatus2"),
    @NamedQuery(name ="entityBeans.OrderItem.selectOrderItemOptions", 
            query = "select o.options from OrderItem o where o = :paramItem"),
    @NamedQuery(name ="entityBeans.OrderItem.selectOrderItemIngredients", 
            query = "select o.ingredients from OrderItem o where o = :paramItem"),
        @NamedQuery(name ="entityBeans.OrderItem.selectOrderItemById",
        query="Select o from OrderItem o where o.id = :paramOrderItemId")    
//    @NamedQuery(name ="entityBeans.OrderItem.getProductsByOrder",
//          query="Select o.products from OrderItem o where o.order.id =:paramMyOrderId")            
})
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float price;
    private float tax;
    
    @ManyToOne
    private Status status;
    
    @ManyToOne
    private Combo combo;
    

//    @ManyToOne
//    OrderItem comboOrderItem;
    @ManyToOne
    private MyOrder myOrder;

    @ManyToOne
    private OrderItem comboOrderItem;

    @OneToMany(mappedBy = "comboOrderItem")
    private Collection<OrderItem> orderItems;

    @ManyToMany
    private Collection <Ingredient> ingredients; 
        
    @ManyToMany
    private Collection <Option> options; 
    
    @ManyToOne
    private Product product; 
     
//    @ManyToOne
//    private ComboOrderItem comboOrderItem;
    public OrderItem() {
        orderItems = new ArrayList();
        ingredients = new ArrayList();
        options = new ArrayList();
    }

    public OrderItem(float price, float tax) {
        this();
        this.price = price;
        this.tax = tax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entityBeans.OrderItem[ id=" + id + " ]";
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public MyOrder getMyOrder() {
        return myOrder;
    }

    public void setMyOrder(MyOrder myOrder) {
        this.myOrder = myOrder;
    }

    public OrderItem getComboOrderItem() {
        return comboOrderItem;
    }

    public void setComboOrderItem(OrderItem comboOrderItem) {
        this.comboOrderItem = comboOrderItem;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Collection<Option> getOptions() {
        return options;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }
    
    

}
