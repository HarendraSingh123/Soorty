package com.camellia.soorty.Repos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.camellia.soorty.models.VerifyOtpUserModel;

public class MyAppPref {
    private String TAG = MyAppPref.class.getSimpleName();
    private static final String FIRST_LAUNCH = "first_launch";
    private static final String PREFERENCE = "";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_STATIC_ACCESS_TOKEN = "staticToken";
    public static final String KEY_ACCESS_TOKEN = "accessToken";
    public static final String KEY_USER_TYPE = "userType";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_USER_MOB = "userMob";
    public static final String KEY_USER_EMAIL = "userEmail";
    public static final String KEY_USER_PROFILE = "userImageUrl";
    public static final String KEY_USER_ADDRESS = "userAddress";

    public static String getKeyUserLastName() {
        return KEY_USER_LAST_NAME;
    }

    public static String getKeyUserFirstName() {
        return KEY_USER_FIRST_NAME;
    }

    public static final String KEY_FCM_TOKEN = "fcmToken";
    public static final String KEY_QB_REGISTERED = "isQBRegistered";
    public static final String KEY_QB_USERID = "qbUserId";
    private static final String PREF_NAME = "Soorty";


    private static final String KEY_USER_FIRST_NAME = "first_name";
    private static final String KEY_USER_LAST_NAME= "last_name";

    private Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences sharedPreferences2;


    public void setFirstLaunch(boolean isFirstLaunch) {

        editor.putBoolean(FIRST_LAUNCH, isFirstLaunch);
        editor.commit();
    }

    public boolean FirstLaunch() {

        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }

    public MyAppPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences2 = context.getSharedPreferences("Soorty2", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }





    public void storeUser(VerifyOtpUserModel user) {
        editor.putString(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_USER_FIRST_NAME, user.getUserFirstname());
        editor.putString(KEY_USER_LAST_NAME, user.getUserLastname());
        editor.putString(KEY_USER_EMAIL, user.getUserEmail());
        editor.putString(KEY_USER_MOB, user.getUserMobile());
        editor.putString(KEY_ACCESS_TOKEN, user.getAccess_token());
        editor.putString(KEY_FCM_TOKEN , user.getFcmToken());
        editor.commit();
        Log.e(TAG, "User is stored in shared preferences. " + user.getUserFirstname() + ", " + user.getUserMobile());
    }


    public  VerifyOtpUserModel getUser() {
        if (pref.getString(KEY_USER_ID, null) != null) {
            String id, fname, lname, email, telephone,access_token,fcmtoken;
            id = pref.getString(KEY_USER_ID, null);
            fname=pref.getString(KEY_USER_FIRST_NAME,null);
            lname = pref.getString(KEY_USER_LAST_NAME, null);
            email = pref.getString(KEY_USER_EMAIL, null);
            telephone = pref.getString(KEY_USER_MOB, null);
            access_token = pref.getString(KEY_ACCESS_TOKEN, null);
            fcmtoken=pref.getString(KEY_FCM_TOKEN,null);
            return new VerifyOtpUserModel(id, fname, lname, email,  telephone,access_token,fcmtoken);
        }
        return null;
    }






    @SuppressLint("LongLogTag")
    public void setAccessToken(String accessToken) {
        Log.e("STr ACcess token in SHared Pref ********************", accessToken);
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    @SuppressLint("LongLogTag")
    public void setUserId(String  userId) {
        Log.e("STr userID in SHared Pref ********************", userId + "");

        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply();
    }

    public String  getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, Integer.toString(0));
    }

    public void setUserType(String userType) {
        sharedPreferences.edit().putString(KEY_USER_TYPE, userType).apply();
    }

    public String getUserType() {
        return sharedPreferences.getString(KEY_USER_TYPE, null);
    }
    public void setKeyUserFirstName(String userFirstName)
    {
        sharedPreferences.edit().putString(KEY_USER_FIRST_NAME, userFirstName).apply();

    }

    public void setKeyUserLastName(String userLastName)
    {
        sharedPreferences.edit().putString(KEY_USER_FIRST_NAME, userLastName).apply();


    }
    public void setfirstName(String firstname)
    {
        sharedPreferences.edit().putString(KEY_USER_FIRST_NAME, firstname).apply();


    }
    public String getfirstname()
    {

        return sharedPreferences.getString(KEY_USER_FIRST_NAME,null);

    }

    public void setlastName(String lastname)
    {
        sharedPreferences.edit().putString(KEY_USER_LAST_NAME, lastname).apply();



    }

    public String getlastnmae()
    {
        return sharedPreferences.getString(KEY_USER_LAST_NAME,null);


    }






    public void setUserName(String userName) {
        sharedPreferences.edit().putString(KEY_USER_NAME, userName).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, null);
    }

    public void clearMyPref() {
        sharedPreferences.edit().clear().apply();
    }

    public String getMobile() {
        return sharedPreferences.getString(KEY_USER_MOB, null);
    }

    public void setMobile(String mobile) {
        sharedPreferences.edit().putString(KEY_USER_MOB, mobile).apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public void setEmail(String email) {
        sharedPreferences.edit().putString(KEY_USER_EMAIL, email).apply();
    }

    public String getImageUrl() {
        return sharedPreferences.getString(KEY_USER_PROFILE, "Na");
    }

    public void setImageUrl(String url) {
        sharedPreferences.edit().putString(KEY_USER_PROFILE, url).apply();
    }

    public String getAddress() {
        return sharedPreferences.getString(KEY_USER_ADDRESS, "NA");
    }

    public void setAddress(String address) {
        sharedPreferences.edit().putString(KEY_USER_ADDRESS, address).apply();
    }

    public void addFcmToken(String fcmToken) {
        sharedPreferences2.edit().putString(KEY_FCM_TOKEN, fcmToken).apply();
    }

    public String getFcmToken() {
        return sharedPreferences2.getString(KEY_FCM_TOKEN, "NA");
    }

    public void setIsQBRegistered(boolean isQBRegistered) {
        sharedPreferences.edit().putBoolean(KEY_QB_REGISTERED, isQBRegistered).apply();
    }

    public boolean isQBRegistered() {
        return sharedPreferences.getBoolean(KEY_QB_REGISTERED, false);
    }

    public void setQBUserId(int qbUserId) {
        sharedPreferences.edit().putInt(KEY_QB_USERID, qbUserId).apply();
    }

    public int getQBUserId() {
        return sharedPreferences.getInt(KEY_QB_USERID, 0);
    }
}
