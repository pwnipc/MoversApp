package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MoversActivity extends AppCompatActivity {
    private RecyclerView moversRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movers);
        moversRecyclerView = findViewById(R.id.moversRecyclerView);
    }
}