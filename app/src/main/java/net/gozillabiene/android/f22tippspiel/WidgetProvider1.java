package net.gozillabiene.android.f22tippspiel;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider1 extends AppWidgetProvider{

    String widgettextview;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent appopenIntent = new Intent(context,MainActivity.class);
        PendingIntent appopenPendingIntent = PendingIntent.getActivity(context,0,appopenIntent,0);
        remoteViews.setOnClickPendingIntent(R.id.widgetButton1,appopenPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        if(intent.hasExtra("benutzername")){
            widgettextview = intent.getStringExtra("benutzername");
            remoteViews.setTextViewText(R.id.textViewWidget1,"Hallo "+widgettextview);
            updateWidgetNow(context,remoteViews);
        }

        super.onReceive(context, intent);
    }

    public void updateWidgetNow(Context context, RemoteViews remoteViews){
        ComponentName widgetComponent = new ComponentName(context,WidgetProvider1.class);
        AppWidgetManager.getInstance(context).updateAppWidget(widgetComponent,remoteViews);
    }
}
