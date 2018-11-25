package com.se.weapp.admin_thetimecondo;

class Parcel {
    private String id;
    private String Parcel_text_num_room;
    private String Parcel_text_num_track;
    private String Parcel_text_nameOrderParcel;
    private String Parcel_Spinner_TypeParcel_spinner;
    private String Parcel_Spinner_Transport_spinner;

    public Parcel() {

    }


    public Parcel(String id, String parcel_text_num_room, String parcel_text_num_track, String parcel_text_nameOrderParcel, String parcel_Spinner_TypeParcel_spinner, String parcel_Spinner_Transport_spinner) {
        this.id = id;
        Parcel_text_num_room = parcel_text_num_room;
        Parcel_text_num_track = parcel_text_num_track;
        Parcel_text_nameOrderParcel = parcel_text_nameOrderParcel;
        Parcel_Spinner_TypeParcel_spinner = parcel_Spinner_TypeParcel_spinner;
        Parcel_Spinner_Transport_spinner = parcel_Spinner_Transport_spinner;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParcel_text_num_room(String parcel_text_num_room) {
        Parcel_text_num_room = parcel_text_num_room;
    }

    public void setParcel_text_num_track(String parcel_text_num_track) {
        Parcel_text_num_track = parcel_text_num_track;
    }

    public void setParcel_text_nameOrderParcel(String parcel_text_nameOrderParcel) {
        Parcel_text_nameOrderParcel = parcel_text_nameOrderParcel;
    }

    public void setParcel_Spinner_TypeParcel_spinner(String parcel_Spinner_TypeParcel_spinner) {
        Parcel_Spinner_TypeParcel_spinner = parcel_Spinner_TypeParcel_spinner;
    }

    public void setParcel_Spinner_Transport_spinner(String parcel_Spinner_Transport_spinner) {
        Parcel_Spinner_Transport_spinner = parcel_Spinner_Transport_spinner;
    }

}