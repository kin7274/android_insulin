package com.example.administrator.app02;


import android.graphics.drawable.Drawable;

public class CardItem {
//    private int image;
    private String state;
    private String setting;

//    public CardItem(int image, String state, String setting) {
    public CardItem(String state, String setting) {
//        this.image = image;
        this.state = state;
        this.setting = setting;
    }
//
//    public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }

    public String getState() {
        return state;
    }
}