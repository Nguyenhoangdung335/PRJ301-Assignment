package DTO.Product;

public class ProductType {
    private int typeId;
    private String name;

    public ProductType(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public ProductType() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
