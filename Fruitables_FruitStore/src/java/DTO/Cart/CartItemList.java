package DTO.Cart;

import DTO.Product.ProductDetail;
import java.util.HashMap;
import java.util.Random;

public class CartItemList extends HashMap<ProductDetail, Double>{
    private int userID;
    private int cartID;

    public CartItemList(int userID, int cartID) {
        this.userID = userID;
        this.cartID = cartID;
    }   

    public CartItemList() {
        this.userID = -1;
        this.cartID = new Random().nextInt(10000);
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
    //</editor-fold>
    
    public boolean addToCart(ProductDetail product) {
        if (product == null) return false;
        
        ProductDetail search = this.getProduct(product.getid());
        if (search != null)
            this.replace(search, this.get(search) + 1);
        else 
            this.put(product, 1.0);
        return true;
    }
    
    public boolean addToCart(ProductDetail product, double quantity) {
        if (product == null) return false;
        
        ProductDetail search = this.getProduct(product.getid());
        if (search != null)
            this.replace(search, this.get(search) + quantity);
        else 
            this.put(product, quantity);
        return true;
    }
    
    public boolean removeProduct (ProductDetail product) {
        for (ProductDetail item: this.keySet()) {
            if (item.getid() == product.getid()) {
                this.remove(item);
                return true;
            }
        }
        return false;
    }
    
    public Entry<ProductDetail, Double> getProductEntry (int productId) {
        for (Entry<ProductDetail, Double> entry: this.entrySet()) {
            if (entry.getKey().getid() == productId)
                return entry;
        }
        return null;
    }
    
    public ProductDetail getProduct (int productId) {
        for (ProductDetail pro: this.keySet()) {
            if (pro.getid() == productId)
                return pro;
        }
        return null;
    }
    
    public double getProductQuantity (int productId) {
        for (Entry<ProductDetail, Double> entry: this.entrySet()) {
            if (entry.getKey().getid() == productId)
                return entry.getValue();
        }
        return -1;
    }
    
    public double getTotalPrice () {
        double totalPrice = 0;
        for (Entry<ProductDetail, Double> entry: this.entrySet()) {
            ProductDetail pro = entry.getKey();
            totalPrice += pro.getPrice() * entry.getValue();
        }
        return totalPrice;
    }
    
    public int getTotalQuantity () {
        int quantity = 0;
        for (Entry<ProductDetail, Double> entry: this.entrySet()) {
            quantity += entry.getValue();
        }
        return quantity;
    }
}
