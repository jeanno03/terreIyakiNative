
package subControllers;

import entityBeans.Category;
import entityBeans.Product;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.CatalogTreatmentLocal;

public class OverviewCtrl implements ControllerInterface, Serializable {
    CatalogTreatmentLocal catalogTreatment = lookupCatalogTreatmentLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("product-id");
        String categoryId = request.getParameter("category-id");
        if (productId != null) {
            Product prod = catalogTreatment.getProductById(productId);
            request.setAttribute("product", prod);
            request.setAttribute("properties", catalogTreatment.getPropertiesByProduct(prod));
            return "product-overview";
        } else if (categoryId != null) {
            Category cat = catalogTreatment.getCategoryById(categoryId);
            request.setAttribute("category", cat);
            request.setAttribute("products", catalogTreatment.getProductsByCategory(cat));
            return "category-overview";
        } else {
            request.setAttribute("categories", catalogTreatment.getAllCategories());
            return "menu-overview";
        }
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
