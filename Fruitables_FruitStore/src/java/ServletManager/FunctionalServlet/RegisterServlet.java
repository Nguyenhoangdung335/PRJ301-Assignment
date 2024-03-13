package ServletManager.FunctionalServlet;

import DAO.User.SiteUserDAO;
import DTO.User.SiteUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar;

public class RegisterServlet extends HttpServlet {
    SiteUserDAO dao = SiteUserDAO.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String email = request.getParameter("txtEmail");
        String phoneNumber = request.getParameter("txtPhoneNumber");
        
        SiteUser user = new SiteUser(username, password, phoneNumber, email);
        if (dao.save(user)) {
            String state = String.format("registerSuccessful");
            request.setAttribute("state", state);
            request.getRequestDispatcher(ConstVar.PageLink.DISPATCH_SERVLET + "?btAction=registration").forward(request, response);
        } else {
            request.getRequestDispatcher(ConstVar.PageLink.DISPATCH_SERVLET + "?btAction=registration").forward(request, response);
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
