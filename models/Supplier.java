// models/Supplier.java

package models;

/**
 * Represents a supplier for drugs in the pharmacy.
 * Contains details such as name, id, location, and delivery time.
 */
public class Supplier {
    private String name;
    private String id;
    private String location;
    private int deliveryTime; // in days

    public String getName() { return name; }
    public String getId() { return id; }
    public String getLocation() { return location; }
    public int getDeliveryTime() { return deliveryTime; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public void setLocation(String location) { this.location = location; }
    public void setDeliveryTime(int deliveryTime) { this.deliveryTime = deliveryTime; }
}
} 