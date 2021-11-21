package com.demo.config;

public class Airline {
    String name;
    String contactNumber;
    String contactAddress;
    Boolean isActive;

    public Airline() {

    }

    public Airline(String name, String contactNumber, String contactAddress, Boolean isActive) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactAddress = contactAddress;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}