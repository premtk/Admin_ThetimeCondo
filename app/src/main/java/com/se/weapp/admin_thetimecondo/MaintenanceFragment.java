package com.se.weapp.admin_thetimecondo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MaintenanceFragment extends Fragment {
    CheckBox checkBox_aircon;CheckBox checkBox_stove;CheckBox checkBox_electric;CheckBox checkBox_pipe;CheckBox checkBox_water;CheckBox checkBox_bathroom;CheckBox checkBox_other;
    EditText input_tel;EditText input_other;
    Button confirmMaintenanceBt;

    public DatabaseReference databaseMaintenance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);

        databaseMaintenance = FirebaseDatabase.getInstance().getReference("Building A");

        checkBox_aircon = (CheckBox)v.findViewById(R.id.checkBox_aircon);
        checkBox_stove = (CheckBox)v.findViewById(R.id.checkBox_stove);
        checkBox_electric = (CheckBox)v.findViewById(R.id.checkBox_electric);
        checkBox_pipe = (CheckBox)v.findViewById(R.id.checkBox_pipe);
        checkBox_water = (CheckBox)v.findViewById(R.id.checkBox_water);
        checkBox_bathroom = (CheckBox)v.findViewById(R.id.checkBox_bathroom);
        checkBox_other = (CheckBox)v.findViewById(R.id.checkBox_other);
        input_tel = (EditText)v.findViewById(R.id.input_tel);
        input_other = (EditText)v.findViewById(R.id.input_other);
        confirmMaintenanceBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //addRoom();
                //openAnnouncement();
            }
        });


        return v;
    }
    public void addMaintenance(){
        String id;
        if(checkBox_aircon.isChecked()){
            id = databaseMaintenance.push().getKey();
            MaintenanceCase maintenanceCase = new MaintenanceCase();
            //databaseMaintenance.child(text_numroom).setValue(maintenanceCase);
        }
        if(checkBox_stove.isChecked()){

        }
        if(checkBox_electric.isChecked()){

        }
        if(checkBox_pipe.isChecked()){

        }
        if(checkBox_water.isChecked()){

        }
        if(checkBox_bathroom.isChecked()){

        }
        if(checkBox_other.isChecked()){

        }
    }
}

