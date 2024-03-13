package DTO.Cart;

public class ShoppingCart {
    private int id;
    private int userID;

    public ShoppingCart(int id, int userID) {
        this.id = id;
        this.userID = userID;
    }

    public ShoppingCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
}
