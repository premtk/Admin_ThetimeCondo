package com.se.weapp.admin_thetimecondo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class AddAnnoun extends AppCompatDialogFragment {
    private DatabaseReference myRef;
    //private FirebaseDatabase fdatabase;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        myRef = FirebaseDatabase.getInstance().getReference("Announcement");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.add_announ,null);
        final EditText detail = (EditText) v.findViewById(R.id.detail);
        final EditText title = (EditText) v.findViewById(R.id.title);
        builder.setView(v)
                .setTitle("เพิ่มประกาศ")
                .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("บันทึก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String getTitle = title.getText().toString();
                        String getDetail = detail.getText().toString();
                        ItemAnnoun item = new ItemAnnoun();
                        item.setTitle(getTitle);
                        item.setDetail(getDetail);
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                        Date date = new Date();
                        String datetime = dateFormat.format(date).toString();
                        myRef.child(datetime).setValue(item);
                        Toast.makeText(getContext(),"Announcement added.",Toast.LENGTH_SHORT).show();

                    }
                });
        return builder.create();
    }
}
