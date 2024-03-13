package DTO.Order;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int userID;
    private LocalDateTime date;
    private int paymentID;
    private String shippingAddress;
    private ShipType shippingType;
    private double totalProductPrice;
    private String status;

    public Order() {
    }

    public Order(int id, int userID, LocalDateTime date, int paymentID, String address, ShipType shippingTypeID, double totalProductPrice, String status) {
        this.id = id;
        this.userID = userID;
        this.date = date;
        this.paymentID = paymentID;
        this.shippingAddress = address;
        this.shippingType = shippingTypeID;
        this.totalProductPrice = totalProductPrice;
        this.status = status;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getshippingAddress() {
        return shippingAddress;
    }

    public void setshippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShipType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShipType shippingType) {
        this.shippingType = shippingType;
    }

    public double getTotal() {
        return totalProductPrice;
    }

    public void setTotal(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
