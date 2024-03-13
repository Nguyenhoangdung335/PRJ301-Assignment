package DAO.Product;

import DTO.Product.ProductItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.naming.NamingException;
import util.*;

public class ProductItemDAO implements DAO<ProductItem>{
    private static ProductItemDAO instance;
    private List<ProductItem> cachedList = null;
    private boolean hasChanged = true;
    
    public static ProductItemDAO getInstance () {
        if (instance == null) instance = new ProductItemDAO();
        return instance;
    }
    
    private Connection con;
    private final String TABLE_NAME = "product_item";
    private final String[] PARA = {"id", "product_id", "qty_in_stock", "price"};

    public ProductItemDAO() {
        try {
            con = util.makeConnection();
        } catch (NamingException | SQLException ex) {}
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getTableParameters() {
        return PARA;
    }
    
    @Override
    public ProductItem get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductItem> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        
        String[] proItemPara = ConstVar.TablePara.PRODUCT_ITEM;
        String[] prodPara = ConstVar.TablePara.PRODUCT;
        String[] catePara = ConstVar.TablePara.PRODUCT_CATEGORY;
//        String command = String.format("SELECT * FROM %s", 
//                ConstVar.TableName.PRODUCT_ITEM);

        String command = String.format(
                "SELECT p.%s, pc.%s AS %s, p.%s, p.%s, p.%s\n" +
                "FROM %s AS p JOIN %s AS pc\n" +
                "ON p.%s = pc.%s;",
                prodPara[0], catePara[1], prodPara[1], prodPara[2], prodPara[3], prodPara[4],
                ConstVar.TableName.PRODUCT, ConstVar.TableName.PRODUCT_CATEGORY,
                prodPara[1], catePara[0]
        );

        List<ProductItem> list = new LinkedList<>();
        
        try (PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductItem(
                        rs.getInt(proItemPara[0]),
                        rs.getInt(proItemPara[1]),
                        rs.getDouble(proItemPara[2]),
                        rs.getDouble(proItemPara[3])
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = false;
        cachedList = list;
        
        return list;
    }

    @Override
    public boolean save(ProductItem t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductItem t1, String[] params) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ProductItem t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
