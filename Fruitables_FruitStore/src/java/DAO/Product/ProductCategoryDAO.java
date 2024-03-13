package DAO.Product;

import DTO.Product.*;
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

public class ProductCategoryDAO implements DAO<ProductCategory>{
    private static ProductCategoryDAO instance;
    
    private List<ProductCategory> cachedList = null;
    private boolean hasChanged = true;
    
    public static ProductCategoryDAO getInstance () {
        if (instance == null) instance = new ProductCategoryDAO();
        return instance;
    }

    public ProductCategoryDAO() {
    }
    
    @Override
    public ProductCategory get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductCategory> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        
        String[] catePara = ConstVar.TablePara.PRODUCT_CATEGORY;
        String command = String.format(
                "SELECT * FROM %s;", ConstVar.TableName.PRODUCT_CATEGORY
        );

        LinkedList<ProductCategory> list = new LinkedList<>();
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductCategory(
                        rs.getInt(catePara[0]),
                        rs.getString(catePara[1])
                ));
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = false;
        cachedList = list;
        
        return list;
    }

    @Override
    public boolean save(ProductCategory t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductCategory t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ProductCategory t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
