/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subControllers;

import java.beans.*;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeanno
 */
public class PanierCtrl implements Serializable, ControllerInterface {
 
    
    
    
        public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
         return "panier";
        }
    
}
