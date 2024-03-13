package DTO.Product;

import java.io.Serializable;

public class ProductDetail implements Serializable{
    private int id;
    private String category;
    private String name;
    private String description;
    private String imageLink;
    private double inStock;
    private double price;
    private String productType;

    //<editor-fold desc="CONSTRUCTORS" defaultstate="collapsed">
    public ProductDetail(int ID, String category, String name, String description, String imageLink, double inStock, double price, String productType) {
        this.id = ID;
        this.category = category;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.inStock = inStock;
        this.price = price;
        this.productType = productType;
    }

    public ProductDetail() {
    }
    //</editor-fold>
    
    //<editor-fold desc="GETTERES AND SETTERS" defaultstate="collapsed">
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s_%s_%s_%s_%s_%s_%s_%s", 
                id, category, name, description, imageLink, inStock, price, productType
        );
    }
}
