package DTO.Cart;

import java.util.HashMap;

public class ShoppingCartItems {
    private int userID;
    private int cartID;
    private HashMap<Integer, Double> proConfigQuantity;
    

    public ShoppingCartItems(int userID, int cartID, HashMap<Integer, Double> proConfigQuantity) {
        this.userID = userID;
        this.cartID = cartID;
        this.proConfigQuantity = proConfigQuantity;
    }

    public ShoppingCartItems() {
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public HashMap<Integer, Double> getProConfigQuantity() {
        return proConfigQuantity;
    }

    public void setProConfigQuantity(HashMap<Integer, Double> proConfigQuantity) {
        this.proConfigQuantity = proConfigQuantity;
    }
    //</editor-fold>
}
