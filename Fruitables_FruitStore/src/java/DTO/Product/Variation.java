package DTO.Product;

import java.util.Objects;

public class Variation {
    private int variationID;
    private String pType;
    private double value;
    private double pricePercent;
    
    //<editor-fold desc="CONSTRUCTORS" defaultstate="collapsed">
    public Variation(int variationID, String pType, double value, double pricePercent) {
        this.variationID = variationID;
        this.pType = pType;
        this.value = value;
        this.pricePercent = pricePercent;
    }

    public Variation() {
    }
    //</editor-fold>
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getVariationID() {
        return variationID;
    }

    public void setVariationID(int variationID) {
        this.variationID = variationID;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPricePercent() {
        return pricePercent;
    }

    public void setPricePercent(double pricePercent) {
        this.pricePercent = pricePercent;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variation) {
            Variation other = (Variation)obj;
            return (this.variationID == other.variationID) && (this.pType.equals(other.pType)) && 
                    (this.value == other.value) && (this.pricePercent == other.pricePercent);
            }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.variationID;
        hash = 47 * hash + Objects.hashCode(this.pType);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.pricePercent) ^ (Double.doubleToLongBits(this.pricePercent) >>> 32));
        return hash;
    }
}
