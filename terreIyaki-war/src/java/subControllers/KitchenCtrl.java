package subControllers;

import entityBeans.Ingredient;
import entityBeans.Option;
import entityBeans.OrderItem;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.KitchenTreatmentLocal;

public class KitchenCtrl implements ControllerInterface, Serializable {

    KitchenTreatmentLocal kitchenTreatment = lookupKitchenTreatmentLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if ("status-forward".equals(action)) {
            if (request.getParameter("item-id") != null) {
                kitchenTreatment.setItemStatusForward(request.getParameter("item-id"));      
            }
        }
        if ("set-status-forward".equals(action)) {
            if (request.getParameter("item-id") != null) {                           
                kitchenTreatment.setItemStatusForward(request.getParameter("item-id"));
                OrderItem oi = kitchenTreatment.getOrderItemById(request.getParameter("item-id")); 
                request.setAttribute("kitchenOrderItem", oi);
                return "/includes/ajax/order-item-display";
            }
        }
        if ("get-customization".equals(action)) {
            if (request.getParameter("item-id") != null) {
                OrderItem oi = kitchenTreatment.getOrderItemById(request.getParameter("item-id"));
                request.setAttribute("item", oi);                
                if (!oi.getOptions().isEmpty()){
                    List<Option> options = kitchenTreatment.getOptionsByOrderItem(oi);
                    request.setAttribute("options", options);
                }
                if (!oi.getIngredients().isEmpty()){
                    List<Ingredient> ingredients = kitchenTreatment.getIngredientsByOrderItem(oi);
                    request.setAttribute("ingredients", ingredients);
                }
                return "/includes/ajax/order-item-customization";
            }
        }
        request.setAttribute("orderItems", kitchenTreatment.getToPrepareOrInPreparationItems());
        return "kitchen";
    }

    private KitchenTreatmentLocal lookupKitchenTreatmentLocal() {
        try {
            Context c = new InitialContext();
            return (KitchenTreatmentLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/KitchenTreatment!sessionBeans.KitchenTreatmentLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
