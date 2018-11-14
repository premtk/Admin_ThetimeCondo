package com.se.weapp.admin_thetimecondo;

import android.util.Log;

public class Room {
    String id;
    String Room_Numroom;
    String Room_Ownername;
    String Room_Ownertel;
    String Room_Ownermail;
    String Room_Rentname;
    String Room_Renttel;
    String Room_Rentmail;
    String Room_Nummotorcycle;
    String Room_Nummotorcycletwo;
    String Room_Numcar;
    String Room_Numcartwo;

    public Room(){

    }

    public Room(String id,String room_Numroom, String room_Ownername, String room_Ownertel, String room_Ownermail, String room_Rentname, String room_Renttel, String room_Rentmail, String room_Nummotorcycle, String room_Nummotorcycletwo, String room_Numcar, String room_Numcartwo) {
        this.id = id;
        this.Room_Numroom = room_Numroom;
        this.Room_Ownername = room_Ownername;
        this.Room_Ownertel = room_Ownertel;
        this.Room_Ownermail = room_Ownermail;
        this.Room_Rentname = room_Rentname;
        this.Room_Renttel = room_Renttel;
        this.Room_Rentmail = room_Rentmail;
        this.Room_Nummotorcycle = room_Nummotorcycle;
        this.Room_Nummotorcycletwo = room_Nummotorcycletwo;
        this.Room_Numcar = room_Numcar;
        this.Room_Numcartwo = room_Numcartwo;
        Log.d("wrong","3");
    }

    public String getId() {
        return id;
    }

    public String getRoom_Numroom() {
        return Room_Numroom;
    }

    public String getRoom_Ownername() {
        return Room_Ownername;
    }

    public String getRoom_Ownertel() {
        return Room_Ownertel;
    }

    public String getRoom_Ownermail() {
        return Room_Ownermail;
    }

    public String getRoom_Rentname() {
        return Room_Rentname;
    }

    public String getRoom_Renttel() {
        return Room_Renttel;
    }

    public String getRoom_Rentmail() {
        return Room_Rentmail;
    }

    public String getRoom_Nummotorcycle() {
        return Room_Nummotorcycle;
    }

    public String getRoom_Nummotorcycletwo() {
        return Room_Nummotorcycletwo;
    }

    public String getRoom_Numcar() {
        return Room_Numcar;
    }

    public String getRoom_Numcartwo() {
        return Room_Numcartwo;
    }
}

