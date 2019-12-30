package com.iremipek.AcPisir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


//Anasayfa fragmentine Firebseden veri çekmek için kullandığımız adapter.

public class AcPisirAdapter extends RecyclerView.Adapter<AcPisirAdapter.MyViewHolder> {

    Context context;
    ArrayList<Veri> profiles;

    public AcPisirAdapter(Context c, ArrayList<Veri> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.isim.setText(profiles.get(position).getIsim());
        Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);

        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {                               //cardviewlere tıklanıldığında Tabmenu1 activity açar.
                Intent intent = new Intent(context, TabMenu1.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView isim;
        ImageView profilePic;
        CardView cardView;

        public MyViewHolder(final View itemView){
            super(itemView);
            isim = (TextView)itemView.findViewById(R.id.isim);
            profilePic = (ImageView)itemView.findViewById(R.id.profilePic);
            cardView = (CardView) itemView.findViewById(R.id.CardView);

        }


    }

}
