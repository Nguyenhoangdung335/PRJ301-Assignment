package DTO.User;

import java.time.LocalDate;
import util.ConstVar;

public class UserPayment {
    private int id;
    private int userID;
    private String provider;
    private int accNumber;
    private LocalDate expDate;
    
    //<editor-fold desc="CONSTRUCTORS" defaultstate="collapsed">
    public UserPayment() {
    }

    public UserPayment(int id, int userID, String providerID, int accNumber, LocalDate expDate) {
        this.id = id;
        this.userID = userID;
        this.provider = providerID;
        this.accNumber = accNumber;
        this.expDate = expDate;
    }
    //</editor-fold>
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
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

    public String getProviderID() {
        return provider;
    }

    public void setProviderID(String providerID) {
        this.provider = providerID;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public String getExpDate() {
        return expDate.format(ConstVar.DATEPATTERN);
    }

    public void setExpDate(String expDate) {
        this.expDate = LocalDate.parse(expDate, ConstVar.DATEPATTERN);
    }
    //</editor-fold>
}
