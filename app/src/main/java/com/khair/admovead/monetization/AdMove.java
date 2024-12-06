package com.khair.admovead.monetization;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdMove {
    private  static InterstitialAd minterstitialAd;
    private AdMobCallBack adMobCallBack;

    public AdMove() {
    }

    public AdMove(AdMobCallBack adMobCallBack) {
        this.adMobCallBack = adMobCallBack;
    }

    public static void setBannerAd(LinearLayout adContainerView, Context context){
       if (!Constrain.Is_Ad_ON)return;
AdView adView = new AdView(context);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                Toast.makeText(context, "AdClicked", Toast.LENGTH_SHORT).show();
            }
        });
        adView.setAdUnitId(Constrain.Banner_AD_ID);
        adView.setAdSize(AdSize.BANNER);
        adContainerView.removeAllViews();
        adContainerView.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



    }///BannerAd end here****************************
    public static void loadInterstitialAd(Context context) {
        if (!Constrain.Is_Ad_ON) return;

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, Constrain.Interstitial_AD_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        minterstitialAd = interstitialAd;
                        Log.i(TAG, "Ad successfully loaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e(TAG, "Failed to load ad: " + loadAdError.toString());
                        minterstitialAd = null;
                    }
                });
    }///lodeInterstitialAd end here ********************
    public void showInterstitialAd(Activity activity, boolean isReload) {
        if (!Constrain.Is_Ad_ON || minterstitialAd == null) {
            notifyDismiss();
            return;
        }

        minterstitialAd.show(activity);

        minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                Log.i(TAG, "Ad dismissed");
                if (isReload) {
                    minterstitialAd = null;
                    loadInterstitialAd(activity);
                }
                notifyDismiss();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                Log.e(TAG, "Failed to show ad: " + adError.toString());
                notifyDismiss();
            }
        });
    }///ShowInterstitialAd end here**************************
 public  void notifyDismiss() {
     if (adMobCallBack!=null){
         adMobCallBack.onDismiss();

     }///notifyDismiss end here**************************
 }
}///public class end here*************************************
