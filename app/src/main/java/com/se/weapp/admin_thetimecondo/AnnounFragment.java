package com.se.weapp.admin_thetimecondo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//vvvvvvvvvvvvvvvvvvvvvvvvvvvvแปลง activity เป็น fragment vvvvvvvvvvvvvvvvvvvvvvvvvvvv
public class AnnounFragment extends Fragment  {
    @Nullable
    ProgressDialog progress;
    public DatabaseReference myRef;      //เกี่ยวกับ firebase
    public Storage stRef;
    private Adapter_Announ announAdapter;
    private TextView firebase_idroom;
    private ListView mListView;
    private CustomAdapter mAdapter;
    //---------Fragment เราต้องทำการ inflate หน้า layout ของมันเองนะ โดยที่คำสั่งมันตรงตัวเลยเห็นมะ คำสั่ง View โดยการที่เราต้องสร้าง LayoutInflater ขึ้นมา
    // เพื่อเรียกคำสั่ง Inflate ที่อยู่ข้างในมัน จากนั้นเราก็โยน layout เข้าไปโดยการอ้างอิงผ่าน R.layout. ชื่อ layoutของเราจากนั้นโยน ViewGroup เข้าไปตามด้วย คำสั่ง false
    // เพื่อไม่ให้ Fragment ตัวไหนเข้ามาจัดการหน้านี้ได้ให้มันจัดการเฉพาะ Fragment ของตัวมันเอง เสร็จแล้วเราก็ Return View มันกลับไป--------------------------
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_announ,container,false);
        // อันนี้ของกันเอง ///////////////////////
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();
        mListView = (ListView) v.findViewById(R.id.listview); // new list view var.
        myRef = FirebaseDatabase.getInstance().getReference("Announcement");
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) { /// longclick listenner
                final ItemAnnoun selectedItem = (ItemAnnoun) adapterView.getItemAtPosition(i);
                myRef.addListenerForSingleValueEvent(new ValueEventListener() { /// DB single time listener
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()){ // query and compare var from DB
                            ItemAnnoun item = new ItemAnnoun();
                            item = postSnapshot.getValue(ItemAnnoun.class);
                            if(item.getTitle() == selectedItem.getTitle() && item.getDetail() == selectedItem.getDetail()){ //data is match
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); /// build edit dialog
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                View v = inflater.inflate(R.layout.add_announ,null);
                                final EditText detail = (EditText) v.findViewById(R.id.detail);
                                final EditText title = (EditText) v.findViewById(R.id.title);
                                detail.setText(item.getDetail());// set value on dialog
                                title.setText(item.getTitle());
                                builder.setView(v)
                                        .setTitle("แก้ประกาศ")
                                        .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                dialog.dismiss();
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
                                                myRef.child(postSnapshot.getKey()).setValue(item);
                                                Toast.makeText(getContext(),"Announcement changed.",Toast.LENGTH_SHORT).show();

                                            }
                                        }).setNeutralButton("ลบ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create(); // dialog in dialog for delete confirmation
                                        alertDialog.setTitle("Delete Announcement");
                                        alertDialog.setMessage("Are you sure to delete??");
                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Toast.makeText(getContext(),"Announcement has deleted.",Toast.LENGTH_SHORT).show();
                                                        myRef.child(postSnapshot.getKey()).setValue(null);
                                                    }
                                                });
                                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                        alertDialog.show();
                                    }
                                });
                                builder.show();

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                //Toast.makeText(getContext(),selectedItem.getTitle(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        showData(); // get data from firebase , make an adapter and set it up. ///
        //////// ของกันจบแล้ว //////////////////////////////////////////////
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


//        firebase_idroom = v.findViewById(R.id.test);
////Get firebase database reference
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        myRef = database.getReference();
//
//
////รับค่าเลขห้องจากดาต้าเบส
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Map map = (Map)dataSnapshot.getValue();
//                String value = String.valueOf(map.get("id_room"));
//                firebase_idroom.setText(value);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });
        return v;
    }
    private void showData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot == null){
                    Toast.makeText(getContext(),"!!!!No any Announcement",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    List<ItemAnnoun> posts = new ArrayList<>();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) { // loop out all of child value and put it in list//
                        ItemAnnoun item = new ItemAnnoun();
                        item = postSnapshot.getValue(ItemAnnoun.class);
                        posts.add(item);
                    }
                    mAdapter = new CustomAdapter(getActivity(), posts);
                    mListView.setAdapter(mAdapter);
                    progress.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


