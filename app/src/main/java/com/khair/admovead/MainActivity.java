package com.khair.admovead;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.khair.admovead.monetization.AdMove;

public class MainActivity extends AppCompatActivity {
LinearLayout adContainerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adContainerView=findViewById(R.id.adContainerView);

        AdMove.setBannerAd(adContainerView,MainActivity.this);
        AdMove.loadInterstitialAd(MainActivity.this);

        findViewById(R.id.interstitialAd).setOnClickListener(v -> {
             new AdMove(() -> {
                 Toast.makeText(this, "interstitialAd", Toast.LENGTH_SHORT).show();
             }).showInterstitialAd(MainActivity.this,true);
        });


    }
}