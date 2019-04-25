package com.ninositsolution.javalib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TestClass {

    public static void main(String[] args) {

        HashMap<Integer, String> hashMap = new HashMap<>();

        hashMap.put(1,"Partha");
        hashMap.put(2,"bobby");
        hashMap.put(3,"sarathy");
        hashMap.put(4,"arun");

        for (int i=0; i<hashMap.size(); i++)
        {
            Model model = new Model();

         //   model.setLabel_id(hashMap.get());
        }

        System.out.println(hashMap);
    }

    public static class Model
    {
        private String label_id, value;

        public String getLabel_id() {
            return label_id;
        }

        public void setLabel_id(String label_id) {
            this.label_id = label_id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
