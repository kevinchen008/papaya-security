package com.papaya.core.properties;

public class ImageCodeProperties {
    private int width = 80;
    private int height= 40;
    private int length = 4;
    private int expried = 60;
    private String urls;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpried() {
        return expried;
    }

    public void setExpried(int expried) {
        this.expried = expried;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
