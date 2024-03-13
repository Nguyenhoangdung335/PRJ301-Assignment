package ServletManager.FunctionalServlet;

import DAO.Product.ProductDetailDAO;
import DTO.Cart.CartItemList;
import DTO.Product.ProductDetail;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

public class CartAddServlet extends HttpServlet {
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    
    private void processRequest (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Cart add Servlet called");
        HttpSession currSession = request.getSession(true);
        CartItemList cart = (CartItemList)currSession.getAttribute("cart");
        if (cart == null) cart = new CartItemList();
    
        int productID = Integer.parseInt(request.getParameter("productID"));
        ProductDetail product = pdDao.get(new Object[]{productID});
        String quantityStr = request.getParameter("txtQuantity");

        if (quantityStr != null)
            cart.addToCart(product, Double.parseDouble(quantityStr));
        else 
            cart.addToCart(product);

        currSession.setAttribute("cart", cart);
        
        try {
            JSONObject json = new JSONObject();
            json.put("cartItemNum", cart.getTotalQuantity());
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (IOException | JSONException | NumberFormatException ex) {
            System.out.println("Testing exception");
            Logger.getLogger(CartAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //<editor-fold desc="HTTP REQUEST METHODS" defaultstate="collapsed">
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
    //</editor-fold>
}
