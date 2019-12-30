package com.iremipek.AcPisir;

import android.content.Context;;
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

//anasayfa hariç diğer kategoriler için oluşturulan adapter.

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyHolderView> {

    Context context;
    ArrayList<Veri> profiles;

    public CardAdapter(Context c, ArrayList<Veri> p){
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolderView(LayoutInflater.from(context).inflate(R.layout.listcard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
        holder.isim2.setText(profiles.get(position).getIsim());
        Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic2);

        holder.card_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                 //Tıklanıldığında yeni bir activity başlatır.
                Intent intent = new Intent(context, TabMenu1.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        TextView isim2;
        ImageView profilePic2;
        CardView card_View;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            isim2 = (TextView)itemView.findViewById(R.id.isim);
            profilePic2 = (ImageView)itemView.findViewById(R.id.profilePic);
            card_View = (CardView) itemView.findViewById(R.id.listCard);
        }
    }
}
