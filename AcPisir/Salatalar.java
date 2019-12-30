package com.iremipek.AcPisir;

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

import java.util.ArrayList;

public class Salatalar extends Fragment {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Veri> list;
    CardAdapter adapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.salatalar,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.mysalatalar);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        list = new ArrayList<Veri>();

        reference = FirebaseDatabase.getInstance().getReference().child("Salatalar");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshotA: dataSnapshot.getChildren())
                {
                    Veri p = dataSnapshotA.getValue(Veri.class);
                    list.add(p);
                }
                adapter = new CardAdapter(getContext(),list);
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
