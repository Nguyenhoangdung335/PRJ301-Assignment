package ServletManager.FunctionalServlet;

import DAO.Product.ProductCategoryDAO;
import DAO.Product.ProductTypeDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetProductTypeServlet extends HttpServlet {
    private final ProductTypeDAO ptDao = ProductTypeDAO.getInstance();
    private final ProductCategoryDAO pcDao = ProductCategoryDAO.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONArray ptListJson = new JSONArray(ptDao.getAll());
        JSONArray pcListJson = new JSONArray(pcDao.getAll());
        JSONObject json = new JSONObject();
        json.put("typeList", ptListJson);
        json.put("categoryList", pcListJson);
        
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
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
