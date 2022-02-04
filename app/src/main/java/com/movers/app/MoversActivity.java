package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoversActivity extends AppCompatActivity {
    private RecyclerView moversRecView;


    ArrayList<Mover> movers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movers);
        ButterKnife.bind(this);
        movers.add(new Mover("Alpha Movers Kenya","Home Relocation, International Relocation"));
        movers.add(new Mover("Access Movers", "Home Relocation"));
        movers.add(new Mover("Prestige Movers", "International Relocation"));
        movers.add(new Mover("Amazon Movers", "Air Relocation"));
        movers.add(new Mover("bolt Movers", "Local Relocation made possible"));
        movers.add(new Mover("Swivl Movers", "Bulk Relocation"));
        movers.add(new Mover("Kibana Movers", "Studio Relocation"));


        moversRecView = findViewById(R.id.moversRecView);


        MoversRecyclerAdapter adapter = new MoversRecyclerAdapter();
        adapter.setContacts(movers);
        moversRecView.setAdapter(adapter);
        moversRecView.setLayoutManager(new LinearLayoutManager(this));


    }
}