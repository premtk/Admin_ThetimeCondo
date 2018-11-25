package com.se.weapp.admin_thetimecondo;

public class ItemAnnoun {
    String title,detail;

    public ItemAnnoun(){ }

    public ItemAnnoun(String title, String detail) {            //create Constructor
        this.title = title;
        this.detail = detail;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }


}
