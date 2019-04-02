package com.ninositsolution.inveleapp.fitme.pojo;

public class FitmeRequest {

    private String user_id;
    private String name;
    private String measurement;
    private String gender;


    public FitmeRequest(String user_id, String name, String measurement, String gender) {
        this.user_id = user_id;
        this.name = name;
        this.measurement = measurement;
        this.gender = gender;
    }
}
