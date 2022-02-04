package com.movers.app;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MoversRecyclerAdapter extends RecyclerView.Adapter<MoversRecyclerAdapter.ViewHolder> {

    private ArrayList<Mover> movers = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movers_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(movers.get(position).getName());
        holder.txtName2.setText(movers.get(position).getServices());

    }

    @Override
    public int getItemCount() {
        return movers.size();
    }

    public void setContacts(ArrayList<Mover> contacts) {
        this.movers = contacts;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtName2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);

            txtName2 = itemView.findViewById(R.id.txtName2);
        }
    }



}







