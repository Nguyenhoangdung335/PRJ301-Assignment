package ServletManager.FunctionalServlet;

import DAO.Cart.ShoppingCartItemDAO;
import DAO.Order.OrderDAO;
import DAO.Order.OrderLineDAO;
import DAO.Product.ProductDetailDAO;
import DTO.Cart.CartItemList;
import DTO.User.SiteUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
 
public class MakeOrderServlet extends HttpServlet {    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Testing order make servlet");
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        SiteUser user = (SiteUser)session.getAttribute("user");
        CartItemList cart = (CartItemList)session.getAttribute("cart");
        try {
            //DAO declare
            OrderDAO orderDAO = OrderDAO.getInstance();
            OrderLineDAO orderLineDAO = OrderLineDAO.getInstance();
            ShoppingCartItemDAO sciDao = ShoppingCartItemDAO.getInstance();
            ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
            
            //Cart items informations
            int userID = user.getId();
            int paymentTypeID = Integer.parseInt(request.getParameter("slPayment"));
            String address = request.getParameter("slAddress");
            int shippingTypeID = Integer.parseInt(request.getParameter("slShipType"));
            double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));
            String[] proID = request.getParameterValues("productID");
            String[] prodConfigID = request.getParameterValues("configID");
            String[] quantity = request.getParameterValues("txtQuantity");
            String[] subTotal = request.getParameterValues("subTotal");
            
            //Testing information 
            System.out.println(userID + " " + paymentTypeID + " " + address + " " + shippingTypeID + " " + finalPrice);
            for (int i = 0; i < subTotal.length; i++ ){
                System.out.println("\t" + prodConfigID[i] + ": " + subTotal[i]);
            }
            
            //Save to Order, OrderLine database and update cart and productDetail
            int orderID = orderDAO.save(new Object[]{userID, paymentTypeID, address, shippingTypeID, finalPrice});
            orderLineDAO.save(new Object[]{orderID, prodConfigID, quantity, subTotal});
            sciDao.delete(new Object[] {userID});
            cart.clear();
            System.out.println(pdDao.updateNewQuantity(proID, quantity));
            
            json.put("state", "success");
            json.put("message", "Successfully order\nThank for your supports.");
        } catch (NumberFormatException | NamingException | SQLException ex) {
            Logger.getLogger(MakeOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            json.put("state", "fail");
            json.put("message", "Error while making order, please check again!");
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
