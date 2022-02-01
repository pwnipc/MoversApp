package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryActivity extends AppCompatActivity {

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




        int KitchenCostInt = Integer.parseInt(KitchenCost);
        int BedroomCostInt = Integer.parseInt(BedroomCost);
        int LivingRoomCostInt = Integer.parseInt(LivingRoomCost);
        total = KitchenCostInt +  BedroomCostInt +LivingRoomCostInt + CompanyCost;
//        mTotalcostValue.setText("Ksh"+Integer.toString(total));
    }
}