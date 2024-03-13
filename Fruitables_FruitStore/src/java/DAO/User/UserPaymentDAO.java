package DAO.User;

import DTO.User.UserPayment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;
import util.ConstVar.TableName;
import util.ConstVar.TablePara;

public class UserPaymentDAO implements DAO<UserPayment>{
    private static UserPaymentDAO instance;
    
    public static UserPaymentDAO getInstance () {
        if (instance == null) instance = new UserPaymentDAO();
        return instance;
    }
    
    private Connection con;
    private List<UserPayment> cachedList = null;
    private boolean hasChanged = true;

    public UserPaymentDAO() {
        try {
            con = util.makeConnection();
        } catch (NamingException | SQLException ex) {}
    }
    
    @Override
    public UserPayment get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserPayment> getAll() {
        String[] userPayPara = TablePara.PAYMENT;
        String command = String.format("SELECT * FROM %s", TableName.PAYMENT);
        List<UserPayment> list = new LinkedList<>();
        
        try (PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
//            while (rs.next()) 
//                list.add(new UserPayment(
//                        rs.getInt(userPayPara[0]), 
//                        rs.getInt(userPayPara[1]), 
//                        , 
//                        0, 
//                        LocalDate.MAX
//                ));
        } catch (SQLException ex) {
            Logger.getLogger(UserPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(UserPayment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserPayment t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(UserPayment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
