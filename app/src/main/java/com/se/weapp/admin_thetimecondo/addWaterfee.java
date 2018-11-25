package com.se.weapp.admin_thetimecondo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addWaterfee extends Fragment{

    public DatabaseReference databaseWaterfee;

    RadioButton BuildingA; RadioButton BuildingB;
    EditText editText_numroom;EditText editText_lastUnit;EditText editText_latestUnit;
    Button confirmAddWaterfeeBt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_waterfee,container,false);
        ImageView image = (ImageView)v.findViewById(R.id.search);
        BuildingA = (RadioButton) v.findViewById(R.id.buildingA);
        BuildingB = (RadioButton) v.findViewById(R.id.buildingB);
        editText_numroom = (EditText)v.findViewById(R.id.editText_numroom);
        editText_lastUnit = (EditText)v.findViewById(R.id.editText_lastUnit);
        editText_latestUnit = (EditText)v.findViewById(R.id.editText_latestUnit);
        confirmAddWaterfeeBt = (Button)v.findViewById(R.id.confirmAddWaterfeeBt);

        databaseWaterfee = FirebaseDatabase.getInstance().getReference("Waterfee");

        confirmAddWaterfeeBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addWaterfee();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalFragment total = new TotalFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, total);
                transaction.commit();
            }
        });
        return v;
    }

    public void addWaterfee(){
        String text_editText_numroom = editText_numroom.getText().toString().trim();
        String text_editText_lastUnit = editText_lastUnit.getText().toString().trim();
        String text_editText_latestUnit = editText_latestUnit.getText().toString().trim();

        if(BuildingB.isChecked()||BuildingA.isChecked()){
            if(!TextUtils.isEmpty(text_editText_numroom)&& !TextUtils.isEmpty(text_editText_lastUnit)&& !TextUtils.isEmpty(text_editText_latestUnit)){
                String id;
                if(BuildingA.isChecked()){
                    id = databaseWaterfee.push().getKey();
                    Waterfee waterfee = new Waterfee(id,text_editText_numroom,text_editText_lastUnit,text_editText_lastUnit);
                    databaseWaterfee.child("Building A").child(text_editText_numroom).setValue(waterfee);
                }
                else if(BuildingB.isChecked()){
                    id = databaseWaterfee.push().getKey();
                    Waterfee waterfee = new Waterfee(id,text_editText_numroom,text_editText_lastUnit,text_editText_lastUnit);
                    databaseWaterfee.child("Building A").child(text_editText_numroom).setValue(waterfee);
                }
                Toast.makeText(getActivity(),"เพิ่มค่าน้ำเรียบร้อย",Toast.LENGTH_LONG).show();

            }
        }
    }
}
