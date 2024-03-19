package ServletManager.FunctionalServlet;

import DAO.Cart.ShoppingCartItemDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.User.*;
import DTO.Cart.CartItemList;
import DTO.User.*;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import util.ConstVar.PageLink;

public class LogInServlet extends HttpServlet {
    SiteUserDAO uDao = SiteUserDAO.getInstance();
    ShoppingCartItemDAO scItemDao = ShoppingCartItemDAO.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            SiteUser user = uDao.get(new Object[]{username, password});
            if (user != null) {
                HttpSession session = request.getSession();

                session.setAttribute("user", user);
                CartItemList cart = (CartItemList)session.getAttribute("cart");
                if (cart == null) cart = scItemDao.convertToCartItemList(new Object[]{user.getId()});

                if (cart == null) cart = new CartItemList();
                cart.setUserID(user.getId());
                session.setAttribute("cart", cart);
                
                json.put("state", "success");
            } else {
                json.put("state", "login");
                json.put("message", "Invalid username or password, please try again!");
            }
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
