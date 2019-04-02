package com.camellia.soorty.utills;



public class AppConstant {

    public static final String BASE_URL= "http://192.168.1.82/swagbike/api/";

    //Live url
//     public static final String BASE_URL= "http://13.233.239.210/swagbike/api/";
    public static final String SIGN_UP= BASE_URL+"login/verify/";
    public static final String SEND_OTP = BASE_URL+"login/sendOtp/";
    public static final String SIGN_IN= BASE_URL+"login/login/";
    public static final String UPDATE_PROFILE= BASE_URL+"cud/update_user/";


    public static String Auth="8f3a07f4b8575c447078a203ff97d21806384458f8cfb3e4def94773dfc6077f62588383408be7c2";

    public static String GOOGLE_IMAGE_URL_PREFIX="https://maps.googleapis.com/maps/api/place/photo?maxwidth=150&photoreference=";
    public static String USERTYPE_CUSTOMER="customer";
    public static String USERTYPE_ORG="organization";
    public static final String BROADCAST_ACTION = "LocationUtter";
    public static final String BROADCAST_TAG ="broadCastTag" ;
    public static final String BROADCAST_BOOLEAN ="status" ;

    public static final String PACKAGE_NAME = "com.camellia.soorty";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String LOCATION_DATA_EXTRA =  PACKAGE_NAME +".locationDataExtra";
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static int SUCCESS_RESULT_ADDRESS_LATLONG=2025;
    public static String EXTRA_DATA_IMAGE="imageReceiver";

    public static final String RESULT_ADDRESS_KEY ="resultAddress" ;
    public static final String RESULT_PINCODE_KEY = "pinCOdeKey";
    public static final String QB_PASSWORD = "nowUtterTss";
    public static boolean IsChatScreen=false;

}
