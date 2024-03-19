package ServletManager.FunctionalServlet;

import DAO.Product.ProductDetailDAO;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class ProductCreateServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        request.setCharacterEncoding("UTF-8");
        String url = "";
        
        try {
            int category = Integer.parseInt(request.getParameter("slCategory"));
            String name = URLDecoder.decode(request.getParameter("txtName"), "UTF-8");
            String description = URLDecoder.decode(request.getParameter("txtDescription"), "UTF-8");
            System.out.println(description);
            String imageLink = URLDecoder.decode(request.getParameter("txtImageLink"), "UTF-8");
            double inStock = Double.parseDouble(request.getParameter("txtStock"));
            double price = Double.parseDouble(request.getParameter("txtPrice"));
            int productType = Integer.parseInt(request.getParameter("slType"));
            Object[] params = {null, category, name, description, imageLink, inStock, price, productType};
            
            ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
            if(pdDao.create(params)) {
                json.put("message", "Product create successfully");
            } else
                json.put("message", "Product create unsuccessfully, please wait while the staffs are fixing");
        } catch (NumberFormatException ex ) {
            json.put("message", "There was an error while parsing input, please try again!");
        } catch (Exception e) {
            json.put("message", "There was an error during the creation process, please wait while the staffs are fixing!");
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
