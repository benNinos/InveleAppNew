package com.ninositsolution.inveleapp.fitme.pojo;

public class FitmeRequest {

    private String user_id;
    private String name;
    private String measurement;
    private String gender;
    private String fitme_details;


    public FitmeRequest(String user_id, String name, String measurement, String gender, String fitme_details) {
        this.user_id = user_id;
        this.name = name;
        this.measurement = measurement;
        this.gender = gender;
        this.fitme_details = fitme_details;
    }
}
