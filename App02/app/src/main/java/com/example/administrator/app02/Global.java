package com.example.administrator.app02;

// 설정한 약1, 2번값 저장용 전역변수
public class Global {
    private static String data1 = "";
    private static String data2 = "";

    public static void setData1(String data1) {
        Global.data1 = data1;
    }

    public static void setData2(String data2) {
        Global.data2 = data2;
    }

    public static String getData1() {

        return data1;
    }

    public static String getData2() {
        return data2;
    }
}
