package ServletManager.PageGetServlet;

import DAO.Cart.ShoppingCartDAO;
import DAO.Cart.ShoppingCartItemDAO;
import DAO.Order.ShippingTypeDAO;
import DAO.Product.ProductConfigDAO;
import DAO.User.ProviderDAO;
import DTO.Cart.CartItemList;
import DTO.Order.ShipType;
import DTO.Product.ProductConfig;
import DTO.User.Provider;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ConstVar.PageLink;

public class CartServlet extends HttpServlet {
    ShoppingCartItemDAO scItemDao = ShoppingCartItemDAO.getInstance();
    ShoppingCartDAO scDao = ShoppingCartDAO.getInstance();
    ShippingTypeDAO stDao = ShippingTypeDAO.getInstance();
    ProductConfigDAO pcDao = ProductConfigDAO.getInstance();
    ProviderDAO pDao = ProviderDAO.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartItemList cart = (CartItemList)session.getAttribute("cart");
        if (cart == null) cart = new CartItemList();
        
        List<ShipType> shipTypeList = stDao.getAll();
        List<ProductConfig> pcList = pcDao.getByCartItem(cart);
        List<Provider> pList = pDao.getAll();
        
        request.setAttribute("shipTypeList", shipTypeList);
        request.setAttribute("variationList", pcList);
        request.setAttribute("providerList", pList);
        request.getRequestDispatcher(PageLink.CART).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
