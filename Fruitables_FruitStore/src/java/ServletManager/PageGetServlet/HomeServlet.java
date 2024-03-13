package ServletManager.PageGetServlet;

import DAO.Product.*;
import DTO.Product.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar;


public class HomeServlet extends HttpServlet {
    ProductDetailDAO pdDAO = ProductDetailDAO.getInstance();
    ProductCategoryDAO pcDAO = ProductCategoryDAO.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductDetail> pdList = pdDAO.getAll();
        List<ProductCategory> pcList = pcDAO.getAll();
        List<ProductDetail> bestSellerList = pdDAO.getBestSeller();
        
        request.setAttribute("productDetailList", pdList);
        request.setAttribute("productCategoryList", pcList);
        request.setAttribute("bestSellerList", bestSellerList);
        request.getRequestDispatcher(ConstVar.PageLink.HOME).forward(request, response);
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
