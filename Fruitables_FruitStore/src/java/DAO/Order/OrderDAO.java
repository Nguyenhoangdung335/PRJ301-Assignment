package DAO.Order;

import DTO.Order.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;

public class OrderDAO implements DAO<Order>{
    private static OrderDAO instance;
    
    public static OrderDAO getInstance () throws NamingException, SQLException{
        if (instance == null) instance = new OrderDAO();
        return instance;
    }
    
    public OrderDAO() throws SQLException, NamingException {
    }
    
    @Override
    public Order get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int save (Object[] para) {
        String userIDStr = String.valueOf(para[0]);
        String paymentIDStr = String.valueOf(para[1]);
        String shippingAddr = String.valueOf(para[2]);
        String shippingTypeIDStr = String.valueOf(para[3]);
        String finalPriceStr = String.valueOf(para[4]);
        if (userIDStr == null || paymentIDStr == null || shippingAddr == null || shippingTypeIDStr == null || finalPriceStr == null) 
            return -1;
        
        try {
            int userID = Integer.parseInt(userIDStr);
            int paymentID = Integer.parseInt(paymentIDStr);
            int shippingTypeID = Integer.parseInt(shippingTypeIDStr);
            double finalPrice = Double.parseDouble(finalPriceStr);
        
            String command = String.format(
                    "INSERT INTO [order] (user_id, date, payment_id, address, shipping_type_id, total_price, status_id)\n" + 
                    "VALUES (%d, GETDATE(), %d, N'%s', %d, %.2f, 0)", 
                    userID, paymentID, shippingAddr, shippingTypeID, finalPrice
            );
            String select = String.format(
                    "SELECT * \nFROM [order]\n" +
                    "WHERE user_id = %d \nORDER BY date DESC;",
                    userID
            );
            
            ResultSet selectResult;
            
            try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command); PreparedStatement selectQuery = con.prepareStatement(select);) {
                int row = stm.executeUpdate();
                selectResult = selectQuery.executeQuery();
                int id = -1;
                if (selectResult.next()) id = selectResult.getInt("id");
                selectResult.close();
                return id;
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } catch (NumberFormatException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public boolean update(Order t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
