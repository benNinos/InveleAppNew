package com.ninositsolution.inveleapp.utils;

import android.content.Context;
import android.content.Intent;

import com.ninositsolution.inveleapp.pojo.HomeArrayLists;
import com.ninositsolution.inveleapp.pojo.POJOClass;
import com.ninositsolution.inveleapp.pojo.Users;

public final class Constants {

    public static final Integer SUCCESS = 0;

    public static final Integer EMAIL_EMPTY = 1;
    public static final Integer EMAIL_NAME_EMPTY = 2;
    public static final Integer PASSWORD_EMPTY = 3;
    public static final Integer MOBILE_NO_EMPTY = 4;
    public static final Integer MOBILE_NAME_EMPTY = 5;
    public static final Integer NO_EMAIL_PATTERN = 6;
    public static final Integer OTP_EMPTY = 7;
    public static final Integer CONFIRM_PASSWORD_EMPTY = 8;

    public static final Integer NAME_EMPTY = 9;
    public static final Integer CONTACT_NUMBER_EMPTY = 10;
    public static final int POSTAL_CODE =11;
    public static final Integer ADDRESS = 12;
    public static final Integer ADDRESS1 = 13;
    public static final Integer CITY = 14;
    public static final Integer ADDRESS_TYPE = 15;
    public static final Integer EMAIL_VALID = 16;
    public static final Integer EMAIL_INVALID = 17;
    public static final Integer INVALID_OTP_LENGTH = 18;
    public static final Integer PASSWORD_MATCH = 1018;
    public static final Integer PASSWORD_MISMATCH = 19;
    public static final Integer CONFIRM_NEW_PASSWORD_EMPTY = 20;
    public static final Integer NEW_PASSWORD_EMPTY = 21;
    public static final Integer DATE_OF_BIRTH_EMPTY = 22;



    public static  String user_address_id="";
    public static String select_menu_id = "";
    public static String select_banner ="";
    public static String category_position="";
    public static String all_category_clicked="false";


    public static final String CURRENCY = "$";
    public static final String TYPE_MOBILE = "mobile";
    public static final String TYPE_EMAIL = "email";

    public static final int SEARCH_EVERYWHERE_PRODUCTS = 23;
    public static final int SEARCH_EVERYWHERE_CATEGORIES = 24;
    public static final int SEARCH_EVERYWHERE_BRANDS = 25;
    public static final int SEARCH_EVERYWHERE_LOCATIONS = 26;
    public static final int SEARCH_EVERYWHERE_ATTRIBUTES = 27;
    public static final int SEARCH_EVERYWHERE_ATTRIBUTES_CHILD = 28;
    public static final int SEARCH_EVERYWHERE_FITME_SIZE = 29;


    public static final Integer FILTER_SHOW_LESS = 101;
    public static final Integer FILTER_SHOW_MORE = 102;

    public static final int SEARCH_EVERYWHERE_SHIPPING = 103;
    public static final Integer FITME_MEN = 104;
    public static final Integer FITME_WOMEN = 105;

    public static void setSession(Users user, Context context)
    {
        if (user.id != null)
        {
            String value = user.id.toString();
            Session.setUserId(value, context);
        }

        if (user.first_name != null)
        {
            String value = user.first_name;
            Session.setUserFirstName(value, context);
        }

        if (user.last_name != null)
        {
            String value = user.last_name;
            Session.setUserLastName(value, context);
        }
        if (user.email != null)
        {
            String value = user.email;
            Session.setUserEmail(value, context);
        }

        if (user.mobile != null)
        {
            String value = user.mobile;
            Session.setUserPhone(value, context);
        }

        if (user.dob != null)
        {
            String value = user.dob;
            Session.setUserDob(value, context);
        }

        if (user.image != null)
        {
            String value = user.image;
            Session.setUserPhoto(value, context);
        }

        if (user.gender != null)
        {
            String value = user.gender;
            Session.setUserGender(value, context);
        }
    }


}

