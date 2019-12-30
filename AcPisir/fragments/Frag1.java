package com.iremipek.AcPisir.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iremipek.AcPisir.R;
import com.iremipek.AcPisir.Veri;
import com.iremipek.AcPisir.ui.TarifAdapter;

import java.util.ArrayList;

public class Frag1 extends Fragment {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Veri> list;
    TarifAdapter adapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    public Frag1() {
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.Frag1Recycler);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        list = new ArrayList<Veri>();

        reference = FirebaseDatabase.getInstance().getReference().child("Tarif");                   //Firebaseden Tarif childını çeker
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();                                                                       //uygulama her açıldığında aynı verileri sürekli çekmemesi için liste silinir.

                Veri p = dataSnapshot.child("1").getValue(Veri.class);                              //Tarif childının "1" isminde ki childını çeker
                list.add(p);

                adapter = new TarifAdapter(getContext(),list);
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
