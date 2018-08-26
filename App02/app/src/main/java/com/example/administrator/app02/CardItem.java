package com.example.administrator.app02;


public class CardItem {
    private String state;
    private String setting;

    public CardItem(String state, String setting) {
        this.state = state;
        this.setting = setting;
    }

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

//
//public class CardItem {
//    private String contents;
//
//    public CardItem(String contents) {
//        this.contents = contents;
//    }
//
//    public String getContents() {
//        return contents;
//    }
//
//    public void setContents(String contents) {
//        this.contents = contents;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer("CardItem{");
//        sb.append("contents='").append(contents).append('\'');
//        sb.append('}');
//        return sb.toString();
//    }
//}