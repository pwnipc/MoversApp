package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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

    String KitchenCost, BedroomCost, LivingRoomCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);
        mImageViewBedroom.setOnClickListener(this);
        mImageViewKitchen.setOnClickListener(this);
        mImageViewLivingRoom.setOnClickListener(this);
        inventory.setOnClickListener(this);


        Intent intent = getIntent();

        if(intent.getStringExtra("livingActivity") != null){
            if(intent.getStringExtra("livingActivity").equals("LivingRoom")){
                LivingRoomCost = intent.getStringExtra("LivingRoomCost");
            }
        }

        if(intent.getStringExtra("KitchenActivity") != null){
            if(intent.getStringExtra("KitchenActivity").equals("KitchenRoom")){
                KitchenCost = intent.getStringExtra("KitchenRoomCost");
            }
        }
        if(intent.getStringExtra("BedroomActivity") != null){
            if(intent.getStringExtra("BedroomActivity").equals("Bedroom")){
                BedroomCost = intent.getStringExtra("BedroomCost");
            }
        }


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
            if(LivingRoomCost != null && BedroomCost != null && KitchenCost != null){
                //To the summary activity
                Toast.makeText(Inventory.this,"Still in Progress",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(Inventory.this,"Please select inventory",Toast.LENGTH_LONG).show();
            }

        }
    }

}