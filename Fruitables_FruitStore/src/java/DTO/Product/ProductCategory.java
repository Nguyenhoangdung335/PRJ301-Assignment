package DTO.Product;

public class ProductCategory {
    private int id;
    private String name;
    
    //<editor-fold desc="CONSTRUCTORS" defaultstate="collapsed">
    public ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory() {
    }
    //</editor-fold>

    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
}
