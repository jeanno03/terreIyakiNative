package subControllers;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.TableTreatmentLocal;
import entityBeans.MyTable;
import javax.servlet.http.HttpSession;

/**
 *
 * @author samira
 */
public class VersMyTablesCtrl implements ControllerInterface, Serializable {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TableTreatmentLocal gestionTable = lookupTableTreatmentLocal();

       // test if sur création d'une nouvelle commande 
        // ou creation d'un nouveau controleur verscontroleurs 
        // submit vers le nouveau controleur 

            try {
                List<MyTable> mt = gestionTable.selectAll();
                request.setAttribute("myTable", mt);
              

            } catch (NullPointerException ex) {
                String texte = ex.getMessage();
                request.setAttribute("message", texte);
            }

        
        return  "myTable";

    }


    private TableTreatmentLocal lookupTableTreatmentLocal() {
        try {
            Context c = new InitialContext();
            return (TableTreatmentLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/TableTreatment!sessionBeans.TableTreatmentLocal");

        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);

        }

    }
}