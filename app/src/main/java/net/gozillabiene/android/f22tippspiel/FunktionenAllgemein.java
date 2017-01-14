package net.gozillabiene.android.f22tippspiel;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FunktionenAllgemein {

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDate(String timeStampStr){
        long timestamp = Long.parseLong(timeStampStr) * 1000L;


        try{
            DateFormat sdf = new SimpleDateFormat("EEE dd.MM.yyyy 'um' kk.mm");
            Date netDate = (new Date(timestamp));
            return sdf.format(netDate);
        } catch (Exception ignored) {
            return "xx";
        }
    }

    public static Long getTimestamp(String xtime){

        DateFormat dfm = new SimpleDateFormat("EEE dd.MM.yyyy 'um' kk.mm");

        long unixtime = 0;

        //dfm.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));//Specify your timezone
        try
        {
            unixtime = dfm.parse(xtime).getTime();
            unixtime=unixtime/1000;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return unixtime;
        //return String.valueOf(date.getTime());

    }

    public static  void checkPermissions(final Context context,final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
        MainActivity ma = (MainActivity) context;

        if (ContextCompat.checkSelfPermission(ma, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Achtung");
            builder.setMessage("Zugriff auf den externen Speicher wird benötigt um die Ergebnisse in eine Datei schreiben zu können.")
                    .setCancelable(false)
                    .setIcon(R.drawable.ic_error_outline_black_24dp)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public static void isOffline(final Context context){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Warnung !!");

        alertDialogBuilder
                .setMessage("Du bist momentan nicht.\n\n" +
                            "mit dem Internet verbundnen.\n\n" +
                            "Bitte sorge dafür das du eine \n\n" +
                            "Verbindung zum Internet herstellst,\n\n" +
                            "oder der Server vom Spiel ist Offline.\n\n" +
                            "Die App wird jetzt beendet.")
                .setIcon(R.drawable.ic_error_outline_black_24dp)
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                MainActivity ma = (MainActivity) context;
                                ma.finish();
                            }
                        }
                );
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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

                if (conn.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).

                    return true;
                } else {
                    isOffline(context);
                    return false;
                }
            } catch (MalformedURLException e1) {
                isOffline(context);
                return false;
            } catch (IOException e) {
                isOffline(context);
                return false;
            } catch (Exception ex) {
                return true;
            }
        }
        isOffline(context);
        return false;
    }
}