package DAO.User;

import DTO.User.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.ConstVar.TableName;
import util.DAO;
import util.util;

public class ProviderDAO implements DAO<Provider>{
    private static ProviderDAO instance;
    
    public static ProviderDAO getInstance () {
        if (instance == null) instance = new ProviderDAO();
        return instance;
    }
    
    private List<Provider> cachedList = null;
    private boolean hasChanged = true;
    
    public ProviderDAO() {
    }
    
    @Override
    public Provider get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        
        String command = String.format("SELECT * FROM %s", TableName.PROVIDER);
        List<Provider> list = new LinkedList<>();
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            while (rs.next())
                list.add(new Provider(rs.getInt("id"), rs.getString("name")));
        } catch (SQLException ex) {
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        cachedList = list;
        hasChanged = false;
        
        return list;
    }

    @Override
    public boolean save(Provider t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Provider t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Provider t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
