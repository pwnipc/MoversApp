package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Inventory extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.imageViewKitchen)
    ImageView mImageViewKitchen;
    @BindView(R.id.imageViewLivingRoom)
    ImageView mImageViewLivingRoom;
    @BindView(R.id.imageViewBedroom)
    ImageView mImageViewBedroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);
        mImageViewBedroom.setOnClickListener(this);
        mImageViewKitchen.setOnClickListener(this);
        mImageViewLivingRoom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == mImageViewKitchen){
            Intent intent = new Intent(Inventory.this,KitchenActivity.class);
            startActivity(intent);
        }

        if (v == mImageViewBedroom){
            Intent intent = new Intent(Inventory.this,BedroomActivity.class);
            startActivity(intent);
        }
        if (v == mImageViewLivingRoom){
            Intent intent = new Intent(Inventory.this,LivingRoomActivity.class);
            startActivity(intent);
        }
    }
}