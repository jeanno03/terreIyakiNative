
package subControllers;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.CatalogTreatmentLocal;

/**
 *
 * @author chelmix
 */
public class BackOfficeCtrl implements ControllerInterface, Serializable {
    CatalogTreatmentLocal catalogTreatment = lookupCatalogTreatmentLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        
        request.setAttribute("categories", catalogTreatment.getAllCategories());
        request.setAttribute("vats", catalogTreatment.getAllVATs());
        request.setAttribute("properties", catalogTreatment.getAllProperties());
        request.setAttribute("options", catalogTreatment.getAllOptions());
        request.setAttribute("ingredients", catalogTreatment.getAllIngredients());
        //request.setAttribute("sides", catalogTreatment.getAllProducts());
        
        if("createproduct".equals(action)) {
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String picture = request.getParameter("picture");
            String description = request.getParameter("description");
            String categoryName = request.getParameter("pcategory");
            String vatId = request.getParameter("pvat");
            String[] pproperties = request.getParameterValues("pproperties");
            String[] poptions = request.getParameterValues("poptions");
            String[] pingredients = request.getParameterValues("pingredients");
            catalogTreatment.createProduct(name, price, picture, description, categoryName, vatId, pproperties, poptions, pingredients);
        }
        
        
        return "backoffice";
    }

    private CatalogTreatmentLocal lookupCatalogTreatmentLocal() {
        try {
            Context c = new InitialContext();
            return (CatalogTreatmentLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/CatalogTreatment!sessionBeans.CatalogTreatmentLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
