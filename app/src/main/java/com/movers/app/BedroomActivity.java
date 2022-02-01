package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class BedroomActivity extends AppCompatActivity {
    Button submitBedroom;
    CheckBox bed, drawer, babyCot, chair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedroom);
        submitBedroom = findViewById(R.id.submitBedroomButton);
        addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick(){
        bed = (CheckBox) findViewById(R.id.bedCheckBox);
        drawer = (CheckBox) findViewById(R.id.drawerCheckBox);
        babyCot = (CheckBox) findViewById(R.id.babyCotCheckBox);
        chair = findViewById(R.id.chairCheckBox);

        submitBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalCost =0;
                StringBuilder result=new StringBuilder();
                result.append("Selected Items:");

                if(bed.isChecked()){
                    result.append("bed : 1000");
                    totalCost += 1000;
                }else if(drawer.isChecked()){
                    result.append("drawer : 500");
                    totalCost += 500;
                }else if(babyCot.isChecked()){
                    result.append("Baby Cot : 500");
                    totalCost += 500;
                }else if (chair.isChecked()){
                    result.append("Microwave : 500");
                    totalCost += 500;
                }else if(!bed.isChecked() && !drawer.isChecked() && !babyCot.isChecked() && !chair.isChecked()){
                    Toast.makeText(BedroomActivity.this,"Please select an item to move",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(BedroomActivity.this, "Inventory added successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BedroomActivity.this, Inventory.class);
                intent.putExtra("BedroomCost",totalCost);
                startActivity(intent);

            }
        });
    }
}