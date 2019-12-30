package com.iremipek.AcPisir;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//Uygulama ilk açıldığında açılan sayfa

public class Anasayfa extends Fragment {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Veri> list;
    AcPisirAdapter adapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    CardView card;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.anasayfa,container,false);     //anasayfayı xml den javaya dönüştürdü
        Log.d("tag","OnCreate started");

        recyclerView = (RecyclerView) view.findViewById(R.id.myanasayfa);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);     //Anasayfanın zigzag görünümünde olmasını sağlıyor.
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        list = new ArrayList<Veri>();

        reference = FirebaseDatabase.getInstance().getReference().child("Anasayfa");                //firebaseden Anasayfa childını çeker.
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshotA: dataSnapshot.getChildren())
                {
                    Veri p = dataSnapshotA.getValue(Veri.class);                                    //oluşturduğumuz datamodel sınıfı
                    list.add(p);
                }
                adapter = new AcPisirAdapter(getContext(),list);                                    //Anasayfa için oluşturulan adapter.
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Opss...", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }


}
