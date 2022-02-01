package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Inventory extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.imageViewKitchen)
    ImageView mImageViewKitchen;
    @BindView(R.id.imageViewLivingRoom)
    ImageView mImageViewLivingRoom;
    @BindView(R.id.imageViewBedroom)
    ImageView mImageViewBedroom;
    @BindView(R.id.buttonCheckInventory)
    Button inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);
        mImageViewBedroom.setOnClickListener(this);
        mImageViewKitchen.setOnClickListener(this);
        mImageViewLivingRoom.setOnClickListener(this);

//        Intent intent = getIntent();
//        int kitchenCost = Integer.parseInt(intent.getStringExtra("KitchenCost"));
//        int BedroomCost = Integer.parseInt(intent.getStringExtra("BedroomCost"));
//        int LivingRoomCost = Integer.parseInt(intent.getStringExtra("LivingRoomCost"));

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
        if(v == inventory){
            //To summary Activity
            Toast.makeText(Inventory.this,"Still in Progress",Toast.LENGTH_LONG).show();
        }
    }

    
}