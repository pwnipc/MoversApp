package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
            bottomSheetDialog.setCanceledOnTouchOutside(false);



            // initialize and Assign Variables
            AutoCompleteTextView mInventorySize = bottomSheetDialog.findViewById(R.id.autoCompleteTextViewInventorySize);
            AutoCompleteTextView mDateAndTime = bottomSheetDialog.findViewById(R.id.autoCompleteTextViewDate);
            Button mButtonBook = bottomSheetDialog.findViewById(R.id.buttonBook);
            mDateAndTime.setInputType(InputType.TYPE_NULL);

            mButtonBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open Summary Activity Dialog
                }
            });
            mDateAndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDateDialog(mDateAndTime);
                }
            });


            //show bottomSheetDialog
            bottomSheetDialog.show();

        }

        }


    private void showDateDialog(AutoCompleteTextView mAutoCompleteTextViewDate) {

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(calendar.YEAR,i);
                calendar.set(calendar.MONTH,i1);
                calendar.set(calendar.DAY_OF_MONTH,i2);

                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                        calendar.set(calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                        mAutoCompleteTextViewDate.setText(simpleDateFormat.format(calendar.getTime()));



                    }
                };

                new TimePickerDialog(MainActivity.this,onTimeSetListener,calendar.get(calendar.HOUR_OF_DAY),calendar.get(calendar.MINUTE),false).show();

            }
        };

        new DatePickerDialog(MainActivity.this,datePickerDialog,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
    }
}