package com.movers.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_TIME = 2000;
    @BindView(R.id.textViewlogo)
    TextView mTextViewMovers;
    @BindView(R.id.lottie)
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.graye));
            getWindow().setStatusBarColor(getResources().getColor(R.color.graye));
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(SplashScreen.this,
                        MapsActivity.class);

                startActivity(intent);
               finish();

            }
        }, SPLASH_DISPLAY_TIME);


//        lottieAnimationView.animate().translationY(-1600).setDuration(3900).setStartDelay(3000);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);
        mTextViewMovers.startAnimation(fadeIn);



    }


}