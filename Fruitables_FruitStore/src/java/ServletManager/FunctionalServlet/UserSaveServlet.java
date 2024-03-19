package ServletManager.FunctionalServlet;

import DAO.User.SiteUserDAO;
import DTO.User.SiteUser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class UserSaveServlet extends HttpServlet {
    SiteUserDAO suDao = SiteUserDAO.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String updateType = String.valueOf(request.getAttribute("update"));
        JSONObject json = new JSONObject();
        SiteUser user = (SiteUser)request.getSession(false).getAttribute("user");
        
        try {
            if (updateType.equalsIgnoreCase("user")) {
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPassword");
                String email = request.getParameter("txtEmail");
                String phoneNumber = request.getParameter("txtPhoneNumber");
                System.out.println(username);
                System.out.println(password);
                System.out.println(email);
                System.out.println(phoneNumber);
                
                if (suDao.update(user, new String[]{username, password, email, phoneNumber})) {
                    request.getSession().setAttribute("user", user);
                    json.put("message", "Successfully update user info");
                } else throw new Exception();
                
            } else if (updateType.equalsIgnoreCase("address")) {
                
                String address = request.getParameter("txtNewAddr");
                if (suDao.addAddress(address, user)) {
                    request.getSession().setAttribute("user", user);
                    json.put("message", "Successfully added address");
                } else throw new Exception();
                
            } else throw new Exception();
        } catch (Exception ex) {
            Logger.getLogger(UserSaveServlet.class.getName()).log(Level.SEVERE, null, ex);
            json.put("message", "There was an error occured while update info, please wait while the staffs are fixing!");
        } finally {
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    // </editor-fold>
}
