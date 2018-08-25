package com.example.administrator.app02;

public class CardItem {
    private String contents;

    public CardItem(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CardItem{");
        sb.append("contents='").append(contents).append('\'');
        sb.append('}');
        return sb.toString();
    }
}