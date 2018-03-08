package com.papaya.core.properties;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/3/8 9:47
 * @Version: 1.0
 */
public class SmsCodeProperties {
    private int length = 4;
    private int expried = 60;
    private String urls;

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
