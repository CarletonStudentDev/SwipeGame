package com.example.owner.gameproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
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
        Intent intent = new Intent(this, StartNewGameActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Store button */
    public void openStore(View view) {
        Intent intent = new Intent(this, OpenStoreActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Store button */
    public void openSettings(View view) {
        Intent intent = new Intent(this, OpenSettingsActivity.class);
        startActivity(intent);
    }
}
