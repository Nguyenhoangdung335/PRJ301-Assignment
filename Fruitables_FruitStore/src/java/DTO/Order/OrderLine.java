package DTO.Order;

public class OrderLine {
    private int id;
    private int orderID;
    private int cartID;
    private int productConfigID;
    private double price;
    private double quantity;

    public OrderLine() {
    }

    public OrderLine(int id, int productConfigID, int orderID, int cartID, double price, double quantity) {
        this.id = id;
        this.productConfigID = productConfigID;
        this.orderID = orderID;
        this.cartID = cartID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductConfigID() {
        return productConfigID;
    }

    public void setProductConfigID(int productConfigID) {
        this.productConfigID = productConfigID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
