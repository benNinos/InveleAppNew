package com.ninositsolution.javalib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {

        ArrayList<Integer> brand_ids = new ArrayList<>();

        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray(brand_ids);

        jsonObject.put("array", brand_ids);
        jsonObject.put("json_array", jsonArray);

        System.out.println(jsonObject);


    }
}
