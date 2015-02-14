package com.example.owner.gameproject;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast; //Used for testing
import android.widget.ToggleButton;




public class MyActivity extends Activity {

    public static float volume = 1f;

    private ToggleButton toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

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
        boolean on = ((ToggleButton)view).isChecked();
        if(on){
            volume = 1f;
        }else{
            volume = 0f;
        }
        /*
        toggleBtn = (ToggleButton) findViewById(R.id.toggleButton);
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (toggleBtn.isChecked()) {
                    volume = 1f;
                } else {
                    volume = 0f;
                }
            }
        });
        */
    }
    public static float getVolume(){
        return volume;
    }
}
