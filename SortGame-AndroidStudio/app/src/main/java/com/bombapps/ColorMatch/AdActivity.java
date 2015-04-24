package com.bombapps.ColorMatch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        Log.i("count", Integer.toString(MyActivity.count));
        if(MyActivity.count == 4) {
            requestNewInterstitial();
        }

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if(MyActivity.display){
                    displayInterstitial();
                    MyActivity.display = false;
                }
            }
            public void onAdClosed(){
                //setContentView(view);
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

    public void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        interstitial.loadAd(adRequest);
        displayInterstitial();
    }
}
