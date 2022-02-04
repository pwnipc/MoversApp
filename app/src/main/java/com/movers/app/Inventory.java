package com.movers.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

    int CompanyCost = 1500;
    int KitchenCost, BedroomCost, LivingRoomCost;
    String total;
    private String mDestination;
    private String subTotal;
    private  String mDate;
    private String mSelectedInventory;
    private NotificationManagerCompat NotificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);
        mImageViewBedroom.setOnClickListener(this);
        mImageViewKitchen.setOnClickListener(this);
        mImageViewLivingRoom.setOnClickListener(this);
        inventory.setOnClickListener(this);
        mDestination =  MapsActivity.getDestination();
        mDate = MapsActivity.getDate();
        mSelectedInventory = MapsActivity.getInventory();
        NotificationManagerCompat = NotificationManagerCompat.from(this);



        Intent intent = getIntent();

        if (intent.getStringExtra("livingActivity") != null) {
            if (intent.getStringExtra("livingActivity").equals("LivingRoom")) {
                LivingRoomCost = Integer.parseInt(intent.getStringExtra("LivingRoomCost"));

            }
        }

        if (intent.getStringExtra("KitchenActivity") != null) {
            if (intent.getStringExtra("KitchenActivity").equals("KitchenRoom")) {
                KitchenCost = Integer.parseInt(intent.getStringExtra("KitchenRoomCost"));
            }
        }
        if (intent.getStringExtra("BedroomActivity") != null) {
            if (intent.getStringExtra("BedroomActivity").equals("Bedroom")) {
                BedroomCost = Integer.parseInt(intent.getStringExtra("BedroomCost"));
            }
        }

               total = Integer.toString(KitchenCost+BedroomCost+LivingRoomCost+CompanyCost);
               subTotal = Integer.toString(KitchenCost+BedroomCost+LivingRoomCost);
    }
    @Override
    public void onClick(View v) {

        if (v == mImageViewKitchen) {
            Intent intent = new Intent(Inventory.this, KitchenActivity.class);
            startActivity(intent);
        }

        if (v == mImageViewBedroom) {
            Intent intent = new Intent(Inventory.this, BedroomActivity.class);
            startActivity(intent);
        }
        if (v == mImageViewLivingRoom) {
            Intent intent = new Intent(Inventory.this, LivingRoomActivity.class);
            startActivity(intent);
        }
        if (v == inventory) {

                showBookingDialog();

        }
    }

    private void pushNotification(){
        Notification notification = new NotificationCompat.Builder(Inventory.this,App.CHANNEL_ID1)
                .setSmallIcon(R.drawable.sticker_check_outline)
                .setContentTitle("Order Placed")
                .setContentText("You just made a new Order. Click to view your Orders!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        NotificationManagerCompat.notify(1,notification);
    }

    private void showBookingDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Inventory.this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.confirm_order_dialog);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setWhiteNavigationBar(bottomSheetDialog);
        }
            TextView mFinalPriceTextView = bottomSheetDialog.findViewById(R.id.finalPriceTextView);
            TextView mTextViewDestination = bottomSheetDialog.findViewById(R.id.textViewDestination);
            TextView mTextViewDate = bottomSheetDialog.findViewById(R.id.textViewDate);
            TextView mTextViewSelectedInventory = bottomSheetDialog.findViewById(R.id.textViewSelectedInventory);
            TextView mTextViewPrice = bottomSheetDialog.findViewById(R.id.textViewPrice);
            TextView mButtonCancelOrder = bottomSheetDialog.findViewById(R.id.ButtonCancelOrder);
            Button mConfirm = bottomSheetDialog.findViewById(R.id.ConfirmButton);
            mTextViewDestination.setText(mDestination);
            mTextViewDate.setText(mDate);
            mTextViewPrice.setText("Kes " + subTotal);
            mTextViewSelectedInventory.setText(mSelectedInventory);
            mFinalPriceTextView.setText("Kes " + total);
            bottomSheetDialog.show();


            // dismiss dialog onclick

            mButtonCancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            mConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                    pushNotification();
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Inventory.this);
                    LayoutInflater inflater = Inventory.this.getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.success_dialog, null);
                    dialogBuilder.setView(dialogView);
                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();
                    Button buttonSuccess = (Button) dialogView.findViewById(R.id.buttonSuccess);
                    buttonSuccess.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            startActivity(new Intent(Inventory.this, MoversActivity.class));
                        }
                    });


                }
            });
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

}