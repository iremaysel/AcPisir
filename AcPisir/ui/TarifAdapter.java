package com.iremipek.AcPisir.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iremipek.AcPisir.R;
import com.iremipek.AcPisir.Veri;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Tabmenü için oluşturulan adapter.

public class TarifAdapter extends RecyclerView.Adapter<TarifAdapter.MyHolder> {

    Context context;
    ArrayList<Veri> profiles;

    public TarifAdapter(Context c, ArrayList<Veri> p)
    {
        this.context = c;
        this.profiles = p;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TarifAdapter.MyHolder(LayoutInflater.from(context).inflate(R.layout.tarif,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.isim.setText(profiles.get(position).getIsim());
        Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView isim;
        ImageView profilePic;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            isim = (TextView)itemView.findViewById(R.id.isim    );
            profilePic = (ImageView)itemView.findViewById(R.id.profilePic);
        }
    }
}
