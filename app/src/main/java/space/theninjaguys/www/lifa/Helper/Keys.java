package space.theninjaguys.www.lifa.Helper;

/**
 * Created by rj on 8/7/15.
 */
public class Keys {

    /*General Keys*/
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String PREFER_NAME = "userSession";
    public static final String USER_OBJECT_KEY = "user";

    /* User keys */
    public static final String USER_ID_POST_REGISTRATION = "id";
    public static final String USER_ID_POST_LOGIN ="user_id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_CONTACT = "mobile_number";
    public static final String USER_PASSWORD = "password";

    /* Barber List Object */
    public static final String BARBER_ID = "";
    public static final String BARBER_NAME = "";
    public static final String BARBER_SHOP_NAME = "";
    public static final String BARBER_ADDRESS = "";
    public static final String BARBER_AVAILABILITY = "";

    /*Location Object*/
    public static String LOCATION_NAME = "";

    /*Booking History Object */
        //Barber List Object +

    public static final String BOOKING_PIN = "";
    public static final String TIME ="";
    public static final String DATE = "";
    public static final String BOOKING_STATUS_PENDING = "";
    public static final String BOOKING_STATUS_CANCELLED = "";
    public static final String BOOKING_STATUS_COMPLETED = "";



    /* Urls */
    public static final String BASE_URL = "https://young-basin-1937.herokuapp.com";
    public static final String URL_LOGIN = "/api/users/login";
    public static final String URL_REGISTRATION = "/api/users";
    public static final String URL_GET_LOCATION = "";
    public static final String URL_GET_BARBER_LIST = "";
    public static final String URL_GET_BOOKED_HISTORY = "";
    public static final String URL_SAVE_BOOKING_DETAILS = "";
    public static final String URL_CANCEL_BOOKING = "";


}
