package com.movers.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{
    private NotificationManagerCompat NotificationManagerCompat;
    private GoogleMap mMap;
    EditText search;
    String place;
    EditText mSearchValue;
    List<Address> addressList;
    private static String dialogResult;
    private static String dateDialogResult;
    private final int kilometers = 20;
    private final  int rate = 30;
    private int subPrice = (rate * kilometers);
    private String finalPrice;
    Dialog dialog;
    private static String mDestination;
    private ImageButton logoutBtn;



    // Dropdown Menu Items
    String[] inventoryList = {"Bedsitter", "One Bedroom", "Studio", "Two Bedroom", "Other"};
    ArrayAdapter<String> adapterInventoryList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);

        NotificationManagerCompat = NotificationManagerCompat.from(this);


        search = (EditText) findViewById(R.id.EditTextSearchValue);
        logoutBtn = findViewById(R.id.logoutBtn);


        // get address from previous activity
        place = getIntent().getStringExtra("place");
        search.setText(place);
        search.setFocusable(false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, BookingActivity.class);
                intent.putExtra("searchValue", search.getText().toString());
                startActivity(intent);
                overridePendingTransition(0, 0);



            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
                startActivity(intent);




            }
        });

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext());
            addressList = geocoder.getFromLocationName(place, 5);
            Address location = addressList.get(0);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(place));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        } catch (Exception  e) {
            e.printStackTrace();
        }

         mSearchValue = (EditText) findViewById(R.id.EditTextSearchValue);
        if (!mSearchValue.getText().toString().isEmpty()){
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            showBookingDialog();
            mDestination = mSearchValue.getText().toString();
        }

    }




    private void showBookingDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MapsActivity.this,R.style.AppBottomSheetDialogTheme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setWhiteNavigationBar(bottomSheetDialog);
        }
        bottomSheetDialog.setContentView(R.layout.choose_inventory_bottom_sheet_dialog);
        bottomSheetDialog.setCanceledOnTouchOutside(false);





        // initialize and Assign Variables
        AutoCompleteTextView mInventorySize = bottomSheetDialog.findViewById(R.id.autoCompleteTextViewInventorySize);
        AutoCompleteTextView mDateAndTime = bottomSheetDialog.findViewById(R.id.autoCompleteTextViewDate);
        Button mButtonBook = bottomSheetDialog.findViewById(R.id.ConfirmButton);
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
                    startActivity(new Intent(MapsActivity.this,Inventory.class));
                    bottomSheetDialog.dismiss();
                }
            }
        });

        mDateAndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(mDateAndTime);
            }
        });

        bottomSheetDialog.show();
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

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy    hh:mm aaa");
                        dateDialogResult = simpleDateFormat.format(calendar.getTime());


                    }
                };

                new TimePickerDialog(MapsActivity.this,onTimeSetListener,calendar.get(calendar.HOUR_OF_DAY),calendar.get(calendar.MINUTE),false).show();

            }
        };

        new DatePickerDialog(MapsActivity.this,datePickerDialog,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



   public  static String getDestination(){
        return mDestination;
    }

    public  static String getDate(){
        return dateDialogResult;
    }

    public  static String getInventory(){
        return dialogResult;
    }

}