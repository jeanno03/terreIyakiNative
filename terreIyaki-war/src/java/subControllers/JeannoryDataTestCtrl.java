/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subControllers;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.JeannoryDataTestLocal;

/**
 *
 * @author jeanno
 */
public class JeannoryDataTestCtrl implements ControllerInterface, Serializable {
  
    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        JeannoryDataTestLocal monTest = lookupJeannoryDataTestLocal();
        monTest.dataTest();
        request.setAttribute("message", "Le jeu d'essai a bien été généré !");
        return "temp-home";
        
        
    }
    
    private JeannoryDataTestLocal lookupJeannoryDataTestLocal(){
        try{
            Context c = new InitialContext();
            return (JeannoryDataTestLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/JeannoryDataTest!sessionBeans.JeannoryDataTestLocal");
            
        }catch (NamingException ne){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
            
        }
        
    }
    
    
}
