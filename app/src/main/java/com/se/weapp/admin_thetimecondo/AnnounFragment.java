package com.se.weapp.admin_thetimecondo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


//vvvvvvvvvvvvvvvvvvvvvvvvvvvvแปลง activity เป็น fragment vvvvvvvvvvvvvvvvvvvvvvvvvvvv
public class AnnounFragment extends Fragment  {
    @Nullable

    public DatabaseReference myRef;      //เกี่ยวกับ firebase
    public Storage stRef;
    private TextView firebase_idroom;

    //---------Fragment เราต้องทำการ inflate หน้า layout ของมันเองนะ โดยที่คำสั่งมันตรงตัวเลยเห็นมะ คำสั่ง View โดยการที่เราต้องสร้าง LayoutInflater ขึ้นมา
    // เพื่อเรียกคำสั่ง Inflate ที่อยู่ข้างในมัน จากนั้นเราก็โยน layout เข้าไปโดยการอ้างอิงผ่าน R.layout. ชื่อ layoutของเราจากนั้นโยน ViewGroup เข้าไปตามด้วย คำสั่ง false
    // เพื่อไม่ให้ Fragment ตัวไหนเข้ามาจัดการหน้านี้ได้ให้มันจัดการเฉพาะ Fragment ของตัวมันเอง เสร็จแล้วเราก็ Return View มันกลับไป--------------------------
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_announ,container,false);

        ImageView image = (ImageView)v.findViewById(R.id.profile_image);
        ImageView add_announ = (ImageView) v.findViewById(R.id.add_announ_image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileFragment fragmentpro = new ProfileFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragmentpro);
                transaction.commit();
            }
        });

        //-----------------   คลิกปุ่มเพิ่ม announ แล้วมี dialog ----------------------------
            add_announ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDialog();
            }

            private void openDialog() {
                AddAnnoun addDialog = new AddAnnoun();
                addDialog.show(getFragmentManager(),"example dialog");
            }
        });


        firebase_idroom = v.findViewById(R.id.test);
//Get firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


//รับค่าเลขห้องจากดาต้าเบส
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String value = String.valueOf(map.get("id_room"));
                firebase_idroom.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        return v;
    }
}


