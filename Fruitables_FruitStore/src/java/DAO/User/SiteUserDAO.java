package DAO.User;

import DTO.User.SiteUser;
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

public class SiteUserDAO implements DAO<SiteUser>{
    private static SiteUserDAO instance;
    
    public static SiteUserDAO getInstance () {
        if (instance == null) instance = new SiteUserDAO();
        return instance;
    }
    
    public SiteUserDAO() {
    }
    
    @Override
    public SiteUser get(Object[] cond) {
        String[] userPara = TablePara.USER;
        String[] addrPara = TablePara.ADDRESS;
        String command = String.format(
                "SELECT su.*, ua.%s, ua.%s\n" +
                "FROM %s AS su LEFT JOIN %s AS ua \n" +
                "ON su.%s = ua.%s \n" + 
                "WHERE su.%s = N'%s' AND su.%s = N'%s';",
                addrPara[1], addrPara[2],
                TableName.USER, TableName.ADDRESS,
                userPara[0], addrPara[0],
                userPara[1], String.valueOf(cond[0]), userPara[2], String.valueOf(cond[1])
        );
        SiteUser user = null;
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(SiteUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    private SiteUser createUser (ResultSet rs) throws SQLException{
        String[] userPara = TablePara.USER;
        String[] addrPara = TablePara.ADDRESS;
        SiteUser user;
        
        int userId = rs.getInt(userPara[0]);
        user = new SiteUser(rs.getInt(userPara[0]), rs.getString(userPara[1]), rs.getString(userPara[2]), rs.getString(userPara[3]), rs.getString(userPara[4]));
        user.setIsAdmin(rs.getBoolean(userPara[5]));
        
        if (rs.getString(addrPara[1]) == null || rs.getString(addrPara[1]).equals("null"))
            return user;
        
        List<String> addresses = new LinkedList<>();
        int mainIndex = 0;
        int counter = 0;
        do {
            addresses.add(rs.getString(addrPara[1]));
            if (rs.getBoolean(addrPara[2])) 
                mainIndex = counter;
            counter++;
        } while (rs.next() && userId == rs.getInt(userPara[0]));

        user.setShippingAddresses(addresses);
        user.setDefaultAddressIndex(mainIndex);

        return user;
    }

    @Override
    public List<SiteUser> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(SiteUser user) {
        String[] userPara = TablePara.USER;
        String command = String.format(
                "INSERT INTO %s (%s, %s, %s, %s) VALUES\n" +
                "(N'%s', N'%s', N'%s', N'%s');",
                TableName.USER, userPara[1], userPara[2], userPara[3], userPara[4],
                user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber()
        );
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command);) {
            int rowAffected = stm.executeUpdate();
            System.out.println("Row affected: " + rowAffected);
            return true;
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(SiteUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(SiteUser user, String[] params) {
        String[] userPara = TablePara.USER;
        String command = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                TableName.USER, userPara[1], userPara[2], userPara[3], userPara[4], userPara[0]
        );
        System.out.println(command);
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command)) {
            stm.setString(1, params[0]);
            stm.setString(2, params[1]);
            stm.setString(3, params[2]);
            stm.setString(4, params[3]);
            stm.setInt(5, user.getId());
            
            boolean success = stm.executeUpdate() > 0;
            if (success) {
                user.setUsername(params[0]);
                user.setPassword(params[1]);
                user.setEmail(params[2]);
                user.setPhoneNumber(params[0]);
            }
            return success;
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(SiteUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean addAddress (String newAddr, SiteUser user) {
        String[] addrPara = TablePara.ADDRESS;
        String command = String.format(
                "INSERT INTO %s (%s, %s) VALUES\n" +
                "(?, ?);",
                TableName.ADDRESS, addrPara[0], addrPara[1]
        );
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command)) {
            stm.setInt(1, user.getId());
            stm.setString(2, newAddr);
            
            boolean success = stm.executeUpdate()> 0;
            if (success) {
                user.getShippingAddresses().add(newAddr);
            }
            return success;
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(SiteUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(SiteUser t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
