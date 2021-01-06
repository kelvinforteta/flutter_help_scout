package dev.kelvinforteta.flutter_help_scout;

import android.content.Context;

import com.helpscout.beacon.Beacon;
import com.helpscout.beacon.ui.BeaconActivity;

class BeaconHelpers {

    // this method will initialize the beacon

    public void initialize(String beaconId){

        new Beacon.Builder()
                .withBeaconId(beaconId)
                .build();
    }


   /*
   * Once you’ve initialized Beacon, you’re ready to interact with it.
   * Whenever you want to invoke Beacon, use the code below to
   * display the Beacon user interface.
   */
    public void openBeacon(Context context){
        BeaconActivity.open(context);
    }


    /*
    * Calling this method resets the current Beacon state,
    * thereby deleting all the user data: email, name, user attributes,
    * push token and resets the Beacon Device ID. It won’t
    * remove the Beacon ID, or any local config overrides.
    */
    public void logout(){
        Beacon.logout();
    }


    /*
    * This method wipes all data from the Beacon,
    * including the Beacon ID. This may be useful if
    * you are using different Beacons in different parts of your app.
    */
    public void clear(){
        Beacon.logout();
    }


    // add some more extra attributes such as company name, job title and a url to use as the customer’s avatar.
    public void identity(String email,
                         String name,
                         String avatar,
                         String company,
                         String jobTitle){
        Beacon.identify(email, name, company, jobTitle, avatar);
    }
}
