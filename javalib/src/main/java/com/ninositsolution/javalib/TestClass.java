package com.ninositsolution.javalib;
import java.util.Scanner;

public class TestClass
{
    public static void main(String[] args)
    {
        int a,result = 0;
        int value = 0;
        Scanner scan;
        System.out.println("Please Enter the number");
        scan = new Scanner(System.in);
        a = scan.nextInt();

        while (result <=a)
       {
           value = value + result;
           result++;
       }
       System.out.println("The result is:" +value);

    }
}