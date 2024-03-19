package DAO.Product;

import DTO.Product.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import util.*;

public class ProductDetailDAO implements DAO<ProductDetail>{
    private static ProductDetailDAO instance;
    
    private List<ProductDetail> cachedList = null;
    private boolean hasChanged = true;
    
    public static ProductDetailDAO getInstance () {
        if (instance == null) instance = new ProductDetailDAO();
        return instance;
    }
    
    public static boolean closeInstance () {
        instance = null;
        return true;
    }

    public ProductDetailDAO() {
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
    
    public ProductDetail getLatest() {
        if (hasChanged || cachedList == null) getAll();
        LinkedList<ProductDetail> linkedList = (LinkedList<ProductDetail>)cachedList;
        return linkedList.getLast();
    }

    @Override
    public List<ProductDetail> getAll() {
        System.out.println("getAll called");
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
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
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
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean create(Object[] params) {
        hasChanged = true;
        String command = String.format(
                "INSERT INTO product (category_id, name, description, product_image)\n" +
                "VALUES (?, ?, ?, ?)\n" +
                "INSERT INTO product_item (product_id, qty_in_stock, price, product_type_id)\n" +
                "VALUES ((SELECT TOP 1 id FROM product ORDER BY id DESC), ?, ?, ?)\n",
                (int)params[1],(String)params[2],(String)params[3],(String)params[4],(double)params[5],(double)params[6],(int)params[7]
                //cateID        name              des               img               stock             price             typeID        
        );
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command)) {
            stm.setInt(1, (int)params[1]);
            stm.setString(2, (String)params[2]);
            stm.setString(3, (String)params[3]);
            stm.setString(4, (String)params[4]);
            stm.setDouble(5, (double)params[5]);
            stm.setDouble(6, (double)params[6]);
            stm.setInt(7, (int)params[7]);
            return stm.executeUpdate() > 0; 
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(ProductDetail t1, String[] params) {
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean updateInfo(Object[] params) {
        hasChanged = true;
        
        String command = String.format(
                "UPDATE	product\n" +
                "SET category_id = ?, name = ?, description = ?, product_image = ?\n" +
                "WHERE id = ?;\n" +
                "UPDATE product_item\n" +
                "SET qty_in_stock = ?, price = ?, product_type_id = ?\n" +
                "WHERE product_id = ?;"
        );
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command);) {
            stm.setInt(1, (int)params[1]);
            stm.setString(2, (String)params[2]);
            stm.setString(3, (String)params[3]);
            stm.setString(4, (String)params[4]);
            stm.setInt(5, (int)params[0]);
            stm.setDouble(6, (double)params[5]);
            stm.setDouble(7, (double)params[6]);
            stm.setInt(8, (int)params[7]);
            stm.setInt(9, (int)params[0]);
            return stm.executeUpdate() > 0;
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateNewQuantity (String[] productItemID, String[] quantity) {
        if (productItemID.length != quantity.length) return false;
        
        String command = String.format(
                "UPDATE product_item \n" +
                "SET qty_in_stock -= ?\n" +
                "WHERE id = ?;"
        );
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command)) {
            for (int i = 0; i < productItemID.length; i++) {
                stm.setDouble(1, Double.parseDouble(quantity[i]));
                stm.setInt(2, Integer.parseInt(productItemID[i]));
                stm.executeUpdate();
            }
        } catch (SQLException | NamingException ex) {
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
    
    public Pair<List<ProductDetail>, Integer> searchByName (String searchValue, int pageIndex, int numPerPage) {
        List<ProductDetail> resultList = new LinkedList<>();
        if (hasChanged || cachedList == null) getAll();
        if (cachedList == null) return new Pair<>(resultList, 0);
        
        int startIndex = numPerPage * pageIndex, endIndex = startIndex + numPerPage;
        System.out.println("Start: " + startIndex + " End: " + endIndex);
        int count = 0;
        for (int i = 0, j = 0; i < cachedList.size(); i++) {
            if (StringUtil.containsIgnoreCase(cachedList.get(i).getName(), searchValue)) {
                count++;
                if (j >= startIndex && j < endIndex) {
                    resultList.add(cachedList.get(i));
                    System.out.println(cachedList.get(i));
                }
                j++;
            }
        }
        return new Pair<>(resultList, count);
    }
    
    public Pair<List<ProductDetail>, Integer> searchByCategory (String cateValue, int pageIndex, int numPerPage) {
        List<ProductDetail> resultList = new LinkedList<>();
        if (hasChanged || cachedList == null) getAll();
        if (cachedList == null) return new Pair<>(resultList, 0);
        
        int startIndex = numPerPage * pageIndex, endIndex = startIndex + numPerPage;
        int count = 0;
        for (int i = 0, j = 0; i < cachedList.size(); i++) {
            if (cachedList.get(i).getCategory().equalsIgnoreCase(cateValue)) {
                count++;
                if (j >= startIndex && j < endIndex) {
                    resultList.add(cachedList.get(i));
                }
                j++;
            }
        }
        return new Pair<>(resultList, count);
    }
    
    public Pair<List<ProductDetail>, Integer> getAll (int pageIndex, int numPerPage) {
        
        List<ProductDetail> resultList = new LinkedList<>();
        if (hasChanged || cachedList == null) getAll();
        if (cachedList == null) return new Pair<>(resultList, 0);
        
        int startIndex = numPerPage * pageIndex, endIndex = startIndex + numPerPage;
        for (int i = 0, j = 0; i < cachedList.size(); i++) {
            if (j >= startIndex && j < endIndex) {
                resultList.add(cachedList.get(i));
            }
            j++;
        }
        return new Pair<>(resultList, cachedList.size());
    }
    
    public List<ProductDetail> getBestSeller () {
        if (hasChanged || cachedList == null) getAll();
        
        List<ProductDetail> resultList = new LinkedList<>(cachedList);
        Collections.sort(resultList, Comparator.comparingDouble(ProductDetail::getInStock).reversed());
        
        return resultList;
    }
}
