package mainPackage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.varun.bannerexample.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.*;

public class MainActivity extends Activity {

    private InterstitialAd interstitial; //This is the class that is used to manage and create interstitial ads
    private AdRequest adRequest;// This class is used to manage and send requests to the google admob servers

    @Override


    //For this test app I had it load an interstitial ad when the activity is created.
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setup a new ad
        this.setupAd();
        // Begin loading your interstitial.
        this.loadAd();
        // Display your interstitial.
        this.displayInterstitial();

    }

//This method I made will instantiate the interstital Ad
    public void setupAd(){
        // Create the interstitial.
        interstitial = new InterstitialAd(this);
        //THIS ID is a default test ID. When we publish we need to register with AdMob and get an ID so we can monotize
        interstitial.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        // Create ad request.

                this.adRequest =new AdRequest.Builder()

                 //THIS CODE IS NEEDED FOR TESTING PURPOSES. THIS IS SO THE ADMOB DOESNT THINK WE ARE COMMITING FRAUD AND
                //SUSPEND AN ADMOB ACCOUNT.
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)//add the emulator device ID incase you areusing the emulator
                .addTestDevice("E773FBEE6B1B4CDFF7CADEBBA5F3AA64")//PUT YOUR DEVICE ID CODE HERE THIS ONE IS MINE
                //Build the Ad.
                .build();
    }
    //Method to load an ad.
    public void loadAd(){
        interstitial.loadAd(adRequest);
    }

    //Display the newly loaded add.
    public void displayInterstitial() {
        //You must set an addlistener which I create an instance of within the setadlistener method.
        interstitial.setAdListener(new AdListener() {
            @Override
            //When an ad finishes loading.
            public void onAdLoaded() {
                //error check incase something goes wrong during the ad loading process.
                if (interstitial.isLoaded()) {
                    //show the ad.
                    interstitial.show();
                }
            }
        });

    }

}