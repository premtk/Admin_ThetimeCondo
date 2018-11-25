package com.se.weapp.admin_thetimecondo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.PrintStream;

public class ProfileFragment extends Fragment {
    EditText numroom; EditText ownername; EditText ownertel; EditText ownermail; EditText rentname; EditText renttel; EditText rentmail;
    EditText nummotorcycle; EditText nummotorcycletwo; EditText numcar; EditText numcartwo; EditText password;
    Button confirmProfileBt;
    RadioButton BuildingA; RadioButton BuildingB;

    public DatabaseReference databaseRooms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);

        databaseRooms = FirebaseDatabase.getInstance().getReference("Users");


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
        password = (EditText)v.findViewById(R.id.pass);
        confirmProfileBt =(Button)v.findViewById(R.id.confirmProfileBt);
        BuildingA = (RadioButton) v.findViewById(R.id.buildingA);
        BuildingB = (RadioButton) v.findViewById(R.id.buildingB);

        confirmProfileBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addRoom();
                //openAnnouncement();
            }
        });

        return v;
    }
    public void openAnnouncement(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AnnounFragment llf = new AnnounFragment();
        ft.replace(R.id.fragment, llf);
        ft.commit();
    }
    public void addRoom(){
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
        String text_password = password.getText().toString().trim();

        if(BuildingB.isChecked()||BuildingA.isChecked()){
            if(!TextUtils.isEmpty(text_numroom)&& !TextUtils.isEmpty(text_ownername) && !TextUtils.isEmpty(text_ownertel) && !TextUtils.isEmpty(text_ownermail)&&!TextUtils.isEmpty(text_password)){
                String id;
                if(BuildingA.isChecked()){
                    id = databaseRooms.push().getKey();
                    Room room = new Room(id, text_numroom,text_ownername,text_ownertel,text_ownermail,text_rentname,text_renttel,text_rentmail,text_nummotorcycle,text_nummotorcycletwo,text_numcar,text_numcartwo,text_password);
                    databaseRooms.child("Building A").child(text_numroom).setValue(room);
                }
                else if(BuildingB.isChecked()){
                    id = databaseRooms.push().getKey();
                    Room room = new Room(id, text_numroom,text_ownername,text_ownertel,text_ownermail,text_rentname,text_renttel,text_rentmail,text_nummotorcycle,text_nummotorcycletwo,text_numcar,text_numcartwo,text_password);
                    databaseRooms.child("Building B").child(text_numroom).setValue(room);
                }
                Toast.makeText(getActivity(),"เพิ่มห้องเรียบร้อย",Toast.LENGTH_LONG).show();
                openAnnouncement();
            }
            else{
                if(!BuildingA.isChecked()&&!BuildingB.isChecked()){
                    Toast.makeText(getActivity(),"กรุณาเลือกอาคาร",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_numroom)){
                    Toast.makeText(getActivity(),"กรุณากรอกเลขห้อง",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_ownername)){
                    Toast.makeText(getActivity(),"กรุณากรอกชื่อเจ้าของห้อง",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_ownertel)){
                    Toast.makeText(getActivity(),"กรุณากรอกเบอร์เจ้าของห้อง",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_ownermail)){
                    Toast.makeText(getActivity(),"กรุณากรอกอีเมลเจ้าของห้อง",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_password)){
                    Toast.makeText(getActivity(),"กรุณากรอกรหัสผ่าน",Toast.LENGTH_LONG).show();
                }
            }
        }
        else{
            if(!BuildingA.isChecked()&&!BuildingB.isChecked()){
                Toast.makeText(getActivity(),"กรุณาเลือกอาคาร",Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(text_numroom)){
                Toast.makeText(getActivity(),"กรุณากรอกเลขห้อง",Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(text_ownername)){
                Toast.makeText(getActivity(),"กรุณากรอกชื่อเจ้าของห้อง",Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(text_ownertel)){
                Toast.makeText(getActivity(),"กรุณากรอกเบอร์เจ้าของห้อง",Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(text_ownermail)){
                Toast.makeText(getActivity(),"กรุณากรอกอีเมลเจ้าของห้อง",Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(text_password)){
                Toast.makeText(getActivity(),"กรุณากรอกรหัสผ่าน",Toast.LENGTH_LONG).show();
            }
        }

    }
}

