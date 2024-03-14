package sessionListener;

import DTO.Cart.CartItemList;
import DTO.User.SiteUser;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("-- HttpSessionListener#sessionCreated invoked --");
        HttpSession session = se.getSession();
        System.out.println("session id: " + session.getId());
        session.setMaxInactiveInterval(60*60*1);//in seconds
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        SiteUser user = (SiteUser)session.getAttribute("user");
        if (user != null) {
            CartItemList cart = (CartItemList)session.getAttribute("cart");
        }    
    }
}
