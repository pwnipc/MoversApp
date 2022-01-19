package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Initialize Variables
   @BindView(R.id.buttonOpenDialog)
    Button mButtonOpenDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign Variables to respective Id Addresses
        ButterKnife.bind(this);
        mButtonOpenDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == mButtonOpenDialog){

            //create new BottomSheetDialog
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
            bottomSheetDialog.setContentView(R.layout.choose_inventory_bottom_sheet_dialog);

        }

    }
}