package ServletManager.FunctionalServlet;

import DAO.User.SiteUserDAO;
import DTO.User.SiteUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class RegisterServlet extends HttpServlet {
    SiteUserDAO dao = SiteUserDAO.getInstance();
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String email = request.getParameter("txtEmail");
        String phoneNumber = request.getParameter("txtPhoneNumber");
                 
        try {
            SiteUser user = dao.get(new Object[]{username, password});
            if (user != null) {
                json.put("message", "Account already exist, please try again");
                json.put("state", "register");
                throw new Exception();
            }
            boolean saveSuccess = dao.save(new SiteUser(username, password, phoneNumber, email));
            json.put("message", (saveSuccess)? "Register successfull, please log in your account": "Register unsuccessful, there is error at server side, please wait");
            json.put("state", (saveSuccess)? "login": "register");
        } catch (Exception e) {
        } finally {
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods">
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
