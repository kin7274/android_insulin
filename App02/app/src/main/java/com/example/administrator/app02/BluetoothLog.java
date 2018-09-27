package com.example.administrator.app02;

public class BluetoothLog {
    private static String name = "";
    private static String address = "";
    private static String abc = "";

    public static void setName(String name) {
        BluetoothLog.name = name;
    }

    public static void setAddress(String address) {
        BluetoothLog.address = address;
    }

    public static void setAbc(String abc) {
        BluetoothLog.abc = abc;
    }

    public static String getName() {

        return name;
    }

    public static String getAddress() {
        return address;
    }

    public static String getAbc() {
        return abc;
    }
}