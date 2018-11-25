package com.se.weapp.admin_thetimecondo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddParcelFragment extends Fragment {
    private Spinner TypeParcel_spinner;Spinner Transport_spinner;
    private String[] parcel_type_array = {"ห่อ/ซองใหญ","กล่อง"};
    private String[] transport_array = {"ลงทะเบียน","อีเอ็มเอส","เคอรี่"};
    EditText num_room;EditText num_track;EditText nameOrderParcel;
    Button confirmParcelBt;
    RadioButton BuildingA; RadioButton BuildingB;

    public DatabaseReference databaseParcel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_parcel,container,false);

        databaseParcel = FirebaseDatabase.getInstance().getReference("Parcel");


        num_room = (EditText)v.findViewById(R.id.num_room);
        num_track = (EditText)v.findViewById(R.id.num_track);
        nameOrderParcel = (EditText)v.findViewById(R.id.nameOrderParcel);
        Transport_spinner = (Spinner) v.findViewById(R.id.Transport_spinner);
        TypeParcel_spinner = (Spinner) v.findViewById(R.id.TypeParcel_spinner);
        BuildingA = (RadioButton) v.findViewById(R.id.buildingA);
        BuildingB = (RadioButton) v.findViewById(R.id.buildingB);

        confirmParcelBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //addRoom();
                //openAnnouncement();
            }
        });

        ArrayAdapter<CharSequence> mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, parcel_type_array);
        ArrayAdapter<CharSequence> nSortAdapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, transport_array);
        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TypeParcel_spinner.setAdapter(mSortAdapter);
        Transport_spinner.setAdapter(nSortAdapter);
        return v;
    }
    public void addParcel(){
        String text_num_room = num_room.getText().toString().trim();
        String text_num_track = num_track.getText().toString().trim();
        String text_nameOrderParcel = nameOrderParcel.getText().toString().trim();
        String Spinner_Transport_spinner = Transport_spinner.getSelectedItem().toString();
        String Spinner_TypeParcel_spinner = TypeParcel_spinner.getSelectedItem().toString();

        if(BuildingB.isChecked()||BuildingA.isChecked()){
            if(!TextUtils.isEmpty(text_num_room)&& !TextUtils.isEmpty(text_num_track)&& !TextUtils.isEmpty(text_nameOrderParcel)){
                String id;
                if(BuildingA.isChecked()){
                    id = databaseParcel.push().getKey();
                    Parcel parcel = new Parcel(id,text_num_room,text_num_track,text_nameOrderParcel,Spinner_Transport_spinner,Spinner_TypeParcel_spinner);
                    databaseParcel.child("Building A").child(text_num_room).setValue(parcel);
                }
                else if(BuildingB.isChecked()){
                    id = databaseParcel.push().getKey();
                    Parcel parcel = new Parcel(id,text_num_room,text_num_track,text_nameOrderParcel,Spinner_Transport_spinner,Spinner_TypeParcel_spinner);
                    databaseParcel.child("Building B").child(text_num_room).setValue(parcel);
                }
                Toast.makeText(getActivity(),"เพิ่มพัสดุรียบร้อย",Toast.LENGTH_LONG).show();
            }else{
                if(!BuildingA.isChecked()&&!BuildingB.isChecked()){
                    Toast.makeText(getActivity(),"กรุณาเลือกอาคาร",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_num_room)){
                    Toast.makeText(getActivity(),"กรุณากรอกเลขห้อง",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_num_track)){
                    Toast.makeText(getActivity(),"กรุณากรอกรหัสพัสดุ",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(text_nameOrderParcel)){
                    Toast.makeText(getActivity(),"กรุณากรอกชื่อผู้รับ",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(Spinner_Transport_spinner)){
                    Toast.makeText(getActivity(),"กรุณาเลือกบริษัทขนส่ง",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(Spinner_TypeParcel_spinner)){
                    Toast.makeText(getActivity(),"กรุณาเลือกชนิดของพัสดุ",Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
