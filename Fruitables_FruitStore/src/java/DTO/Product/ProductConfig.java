package DTO.Product;

import java.util.LinkedList;
import java.util.List;

public class ProductConfig {
    private int proItemId;
    private List<Integer> configId;
    private List<Variation> variations;
    
    //<editor-fold desc="CONSTRUCTORS" defaultstate="collapsed">
    public ProductConfig(int productItemID, List<Integer> configId, List<Variation> variations) {
        this.proItemId = productItemID;
        this.configId = configId;
        this.variations = variations;
    }

    public ProductConfig(int productItemID) {
        this.proItemId = productItemID;
        this.configId = new LinkedList<>();
        this.variations = new LinkedList<>();
    }

    public ProductConfig() {
    }
    //</editor-fold>
    

    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getProItemId() {    
        return proItemId;
    }
    
    public void setProItemId(int proItemId) {
        this.proItemId = proItemId;
    }

    public List<Integer> getConfigId() {
        return configId;
    }

    public void setConfigId(List<Integer> configId) {
        this.configId = configId;
    }

    public List<Variation> getVariations() {
        return variations;
    }

    public void setVariations(List<Variation> variations) {
        this.variations = variations;
    }
    //</editor-fold>
    
    public void addVariation (Variation newV, Integer newId) {
        if (!variations.contains(newV)) {
            this.variations.add(newV);
            this.configId.add(newId);
        }
    }

    @Override
    public String toString() {
        return String.format("%s_%s_%s", proItemId, configId, variations);
    }
}
