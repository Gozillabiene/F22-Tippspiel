package net.gozillabiene.android.f22tippspiel;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;

public class NotificationDisplayReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Intent wtdSServiceIntent = new Intent(context, NotificationDisplayService.class);
            PendingIntent wtdSServicePendingIntent = PendingIntent.getService(context, 0, wtdSServiceIntent, 0);

            long interval = DateUtils.MINUTE_IN_MILLIS * 60; // 60 Minuten
            //long firstStart = System.currentTimeMillis() + interval;
            long firstStart = FunktionenAllgemein.getNextHour();

            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am.setInexactRepeating(AlarmManager.RTC, firstStart, interval, wtdSServicePendingIntent);

        }

    }
}