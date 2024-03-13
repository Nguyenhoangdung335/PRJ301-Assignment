package DTO.Order;

public class ShipType {
    private int id;
    private String type;
    private double price;

    public ShipType(int id, String type, double price) {
        this.id= id;
        this.type = type;
        this.price = price;
    }

    public ShipType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
