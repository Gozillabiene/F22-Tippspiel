package net.gozillabiene.android.f22tippspiel;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static android.app.Notification.DEFAULT_VIBRATE;

public class NotificationDisplayService extends Service {

    final int NOTIFICATION_ID = 4150;
    public IBinder onBind(Intent intent) {
        // Fuer dieses Tutorial irrelevant. Gehoert zu bounded Services.
        return null;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        SharedPreferences sPrefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String prefBenutzernameKey = getString(R.string.login_benutzername_key);
        String prefBenutzernameDefault = getString(R.string.login_benutzername_default);
        String benutzername = sPrefs1.getString(prefBenutzernameKey,prefBenutzernameDefault);

        String prefPasswortKey = getString(R.string.login_passwort_key);
        String prefPasswortDefault = getString(R.string.login_passwort_default);
        String passwort = sPrefs1.getString(prefPasswortKey,prefPasswortDefault);

        if(isURLReachable(this)) {
            String spieltag = spielTag();
            String spiel = naechstesSpiel(benutzername, FunktionenAllgemein.md5(passwort));
            String getippt = spieltagGetippt(benutzername, FunktionenAllgemein.md5(passwort), spieltag);

            String[] spieltag1 = spieltag.split("#@@#");
            String[] spiel1 = spiel.split("#@@#");
            String[] getippt1 = getippt.split("#@@#");

            String spielGesetzt;
            String jokerGesetzt;

            if (spiel1[1].equals("true")) {
                if (getippt1[0].equals("false") || getippt1[1].equals("false")) {
                    if (getippt1[0].equals("true")) {
                        spielGesetzt = "Ja";
                    } else {
                        spielGesetzt = "Nein";
                    }
                    if (getippt1[1].equals("true")) {
                        jokerGesetzt = "Ja";
                    } else {
                        jokerGesetzt = "Nein";
                    }

                    String[] events = {"Spieltag :\u0009\u0009\u0009\u0009" + spieltag1[0],
                                       "Nächstes Spiel :\u0009" + FunktionenAllgemein.getDate(spiel1[0]),
                                       "Alles getippt :\u0009" + spielGesetzt,
                                       "Joker gesetzt :\u0009" + jokerGesetzt};


                    // Unsere auszufuehrende Methode.
                    //displayNotification("Dies ist eine Test-Notification mit einem seeehr langen Text, den man ausklappen muss.");
                    displayNotification(events);
                }
            }
        }
        // Nachdem unsere Methode abgearbeitet wurde, soll sich der Service
        // selbst stoppen.
        stopSelf();

        // Um den Service laufen zu lassen, bis er explizit gestoppt wird,
        // geben wir "START_STICKY" zurueck.
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
    }
    public void displayNotification(String[] events){

        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prefBenachrichtigungKey = getString(R.string.einstellungen_benachrichtigung_key);
        Boolean benachrichtigungOn = sPrefs.getBoolean(prefBenachrichtigungKey,false);
        String prefBenachrichtigungTonKey = getString(R.string.einstellungen_benachrichtigung_ton_key);
        Boolean benachrichtigungTonOn = sPrefs.getBoolean(prefBenachrichtigungTonKey,false);
        String prefBenachrichtigungLEDKey = getString(R.string.einstellungen_benachrichtigung_led_key);
        Boolean benachrichtigungLEDOn = sPrefs.getBoolean(prefBenachrichtigungLEDKey,false);
        String prefBenachrichtigungVibKey = getString(R.string.einstellungen_benachrichtigung_vib_key);
        Boolean benachrichtigungVibOn = sPrefs.getBoolean(prefBenachrichtigungVibKey,false);

        String prefBenachrichtigungLEDfarbeKey = getString(R.string.einstellungen_benachrichtigung_farbe_key);
        String benachrichtigungLEDFarbe = sPrefs.getString(prefBenachrichtigungLEDfarbeKey,"CYAN");

        int farbe = Color.parseColor(benachrichtigungLEDFarbe);

        if(benachrichtigungOn) {
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.putExtra(getString(R.string.NOTIFICATION_ID_KEY), NOTIFICATION_ID);
            PendingIntent pi = PendingIntent.getActivity(this, 1, notificationIntent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

            mBuilder.setSmallIcon(R.drawable.ic_action_name);
            mBuilder.setContentTitle(getString(R.string.app_name));
            mBuilder.setOngoing(false);
            mBuilder.setContentIntent(pi);
            mBuilder.addAction(R.drawable.ic_open_mainactivity_action ,"MainActivity öffnen", pi);

            if(benachrichtigungVibOn){mBuilder.setVibrate(new long[]{DEFAULT_VIBRATE});}
            if(benachrichtigungTonOn){mBuilder.setSound(soundUri);}
            if(benachrichtigungLEDOn){mBuilder.setLights(farbe, 500, 1000);}
//            mBuilder.setContentText(text);
//            mBuilder.setStyle( new NotificationCompat.BigTextStyle().bigText(text));

            inboxStyle.setBigContentTitle(getString(R.string.app_name));

            for (int i=0; i < events.length; i++) {
                inboxStyle.addLine(events[i]);
            }
            mBuilder.setStyle(inboxStyle);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }
    }
    public String spielTag (){
        String output=null;
        try {
            output = new AuslesenWeb()
                    .execute("","","spieltag","","")
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        output=output.replaceAll("[\\D]","");
        return output;
    }

    public String naechstesSpiel(String ben,String pass){
        String output = null;
        try {
            output = new AuslesenWeb()
                     .execute(ben,pass,"naechstes_spiel","1",getString(R.string.saison))
                     .get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return output;
    }
    public String spieltagGetippt(String ben,String pass,String spieltag){
        String output=null;
        try {
            output = new AuslesenWeb()
                    .execute(ben,pass,"spieltag_getippt",spieltag,getString(R.string.saison))
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return output;
    }
    static public boolean isURLReachable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL("https://tipp.gozillabiene.net");   // Change to "http://google.com" for www  test.
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(10 * 1000);          // 10 s.
                conn.connect();

                // 200 = "OK" code (http connection is fine).
                return conn.getResponseCode() == 200;
            } catch (MalformedURLException e1) {
                return false;
            } catch (IOException e) {
                return false;
            } catch (Exception ex) {
                return true;
            }
        }
        return false;
    }

}