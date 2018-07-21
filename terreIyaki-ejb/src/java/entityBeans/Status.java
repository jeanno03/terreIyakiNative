/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author samira
 */
@Entity
@NamedQueries({
    @NamedQuery(name="entityBeans.Status.getStatus",
        query="Select s from Status s where s.Name=:nameStatus"), 
    @NamedQuery(name="entityBeans.Status.getStatusByNum",
        query="Select s from Status s where s.num=:paramNumStatus")          
})

public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int num;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private String Label;

    @OneToMany(mappedBy = "status")
    private Collection<Account> accounts;
    @OneToMany(mappedBy = "status")
    private Collection<Combo> combos;
    @OneToMany(mappedBy = "status")
    private Collection<MyOrder> myOrders;
    @OneToMany(mappedBy = "status")
    private Collection<MyTable> myTables;
    @OneToMany(mappedBy = "status")
    private Collection<OrderItem> orderItems;
    @OneToMany(mappedBy = "status")
    private Collection<Payment> payments;
    @OneToMany(mappedBy = "status")
    private Collection<PaymentOption> paymentOptions;
    @OneToMany(mappedBy = "status")
    private Collection<Product> products;
    @OneToMany(mappedBy = "status")
    private Collection<VAT> vats;

    public Status() {
        accounts = new ArrayList();
        combos = new ArrayList();
        myOrders = new ArrayList();
        myTables = new ArrayList();
        orderItems = new ArrayList();
        payments = new ArrayList();
        paymentOptions = new ArrayList();
        products = new ArrayList();
        vats = new ArrayList();
    }

    public Status(int num, String Name, String Label) {
        this.num = num;
        this.Name = Name;
        this.Label = Label;
    }

    
   
    
    
    
    
    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Combo> getCombos() {
        return combos;
    }

    public void setCombos(Collection<Combo> combos) {
        this.combos = combos;
    }

    public Collection<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(Collection<MyOrder> myOrders) {
        this.myOrders = myOrders;
    }

    public Collection<MyTable> getMyTables() {
        return myTables;
    }

    public void setMyTables(Collection<MyTable> myTables) {
        this.myTables = myTables;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Collection<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Collection<Payment> payments) {
        this.payments = payments;
    }

    public Collection<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(Collection<PaymentOption> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Collection<VAT> getVats() {
        return vats;
    }

    public void setVats(Collection<VAT> vats) {
        this.vats = vats;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return Name ;
    }

}
