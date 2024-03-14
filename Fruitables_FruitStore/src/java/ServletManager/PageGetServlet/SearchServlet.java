package ServletManager.PageGetServlet;

import DAO.Product.ProductCategoryDAO;
import DAO.Product.ProductDetailDAO;
import DTO.Product.ProductCategory;
import DTO.Product.ProductDetail;
import java.io.IOException;
import java.util.List;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar;

public class SearchServlet extends HttpServlet {
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    ProductCategoryDAO pcDao = ProductCategoryDAO.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchValue = request.getParameter("txtSearchValue");
        String type = request.getParameter("type");
        List<ProductDetail> pdList = null;
        
        if (type != null) {
            type = type.toLowerCase();
            switch (type) {
                case "category": 
                    System.out.println("Type: category");
                    pdList = pdDao.searchByCategory(new Object[]{searchValue});
                    break;
                case "all":
                    System.out.println("Type: all");
                    pdList = pdDao.getAll();
                    break;
                case "name":
                    System.out.println("Type: name");
                    pdList = pdDao.searchByName(new Object[]{searchValue});
                    break;
            }
        } else 
            pdList = pdDao.searchByName(new Object[]{searchValue});
        
        if (pdList != null) {
            request.setAttribute("searchedList", pdList);
        }
        request.setAttribute("productCategoryList", pcDao.getAll());
        request.setAttribute("bestSellerList", pdDao.getBestSeller());
        request.setAttribute("txtSearchValue", searchValue);
        
        request.getRequestDispatcher(ConstVar.PageLink.SEARCHED).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
