package Model;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Manages the ads to be displayed at end of game.
 *
 * @author Varun Sriram
 * @version 1.0
 * @since 2015-02-06
 *
 */

public class AdManager
{

    private AdView interstitial; //This is the class that is used to manage and create interstitial ads
    private AdRequest adRequest;// This class is used to manage and send requests to the google admob servers
    private Context context;

    public AdManager(Context context)
    {
        this.context = context;
    }


    //This method I made will instantiate the interstital Ad
    private void setupAd()
    {
        //interstitial = new InterstitialAd(this.context);
        interstitial = new AdView(this.context);


        //THIS ID is a default test ID. When we publish we need to register with AdMob and get an ID so we can monotize
        interstitial.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        interstitial.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);




        // Create ad request.
        this.adRequest =new AdRequest.Builder()

                //THIS CODE IS NEEDED FOR TESTING PURPOSES. THIS IS SO THE ADMOB DOESNT THINK WE ARE COMMITING FRAUD AND
                //SUSPEND AN ADMOB ACCOUNT.
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)//add the emulator device ID incase you areusing the emulator
                .addTestDevice("F6D8A394CA5836BB5C6AFC3FD7BA852B")//PUT YOUR DEVICE ID CODE HERE THIS ONE IS MINE
                .addTestDevice("E773FBEE6B1B4CDFF7CADEBBA5F3AA64")

                        //Build the Ad.
                .build();


    }

    //Method to load an ad.
    private void loadAd(){
        interstitial.loadAd(adRequest);
    }

    //Display the newly loaded add.
    private void displayInterstitial() {

        //You must set an addlistener which I create an instance of within the setadlistener method.
        interstitial.setAdListener(new AdListener() {
            @Override

            //When an ad finishes loading.
            public void onAdLoaded() {
                //error check incase something goes wrong during the ad loading process.
                //if (interstitial.isLoaded()) {
                //show the ad.
                //interstitial.show();
                //}
                //loadAd();


            }
        });
    }

    public AdView getInterstitial()
    {
        return this.interstitial;
    }

    public void displayAds()
    {
        this.setupAd();
        this.loadAd();
        this.displayInterstitial();
    }

}
