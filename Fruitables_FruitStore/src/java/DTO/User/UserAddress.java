/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.User;

/**
 *
 * @author hi
 */
public class UserAddress {
    private int userID;
    private String address;

    public UserAddress(int userID, String address) {
        this.userID = userID;
        this.address = address;
    }

    public UserAddress() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
