
package sessionBeans;

import entityBeans.MyTable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class TableTreatment implements TableTreatmentLocal {

    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;
    
    
    @Override
    public List <MyTable> selectAll () {
    
        Query qr = em.createNamedQuery("entityBeans.MyTable.selectAll");
        List<MyTable> mt = qr.getResultList();
        return mt;
    
    
    }
}
