package space.theninjaguys.www.lifa.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import space.theninjaguys.www.lifa.Activities.LoginActivity;

/**
 * Created by rj on 8/7/15.
 */
public class UserSession {

    // Shared Preferences reference
    SharedPreferences pref;
    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(Keys.PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String id, String userName, String email, int contactNumber) {
        // Storing login value as TRUE
        editor.putBoolean(Keys.IS_USER_LOGIN, true);

        editor.putString(Keys.USER_ID, id);

        // Storing name in pref
        editor.putString(Keys.USER_NAME, userName);

        // Storing email in pref
        editor.putString(Keys.USER_EMAIL, email);

        editor.putInt(Keys.USER_CONTACT, contactNumber);

        // commit changes
        editor.commit();

    }


    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     */
    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        //user id
        user.put(Keys.USER_ID,pref.getString(Keys.USER_ID,null));

        // user name
        user.put(Keys.USER_NAME, pref.getString(Keys.USER_NAME, null));

        // user email id
        user.put(Keys.USER_EMAIL, pref.getString(Keys.USER_EMAIL, null));

        user.put(Keys.USER_CONTACT, pref.getString(Keys.USER_CONTACT, null));


        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(Keys.IS_USER_LOGIN, false);
    }

}
