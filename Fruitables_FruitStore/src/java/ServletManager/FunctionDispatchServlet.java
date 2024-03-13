package ServletManager;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstVar.PageLink;
import java.net.URL;


public class FunctionDispatchServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("btAction").toLowerCase();
        System.out.println("FunctionDispatchServlet: " + action);
        RequestDispatcher rd;
        
        switch (action) {
            case "addtocart":
                System.out.println("Action: " + PageLink.CART_ADD_SERVLET);
                rd = request.getRequestDispatcher(PageLink.CART_ADD_SERVLET);
                break;
            case "login":
                System.out.println("Action: " + PageLink.LOGIN_SERVLET);
                rd = request.getRequestDispatcher(PageLink.LOGIN_SERVLET);
                break;
            case "logout":
                System.out.println("Action: " + PageLink.LOGOUT_SERVLET);
                rd = request.getRequestDispatcher(PageLink.LOGOUT_SERVLET);
                break;
            case "register":
                System.out.println("Action: " + PageLink.REGISTER_SERVLET);
                rd = request.getRequestDispatcher(PageLink.REGISTER_SERVLET);
                break;
            case "deletecart":
                System.out.println("Action: " + PageLink.CART_DELETE_SERVLET);
                rd = request.getRequestDispatcher(PageLink.CART_DELETE_SERVLET);
                break;
            case "savecart":
                System.out.println("Action: " + PageLink.CART_SAVE_SERVELT);
                rd = request.getRequestDispatcher(PageLink.CART_SAVE_SERVELT);
                break;
            case "makeorder":
                System.out.println("Action: " + PageLink.ORDER_MAKE_SERVLET);
                rd = request.getRequestDispatcher(PageLink.ORDER_MAKE_SERVLET);
                System.out.println("Testing!" +  PageLink.ORDER_MAKE_SERVLET);
                break;
            default:
                String[] path = new URL(request.getHeader("Referer")).getPath().split("/");
                System.out.println("Cannot found action, return back to " + path[path.length - 1]);
                rd = request.getRequestDispatcher(PageLink.DISPATCH_SERVLET + "?btAction=" + path[path.length - 1]);
        }
        
        rd.forward(request, response);
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
