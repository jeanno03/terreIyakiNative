/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subControllers;

import entityBeans.Combo;
import entityBeans.ComboCategory;
import entityBeans.Product;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.OrderTreatmentLocal;
import tools.CustomException;

/**
 *
 * @author jeanno
 */
@Stateless
public class versCarteJeannoryCtrl implements ControllerInterface, Serializable {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderTreatmentLocal gestionCommande = lookupOrderTreatmentLocal();
//***************************************************************************** 
// ****************************Product*****************************************        
        
        
        if (request.getParameter("action").equals("produit")) {

            try {
                List<Product> p01 = (List<Product>) gestionCommande.getProduct();
                request.setAttribute("product", p01);

            } catch (CustomException ex) {
                String texte = ex.getMessage();
                request.setAttribute("message", texte);
            }
        }
        
 //*****************************************************************************       
 //*****************************************************************************       
           
        
        
        
        
        
        
        
        try {
            
            

            if (request.getParameter("action").equals("formule")) {
                     try {
                        List<Combo> c01 = (List<Combo>) gestionCommande.getCombo();
                        request.setAttribute("combo", c01); 
                                            } catch (CustomException ex) {
                        String texte = ex.getMessage();
                        request.setAttribute("message", texte);
                    }
                
 //****************************************************************************
               try{
                if (request.getParameter("detection").equals("itemFormul")) {

//                    try {
//                        List<Combo> c01 = (List<Combo>) gestionCommande.getCombo();
//                        request.setAttribute("combo", c01);
                        String nomMenu = request.getParameter("nameCombo");
request.setAttribute("nameCombo", nomMenu);
 
                        try {
                            List<ComboCategory> c02 = (List<ComboCategory>) gestionCommande.getComboCat(nomMenu);
                            request.setAttribute("comboCategory", c02);

                        } catch (CustomException ex) {
                            String texte = ex.getMessage();
                            request.setAttribute("message", texte);
                        }
 //****************************************************************************                
                try {
                    if (request.getParameter("faction").equals("produitFormul")) {
                        String nomCategorie = request.getParameter("comboCategory");
                        try {
                            List<Product> po01 = (List<Product>) gestionCommande.getComboProduct(nomCategorie, nomMenu);
                            request.setAttribute("comboProduct", po01);
                            //        System.out.println("********************produits du menu"+po01.toString());
                        } catch (CustomException ex) {
                            String texte = ex.getMessage();
                            request.setAttribute("message", texte);
                        }

                    }
                
                
                
                
                } catch (NullPointerException ex01) {
                    //nada
                }
 //****************************************************************************
                        
                        
                        
                        
                        
                        
                        
                }
                 } catch (NullPointerException ex01) {
                    //nada
                }               
 //****************************************************************************
                
                

            }
        } catch (NullPointerException ex01) {
            //nada
        }
       return "carteJeanno";

    }

    private OrderTreatmentLocal lookupOrderTreatmentLocal() {
        try {
            Context c = new InitialContext();
            return (OrderTreatmentLocal) c.lookup("java:global/terreIyaki/terreIyaki-ejb/OrderTreatment!sessionBeans.OrderTreatmentLocal");

        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);

        }

    }

}
