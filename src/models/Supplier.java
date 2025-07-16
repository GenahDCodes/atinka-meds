//src/main/Supplier.java
package models;

import java.io.Serializable;

public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    final  String id;
    final String name;
    final String location;
    final int deliveryTime;
    private String contactInfo;
    
    //Constructor
    public Supplier(String name, String id, String location, int deliveryTime) {
    this.name = name;
    this.id = id;
    this.location = location;
    this.deliveryTime = deliveryTime;
}


    public String getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getDeliveryTime() { return deliveryTime; }
    public String getContactInfo() { return contactInfo; }

    @Override
    public String toString() {
        return String.format("Supplier ID: %s | Name: %s | Location: %s | Delivery: %d days | Contact: %s",
                id, name, location, deliveryTime, contactInfo);
    }
}

; 