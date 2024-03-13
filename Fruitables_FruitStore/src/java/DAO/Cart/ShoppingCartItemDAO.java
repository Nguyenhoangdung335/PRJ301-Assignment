package DAO.Cart;

import DAO.Product.ProductDetailDAO;
import DTO.Cart.CartItemList;
import DTO.Cart.ShoppingCartItems;
import DTO.Product.ProductDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;
import util.ConstVar.TableName;
import util.ConstVar.TablePara;

public class ShoppingCartItemDAO implements DAO<ShoppingCartItems>{
    private static ShoppingCartItemDAO instance;
    
    public static ShoppingCartItemDAO getInstance () {
        if (instance == null) instance = new ShoppingCartItemDAO();
        return instance;
    }
    
    public ShoppingCartItemDAO() {
    }
    
    @Override
    public ShoppingCartItems get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ShoppingCartItems> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public CartItemList convertToCartItemList (Object[] cond) {
        String userIdStr = String.valueOf(cond[0]);
        if (userIdStr == null) return null;
        
        try {
            String command = String.format(
                    "SELECT pc.id AS config_id, pc.product_item_id, sc.user_id, sci.cart_id, sci.quantity\n" +
                    "FROM shopping_cart AS sc JOIN shopping_cart_item AS sci \n" +
                    "ON sci.cart_id = sc.id JOIN product_configuration as pc ON sci.product_config_id = pc.id\n" +
                    "WHERE pc.id IN (\n" +
                    "	SELECT sci.product_config_id\n" +
                    "	FROM shopping_cart AS sc JOIN shopping_cart_item AS sci \n" +
                    "	ON sc.id = sci.cart_id\n" +
                    "	WHERE sc.user_id = %s\n);",
                    Integer.parseInt(userIdStr)
            );
            ProductDetailDAO pdDao = ProductDetailDAO.getInstance();
            CartItemList cart = null;

            try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    cart = new CartItemList(rs.getInt("user_id"), rs.getInt("cart_id"));
                    do {
                        ProductDetail key = pdDao.get(new Object[] {rs.getInt("product_item_id")});
                        if (key == null) throw new Exception();
                        cart.put(key, rs.getDouble("quantity"));
                    } while (rs.next());
                }
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cart;
        } catch (Exception ex) { 
            Logger.getLogger(ShoppingCartItemDAO.class.getName()).log(Level.SEVERE, null, ex); 
            return null;
        }
    }

    @Override
    public boolean save(ShoppingCartItems t) {
        
        HashMap<Integer, Double> map = t.getProConfigQuantity();
        String[] cartItemPara = TablePara.CART_ITEM;
        String command = String.format(
                "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);", 
                TableName.CART_ITEM, cartItemPara[1], cartItemPara[2], cartItemPara[3]
        );
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command)) {
            delete(t, con);
            for (Entry<Integer, Double> entry: map.entrySet()) {
                stm.setInt(1, t.getCartID());
                stm.setInt(2, entry.getKey());
                stm.setDouble(3, entry.getValue());
                stm.executeUpdate();
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(ShoppingCartItems t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ShoppingCartItems t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean delete (Object[] cond) {
        String userId = String.valueOf(cond[0]);
        if (userId == null) return false;
        
        String[] cartItemPara = TablePara.CART_ITEM;
        String[] cartPara = TablePara.CART;
        String command = String.format(
                "DELETE FROM %s\n" +
                "WHERE %s IN (\n" +
                "    SELECT %s\n" +
                "    FROM %s\n" +
                "    WHERE %s = %s\n" +
                ");",
                TableName.CART_ITEM, cartItemPara[1],
                cartPara[0], TableName.CART, cartPara[1], Integer.parseInt(userId)
        );
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command)) {
            int row = stm.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShoppingCartItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean delete (ShoppingCartItems t, Connection con) {
        HashMap<Integer, Double> map = t.getProConfigQuantity();
        String[] cartItemPara = TablePara.CART_ITEM;
        String command = String.format(
                "DELETE FROM %s WHERE %s = %s",
                TableName.CART_ITEM, cartItemPara[1], t.getCartID()
        );
        
        try (PreparedStatement stm = con.prepareStatement(command)) {
            int row = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public ShoppingCartItems convertToDTO (CartItemList list, String[] configIdList, String[] productId, String[] quantity) {
        ProductDetailDAO pdDAO = ProductDetailDAO.getInstance();
        List<ProductDetail> pdList = pdDAO.getAll();
        HashMap<Integer, Double> proConfigQuantity = new HashMap<>();
        
        if (list != null) {
            for (ProductDetail entry: list.keySet()) {
                ProductDetail product = searchProductById(pdList, entry.getid());
                int i = 0;
                for (; i < productId.length; i++) {
                    if (Integer.parseInt(productId[i]) == product.getid())
                        break;
                }
                if (product != null) 
                    proConfigQuantity.put(Integer.parseInt(configIdList[i]), Double.parseDouble(quantity[i]));
            }
            return new ShoppingCartItems(list.getUserID(), list.getCartID(), proConfigQuantity);
        }
        return new ShoppingCartItems(-1, new Random().nextInt(9999), proConfigQuantity);
    }
    
    private ProductDetail searchProductById (List<ProductDetail> pdList, int productId) {
        for (ProductDetail pro: pdList) {
            if (pro.getid() == productId) 
                return pro;
        }
        return null;
    }
}