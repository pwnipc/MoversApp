package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class KitchenActivity extends AppCompatActivity {
    Button submitKitchen;
    CheckBox refrigerator, cooker, dishwasher, microwave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        submitKitchen = (Button) findViewById(R.id.submitKitchenButton);
        addListenerOnButtonClick();

    }
    public void addListenerOnButtonClick(){
        refrigerator = (CheckBox) findViewById(R.id.refrigeratorCheckBox);
        cooker = (CheckBox) findViewById(R.id.cookerCheckBox);
        dishwasher = (CheckBox) findViewById(R.id.dishWasherCheckBox);
        microwave = findViewById(R.id.microwaveCheckBox);

        submitKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalCost =0;
                StringBuilder result=new StringBuilder();
                result.append("Selected Items:");

                if(refrigerator.isChecked()){
                    result.append("Refrigerator : 1000");
                    totalCost += 1000;
                }if(cooker.isChecked()){
                    result.append("Cooker : 500");
                    totalCost += 500;
                }if(dishwasher.isChecked()){
                    result.append("Dishwasher : 500");
                    totalCost += 500;
                }if (microwave.isChecked()){
                    result.append("Microwave : 500");
                    totalCost += 500;
                } if(!refrigerator.isChecked() && !cooker.isChecked() && !dishwasher.isChecked() && !microwave.isChecked()){
                    Toast.makeText(KitchenActivity.this, "Nothing added from the Kitchen" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(KitchenActivity.this, Inventory.class);
                    String activity = "KitchenRoom";
                    intent.putExtra("KitchenRoomCost",String.valueOf(totalCost));
                    intent.putExtra("KitchenActivity",activity);
                    startActivity(intent);
                }else{
                    Toast.makeText(KitchenActivity.this, "Kitchen inventory added successfully" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(KitchenActivity.this, Inventory.class);
                    String activity = "KitchenRoom";
                    intent.putExtra("KitchenRoomCost",String.valueOf(totalCost));
                    intent.putExtra("KitchenActivity",activity);
                    startActivity(intent);
                }


            }
        });
    }

}