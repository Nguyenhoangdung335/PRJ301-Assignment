package DAO.User;

import DTO.User.UserAddress;
import java.util.List;
import util.*;

public class UserAddressDAO implements DAO<UserAddress>{
    private static UserAddressDAO instance;
    
    public static UserAddressDAO getInstance () {
        if (instance == null) instance = new UserAddressDAO();
        return instance;
    }

    public UserAddressDAO() {
      
    }
    
    @Override
    public UserAddress get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserAddress> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(UserAddress t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserAddress t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(UserAddress t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
