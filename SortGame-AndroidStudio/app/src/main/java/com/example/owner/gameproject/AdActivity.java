package com.example.owner.gameproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.Window;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdActivity extends Activity {

    private InterstitialAd interstitial;

    private SurfaceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        if(MyActivity.count == 3){
            requestNewInterstitial();
            // Get the view from activity_main.xml
            setContentView(R.layout.activity_main);
            MyActivity.count = 0;
        }

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
            }
            public void onAdClosed(){
                setContentView(view);
            }
        });

        //GooglePlay LoginHere
        MyActivity.count++;
    }

    public void displayInterstitial(){
        if(interstitial.isLoaded()){
            interstitial.show();
        }
    }

    public void setView(SurfaceView view){
        this.view = view;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        interstitial.loadAd(adRequest);
    }
}
