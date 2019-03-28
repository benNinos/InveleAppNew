package com.ninositsolution.javalib;

import java.util.ArrayList;

public class TestClass {

    //public ArrayList<Model> modelArrayList;

    public static void main(String[] args) {

        ArrayList<Model> modelArrayList = new ArrayList<>();

        for (int i = 0; i<10; i++)
        {
            Model model = new Model("first"+i, "last"+i);

            modelArrayList.add(model);
        }

       /* for (int i =0; i<modelArrayList.size(); i++)
        {
            System.out.println(modelArrayList.get(i).first_name);
            System.out.println(modelArrayList.get(i).last_name);
            System.out.println();
            System.out.println();
        }*/

       modelArrayList.remove(0);

       System.out.println(modelArrayList.get(0).first_name);
    }

    public static class Model{

        public String first_name;
        public String last_name;

        public Model(String first_name, String last_name) {
            this.first_name = first_name;
            this.last_name = last_name;
        }
    }
}
