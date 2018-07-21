/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subControllers;

import java.io.Serializable;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeanno
 */
public class VersLoginCtrl implements ControllerInterface, Serializable {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    
    
    
                    try {
            if (request.getParameter("action").equals("logout")) {
                session.removeAttribute("user");
                session.removeAttribute("myGrants");
            }
        } catch (NullPointerException ne09) {
            //on fait rien
        }
    
    
    
    
    
    

      
      return "login";
      
  } 
    
}
