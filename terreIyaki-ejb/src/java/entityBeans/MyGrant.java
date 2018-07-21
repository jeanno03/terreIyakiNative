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

/**
 *
 * @author jeanno
 */
@Entity
public class MyGrant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String name;
    
    
    @ManyToMany(mappedBy="myGrants")
    private Collection<Account>accounts;

    public MyGrant() {
        accounts = new ArrayList();
    }

    public MyGrant(String name) {
        this();
        this.name = name;
    }
    
//    
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//
//    @Override
//    public String toString() {
//        return "entityBeans.MyGrant[ id=" + id + " ]";
//    }

    @Override
    public String toString() {
        return "MyGrant{" + "name=" + name + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }
    
}
