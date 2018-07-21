/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author jeanno
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "entityBeans.MyOrder.getOrderToPay",
            query = "Select m from MyOrder m where m.status.Name = :paramStatusName"),
    @NamedQuery(name = "entityBeans.MyOrder.getItemsByOrder",
            query = "Select m.orderItems from MyOrder m where m.id =:paramMyOrderId"),
    @NamedQuery(name = "entityBeans.MyOrder.getOrderById",
            query = "Select m from MyOrder m where m.id =:paramMyOrderId"),
    @NamedQuery(name = "entityBeans.MyOrder.getLastAccount",
            query = "Select m from MyOrder m where m.account.code =:paramAccountNumber order by m.id desc"),
    @NamedQuery(name = "entityBeans.MyOrder.getOrderBylastTableNumber",
            query = "select distinct o from MyOrder o join o.myTables t where t.tableNumber = :paramTableNumber order by o.id desc")
})
public class MyOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date orderDate;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Account account;

    @ManyToMany
    private Collection<MyTable> myTables;

    @OneToMany(mappedBy = "myOrder")
    private Collection<Payment> payments;

    @OneToMany(mappedBy = "myOrder")
    private Collection<OrderItem> orderItems;

    public MyOrder() {
        myTables = new ArrayList();
        orderItems = new ArrayList();
        payments = new ArrayList();
    }

    public MyOrder(Date orderDate) {
        this();
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entityBeans.MyOrder[ id=" + id + " ]";
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Collection<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Collection<Payment> payments) {
        this.payments = payments;
    }

}
