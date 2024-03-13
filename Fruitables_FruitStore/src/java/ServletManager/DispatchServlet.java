package ServletManager;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar;
import util.ConstVar.PageLink;

public class DispatchServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btAction = request.getParameter("btAction");
        if (btAction != null) btAction = btAction.toLowerCase();
        System.out.println(btAction);
        RequestDispatcher rd;
        String url;
        
        switch (btAction) {
            case "home": 
                System.out.println("btAction: " + ConstVar.PageLink.HOME_SERVLET);
                url = ConstVar.PageLink.HOME_SERVLET;
                break;
            case "registration": 
                System.out.println("btAction: " + ConstVar.PageLink.REGISTRATION_SERVLET);
                url = ConstVar.PageLink.REGISTRATION_SERVLET;
                Object stateObj = request.getAttribute("state");
                if (stateObj != null) {
                    String state = String.valueOf(stateObj);
                    System.out.println(state);
                    if (state.equals("loginfailed"))
                        url += "?state=login&message=The username or password is incorrect, please try again!";
                    else if (state.equalsIgnoreCase("registerSuccessful"))
                        url += "?state=login&message=Register successfull, please log in your account";
                    else if (state.equals("login") || state.equals("register")) 
                        url += "?state=" + state;
                    
                }
                stateObj = request.getParameter("state");
                if (stateObj != null ) {
                    String state = String.valueOf(stateObj);    
                    System.out.println(state);
                    if (state.equals("login") || state.equals("register")) 
                        url += "?state=" + state;
                }
                break;
            case "search": 
                String txtSearchValue = request.getParameter("txtSearchValue");
                String type = request.getParameter("type");
                System.out.println("btAction: " + PageLink.SEARCH_SERVLET + " - " + txtSearchValue);
                
                url = String.format("%s?type=%s&txtSearchValue=%s", PageLink.SEARCH_SERVLET, type, txtSearchValue);
                System.out.println(url);
                break;
            case "view":
                String productID = request.getParameter("productID");
                url = String.format("%s?productID=%s", PageLink.PRODUCT_DETAIL_SERVLET, productID);
                break;
            case "cart": 
                url = PageLink.CART_GET_SERVLET;
                break;
            default: 
                System.out.println("btAction: null -> HOME_SERVLET");
                url = ConstVar.PageLink.HOME_SERVLET;
        }
        response.sendRedirect(url);
    }
        
    //<editor-fold desc="HTTPS METHODS" defaultstate="collapsed">
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
    //</editor-fold>
}
