package DAO.Product;

import DTO.Product.Product;
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

public class ProductDAO implements DAO<Product>{
    private static ProductDAO instance;
    
    private Connection con;
    private List<Product> cachedList = null;
    private boolean hasChanged = true;
    
    public static ProductDAO getInstance () {
        if (instance == null) instance = new ProductDAO();
        return instance;
    }

    public ProductDAO() {
        try {
            con = util.makeConnection();
        } catch (NamingException | SQLException ex) {}
    }
    
    @Override
    public Product get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        
        String[] prodPara = ConstVar.TablePara.PRODUCT;
        String[] catePara = ConstVar.TablePara.PRODUCT_CATEGORY;
        String command = String.format(
                "SELECT p.%s, pc.%s AS %s, p.%s, p.%s, p.%s\n" +
                "FROM %s AS p JOIN %s AS pc\n" +
                "ON p.%s = pc.%s;",
                prodPara[0], catePara[1], prodPara[1], prodPara[2], prodPara[3], prodPara[4],
                ConstVar.TableName.PRODUCT, ConstVar.TableName.PRODUCT_CATEGORY,
                prodPara[1], catePara[0]
        );

        LinkedList<Product> list = new LinkedList<>();
        
        try (PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(prodPara[0]),
                        rs.getString(prodPara[1]),
                        rs.getString(prodPara[2]),
                        rs.getString(prodPara[3]),
                        rs.getString(prodPara[4])
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = false;
        cachedList = list;
        
        return list;
    }

    @Override
    public boolean save(Product t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Product t1, String[] params) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Product t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
