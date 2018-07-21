
package subControllers;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncludeCtrl implements ControllerInterface, Serializable{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String url = ""; // à changer
        String action = request.getParameter("action");
        if ("navbar".equals(action)) {
            url = "/includes/aside/navbar";
        }
        if ("header".equals(action)) {
            url = "/includes/aside/header";
        }
        return url;
    }
    
}
