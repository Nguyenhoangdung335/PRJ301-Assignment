package ServletManager.FunctionalServlet;

import DAO.Product.ProductDetailDAO;
import DTO.Cart.CartItemList;
import DTO.Product.ProductDetail;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import util.ConstVar;

public class CartDeleteServlet extends HttpServlet {
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        JSONObject json = new JSONObject();
        String url = ConstVar.PageLink.DISPATCH_SERVLET;
        CartItemList cart = null;
        int product_item_id;
        try {
            product_item_id = Integer.parseInt(request.getParameter("productID"));
            HttpSession sessionCart = request.getSession();
            cart = (CartItemList)sessionCart.getAttribute("cart");
            
            ProductDetail product = pdDao.get(new Object[]{product_item_id});
            System.out.println(product);
            
            cart.removeProduct(product);
            url = ConstVar.PageLink.DISPATCH_SERVLET + "?btAction=cart";
            json.put("cartItemNum", cart.getTotalQuantity());
        } catch (Exception ex) {
            Logger.getLogger(CartDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods.">
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
