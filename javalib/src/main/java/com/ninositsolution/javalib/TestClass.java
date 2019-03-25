package com.ninositsolution.javalib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestClass {

    public static void main(String[] args) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date endDate;
        Date startDate = Calendar.getInstance().getTime();
        Date date;

        String futureDate = "2019-05-08 00:00:00";

        try {
            endDate = format.parse(futureDate);
            long millsEnd = endDate.getTime();
            long mills = startDate.getTime();

            long diff = (millsEnd - mills)/1000;

            System.out.println(diff);

        } catch (ParseException e) {
            e.printStackTrace();
        }




    }
}
