package ServletManager.PageGetServlet;

import DAO.Product.ProductCategoryDAO;
import DAO.Product.ProductDetailDAO;
import DTO.Product.ProductDetail;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar;
import util.Pair;

public class SearchServlet extends HttpServlet {
    ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
    ProductCategoryDAO pcDao = ProductCategoryDAO.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchValue = request.getParameter("txtSearchValue");
        String type = request.getParameter("type");
        String pageNumStr = request.getParameter("page");
        int pageIndex = (pageNumStr == null)? 0: Integer.parseInt(pageNumStr) - 1;
        Pair<List<ProductDetail>, Integer> listPair;
        List<ProductDetail> pdList;
        
        System.out.println(type + " " + searchValue + " " + pageIndex);
        
        if (type != null) {
            type = type.toLowerCase();
            switch (type) {
                case "category": 
                    System.out.println("Type: category");
                    listPair = pdDao.searchByCategory(searchValue, pageIndex, 9);
                    break;
                case "all":
                    System.out.println("Type: all");
                    listPair = pdDao.getAll(pageIndex, 9);
                    break;
                case "name":
                    System.out.println("Type: name");
                    listPair = pdDao.searchByName(searchValue, pageIndex, 9);
                    break;
                default:
                    System.out.println("Type: default");
                    listPair = pdDao.getAll(pageIndex, 9);
                    break;
            }
        } else
            listPair = pdDao.searchByName(searchValue, pageIndex, 9);
        
        pdList = listPair.getFirst();
        for (ProductDetail pro: pdList) {
            System.out.println(pro);
        }
        System.out.println("Testing");
        if (pdList.size() > 0) {
            request.setAttribute("searchedList", pdList);
        }
        request.setAttribute("productCategoryList", pcDao.getAll());
        request.setAttribute("bestSellerList", pdDao.getBestSeller());
        request.setAttribute("txtSearchValue", searchValue);
        request.setAttribute("listLength", listPair.getSecond());
        request.getRequestDispatcher(ConstVar.PageLink.SEARCHED).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
