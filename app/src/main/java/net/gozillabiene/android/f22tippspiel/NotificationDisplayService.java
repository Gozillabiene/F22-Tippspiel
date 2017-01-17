package net.gozillabiene.android.f22tippspiel;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

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

        // Unsere auszufuehrende Methode.
        displayNotification("Dies ist eine Test-Notification mit einem seeehr langen Text, den man ausklappen muss.");

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
    public void displayNotification(String text){

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
            mBuilder.setSmallIcon(R.drawable.ic_action_name);
            mBuilder.setContentTitle(getString(R.string.app_name));
            mBuilder.setOngoing(false);
            mBuilder.setContentIntent(pi);
            mBuilder.addAction(R.drawable.ic_open_mainactivity_action ,"MainActivity öffnen", pi);
            if(benachrichtigungVibOn){mBuilder.setVibrate(new long[]{DEFAULT_VIBRATE});}
            if(benachrichtigungTonOn){mBuilder.setSound(soundUri);}
            if(benachrichtigungLEDOn){mBuilder.setLights(farbe, 500, 1000);}
            mBuilder.setContentText(text);
            mBuilder.setStyle( new NotificationCompat.BigTextStyle().bigText(text));

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }
    }
}