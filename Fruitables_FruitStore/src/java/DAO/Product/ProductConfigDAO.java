package DAO.Product;

import DTO.Cart.CartItemList;
import DTO.Product.ProductConfig;
import DTO.Product.ProductDetail;
import DTO.Product.Variation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;
import util.ConstVar.TableName;
import util.ConstVar.TablePara;

public class ProductConfigDAO implements DAO<ProductConfig>{
    private static ProductConfigDAO instance;
    
    private Connection con;
    private List<ProductConfig> cachedList = null;
    private boolean hasChanged = true;
    
    public static ProductConfigDAO getInstance () {
        if (instance == null) instance = new ProductConfigDAO();
        return instance;
    }

    public ProductConfigDAO() {
        try {
            con = util.makeConnection();
        } catch (NamingException | SQLException ex) {}
    }
    
    @Override
    public ProductConfig get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductConfig> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<ProductConfig> getByCartItem (CartItemList cart) {
        int cartSize = cart.size() - 1;
        if (cartSize < 0) return new LinkedList<>();
        
        String[] configPara = TablePara.PRODUCT_CONFIG;
        String[] variationPara = TablePara.VARIATION;
        String[] pTypePara = TablePara.PRODUCT_TYPE;
        
        int counter = 0;
        StringBuilder range = new StringBuilder("(");
        for (Entry<ProductDetail, Double> entry : cart.entrySet()) {
            System.out.println("Key: " + entry.getKey().getid());
            range.append(entry.getKey().getid()).append((counter++ < cartSize)? ", ": ")");
        }
        System.out.println(range.toString());
        String command = String.format(
                "SELECT pc.%s AS config_id, pc.%s, v.%s, v.%s, v.%s AS variation_id, v.product_type\n" +
                "FROM ( SELECT v.%s, v.%s, v.%s, pt.%s AS product_type\n" +
                "	FROM %s AS v JOIN %s AS pt \n" +
                "	ON v.%s = pt.%s\n" +
                ") AS v JOIN %s AS pc\n" +
                "ON v.%s = pc.%s\n" + 
                "WHERE pc.%s IN %s\n" + 
                "ORDER BY pc.%s ASC, v.%s ASC;",
                configPara[0], configPara[1], variationPara[2], variationPara[3], variationPara[0],
                variationPara[0], variationPara[2], variationPara[3], pTypePara[1],
                TableName.VARIATION, TableName.PRODUCT_TYPE,
                variationPara[1], pTypePara[0],
                TableName.PRODUCT_CONFIG,
                variationPara[0], configPara[2],
                configPara[1], range.toString(),
                configPara[1], variationPara[2]
        );
        
        List<ProductConfig> list = new LinkedList<>();
        
        try (PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            int currProductID = -1;
            ProductConfig newConfig = null;
            while (rs.next()) {
                int temp = rs.getInt(configPara[1]);
                if (currProductID != rs.getInt(configPara[1])) {
                    currProductID = temp;
                    newConfig = new ProductConfig(currProductID);
                    list.add(newConfig);
                }
                newConfig.addVariation(new Variation(
                        rs.getInt("variation_id"),
                        rs.getString("product_type"),
                        rs.getDouble(variationPara[2]),
                        rs.getDouble(variationPara[3])
                ), rs.getInt("config_id"));
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(ProductConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return list;
    }

    @Override
    public boolean save(ProductConfig t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductConfig t1, String[] params) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ProductConfig t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
