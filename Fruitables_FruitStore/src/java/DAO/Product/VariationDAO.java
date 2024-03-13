package DAO.Product;

import DTO.Product.Product;
import DTO.Product.Variation;
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

public class VariationDAO implements DAO<Variation>{
    private static VariationDAO instance;
    private List<Variation> cachedList = null;
    private boolean hasChanged = true;
    
    public static VariationDAO getInstance () {
        if (instance == null) instance = new VariationDAO();
        return instance;
    }
    
    public VariationDAO() {}
    
    @Override
    public Variation get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Variation> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        
        String[] variPara = ConstVar.TablePara.VARIATION;
        String[] typePara = ConstVar.TablePara.PRODUCT_TYPE;
        String command = String.format(
                "SELECT v.%s, pt.%s, v.%s, v.%s\n" +
                "FROM %s AS v JOIN %s AS pt\n" +
                "ON v.%s = pt.%s;",
                variPara[0], typePara[1], variPara[2], variPara[3], 
                ConstVar.TableName.VARIATION, ConstVar.TableName.PRODUCT_TYPE,
                variPara[1], typePara[0]
        );
        List<Variation> list = new LinkedList<>();
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareStatement(command); ResultSet rs = stm.executeQuery()) {
            while (rs.next())
                list.add(new Variation(
                        rs.getInt(variPara[0]), 
                        rs.getString(typePara[1]), 
                        rs.getDouble(variPara[2]), 
                        rs.getDouble(variPara[3])
                ));
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(VariationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = false;
        cachedList = list;
        
        return list;
    }

    @Override
    public boolean save(Variation t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Variation t1, String[] params) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Variation t) {
        //Update the hasChanged flag to allow faster data retrieval
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
