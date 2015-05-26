package com.bombapps.ColorMatch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdActivity extends Activity {

    private InterstitialAd interstitial;

    private GameView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getApplicationContext().getResources().getString(R.string.interstitial_ad_unit_id));
        //testing
        //interstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        requestNewInterstitial();

        Log.i("count", Integer.toString(MyActivity.count));
        Log.i("display", Boolean.toString(MyActivity.display));

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
                MyActivity.count = 0;
            }
            public void onAdClosed(){
                view.setDisplayInterstitial(false);
            }
        });
        MyActivity.count++;
    }

    public void displayInterstitial(){
        if(isNetworkConnected()) {
            view.setDisplayInterstitial(true);
            if (interstitial.isLoaded()) {
                interstitial.show();
            }
        }

    }

    public void setView(GameView view){
        this.view = view;
        setContentView(view);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

    public void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("asdf")
                .build();
        interstitial.loadAd(adRequest);
    }
}
