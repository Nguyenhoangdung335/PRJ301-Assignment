package DAO.Order;

import DTO.Order.ShipType;
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
import util.ConstVar.TableName;
import util.ConstVar.TablePara;

public class ShippingTypeDAO implements DAO<ShipType>{
    private static ShippingTypeDAO instance;
    
    public static ShippingTypeDAO getInstance () {
        if (instance == null) instance = new ShippingTypeDAO();
        return instance;
    }
    
    private boolean hasChanged = true;
    private List<ShipType> cachedList = null;

    public ShippingTypeDAO() {}
    
    @Override
    public ShipType get(Object[] cond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ShipType> getAll() {
        if (!hasChanged && cachedList != null) return cachedList;
        
        String[] shipPara = TablePara.SHIP_TYPE;
        String command = String.format("SELECT * FROM %s", TableName.SHIP_TYPE);
        List<ShipType> list = new LinkedList<>();
        
        try (Connection con = util.makeConnection(); PreparedStatement stm = con.prepareCall(command); ResultSet rs = stm.executeQuery()) {
            while (rs.next())
                list.add(new ShipType(rs.getInt(shipPara[0]), rs.getString(shipPara[1]), rs.getDouble(shipPara[2])));
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShippingTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hasChanged = false;
        cachedList = list;
        
        return list;
    }

    @Override
    public boolean save(ShipType t) {
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ShipType t1, String[] params) {
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ShipType t) {
        hasChanged = true;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
