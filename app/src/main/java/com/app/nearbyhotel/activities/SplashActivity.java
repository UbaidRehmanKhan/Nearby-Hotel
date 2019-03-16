package com.app.nearbyhotel.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.app.nearbyhotel.R;
import com.app.nearbyhotel.databinding.ActivitySplashBinding;
import com.app.nearbyhotel.helper.MyBounceInterpolator;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    final public static int splashTimer = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        Animation bounce = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
        bounce.setInterpolator(interpolator);

        bounce.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        startActivity(new Intent(SplashActivity.this,
                                MainActivity.class));
                        finish();

                    }
                }, splashTimer);

            }
        });
        binding.ivSplash.startAnimation(bounce);
    }
}
