package com.ninositsolution.javalib;

import java.util.Scanner;

public class TestClass {

    public static void main(String[] args) {

        System.out.println("Please enter a name");

        String name;
        Scanner scanner = new Scanner(System.in);

        name = scanner.nextLine();

        boolean validName = checkName(name);

        if (validName)
            splitName(name);
        else
            System.out.println("Please enter the name in odd no. of characters");

    }

    private static void splitName(String name) {

        String startName = name.substring(0, (name.length() / 2));
        String middleName = name.substring((name.length() / 2), (name.length() / 2) + 1);
        String lastName = reverseString(name.substring((name.length() / 2) + 1));

        displayPattern1(startName);
        System.out.println(getBlankSpaces(startName.length()) + middleName);
        displayPattern2(lastName);
    }

    private static void displayPattern2(String lastName) {

        String[] patternLines = new String[lastName.length()];

        for (int i = lastName.length() - 1; i > 0; i--) {
            String space = getBlankSpaces(i);
            patternLines[i] = space + lastName.substring(i, i + 1) + getSpaces(i, lastName.length()) + lastName.substring(i, i + 1);
        }

        printLines(reverseArray(patternLines, lastName.length()));
    }

    private static void displayPattern1(String startName) {

        String[] patternLines = new String[startName.length()];
        String space = "";

        for (int i = 0; i < startName.length(); i++) {
            patternLines[i] = space + startName.substring(i, i + 1) + getSpaces(i, startName.length()) + startName.substring(i, i + 1);
            space = space + " ";
        }

        printLines(patternLines);
    }

    private static void printLines(String[] patternLines) {

        for (int i = 0; i < patternLines.length; i++) {
            System.out.println(patternLines[i]);
        }
    }

    private static String getSpaces(int position, int length) {

        int spaces = ((2 * length) - (2 * position));

        String space = "";

        for (int i = 0; i < spaces; i++) {
            space = space + " ";
        }

        return space;

    }

    private static String getBlankSpaces(int length) {
        String space = "";

        for (int i = 0; i < length; i++) {
            space = space + " ";
        }

        return space;
    }

    private static boolean checkName(String name) {

        if (name.length() % 2 == 0)
            return false;
        else
            return true;
    }

    private static String reverseString(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        return builder.reverse().toString();
    }

    private static String[] reverseArray(String a[], int n) {
        String[] b = new String[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

        return b;
    }

}