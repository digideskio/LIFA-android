package space.theninjaguys.www.lifa.Models;

/**
 * Created by rj on 8/7/15.
 */
public class BarberListItem {

    String id;
    String shopName;
    String barberName;
    String shopAddress;
    boolean Availability;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }
}
