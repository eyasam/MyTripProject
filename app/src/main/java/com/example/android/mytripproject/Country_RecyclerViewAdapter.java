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
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_recycler_country,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

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
        return countryModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView contryname;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            contryname=itemView.findViewById(R.id.textView);



        }
    }
}


