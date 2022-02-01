package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class LivingRoomActivity extends AppCompatActivity {
    Button submitLivingRoom; 
    CheckBox tv, sofa, table, woofer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);
        submitLivingRoom = (Button) findViewById(R.id.submitLivingRoomButton);
        addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick(){
        tv = (CheckBox) findViewById(R.id.tvCheckBox);
        sofa = (CheckBox) findViewById(R.id.sofaCheckBox);
        table = (CheckBox) findViewById(R.id.tableCheckBox);
        woofer = (CheckBox) findViewById(R.id.wooferCheckBox);

        submitLivingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int totalCost = 0;
                StringBuilder result=new StringBuilder();
                result.append("Selected Items:");

                if(tv.isChecked()){
                    result.append("Television : 1000");
                    totalCost += 1000;
                }if(sofa.isChecked()){
                    result.append("Sofa : 1000");
                    totalCost += 1000;
                }if(table.isChecked()){
                    result.append("Table : 1000");
                    totalCost += 1000;
                }if (woofer.isChecked()){
                    result.append("Woofer : 500");
                    totalCost += 500;
                }
                if(!tv.isChecked() && !sofa.isChecked() && !table.isChecked() && !woofer.isChecked()){
                    Toast.makeText(LivingRoomActivity.this, "Nothing added from the Livingroom" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LivingRoomActivity.this, Inventory.class);
                    String activity = "LivingRoom";
                    intent.putExtra("LivingRoomCost",String.valueOf(totalCost));
                    intent.putExtra("livingActivity",activity);
                    startActivity(intent);
                }else{
                    Toast.makeText(LivingRoomActivity.this, "Living room inventory added successfully" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LivingRoomActivity.this, Inventory.class);
                    String activity = "LivingRoom";
                    intent.putExtra("LivingRoomCost",String.valueOf(totalCost));
                    intent.putExtra("livingActivity",activity);
                    startActivity(intent);
                }



            }
        });
    }
}