package ServletManager.FunctionalServlet;

import DAO.Cart.ShoppingCartItemDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.User.*;
import DTO.Cart.CartItemList;
import DTO.Cart.ShoppingCartItems;
import DTO.User.*;
import javax.servlet.http.HttpSession;
import util.ConstVar.PageLink;

public class LogInServlet extends HttpServlet {
    SiteUserDAO uDao = SiteUserDAO.getInstance();
    ShoppingCartItemDAO scItemDao = ShoppingCartItemDAO.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        SiteUser user = uDao.get(new Object[]{username, password});
        if (user != null) {
            System.out.println(user.toString());
            HttpSession session = request.getSession();
            
            session.setAttribute("user", user);
            CartItemList cart = (CartItemList)session.getAttribute("cart");
            if (cart == null) cart = scItemDao.convertToCartItemList(new Object[]{user.getId()});
            
            if (cart == null) cart = new CartItemList();
            cart.setUserID(user.getId());
            session.setAttribute("cart", cart);
            
            
            request.getRequestDispatcher(PageLink.DISPATCH_SERVLET + "?btAction=home").forward(request, response);
        } else {
            String state = String.format("loginfailed");
            request.setAttribute("state", state);
            request.getRequestDispatcher(PageLink.DISPATCH_SERVLET + "?btAction=registration").forward(request, response);
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
