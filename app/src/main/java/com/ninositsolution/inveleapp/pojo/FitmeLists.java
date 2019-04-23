package com.ninositsolution.inveleapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FitmeLists {

    @SerializedName("user_measurement_id")
    private Integer measureId;

    @SerializedName("user_default")
    private Integer userDefault;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("measurement")
    private String measurement;

    @SerializedName("label")
    private String label;

    @SerializedName("value")
    private String value;

    @SerializedName("measurement_details")
    private List<FitmeLists> measureDetails;

    public Integer getMeasureId() {
        return measureId;
    }

    public Integer getUserDefault() {
        return userDefault;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public List<FitmeLists> getMeasureDetails() {
        return measureDetails;
    }

    public String getMeasurement() {
        return measurement;
    }
}
