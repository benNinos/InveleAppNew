package com.ninositsolution.inveleapp.api;

import com.ninositsolution.inveleapp.address_book.pojo.AddressBookRequest;
import com.ninositsolution.inveleapp.address_book.pojo.AddressUpdateRequest;
import com.ninositsolution.inveleapp.change_password.pojo.ChangePassowrdRequest;
import com.ninositsolution.inveleapp.edit_address.pojo.EditAddressRequest;
import com.ninositsolution.inveleapp.forgot_password.pojo.OTPRequest;
import com.ninositsolution.inveleapp.forgot_password.pojo.ResetPasswordRequest;
import com.ninositsolution.inveleapp.login.LoginRequest;
import com.ninositsolution.inveleapp.add_address.pojo.AddAddressRequest;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.registration.pojo.RegistartionRequest;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    Observable<POJOClass>Categories();

    @FormUrlEncoded
    @POST("users/forgot_password")
    Observable<POJOClass> forgotPasswordApi (@Field("forgot_name")String forgotName);

    @POST("users/otp_verify")

    Observable<POJOClass> otpVerifyApi (@Body OTPRequest otpRequest);

    @POST("users/reset_password")
    Observable<POJOClass> resetPasswordApi (@Body ResetPasswordRequest resetPasswordRequest);

    @POST("users/password/update")

    Observable<POJOClass> updatePasswordApi (@Body ChangePassowrdRequest changePassowrdRequest);



    @POST("users/login")
    Observable<POJOClass> loginApi(@Body LoginRequest loginRequest);

  /*  @FormUrlEncoded
    @POST("Users/mobile_register")
    Observable<RegistrationPOJO> registerApi(
            @Field("first_name") String first_name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("password") String password,
            @Field("login_type") String login_type,
            @Field("uid") String uid,
            @Field("device_id") String device_id,
            @Field("device_type") String device_type
    );*/
}
