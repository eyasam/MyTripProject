package com.example.android.mytripproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Country_RecyclerViewAdapter extends RecyclerView.Adapter<Country_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<CountryModel> countryModels;

    public Country_RecyclerViewAdapter(Context context, ArrayList<CountryModel> countryModels) {
        this.context = context;
        this.countryModels = countryModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //etheya li chtaamllna l inflate layout yaani tgd lise en page w taati apparence ll rows (giving a look to each one of our rows)
        //giving the layout for kol row
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_recycler_country,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //taati les valeurs l kol rows based on the position of the recyclerview==process de liaison
        //change data based on recyclerviewposition
        holder.contryname.setText(countryModels.get(position).getCountryname());
        holder.img.setImageResource(countryModels.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ProgramActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        //the number of items li yaarfhom recyclerview au total
        return countryModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView contryname,contrydesc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            contryname=itemView.findViewById(R.id.textView);



        }
    }
}


