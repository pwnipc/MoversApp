package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryActivity extends AppCompatActivity  {

    String KitchenCost, BedroomCost, LivingRoomCost;
    int total;
    int CompanyCost = 4000;
    @BindView(R.id.totalCostValue)
    TextView mTotalcostValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        ButterKnife.bind(this);

    }

//    @Override
//    public void sendData(int data) {
//         mTotalcostValue.setText(data);
//    }
}