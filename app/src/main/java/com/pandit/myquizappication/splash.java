package com.pandit.myquizappication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash extends AppCompatActivity {
ImageView im;
TextView text1;
Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        im=findViewById(R.id.img);
        text1=findViewById(R.id.text1);
        animation= AnimationUtils.loadAnimation(this,R.anim.imganimation);
        im.setAnimation(animation);
        text1.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}