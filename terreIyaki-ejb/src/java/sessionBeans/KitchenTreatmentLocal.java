/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Ingredient;
import entityBeans.Option;
import entityBeans.OrderItem;
import entityBeans.Status;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi117
 */
@Local
public interface KitchenTreatmentLocal {

    public List<OrderItem> getToPrepareOrInPreparationItems();

    public void setItemStatusForward(String itemId);

    public Status getStatusByNum(int statusNum);

    public OrderItem getOrderItemById(String id);

    public List<Option> getOptionsByOrderItem(OrderItem oi);

    public List<Ingredient> getIngredientsByOrderItem(OrderItem oi);
    
}
