package com.camellia.soorty.REST;


import com.camellia.soorty.activities.EditProfileActivty;
import com.camellia.soorty.login.model.GetOtpApiResponse;
import com.camellia.soorty.models.AddressModel;
import com.camellia.soorty.models.HomePageData;
import com.camellia.soorty.HomeScreen.models.HomeScreenData;
import com.camellia.soorty.models.VerifyOTPMainModel;
import com.camellia.soorty.models.VerifyOtpUserModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("Authorization:XQKCI9492W9dx43UFyg9qkucT9Wktki0SF7HH1hi98c=")
    @GET("/api/sendOtp")
    Call<VerifyOTPMainModel> receivedOTP();


    @FormUrlEncoded
    @Headers("Authorization:XQKCI9492W9dx43UFyg9qkucT9Wktki0SF7HH1hi98c=")
    @POST("/api/register")
    Call<VerifyOtpUserModel> registerUser(@Field("firstname")String firstName,
                                          @Field("lastname") String lastName,@Field("email") String email,
                                          @Field("mobile") String mobile,
                                          @Field("device_type")String device_type,@Field("password") String password);



    @FormUrlEncoded
    @Headers("Authorization:XQKCI9492W9dx43UFyg9qkucT9Wktki0SF7HH1hi98c=")
    @POST("/api/login")
    Call<HomePageData> loginuser(@Field("email")String emailId,
                                          @Field("password") String password);


    @GET("/api/home")
    Call<HomeScreenData> gethomescreendata(@Header("Authorization") String authkey, @Header("access_token") String access_token,
                                           @Query("user_id") String userId);
    @FormUrlEncoded
    @Headers("Authorization:XQKCI9492W9dx43UFyg9qkucT9Wktki0SF7HH1hi98c=")
    @POST("/api//updateProfile")
    Call<VerifyOtpUserModel> updateProfile(@Header("access_token") String access_token,
                                           @Part("first_name") String firstname, @Part("last_name") String lastname, @Part("gender") String gender,
                                           @Part MultipartBody.Part image,@Part("user_id") Integer userId);

    Observable<GetOtpApiResponse> getOtp(
            @Field("mobile_number") String mobile,
            @Field("country_code") String countryCode
                         /* @Field("firstname") String firstname,
                           @Field("lastname") String lastname,
                           @Field("email") String email,
                             @Field("mobile") String mobile,
                              @Field("password") String countryCode,
                              @Field("device_type") String devicetype*/
    );

    @POST("login/verify/")
    @FormUrlEncoded
    Observable<GetOtpApiResponse> verifyOtp(
            @Field("mobile_number") String phoneNo,
            @Field("otp") String otp,
            @Field("role") String role,
            @Field("fcm_token") String token,
            @Field("device_type") String mobile,
            @Field("country_code") String countryCode
    );

    @GET("api/subCategory/")
    Call<HomeScreenData> getSubcategorydata(@Header("Authorization") String authkey,@Header("access_token") String access_token,
    @Query("user_id") Integer userID);


    @GET("/api/shippingAddress")
    Call<AddressModel> getAddressList(@Header("Authorization") String authkey, @Header("access_token") String access_token,
                                      @Query("user_id") String userId);


    /*  @POST("cud/update_user")
    @FormUrlEncoded
    Observable<GetOtpApiResponse> saveExtraDetails(
            @Field("user_id") int userId,
            @Field("name") String userName,
            @Field("email") String userEmail);

    @GET("cud/get_organization")
    Observable<DashBoardApiResponse> getOrgDashBoard(
            @Query("user_id") int userId);

    @GET("cud/get_locations")
    Observable<LocationListApiResponse> getOrgLocations(
            @Query("user_id") int userId);


    @POST("cud/add_deals")
    Observable<GeneralApiResponse> addDeals(
            @Body MultipartBody dealDetailsModel);

    @GET("cud/get_deals")
    Observable<GeneralApiResponse> getDeals(
            @Query("user_id") int userId);

    @GET("https://maps.googleapis.com/maps/api/place/details/json?")
    Observable<Result> getPlaceInfo(
            @Query("placeid") String address,
            @Query("key") String APIkey);

    @POST("cud/add_locations")
    Observable<ApiResponseAddLocation> addLocation(
            @Body LocationModel locationModel
    );

    @GET("cud/get_nearest_location")
    Observable<CustomerHomeApiResponse> getCustomerHomeData(
            @Query("user_id") int userId,
            @Query("latitude") String lat,
            @Query("longitude") String lng);


    @GET("cud/get_location_detail")
    Observable<ApiResponseLocationDeatils> getLocationsDetails(
            @Query("user_id") String userId,
            @Query("location_id") String locationId);




    @GET("cud/get_deals")
    Observable<GeneralApiResponse> getLocationDeals(
            @Query("user_id") int userId,
            @Query("location_id") String locId
    );

    @GET("cud/get_review")
    Observable<ApiResponseReviews> getLocationReviews(
            @Query("user_id") int userId,
            @Query("location_id") String locId,
            @Query("perpage") int perpage,
            @Query("page") int page
    );

    @POST("cud/add_reviews")
    Observable<ApiResponseAddReview> addReview(
            @Body MultipartBody reviewModel);

    @GET("cud/get_review")
    Observable<ApiResponseReviews> getOrgReviews(
            @Query("order_by") String orderBy,
            @Query("user_id") int userId);

    @GET("cud/get_review")
    Observable<ApiResponseReviews> getCustomerReviews(
            @Query("order_by") String orderBy,
            @Query("user_id") int userId,
            @Query("customer") String customer);

    @POST("cud/update_profile")
    Observable<GetOtpApiResponse> updateProfile(
            @Body MultipartBody profileModel);

    @GET("cud/get_points")
    Observable<WalletApiResponse> getTransactions(
            @Query("user_id") int userId,
            @Query("key") String history,
            @Query("month") int month,
            @Query("year") int year);

    @GET("cud/get_points")
    Observable<WalletApiResponse> getPoints(
            @Query("user_id") int userId);

    @POST("cud/buy_deal")
    @FormUrlEncoded
    Observable<BuyDealApiResponse> buyDeal(
            @Field("user_id") int userId,
            @Field("method") String method,
            @Field("deal_id") String dealIds,
            @Field("count") String userEmail);

    @GET("cud/get_review")
    Flowable<ApiResponseReviews> getLocationReviewsWithPagination(
            @Query("user_id") int userId,
            @Query("location_id") String locId,
            @Query("order_by") String orderBy,
            @Query("perpage") int perpage,
            @Query("page") int page
    );

    @POST("cud/review_action")
    @FormUrlEncoded
    Observable<BuyDealApiResponse> addReviewAction(
            @Field("user_id") int userId,
            @Field("review_id") int review_id,
            @Field("review") int review
    );

    @GET("cud/customer_dashboard")
    Observable<CustDashBoardApiResponse> getCustomerDashBoard(
            @Query("user_id") int userId
    );

    @GET("cud/location_visits")
    Flowable<ApiResponseLocationVisit> getVisitHistory(
            @Query("user_id") int userId,
            @Query("perpage") int perpage,
            @Query("page") int page
    );

    @GET("cud/get_review")
    Flowable<ApiResponseReviews> getLocationReviewsofCustomerWithPagination(
            @Query("user_id") int userId,
            @Query("location_id") String locId,
            @Query("perpage") int perpage,
            @Query("page") int page,
            @Query("customer") String customer
    );

    @GET("cud/order_history")
    Flowable<ApiResponseOrderHistory> getOrderHistory(
            @Query("user_id") int userId,
            @Query("perpage") int visible_threshold,
            @Query("page") int page);

    @POST("cud/location_visit")
    @FormUrlEncoded
    Observable<BuyDealApiResponse> visitLocation(
            @Field("user_id") int userId,
            @Field("location_id") String locationId);

    @GET("cud/get_category")
    Observable<ApiResponseCategories> getCategory(
            @Query("user_id") int userId
    );

    @POST("cud/update_deal")
    @FormUrlEncoded
    Observable<BuyDealApiResponse> changeStatusOfDeal(
            @Field("user_id") int userId,
            @Field("deal_id") String deal_id,
            @Field("status") String status
    );

    @POST("cud/update_deal")
    @FormUrlEncoded
    Observable<GeneralApiResponse> updateDeals(
            @Field("user_id") int userId,
            @Field("deal_id") String deal_id,
            @Field("name") String name,
            @Field("description") String description,
            @Field("start_date") String start_date,
            @Field("end_date") String end_date,
            @Field("redeem_points") String redeem_points,
            @Field("discount") String discount,
            @Field("price") String price
    );

    @GET("cud/get_location_exist")
    Observable<ApiResponseValidatePlaceId> validatePlace(
            @Query("user_id") int userId,
            @Query("place_id") String placeId);

    @GET("cud/get_questions")
    Observable<ApiResponseSurveyQuestion> getSurveyQuestions(
            @Query("user_id") int userId);

    @POST("cud/servay_feedback")
    @FormUrlEncoded
    Observable<BuyDealApiResponse> submitSurveyData(
            @Field("user_id") int userId,
            @Field("question_id") String qIds,
            @Field("option") String qRatings,
            @Field("location_id") String locId);

    @GET("cud/get_servay_results")
    Observable<ApiResponseSurveyQuestion> getSurveyResult(
            @Query("user_id") int userId,
            @Query("location_id") String locId);

    @GET("cud/get_redeem_deals")
    Observable<ApiResponseOrderHistory> redeemOrder(
            @Query("user_id") int userId,
            @Query("order_id") String orderId);

//********************Get google places Api Response***********************************

    @GET("https://maps.googleapis.com/maps/api/place/details/json?")
    Call<Result> getLocationDetails(@Query("placeid") String address, @Query("key") String APIkey);*/


}
