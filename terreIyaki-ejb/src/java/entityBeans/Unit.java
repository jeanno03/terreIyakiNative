
package entityBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String abbreviation;
    
    @OneToMany(mappedBy = "unit")
    private Collection<Property> properties;

    public Unit() {
        properties = new ArrayList();
    }

    public Unit(String name, String abbreviation) {
        this();
        this.name = name;
        this.abbreviation = abbreviation;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name+"("+abbreviation+")";
    }
    
}
