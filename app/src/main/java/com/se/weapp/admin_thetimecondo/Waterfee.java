package com.se.weapp.admin_thetimecondo;

class Waterfee {
    private String id;
    private String Waterfee_editText_numroom;
    private String Waterfee_editText_lastUnit;
    private String Waterfee_editText_latestUnit;


    public Waterfee(){

    }

    public Waterfee(String id, String waterfee_editText_numroom, String waterfee_editText_lastUnit, String waterfee_editText_latestUnit) {
        this.id = id;
        this.Waterfee_editText_numroom = waterfee_editText_numroom;
        this.Waterfee_editText_lastUnit = waterfee_editText_lastUnit;
        this.Waterfee_editText_latestUnit = waterfee_editText_latestUnit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWaterfee_editText_numroom() {
        return Waterfee_editText_numroom;
    }

    public void setWaterfee_editText_numroom(String waterfee_editText_numroom) {
        Waterfee_editText_numroom = waterfee_editText_numroom;
    }

    public String getWaterfee_editText_lastUnit() {
        return Waterfee_editText_lastUnit;
    }

    public void setWaterfee_editText_lastUnit(String waterfee_editText_lastUnit) {
        Waterfee_editText_lastUnit = waterfee_editText_lastUnit;
    }

    public String getWaterfee_editText_latestUnit() {
        return Waterfee_editText_latestUnit;
    }

    public void setWaterfee_editText_latestUnit(String waterfee_editText_latestUnit) {
        Waterfee_editText_latestUnit = waterfee_editText_latestUnit;
    }
}
