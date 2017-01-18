package net.gozillabiene.android.f22tippspiel;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public int aktuellerSpieltag;
    final Context context = this;
    public Fragment0_Home fragment0;
    public Fragment1_Tipps fragment1;
    public Fragment2_Tabelle fragment2;
    public Fragment3_Ubersicht fragment3;
    public Fragment4_Admin fragment4;
    public Fragment5_Anleitung fragment5;
    public Fragment6_Hilfe fragment6;
    public Fragment98_Einstellungen fragment98;
    public Fragment99_Login fragment99;
    public FragmentManager fragmanager;
    public FragmentTransaction fragtrans;
    public int neueVersion;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1001;
    long backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        starteService();

        if(getIntent().hasExtra(getString(R.string.NOTIFICATION_ID_KEY))){
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.cancel(getIntent().getIntExtra(getString(R.string.NOTIFICATION_ID_KEY), 0));
        }

    if(FunktionenAllgemein.isURLReachable(context)) {
        versionsKontrolle();

        aktuellerSpieltag = spielTag();
    }
        changeTextview(getString(R.string.nichtangemeldet));

        starteOberflaeche();

        if(FunktionenAllgemein.isURLReachable(context)) {
            onLogin();
        }
    }
    public void datenSpeichern(View v){

        RadioButton btn1 = (RadioButton)findViewById(R.id.rbj1);
        RadioButton btn2 = (RadioButton)findViewById(R.id.rbj2);
        RadioButton btn3 = (RadioButton)findViewById(R.id.rbj3);
        RadioButton btn4 = (RadioButton)findViewById(R.id.rbj4);
        RadioButton btn5 = (RadioButton)findViewById(R.id.rbj5);
        RadioButton btn6 = (RadioButton)findViewById(R.id.rbj6);
        RadioButton btn7 = (RadioButton)findViewById(R.id.rbj7);
        RadioButton btn8 = (RadioButton)findViewById(R.id.rbj8);
        RadioButton btn9 = (RadioButton)findViewById(R.id.rbj9);

        TextView tvz1 =(TextView)findViewById(R.id.time1);
        TextView tvz2 =(TextView)findViewById(R.id.time2);
        TextView tvz3 =(TextView)findViewById(R.id.time3);
        TextView tvz4 =(TextView)findViewById(R.id.time4);
        TextView tvz5 =(TextView)findViewById(R.id.time5);
        TextView tvz6 =(TextView)findViewById(R.id.time6);
        TextView tvz7 =(TextView)findViewById(R.id.time7);
        TextView tvz8 =(TextView)findViewById(R.id.time8);
        TextView tvz9 =(TextView)findViewById(R.id.time9);

        TextView tvh1 =(TextView)findViewById(R.id.tv_heim1);
        TextView tvh2 =(TextView)findViewById(R.id.tv_heim2);
        TextView tvh3 =(TextView)findViewById(R.id.tv_heim3);
        TextView tvh4 =(TextView)findViewById(R.id.tv_heim4);
        TextView tvh5 =(TextView)findViewById(R.id.tv_heim5);
        TextView tvh6 =(TextView)findViewById(R.id.tv_heim6);
        TextView tvh7 =(TextView)findViewById(R.id.tv_heim7);
        TextView tvh8 =(TextView)findViewById(R.id.tv_heim8);
        TextView tvh9 =(TextView)findViewById(R.id.tv_heim9);

        TextView tvg1 =(TextView)findViewById(R.id.tv_gast1);
        TextView tvg2 =(TextView)findViewById(R.id.tv_gast2);
        TextView tvg3 =(TextView)findViewById(R.id.tv_gast3);
        TextView tvg4 =(TextView)findViewById(R.id.tv_gast4);
        TextView tvg5 =(TextView)findViewById(R.id.tv_gast5);
        TextView tvg6 =(TextView)findViewById(R.id.tv_gast6);
        TextView tvg7 =(TextView)findViewById(R.id.tv_gast7);
        TextView tvg8 =(TextView)findViewById(R.id.tv_gast8);
        TextView tvg9 =(TextView)findViewById(R.id.tv_gast9);

        EditText edh1 =(EditText)findViewById(R.id.et_heim1);
        EditText edh2 =(EditText)findViewById(R.id.et_heim2);
        EditText edh3 =(EditText)findViewById(R.id.et_heim3);
        EditText edh4 =(EditText)findViewById(R.id.et_heim4);
        EditText edh5 =(EditText)findViewById(R.id.et_heim5);
        EditText edh6 =(EditText)findViewById(R.id.et_heim6);
        EditText edh7 =(EditText)findViewById(R.id.et_heim7);
        EditText edh8 =(EditText)findViewById(R.id.et_heim8);
        EditText edh9 =(EditText)findViewById(R.id.et_heim9);

        EditText edg1 =(EditText)findViewById(R.id.et_gast1);
        EditText edg2 =(EditText)findViewById(R.id.et_gast2);
        EditText edg3 =(EditText)findViewById(R.id.et_gast3);
        EditText edg4 =(EditText)findViewById(R.id.et_gast4);
        EditText edg5 =(EditText)findViewById(R.id.et_gast5);
        EditText edg6 =(EditText)findViewById(R.id.et_gast6);
        EditText edg7 =(EditText)findViewById(R.id.et_gast7);
        EditText edg8 =(EditText)findViewById(R.id.et_gast8);
        EditText edg9 =(EditText)findViewById(R.id.et_gast9);

        String sep = "aasepaa";
        String punkt = "aapunktaa";
        String space = "aaspaceaa";
        String auml = "aaaumlaa";
        String ouml = "aaoumlaa";
        String uuml = "aauumlaa";

        String a6="";
        String a7="";
        if (edh1.isEnabled()) {
            String a1 = tvz1.getText().toString();
            String a2 = tvh1.getText().toString();
            String a3 = tvg1.getText().toString();
            String a4 = edh1.getText().toString();if(a4.equals("")){a4="nichts";}
            String a5 = edg1.getText().toString();if(a5.equals("")){a5="nichts";}
            if(btn1.isChecked()){a6 ="1";}else{a6 = "0";}
            a7 = FunktionenAllgemein.getTimestamp(a1) + sep + a2 + sep + a3 + sep + a4 + sep + a5 + sep + a6 + sep;
        }
        String b6="";
        String b7="";
        if (edh2.isEnabled()) {
            String b1 = tvz2.getText().toString();
            String b2 = tvh2.getText().toString();
            String b3 = tvg2.getText().toString();
            String b4 = edh2.getText().toString();if(b4.equals("")){b4="nichts";}
            String b5 = edg2.getText().toString();if(b5.equals("")){b5="nichts";}
            if(btn2.isChecked()){b6 ="1";}else{b6 = "0";}
            b7 = FunktionenAllgemein.getTimestamp(b1) + sep + b2 + sep + b3 + sep + b4 + sep + b5 + sep + b6 +sep;
        }
        String c6="";
        String c7="";
        if (edh3.isEnabled()) {
            String c1 = tvz3.getText().toString();
            String c2 = tvh3.getText().toString();
            String c3 = tvg3.getText().toString();
            String c4 = edh3.getText().toString();if(c4.equals("")){c4="nichts";}
            String c5 = edg3.getText().toString();if(c5.equals("")){c5="nichts";}
            if(btn3.isChecked()){c6 ="1";}else{c6 = "0";}
            c7 = FunktionenAllgemein.getTimestamp(c1) + sep + c2 + sep + c3 + sep + c4 + sep + c5 + sep + c6 +sep;
        }
        String d6="";
        String d7="";
        if (edh4.isEnabled()) {
            String d1 = tvz4.getText().toString();
            String d2 = tvh4.getText().toString();
            String d3 = tvg4.getText().toString();
            String d4 = edh4.getText().toString();if(d4.equals("")){d4="nichts";}
            String d5 = edg4.getText().toString();if(d5.equals("")){d5="nichts";}
            if(btn4.isChecked()){d6 ="1";}else{d6 = "0";}
            d7 = FunktionenAllgemein.getTimestamp(d1) + sep + d2 + sep + d3 + sep + d4 + sep + d5 + sep + d6 +sep;
        }
        String e6="";
        String e7="";
        if (edh5.isEnabled()) {
            String e1 = tvz5.getText().toString();
            String e2 = tvh5.getText().toString();
            String e3 = tvg5.getText().toString();
            String e4 = edh5.getText().toString();if(e4.equals("")){e4="nichts";}
            String e5 = edg5.getText().toString();if(e5.equals("")){e5="nichts";}
            if(btn5.isChecked()){e6 ="1";}else{e6 = "0";}
            e7 = FunktionenAllgemein.getTimestamp(e1) + sep + e2 + sep + e3 + sep + e4 + sep + e5 + sep + e6 +sep;
        }
        String f6="";
        String f7="";
        if (edh6.isEnabled()) {
            String f1 = tvz6.getText().toString();
            String f2 = tvh6.getText().toString();
            String f3 = tvg6.getText().toString();
            String f4 = edh6.getText().toString();if(f4.equals("")){f4="nichts";}
            String f5 = edg6.getText().toString();if(f5.equals("")){f5="nichts";}
            if(btn6.isChecked()){f6 ="1";}else{f6 = "0";}
            f7 = FunktionenAllgemein.getTimestamp(f1) + sep + f2 + sep + f3 + sep + f4 + sep + f5 + sep + f6 +sep;
        }
        String g6="";
        String g7="";
        if (edh7.isEnabled()) {
            String g1 = tvz7.getText().toString();
            String g2 = tvh7.getText().toString();
            String g3 = tvg7.getText().toString();
            String g4 = edh7.getText().toString();if(g4.equals("")){g4="nichts";}
            String g5 = edg7.getText().toString();if(g5.equals("")){g5="nichts";}
            if(btn7.isChecked()){g6 ="1";}else{g6 = "0";}
            g7 = FunktionenAllgemein.getTimestamp(g1) + sep + g2 + sep + g3 + sep + g4 + sep + g5 + sep + g6 +sep;
        }
        String h6="";
        String h7="";
        if (edh8.isEnabled()) {
            String h1 = tvz8.getText().toString();
            String h2 = tvh8.getText().toString();
            String h3 = tvg8.getText().toString();
            String h4 = edh8.getText().toString();if(h4.equals("")){h4="nichts";}
            String h5 = edg8.getText().toString();if(h5.equals("")){h5="nichts";}
            if(btn8.isChecked()){h6 ="1";}else{h6 = "0";}
            h7 = FunktionenAllgemein.getTimestamp(h1) + sep + h2 + sep + h3 + sep + h4 + sep + h5 + sep + h6 +sep;
        }
        String i6="";
        String i7="";
        if (edh9.isEnabled()) {
            String i1 = tvz9.getText().toString();
            String i2 = tvh9.getText().toString();
            String i3 = tvg9.getText().toString();
            String i4 = edh9.getText().toString();if(i4.equals("")){i4="nichts";}
            String i5 = edg9.getText().toString();if(i5.equals("")){i5="nichts";}
            if(btn9.isChecked()){i6 ="1";}else{i6 = "0";}
            i7 = FunktionenAllgemein.getTimestamp(i1) + sep + i2 + sep + i3 + sep + i4 + sep + i5 + sep + i6 +sep;
        }

        Spinner sp = (Spinner) findViewById(R.id.spinner2);
        String aa=sp.getSelectedItem().toString();
        String bb[] = aa.split("\\.");
        String string = bb[0]+sep+a7+b7+c7+d7+e7+f7+g7+h7+i7;

        String newString1 = string.replace(" ",space);
        String newString2 = newString1.replace(".",punkt);
        String newString3 = newString2.replace("ä",auml);
        String newString4 = newString3.replace("ö",ouml);
        String newString5 = newString4.replace("ü",uuml);


        String output=null;
        try {
            if(FunktionenAllgemein.isURLReachable(context)) {

                output = new AuslesenWeb()
                        .execute(benutzername(), FunktionenAllgemein.md5(passwort()), "tipp_speichern", newString5, getString(R.string.saison))
                        .get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TextView tv = (TextView)findViewById(R.id.tv_sp);
        tv.setText(output);

        Toast.makeText(this,output, Toast.LENGTH_SHORT).show();


        tv.setText("");

    }

    public void changeTextview(String text){
        TextView t = (TextView)findViewById(R.id.statuszeile);
        t.setText(text);
    }

    public void onLogin() {

        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, benutzername(), passwort());

        Intent intentSendTextToWidget = new Intent(MainActivity.this,WidgetProvider1.class);
        intentSendTextToWidget.setAction(AppWidgetManager.EXTRA_CUSTOM_EXTRAS);
        intentSendTextToWidget.putExtra("benutzername",benutzername());
        sendBroadcast(intentSendTextToWidget);

    }

    public String benutzername(){

        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prefBenutzernameKey = getString(R.string.login_benutzername_key);
        String prefBenutzernameDefault = getString(R.string.login_benutzername_default);
        String benutzername = sPrefs.getString(prefBenutzernameKey,prefBenutzernameDefault);

        return benutzername;
    }
    public String passwort(){

        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prefPasswortKey = getString(R.string.login_passwort_key);
        String prefPasswortDefault = getString(R.string.login_passwort_default);
        String passwort = sPrefs.getString(prefPasswortKey,prefPasswortDefault);

        return passwort;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long t = System.currentTimeMillis();
            if (t - backPressedTime > 2000) {    // 2 secs
                backPressedTime = t;
                Vibrator v = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);
                Toast.makeText(this, "Zum beenden 2 mal drücken", Toast.LENGTH_SHORT).show();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_login) {

            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment99);
            fragtrans.commit();

            return true;
        }else if (id == R.id.action_settings){

            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment98);
            fragtrans.commit();

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_tipps) {
            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment1);
            fragtrans.commit();
        } else if (id == R.id.nav_tabelle) {
            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment2);
            fragtrans.commit();
        } else if (id == R.id.nav_ubersicht) {
            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment3);
            fragtrans.commit();
        } else if (id == R.id.nav_admin) {
            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment4);
            fragtrans.commit();
        } else if (id == R.id.nav_anleitung) {
            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment5);
            fragtrans.commit();
        } else if (id == R.id.nav_hilfe) {
            fragmanager = getFragmentManager();
            fragtrans = fragmanager.beginTransaction();
            fragtrans.replace(R.id.HauptLayout, fragment6);
            fragtrans.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    UpdateApp atualizaApp = new UpdateApp();
                    atualizaApp.setContext(getApplicationContext());
                    atualizaApp.execute("https://tipp.gozillabiene.net/androidAPI/version/","F22-Tippspiel_v"+neueVersion);

                }else{
                    this.finish();
                }
            }

        }
    }

    public void versionsKontrolle(){

        String version = getString(R.string.versionName);
        String output=null;
        try {
            output = new AuslesenWeb()
                    .execute("","","version","","")
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        output=ConnecttoGet.callWeb(context,"-","-","version","-","-");

        String[] result1 = output.split("\\.");
        String[] result2 = version.split("\\.");

        int a = Integer.parseInt(result1[0].replaceAll("[\\D]", ""));
        int b = Integer.parseInt(result2[0].replaceAll("[\\D]", ""));
        int c = Integer.parseInt(result1[1].replaceAll("[\\D]", ""));
        int d = Integer.parseInt(result1[2].replaceAll("[\\D]", ""));

        int dieseVersion=(Integer.parseInt(result2[0])*100)+(Integer.parseInt(result2[1])*10)+Integer.parseInt(result2[2]);
        neueVersion =(a*100)+(c*10)+d;


        if ( a != b ) {
            UpdateDialoge.neueHauptversion(context,version,neueVersion,a,c,d,MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            //mProgressDialog.dismiss();
        }else if(dieseVersion < neueVersion){
            UpdateDialoge.neueVersion(context,version,neueVersion,a,c,d,MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            //mProgressDialog.dismiss();
        }
    }

    public void starteOberflaeche(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        fragment0 = (Fragment0_Home) Fragment.instantiate(this, Fragment0_Home.class.getName(), null);
        fragment1 = (Fragment1_Tipps) Fragment.instantiate(this, Fragment1_Tipps.class.getName(), null);
        fragment2 = (Fragment2_Tabelle) Fragment.instantiate(this, Fragment2_Tabelle.class.getName(), null);
        fragment3 = (Fragment3_Ubersicht) Fragment.instantiate(this, Fragment3_Ubersicht.class.getName(), null);
        fragment4 = (Fragment4_Admin) Fragment.instantiate(this, Fragment4_Admin.class.getName(), null);
        fragment5 = (Fragment5_Anleitung) Fragment.instantiate(this, Fragment5_Anleitung.class.getName(), null);
        fragment6 = (Fragment6_Hilfe) Fragment.instantiate(this, Fragment6_Hilfe.class.getName(), null);
        fragment98 = (Fragment98_Einstellungen) Fragment.instantiate(this, Fragment98_Einstellungen.class.getName(), null);
        fragment99 = (Fragment99_Login) Fragment.instantiate(this, Fragment99_Login.class.getName(), null);

        fragmanager = getFragmentManager();
        fragtrans = fragmanager.beginTransaction();
        fragtrans.add(R.id.HauptLayout, fragment0);
        fragtrans.commit();

    }
    public int spielTag (){
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
        return Integer.parseInt(output);
    }
    @Override
    public void onPause() {
        super.onPause();

    }

    public void starteService(){
        Intent wtdSServiceIntent = new Intent(this, NotificationDisplayService.class);
        PendingIntent wtdSServicePendingIntent = PendingIntent.getService(this, 0, wtdSServiceIntent, 0);

        //Wie gross soll der Intervall sein?
        //long interval = DateUtils.MINUTE_IN_MILLIS * 60; // Alle 60 Minuten
        long interval = DateUtils.HOUR_IN_MILLIS * 24; // einmal am Tag

        //Wann soll der Service das erste Mal gestartet werden?
        //long firstStart = System.currentTimeMillis() ;
        //long firstStart = FunktionenAllgemein.getNextHour(); // start jede std.
        long firstStart = FunktionenAllgemein.getFirstTime(); // Start um 10 Uhr

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //am.set(AlarmManager.RTC, firstStart, wtdSServicePendingIntent);
        //am.setRepeating(AlarmManager.RTC_WAKEUP, firstStart, interval, wtdSServicePendingIntent);
        am.setInexactRepeating(AlarmManager.RTC, firstStart, interval, wtdSServicePendingIntent);

    }

}
