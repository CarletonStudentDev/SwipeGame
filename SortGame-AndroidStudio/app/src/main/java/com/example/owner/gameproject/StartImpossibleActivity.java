package com.example.owner.gameproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

<<<<<<< HEAD:SortGame-AndroidStudio/app/src/main/java/com/example/owner/gameproject/StartEndlessActivity.java
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class StartEndlessActivity extends AdActivity {

    private static InterstitialAd interstitial;
=======
public class StartImpossibleActivity extends Activity {
>>>>>>> origin/Eric:SortGame-AndroidStudio/app/src/main/java/com/example/owner/gameproject/StartImpossibleActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Update uses this to hide bar instead of Line below
        //getSupportActionBar().hide();
        requestWindowFeature(Window.FEATURE_NO_TITLE);

<<<<<<< HEAD:SortGame-AndroidStudio/app/src/main/java/com/example/owner/gameproject/StartEndlessActivity.java
        final GameView gameView = new GameView(this, -1,false);
        setContentView(gameView);

        //GooglePlay LoginHere
        MyActivity.count++;
=======
        setContentView(new GameView(this, 11000L,true,true));
>>>>>>> origin/Eric:SortGame-AndroidStudio/app/src/main/java/com/example/owner/gameproject/StartImpossibleActivity.java
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_endless, menu);
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
}
