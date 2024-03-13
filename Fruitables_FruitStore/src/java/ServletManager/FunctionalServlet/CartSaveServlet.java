package ServletManager.FunctionalServlet;

import DAO.Cart.ShoppingCartDAO;
import DAO.Cart.ShoppingCartItemDAO;
import DAO.Product.ProductDetailDAO;
import DTO.Cart.CartItemList;
import DTO.Cart.ShoppingCartItems;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@MultipartConfig
public class CartSaveServlet extends HttpServlet {
    ShoppingCartDAO scDao = ShoppingCartDAO.getInstance();
    ShoppingCartItemDAO scItemDao = ShoppingCartItemDAO.getInstance();
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        CartItemList cart = (CartItemList)session.getAttribute("cart");
        String[] productId = request.getParameterValues("productID");
        String[] quantity = request.getParameterValues("txtQuantity");
        String[] configId = request.getParameterValues("configID");
        
        try {
            ShoppingCartItems cartItems = scItemDao.convertToDTO(cart, configId, productId, quantity);
            
            cartItems.setCartID(scDao.save(cart.getUserID()));
            if (!scItemDao.save(cartItems))
                throw new Exception();
            reEnterItem(cart, productId, quantity);
            
            json.put("message", "Save cart successfully");
        } catch (Exception ex) {
            json.put("message", "Save cart failed, please check again!");
        } finally {
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        }
    }
    
    private void reEnterItem (CartItemList cart, String[] productId, String[] quantity) {
        cart.clear();
        for (int i = 0; i < productId.length; i++) {
            cart.addToCart(pdDao.get(new Object[]{productId[i]}), Double.parseDouble(quantity[i]));
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
