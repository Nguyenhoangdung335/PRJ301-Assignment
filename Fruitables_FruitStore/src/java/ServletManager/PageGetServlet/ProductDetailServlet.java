package ServletManager.PageGetServlet;

import DAO.Product.ProductDetailDAO;
import DTO.Product.ProductDetail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar.PageLink;

public class ProductDetailServlet extends HttpServlet {
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productID");
        String type = request.getParameter("type");
        ProductDetail product = null;
        if (type.equals("new")) {}
        else if ((productId == null || productId.equals("null") || productId.trim().isEmpty())) 
            product = pdDao.getLatest();
        else 
            product = pdDao.get(new Object[]{productId});
        
        request.setAttribute("product", product);
        request.getRequestDispatcher(PageLink.PRODUCT_DETAIL+"?productID="+productId).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
