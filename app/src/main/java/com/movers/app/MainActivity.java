package com.movers.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Initialize Variables
   @BindView(R.id.buttonOpenDialog)
    Button mButtonOpenDialog;
   private String dialogResult;
   private String dateDialogResult;
  

   // Dropdown Menu Items
    String[] inventoryList = {"Bedsitter", "One Bedroom", "Studio", "Two Bedroom", "Other"};
    ArrayAdapter<String> adapterInventoryList;


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
            showBookingDialog();
        }

        }

    private void showBookingDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.AppBottomSheetDialogTheme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setWhiteNavigationBar(bottomSheetDialog);
        }
        bottomSheetDialog.setContentView(R.layout.choose_inventory_bottom_sheet_dialog);
        bottomSheetDialog.setCanceledOnTouchOutside(false);





        // initialize and Assign Variables
        AutoCompleteTextView mInventorySize = bottomSheetDialog.findViewById(R.id.autoCompleteTextViewInventorySize);
        AutoCompleteTextView mDateAndTime = bottomSheetDialog.findViewById(R.id.autoCompleteTextViewDate);
        Button mButtonBook = bottomSheetDialog.findViewById(R.id.buttonBook);
        mDateAndTime.setInputType(InputType.TYPE_NULL);



        adapterInventoryList = new ArrayAdapter<String>(this,R.layout.inventory_drop_down_item,inventoryList);
        mInventorySize.setAdapter(adapterInventoryList);

        mInventorySize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedInventory = adapterView.getItemAtPosition(i).toString();
                dialogResult = selectedInventory;




            }
        });

        mButtonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //openSummary method
                if (dialogResult == null){
                    mInventorySize.setError("Please Select Inventory Size");
                } else if(dateDialogResult== null ) {

                    mDateAndTime.setError("You have not selected date and time");


                }else {
                    openSummaryDialog();
                }
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

    private void openSummaryDialog () {

        // Open Summary Activity Dialog

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.AppBottomSheetDialogTheme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setWhiteNavigationBar(bottomSheetDialog);
        }
        bottomSheetDialog.setContentView(R.layout.confirm_order_dialog);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();

       TextView mTextViewSelectedInventory  = bottomSheetDialog.findViewById(R.id.textViewSelectedInventory);
       TextView mTextViewPrice = bottomSheetDialog.findViewById(R.id.textViewPrice);
       TextView mTextViewDate = bottomSheetDialog.findViewById(R.id.textViewDate);

       mTextViewSelectedInventory.setText(dialogResult);
       mTextViewDate.setText(dateDialogResult);


       if (dialogResult == "One Bedroom"){

           mTextViewPrice.setText("Kes 5,500");

       }else if (dialogResult == "Studio"){

           mTextViewPrice.setText("Kes 7,500");

       }else if (dialogResult == "Two Bedroom"){

           mTextViewPrice.setText("Kes 8,500");

       }else if (dialogResult == "Other"){

           mTextViewPrice.setText("Kes 9,500");

       }





    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setWhiteNavigationBar(BottomSheetDialog bottomSheetDialog) {

        Window window = bottomSheetDialog.getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            GradientDrawable dimDrawable = new GradientDrawable();
            // ...customize  dim effect

            GradientDrawable navigationBarDrawable = new GradientDrawable();
            navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
            navigationBarDrawable.setColor(Color.WHITE);

            Drawable[] layers = {dimDrawable, navigationBarDrawable};

            LayerDrawable windowBackground = new LayerDrawable(layers);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                windowBackground.setLayerInsetTop(1, metrics.heightPixels);
            }

            window.setBackgroundDrawable(windowBackground);
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

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm a");
                        dateDialogResult = simpleDateFormat.format(calendar.getTime());




                    }
                };

                new TimePickerDialog(MainActivity.this,onTimeSetListener,calendar.get(calendar.HOUR_OF_DAY),calendar.get(calendar.MINUTE),false).show();

            }
        };

        new DatePickerDialog(MainActivity.this,datePickerDialog,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
    }
}