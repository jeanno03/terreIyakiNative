
package sessionBeans;

import entityBeans.Ingredient;
import entityBeans.Option;
import entityBeans.OrderItem;
import entityBeans.Product;
import entityBeans.Status;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class KitchenTreatment implements KitchenTreatmentLocal {
    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;
  
    @Override
    public List<OrderItem> getToPrepareOrInPreparationItems(){
        List<OrderItem> lcr01 = new ArrayList();
        TypedQuery<OrderItem> qr = em.createNamedQuery("entityBeans.OrderItem.selectByStatus2Params", OrderItem.class);
        qr.setParameter("paramStatus1", getStatusByNum(1));
        qr.setParameter("paramStatus2", getStatusByNum(2));
        try {
            lcr01 = qr.getResultList();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return lcr01;
    }
    
    @Override
    public Status getStatusByNum(int statusNum) {
        Status sta;
        TypedQuery<Status> qr = em.createNamedQuery("entityBeans.Status.getStatusByNum", Status.class);
        qr.setParameter("paramNumStatus", statusNum);
        try {
            sta = qr.getSingleResult();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return sta;
    }
    
    @Override
    public OrderItem getOrderItemById(String id) {
        OrderItem oi = em.find(OrderItem.class, Long.valueOf(id));
        return oi;
    }
    
    @Override
    public void setItemStatusForward(String itemId) {
        OrderItem oi = em.find(OrderItem.class, Long.valueOf(itemId));
        int num = oi.getStatus().getNum();
        num++;
        Status sta = em.find(Status.class, num);
        oi.setStatus(sta);
        //em.merge(oi);
    }
    
    @Override
    public List<Option> getOptionsByOrderItem(OrderItem oi) {
        Query qr = em.createNamedQuery("entityBeans.OrderItem.selectOrderItemOptions");
        qr.setParameter("paramItem", oi);
        List<Option> lo = qr.getResultList();
        return lo;
    }
    
    @Override
    public List<Ingredient> getIngredientsByOrderItem(OrderItem oi) {
        Query qr = em.createNamedQuery("entityBeans.OrderItem.selectOrderItemIngredients");
        qr.setParameter("paramItem", oi);
        List<Ingredient> li = qr.getResultList();
        return li;
    }
}
