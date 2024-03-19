package DAO.Product;

import DTO.Product.ProductType;
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
import util.ConstVar.TablePara;
import util.DAO;
import util.util;

public class ProductTypeDAO implements DAO<ProductType>{
    private static ProductTypeDAO instance = null;
    
    public static ProductTypeDAO getInstance () {
        if (instance == null) instance = new ProductTypeDAO();
        return instance;
    }
    
    private List<ProductType> cachedList = null;
    private boolean hasChanged = false;
    
    @Override
    public ProductType get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductType> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        String[] typePara = TablePara.PRODUCT_TYPE;
        String command = String.format("SELECT * FROM %s", TableName.PRODUCT_TYPE);
        List<ProductType> list = new LinkedList<>();
        try (Connection con = util.makeConnection(); PreparedStatement ps = con.prepareStatement(command); ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                list.add(new ProductType(
                        rs.getInt(typePara[0]),
                        rs.getString(typePara[1])
                ));
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cachedList = list;
        hasChanged = false;
        
        return list;
    }

    @Override
    public boolean save(ProductType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductType t1, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ProductType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
