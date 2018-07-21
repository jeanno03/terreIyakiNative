
package frontController;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import subControllers.ControllerInterface;

public class FrontController extends HttpServlet {
    
    private HashMap<String, ControllerInterface> mp;

    @Override // creation de la hashmap à partir du contenu de web.xml
    public void init(ServletConfig config) throws ServletException {
        super.init(config); 
        //To change body of generated methods, choose Tools | Templates.
        mp = new HashMap(); 
        Enumeration<String> noms = config.getInitParameterNames();
        while(noms.hasMoreElements()){
            String nom = noms.nextElement();
            String valeur = config.getInitParameter(nom);
            try {
                mp.put(nom, (ControllerInterface) Class.forName(valeur).newInstance());            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        String section = request.getParameter("section");
        
        String prefixe = "/WEB-INF/";
        String suffixe = ".jsp";
        String page = "temp-home";
        
        if (mp.containsKey(section)) {
            ControllerInterface ci = mp.get(section);
            page = ci.execute(request, response);
        }
        
        Boolean redirection = (Boolean) request.getAttribute("redirect");
        if(redirection == null || !redirection){
            page = response.encodeURL(prefixe + page + suffixe);
            getServletContext().getRequestDispatcher(page).include(request, response);
        }else{
            page = response.encodeURL(page);
            response.sendRedirect(page);
        }
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
