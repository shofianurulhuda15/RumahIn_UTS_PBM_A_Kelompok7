package com.example.rumahin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 3000; // 3 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.splash_logo);
        TextView appName = findViewById(R.id.app_name);

        // Animasi untuk logo dan nama aplikasi
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        logo.startAnimation(fadeIn);
        appName.startAnimation(fadeIn);

        // Handler untuk berpindah ke activity berikutnya setelah beberapa detik
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIMEOUT);
    }
}