package util;

import java.time.format.DateTimeFormatter;

public class ConstVar {
    public static final DateTimeFormatter TIMEPATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter DATEPATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter YEARPATTERN = DateTimeFormatter.ofPattern("yyyy");
    
    public static class PageLink {
        final public static String HOME = "home.jsp";
        final public static String REGISTRATION = "Registration - Login.jsp";
        final public static String SEARCHED = "shop.jsp";
        final public static String PRODUCT_DETAIL = "product_detail.jsp";
        final public static String CART = "cart.jsp";
        
        final public static String DISPATCH_SERVLET = "DispatchServlet";
        final public static String FUNCTION_DISPATCH_SERVLET = "FunctionDispatchServlet";
        
        final public static String HOME_SERVLET = "Home";
        final public static String REGISTRATION_SERVLET = "Registration";
        final public static String SEARCH_SERVLET = "Search";
        final public static String CART_GET_SERVLET = "YourCart";
        final public static String PRODUCT_DETAIL_SERVLET = "ProductDetail";
        
        final public static String CART_ADD_SERVLET = "CartAddServlet";
        final public static String CART_DELETE_SERVLET = "CartDeleteServlet";
        final public static String LOGIN_SERVLET = "LogInServlet";
        final public static String LOGOUT_SERVLET = "LogoutServlet";
        final public static String REGISTER_SERVLET = "RegisterServlet";
        final public static String CART_SAVE_SERVELT = "CartSaveServlet";
        final public static String ORDER_MAKE_SERVLET = "MakeOrderServlet";
    }
    
    //<editor-fold desc="TABLES NAME" defaultstate="collapsed">
    public static class TableName {
        //PRODUCT TABLES
        public static final String PRODUCT_CATEGORY = "product_category";
        public static final String PRODUCT_TYPE = "product_type";
        public static final String VARIATION = "variation";
        public static final String PRODUCT = "product";
        public static final String PRODUCT_ITEM = "product_item";
        public static final String PRODUCT_CONFIG = "product_configuration";
        
        //uSER TABLES
        public static final String USER = "site_user";
        public static final String ADDRESS = "user_address";
        public static final String PROVIDER = "provider";
        public static final String PAYMENT = "user_payment";
        
        //SHOPPPING CART TABLES
        public static final String CART = "shopping_cart";
        public static final String CART_ITEM = "shopping_cart_item";
        
        //ORDER TABLES
        public static final String SHIP_TYPE = "shipping_type";
        public static final String STATUS = "status";
        public static final String ORDER = "order";
        public static final String ORDER_LINE = "order_line";
    }
    //</editor-fold>
    
    //<editor-fold desc="TABLES' PARAMETERS" defaultstate="collapsed">
    public static class TablePara {
        //PRODUCT TABLES
        public static final String[] PRODUCT_CATEGORY = {"id", "name"};
        public static final String[] PRODUCT_TYPE = {"id", "name"};
        public static final String[] VARIATION = {"id", "product_type_id", "value", "price_percentage"};
        public static final String[] PRODUCT = {"id", "category_id", "name", "description", "product_image"};
        public static final String[] PRODUCT_ITEM = {"id", "product_id", "qty_in_stock", "price", "product_type_id"};
        public static final String[] PRODUCT_CONFIG = {"id", "product_item_id", "variation_id"};
        
        //uSER TABLES
        public static final String[] USER = {"id", "username", "password", "email", "phone_number"};
        public static final String[] ADDRESS = {"user_id", "address", "is_main_address"};
        public static final String[] PROVIDER = {"id", "name"};
        public static final String[] PAYMENT = {"id", "user_id", "provider_id", "account_number", "expiracy_date"};
        
        //SHOPPPING CART TABLES
        public static final String[] CART = {"id", "user_id"};
        public static final String[] CART_ITEM = {"id", "cart_id", "product_config_id", "quantity"};
        
        //ORDER TABLES
        public static final String[] SHIP_TYPE = {"id", "type", "price"};
        public static final String[] STATUS = {"id", "name"};
        public static final String[] ORDER = {"id", "user_id", "date", "payment_id", "address", "shipping_type_id", "total_price", "status_id"};
        public static final String[] ORDER_LINE = {"id", "product_config_id", "order_id", "quantity", "calculated_price"};
    }
    //</editor-fold>
}
