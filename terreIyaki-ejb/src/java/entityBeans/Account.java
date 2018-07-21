
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
import javax.persistence.OneToOne;

/**
 *
 * @author jeanno
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "entityBeans.Account.getAccountByCode",
            query = "Select a from Account a where a.code = :paramAccountCode")
})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int code;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Status status;
    @ManyToOne
    private Menu menu; 
    
    @OneToOne
    private MyTable myTable;
    
    @ManyToMany
    private Collection<MyGrant> myGrants;    
    
    @OneToMany(mappedBy = "account")
    private Collection<MyOrder>myOrders;

    public Account() {
        myGrants = new ArrayList();
        myOrders = new ArrayList();
    }
    
    public Account(int code, String firstName, String lastName) {
        this();
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Account{" + "code=" + code + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return id+" : "+firstName+" "+lastName;
//    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(int Status) {
        this.status = status;
    }

    public Collection<MyGrant> getMyGrants() {
        return myGrants;
    }

    public void setMyGrants(Collection<MyGrant> myGrants) {
        this.myGrants = myGrants;
    }

    public Collection<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(Collection<MyOrder> myOrders) {
        this.myOrders = myOrders;
    }

    public MyTable getMyTable() {
        return myTable;
    }

    public void setMyTable(MyTable myTable) {
        this.myTable = myTable;
    }
    
}
