package DTO.User;

import java.util.LinkedList;
import java.util.List;

public class SiteUser {
    private int id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private List<String> shippingAddresses;
    private int defaultAddressIndex;
    private boolean isAdmin;
    
    //<editor-fold desc="CONSTRUCTORS" defaultstate="collapsed">
    public SiteUser() {
        this.shippingAddresses = new LinkedList<>();
        this.defaultAddressIndex = 0;
    }

    public SiteUser(int id, String username, String password, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isAdmin = false;
        this.shippingAddresses = new LinkedList<>();
    }
    
    public SiteUser(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddresses = new LinkedList<>();
        this.defaultAddressIndex = 0;
        this.isAdmin = false;
    }

    public SiteUser(int id, String username, String password, String email, String phoneNumber, List<String> shippingAddresses) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddresses = shippingAddresses;
        this.defaultAddressIndex = 0;
        this.isAdmin = false;
    }
    
    public SiteUser(int id, String username, String password, String email, String phoneNumber, List<String> shippingAddresses, int defaultAddressIndex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddresses = shippingAddresses;
        this.defaultAddressIndex = defaultAddressIndex;
        this.isAdmin = false;
    }
    //</editor-fold>

    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(List<String> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public int getDefaultAddressIndex() {
        return defaultAddressIndex;
    }

    public void setDefaultAddressIndex(int defaultAddressIndex) {
        this.defaultAddressIndex = defaultAddressIndex;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    //</editor-fold>

    @Override
    public String toString() {
        String str = String.format("%s_%s_%s_%s_%s", id, username, password, phoneNumber, email);
        if (shippingAddresses != null) {
            for (String addr: shippingAddresses) 
                str += "_" + addr;
            str += "_" + defaultAddressIndex;
        }
        return str;
    }
}
