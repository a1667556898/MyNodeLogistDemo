package com.anji.plus.myapplication.customview;

/**
 * Created by SummerChen on 2018/6/8.
 */

public class MyLogistPointBean {
    private String time;//目前时间
    private String state;//当前状态


    public MyLogistPointBean(String time, String state) {
        this.time = time;
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
