package DTO.Product;

public class ProductItem {
    private int productItemID;
    private int productID;
    private double inStock;
    private double price;
    
    //<editor-fold desc="CONSTRUCTOR" defaultstate="collapsed">
    public ProductItem(int productItemID, int productID, double inStock, double price) {
        this.productItemID = productItemID;
        this.productID = productID;
        this.inStock = inStock;
        this.price = price;
    }

    public ProductItem() {
    }
    //</editor-fold>
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getProductItemID() {
        return productItemID;
    }

    public void setProductItemID(int productItemID) {
        this.productItemID = productItemID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getInStock() {
        return inStock;
    }

    public void setInStock(double inStock) {
        this.inStock = inStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    //</editor-fold>
}
