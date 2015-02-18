package com.example.owner.gameproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MyActivity extends Activity {

    private static float volume;

    private ToggleButton toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_my);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the New Game button */
    public void newGame(View view) {
        Intent intent = new Intent(this, StartNormalActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Store button */
    public void openMarathon(View view) {
        Intent intent = new Intent(this, StartMarathonActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Store button */
    public void openEndless(View view) {
        Intent intent = new Intent(this, StartEndlessActivity.class);
        startActivity(intent);
    }

    //checks if the toggle button was clicked and sets the volume
    public void onToggleClicked(View view) {

        toggleBtn = (ToggleButton) findViewById(R.id.toggleButton);

        toggleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (toggleBtn.isChecked()){
                    volume = 1f;
                }else{
                    volume = 0f;
                }
            }
        });

    }


    private void checkToggleButton (){

        toggleBtn = (ToggleButton) findViewById(R.id.toggleButton);

        if(toggleBtn.getTextOn().equals("SOUND ON")){
            volume=1f;
        }else if(toggleBtn.getTextOn().equals("SOUND OFF")){
            volume=0;
        }

    }

    private ToggleButton getToggleBtn(){

        return (ToggleButton) findViewById(R.id.toggleButton);

    }

    public static float getVolume()
    {
        return volume;
    }


}
