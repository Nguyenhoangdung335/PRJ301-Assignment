package ServletManager.PageGetServlet;

import DAO.Product.ProductDetailDAO;
import DTO.Product.ProductDetail;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar.PageLink;
import util.Pair;

public class UserServlet extends HttpServlet {
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pair<List<ProductDetail>, Integer> pair = pdDao.getAll(0, 5);
        List<ProductDetail> pdList = pair.getFirst();
        
        request.setAttribute("productList", pdList);
        request.getRequestDispatcher(PageLink.USER_INFO).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
