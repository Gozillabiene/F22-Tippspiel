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
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

public class WTDStartedService extends Service {
    public IBinder onBind(Intent intent) {
        // Fuer dieses Tutorial irrelevant. Gehoert zu bounded Services.
        return null;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My PARTIAL_WAKE_LOCK");
//        wakeLock.acquire();

        // Unsere auszufuehrende Methode.
        updateData();
        // Nachdem unsere Methode abgearbeitet wurde, soll sich der Service
        // selbst stoppen.
//        wakeLock.release();
        stopSelf();

        // Um den Service laufen zu lassen, bis er explizit gestoppt wird,
        // geben wir "START_STICKY" zurueck.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
    }
    public void updateData(){

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
            PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.drawable.ic_action_name);
            mBuilder.setContentTitle(getString(R.string.app_name));
            mBuilder.setOngoing(false);
            mBuilder.setContentIntent(pi);
            if(benachrichtigungTonOn){mBuilder.setSound(soundUri);}
            if(benachrichtigungLEDOn){mBuilder.setLights(farbe, 500, 1000);}
            mBuilder.setContentText("Benachrichtigung\n\nTest");

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(4150, mBuilder.build());
            if(benachrichtigungVibOn){
                Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(250);

            }
        }

    }
}