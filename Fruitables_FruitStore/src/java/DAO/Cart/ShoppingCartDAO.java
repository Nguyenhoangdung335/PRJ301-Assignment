package DAO.Cart;

import DTO.Cart.ShoppingCart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;
import util.ConstVar.TableName;
import util.ConstVar.TablePara;

public class ShoppingCartDAO implements DAO<ShoppingCart>{
    private static ShoppingCartDAO instance;
    
    public static ShoppingCartDAO getInstance () {
        if (instance == null) instance = new ShoppingCartDAO();
        return instance;
    }

    public ShoppingCartDAO() {
    }
    
    @Override
    public ShoppingCart get(Object[] cond) {
        String userId = String.valueOf(cond[0]);
        if (userId == null) return null;
        
        String[] cartPara = TablePara.CART;
        String command = String.format("SELECT * FROM %s WHERE %s = %s", TableName.CART, cartPara[1], Integer.parseInt(userId));
        ShoppingCart cart = null;
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareCall(command); ResultSet rs = stm.executeQuery()) {
            if (rs.next()) 
                cart = new ShoppingCart(rs.getInt(cartPara[0]), rs.getInt(cartPara[1]));
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    @Override
    public List<ShoppingCart> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(ShoppingCart t) {
        if (t == null || t.getId() < 0 || t.getUserID() < 0) return false;
        
        String[] cartPara = TablePara.CART;
        String command = String.format(
                "IF NOT EXISTS (SELECT * FROM %s WHERE %s = %s) " + 
                "INSERT INTO %s (%s) VALUES (%s);", 
                TableName.CART, cartPara[1], t.getUserID(),
                TableName.CART, cartPara[1], t.getUserID()
        );
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareCall(command);) {
            int rowAffected = stm.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public int save(int userId) throws SQLException {
        if (userId < 0) return -1;
        
        String[] cartPara = TablePara.CART;
        String insertCommand = String.format(
                "IF NOT EXISTS (SELECT * FROM %s WHERE %s = %s) " + 
                "INSERT INTO %s (%s) VALUES (%s);", 
                TableName.CART, cartPara[1], userId,
                TableName.CART, cartPara[1], userId
        );
        String selectCommand = String.format(
                "SELECT * FROM %s WHERE %s = %s", 
                TableName.CART, TablePara.CART[0], userId
        );
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(insertCommand); PreparedStatement select = con.prepareStatement(selectCommand);) {
            int rowAffected = stm.executeUpdate();
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                int cartID = rs.getInt(TablePara.CART[0]);
                rs.close();
                return cartID;
            } else 
                throw new SQLException();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShoppingCartDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException();
        }
    }

    @Override
    public boolean update(ShoppingCart t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ShoppingCart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
