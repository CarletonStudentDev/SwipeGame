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

    private ToggleButton toggleBtn;
    private ScoreDataSource dataSource;
    public static int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        MediaSounds.setVolume(1f);

        setContentView(R.layout.activity_my);

        onToggleClicked();

        // initializes ads at bottom of screen
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        dataSource = new ScoreDataSource(this);
        dataSource.open();

        count = 0;
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

    /** Called when the user clicks the Normal button */
    public void openNormal(View view)
    {
        Intent intent = new Intent(this, StartNormalActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Marathon button */
    public void openStroop(View view)
    {
        Intent intent = new Intent(this, StartStroopActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Endless button */
    public void openEndless(View view)
    {
        Intent intent = new Intent(this, StartEndlessActivity.class);
        startActivity(intent);
    }

    //checks if the toggle button was clicked and sets the volume
    private void onToggleClicked()
    {

        toggleBtn = (ToggleButton) findViewById(R.id.toggleButton);
        toggleBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (toggleBtn.isChecked())
                    MediaSounds.setVolume(1f);
                else
                    MediaSounds.setVolume(0f);
            }
        });

    }

}
