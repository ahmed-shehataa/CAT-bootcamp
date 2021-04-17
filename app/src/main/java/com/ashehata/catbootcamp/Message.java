package com.ashehata.catbootcamp;

public class Message {
    private String Content;
    private String deviceModel;
    private String deviceManufacturer;
    private long time;
    private String color;

    public Message() {
    }

    public Message(String content, String deviceModel, String deviceManufacturer, long time, String color) {
        Content = content;
        this.deviceModel = deviceModel;
        this.deviceManufacturer = deviceManufacturer;
        this.time = time;
        this.color = color;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }
}
