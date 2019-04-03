package com.ninositsolution.inveleapp.api;

import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.add_mobile.pojo.MobileOTPRequest;
import com.ninositsolution.inveleapp.add_mobile.pojo.VerifyOTPRequest;
import com.ninositsolution.inveleapp.address_book.pojo.AddressBookRequest;
import com.ninositsolution.inveleapp.address_book.pojo.AddressUpdateRequest;
import com.ninositsolution.inveleapp.change_email.pojo.EmailOTPRequest;
import com.ninositsolution.inveleapp.change_email.pojo.VerifyemailOTPRequest;
import com.ninositsolution.inveleapp.change_password.pojo.ChangePassowrdRequest;
import com.ninositsolution.inveleapp.edit_address.pojo.EditAddressRequest;
import com.ninositsolution.inveleapp.fitme.pojo.FitmeRequest;
import com.ninositsolution.inveleapp.forgot_password.pojo.OTPRequest;
import com.ninositsolution.inveleapp.forgot_password.pojo.ResetPasswordRequest;
import com.ninositsolution.inveleapp.login.LoginRequest;
import com.ninositsolution.inveleapp.pojo.CategoryPojoClass;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.registration.pojo.RegistartionRequest;
import com.ninositsolution.inveleapp.search_everywhere.SearchEverywhereRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @POST("users/mobile_register")
    Observable<POJOClass> registerApi(@Body RegistartionRequest registartionRequest);

    //address book
    @POST("users/address/add")
    Observable<POJOClass> addAddressApi(@Body AddAddressRequest addAddressRequest);

    @POST("users/address/update")
    Observable<POJOClass> UpdateAddressApi(@Body EditAddressRequest editAddressRequest);

    @POST("postal-code-search")
    Observable<POJOClass> locateAddressApi(@Body AddAddressRequest addAddressRequest);

    @POST("users/addresses")
    Observable<POJOClass>addressList(@Body AddressBookRequest addressBookRequest);

    @POST("users/address")
    Observable<POJOClass>showAddress(@Body AddressUpdateRequest addressUpdateRequest);

    @POST("users/address/default")
    Observable<POJOClass> defaultAddressUpdate(@Body AddressUpdateRequest addressUpdateRequest);

    @POST("users/address/delete")
    Observable<POJOClass> addressDelete(@Body AddressUpdateRequest addressUpdateRequest);

    //categories
    @GET("mobile/categories")
    Observable<CategoryPojoClass>Categories();

    @GET("fitme/details")
    Observable<POJOClass> getFitMeDetails();

    @POST("fitme/add")
    Observable<POJOClass> addfitmeApi (@Body FitmeRequest fitmeRequest);

    @FormUrlEncoded
    @POST("users/forgot_password")
    Observable<POJOClass> forgotPasswordApi (@Field("forgot_name")String forgotName);

    @POST("users/otp_verify")
    Observable<POJOClass> otpVerifyApi (@Body OTPRequest otpRequest);

    @POST("users/reset_password")
    Observable<POJOClass> resetPasswordApi (@Body ResetPasswordRequest resetPasswordRequest);

    @POST("users/password/update")
    Observable<POJOClass> updatePasswordApi (@Body ChangePassowrdRequest changePassowrdRequest);

    @Multipart
    @POST("users/profile_update")
    Observable<POJOClass> profileUpdateApi (
                                            @Part("user_id") Integer user_id,
                                            @Part("first_name") String first_name,
                                            @Part("last_name") String last_name,
                                            @Part("mobile") String mobile,
                                            @Part("email") String email,
                                            @Part("gender") String gender,
                                            @Part("dob") String dob
                                           // @Part MultipartBody.Part body
    );

    @FormUrlEncoded
    @POST("users/profile")
    Observable<POJOClass> getProfileDetailsApi(@Field("user_id") String user_id);

    @POST("users/login")
    Observable<POJOClass> loginApi(@Body LoginRequest loginRequest);

    @POST("user/profile_change")
    Observable<POJOClass> mobileChangeApi (@Body MobileOTPRequest mobileOTPRequest);

    @POST("users/otp_profile_verify")
    Observable<POJOClass> verifyOtpMobileApi (@Body VerifyOTPRequest verifyOTPRequest);

    @POST("user/profile_change")
    Observable<POJOClass> emailChangeAPi (@Body EmailOTPRequest emailOTPRequest);

    @POST("users/otp_profile_verify")
    Observable<POJOClass> verifyOtpEmailApi(@Body VerifyemailOTPRequest verifyemailOTPRequest);

    @FormUrlEncoded
    @POST("home_page")
    Observable<POJOClass> homePageApi(@Field("user_id") String user_id);

    // Search Everywhere Api
    @POST("products/search")
    Observable<POJOClass> searchEverywhereApi(@Body SearchEverywhereRequest request);
}