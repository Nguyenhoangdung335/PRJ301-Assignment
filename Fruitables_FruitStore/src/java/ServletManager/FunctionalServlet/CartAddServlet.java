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
import org.json.JSONObject;
import util.CustomException.QuantityOutOfStockException;

public class CartAddServlet extends HttpServlet {
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    
    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        JSONObject json = new JSONObject();
        HttpSession currSession = request.getSession(true);
        CartItemList cart = (CartItemList)currSession.getAttribute("cart");
        if (cart == null) cart = new CartItemList();
        
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String quantityStr = request.getParameter("txtQuantity");
            double addedQuantity = (quantityStr == null)? 1: Double.parseDouble(quantityStr);
            Entry<ProductDetail, Double> productEntry = cart.getProductEntry(productID);
            ProductDetail product;
            
            if (productEntry != null) {
                product = productEntry.getKey();
                double productQuantity = productEntry.getValue();
                if (productQuantity + addedQuantity >= product.getInStock())
                    throw new QuantityOutOfStockException();
            } else {
                product = pdDao.get(new Object[]{productID});
            }
            cart.addToCart(product, addedQuantity);

            currSession.setAttribute("cart", cart);
            
            json.put("cartItemNum", cart.getTotalQuantity());
            json.put("message", String.format("Successfully added %s%s of %s to cart!", addedQuantity, product.getProductType(), product.getName()));
            
        } catch (QuantityOutOfStockException ex) {
            
            json.put("message", "The amount added is larger than the quantity in stock!");
            
        } catch (Exception ex) {
            
            json.put("message", "There is an error while adding product to cart, please try again later!");
            Logger.getLogger(CartAddServlet.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
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
