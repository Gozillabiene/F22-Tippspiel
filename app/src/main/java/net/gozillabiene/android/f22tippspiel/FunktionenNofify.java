package net.gozillabiene.android.f22tippspiel;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class FunktionenNofify {

    static public void Notifi(Context context, String Nachricht){
        //Es wird die Klasse MainActivity.class beim Klick aufgerufen
        final Intent notificationIntent = new Intent(context, MainActivity.class);
        final PendingIntent pi = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_action_name)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setOngoing(false)
                        .setContentIntent(pi)

                        .setContentText(Nachricht);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(4150, mBuilder.build());
    }
    static public void notyfiClear(Context context){
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(4150);
    }

}
