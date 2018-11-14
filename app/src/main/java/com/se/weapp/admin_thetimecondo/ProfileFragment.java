package com.se.weapp.admin_thetimecondo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.PrintStream;

public class ProfileFragment extends Fragment {
    EditText numroom; EditText ownername; EditText ownertel; EditText ownermail; EditText rentname; EditText renttel; EditText rentmail;
    EditText nummotorcycle; EditText nummotorcycletwo; EditText numcar; EditText numcartwo;
    Button confirmProfileBt;

    public DatabaseReference databaseRooms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);

        databaseRooms = FirebaseDatabase.getInstance().getReference("Building A");

        numroom = (EditText)v.findViewById(R.id.numroom);
        ownername = (EditText)v.findViewById(R.id.ownername);
        ownertel = (EditText)v.findViewById(R.id.ownertel);
        ownermail = (EditText)v.findViewById(R.id.ownermail);
        rentname = (EditText)v.findViewById(R.id.rentname);
        renttel = (EditText)v.findViewById(R.id.renttel);
        rentmail = (EditText)v.findViewById(R.id.rentmail);
        nummotorcycle = (EditText)v.findViewById(R.id.nummotorcycle);
        nummotorcycletwo = (EditText)v.findViewById(R.id.nummotorcycletwo);
        numcar = (EditText)v.findViewById(R.id.numcar);
        numcartwo = (EditText)v.findViewById(R.id.numcartwo);
        confirmProfileBt =(Button)v.findViewById(R.id.confirmProfileBt);

        confirmProfileBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addRoom();
            }
        });

        return v;
    }
    private void addRoom(){
        String text_numroom = numroom.getText().toString().trim();
        String text_ownername = ownername.getText().toString();
        String text_ownertel = ownertel.getText().toString().trim();
        String text_ownermail = ownermail.getText().toString().trim();
        String text_rentname = rentname.getText().toString().trim();
        String text_renttel = renttel.getText().toString().trim();
        String text_rentmail = rentmail.getText().toString().trim();
        String text_nummotorcycle = nummotorcycle.getText().toString().trim();
        String text_nummotorcycletwo = nummotorcycletwo.getText().toString().trim();
        String text_numcar = numcar.getText().toString().trim();
        String text_numcartwo = numcartwo.getText().toString().trim();

        if(!TextUtils.isEmpty(text_numroom)){
            String id = databaseRooms.push().getKey();
            Log.d("id1",id.toString());
            Room room = new Room(id, text_numroom,text_ownername,text_ownertel,text_ownermail,text_rentname,text_renttel,text_rentmail,text_nummotorcycle,text_nummotorcycletwo,text_numcar,text_numcartwo);
            databaseRooms.child(id).setValue(room);
            Log.d("wrong","5");
//            Toast.makeText(this,"Room Added",Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("wrong","1");
            System.out.print("Hello! I am an alert box!");
//          Toast.makeText(this,"กรุณากรอกเลขห้อง",Toast.LENGTH_LONG).show();
        }
    }
}

