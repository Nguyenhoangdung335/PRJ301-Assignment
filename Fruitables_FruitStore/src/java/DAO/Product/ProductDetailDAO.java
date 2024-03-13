package DAO.Product;

import DTO.Product.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;

public class ProductDetailDAO implements DAO<ProductDetail>{
    private static ProductDetailDAO instance;
    
    private Connection con;
    private List<ProductDetail> cachedList = null;
    private boolean hasChanged = true;
    
    public static ProductDetailDAO getInstance () {
        if (instance == null) instance = new ProductDetailDAO();
        return instance;
    }

    public ProductDetailDAO() {
        try {
            con = util.makeConnection();
        } catch (NamingException | SQLException ex) {}
    }
    
    
    @Override
    public ProductDetail get(Object[] cond) {
        if (hasChanged || cachedList == null) getAll();
        String productIdStr = String.valueOf(cond[0]);
        
        if (productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);
            for (ProductDetail pro : cachedList) {
                if (pro.getid() == productId)
                    return pro;
            }
        }
        return null;
    }

    @Override
    public List<ProductDetail> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        
        String[] proItemPara = ConstVar.TablePara.PRODUCT_ITEM;
        String[] prodPara = ConstVar.TablePara.PRODUCT;
        String[] catePara = ConstVar.TablePara.PRODUCT_CATEGORY;
        
        String command = String.format(
                "SELECT pi.%s, pi.%s, pi.%s, pi.%s , p.*\n" +
                "FROM (\n" + 
                "   SELECT pi.id, pi.product_id, pi.qty_in_stock, pi.price, pt.name AS product_type_id\n" + 
                "   FROM product_item AS pi JOIN product_type AS pt\n" + 
                "   ON pi.product_type_id = pt.id\n" + 
                ") as pi JOIN (\n" + 
                "   SELECT p.%s, pc.%s AS %s, p.%s, p.%s, p.%s\n" +
                "   FROM %s AS p JOIN %s AS pc\n" +
                "   ON p.%s = pc.%s\n" + 
                ") AS p\n" +
                "ON pi.product_id = p.id;",
                proItemPara[0], proItemPara[2], proItemPara[3], proItemPara[4],
                prodPara[0], catePara[1], prodPara[1], prodPara[2], prodPara[3], prodPara[4],
                ConstVar.TableName.PRODUCT, ConstVar.TableName.PRODUCT_CATEGORY,
                prodPara[1], catePara[0], 
                proItemPara[1], prodPara[0]
        );

        List<ProductDetail> list = new LinkedList<>();
        
        try (PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductDetail(
                        rs.getInt(proItemPara[0]), 
                        rs.getString(prodPara[1]), 
                        rs.getString(prodPara[2]), 
                        rs.getString(prodPara[3]), 
                        rs.getString(prodPara[4]),
                        rs.getDouble(proItemPara[2]), 
                        rs.getDouble(proItemPara[3]),
                        rs.getString(proItemPara[4])
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = false;
        cachedList = list;
        
        return list;
    }

    @Override
    public boolean save(ProductDetail t) {
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductDetail t1, String[] params) {
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean updateNewQuantity (String[] productItemID, String[] quantity) {
        if (productItemID.length != quantity.length) return false;
        
        String command = String.format(
                "UPDATE product_item \n" +
                "SET qty_in_stock -= ?\n" +
                "WHERE id = ?;"
        );
        try (PreparedStatement stm = con.prepareStatement(command)) {
            for (int i = 0; i < productItemID.length; i++) {
                stm.setDouble(1, Double.parseDouble(quantity[i]));
                stm.setInt(2, Integer.parseInt(productItemID[i]));
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        hasChanged = true;
        
        return true;
    }

    @Override
    public boolean delete(ProductDetail t) {
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<ProductDetail> searchByName (Object[] cond) {
        List<ProductDetail> resultList = new LinkedList<>();
        if (hasChanged || cachedList == null) getAll();
        if (cachedList == null) return null;
        String searchValue = String.valueOf(cond[0]);
        
        cachedList.stream().filter((pro) -> (StringUtil.containsIgnoreCase(pro.getName(), searchValue))).forEachOrdered((pro) -> {
            resultList.add(pro);
        });
        return resultList;
    }
    
    public List<ProductDetail> searchByCategory (Object[] cond) {
        List<ProductDetail> resultList = new LinkedList<>();
        if (hasChanged || cachedList == null) getAll();
        if (cachedList == null) return null;
        String cateValue = String.valueOf(cond[0]);
        
        cachedList.stream().filter((pro) -> (pro.getCategory().equalsIgnoreCase(cateValue))).forEachOrdered((pro) -> {
            resultList.add(pro);
        });
        return resultList;
    }
    
    public List<ProductDetail> getBestSeller () {
        if (hasChanged || cachedList == null) getAll();
        
        List<ProductDetail> resultList = new LinkedList<>(cachedList);
        Collections.sort(resultList, Comparator.comparingDouble(ProductDetail::getInStock).reversed());
        
        return resultList;
    }
}
