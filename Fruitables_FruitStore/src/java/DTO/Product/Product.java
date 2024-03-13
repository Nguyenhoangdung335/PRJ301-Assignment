package DTO.Product;

public class Product {
    private int productID;
    private String category;
    private String name;
    private String description;
    private String imageLink;
    
    //<editor-fold desc="CONSTRUCTORS" defaultstate="collapsed">
    public Product(int productID, String category, String name, String description, String imageLink) {
        this.productID = productID;
        this.category = category;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
    }

    public Product() {
    }
    //</editor-fold>

    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s_%s_%s_%s_%s", productID, category, name, description, imageLink);
    }
}
