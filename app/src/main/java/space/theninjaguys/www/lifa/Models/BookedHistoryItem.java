package space.theninjaguys.www.lifa.Models;

/**
 * Created by rj on 8/7/15.
 */
public class BookedHistoryItem {

    String shopName;
    String barberName;
    String shopAddress;
    String date;
    String time;
    String uniquePin;
    boolean Availability;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUniquePin() {
        return uniquePin;
    }

    public void setUniquePin(String uniquePin) {
        this.uniquePin = uniquePin;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }
}
