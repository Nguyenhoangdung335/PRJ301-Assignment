package DAO.Order;

import DTO.Order.OrderLine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;

public class OrderLineDAO implements DAO<OrderLine>{
    private static OrderLineDAO instance;
    
    public static OrderLineDAO getInstance () throws SQLException, NamingException {
        if (instance == null) instance = new OrderLineDAO();
        return instance;
    }
    
    
    public OrderLineDAO() throws SQLException, NamingException {
    }
    
    @Override
    public OrderLine get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderLine> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(OrderLine t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean save(Object[] para) {
        if (para[1] instanceof String[] && para[2] instanceof String[]) {
            String orderIDStr = String.valueOf(para[0]);
            String[] productConfigIDs = (String[])para[1];
            String[] quantity = (String[])para[2];
            String[] subTotals = (String[])para[3];
            if (productConfigIDs.length != subTotals.length) return false;
            
            String command = "INSERT INTO order_line (product_config_id, order_id, quantity, calculated_price) "
                    + "VALUES (?, ?, ?, ?);";
            try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command)) {
                int orderID = Integer.parseInt(orderIDStr);
                for (int i = 0; i < productConfigIDs.length; i++) {
                    stm.setInt(1, Integer.parseInt(productConfigIDs[i]));
                    stm.setInt(2, orderID);
                    stm.setDouble(3, Double.parseDouble(quantity[i]));
                    stm.setDouble(4, Double.parseDouble(subTotals[i]));
                    stm.executeUpdate();
                }
                return true;
            } catch (NumberFormatException | SQLException | NamingException ex) {   
                Logger.getLogger(OrderLineDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } 
        }
        return false;
    }

    @Override
    public boolean update(OrderLine t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(OrderLine t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

