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


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Veri> list;
    TarifAdapter adapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    public Frag2() {
        // Required empty public constructor
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.Frag2Recycler);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        list = new ArrayList<Veri>();

        reference = FirebaseDatabase.getInstance().getReference().child("Tarif");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                Veri p = dataSnapshot.child("2").getValue(Veri.class);
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
