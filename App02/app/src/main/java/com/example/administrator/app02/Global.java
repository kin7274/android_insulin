package com.example.administrator.app02;

public class Global {
    private static String data = "";

    public static void setData(String data) {
        Global.data = data;
    }

    public static String getData() {
        return data;
    }
}
