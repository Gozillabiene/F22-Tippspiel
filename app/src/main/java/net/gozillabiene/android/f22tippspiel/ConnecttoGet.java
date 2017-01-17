package net.gozillabiene.android.f22tippspiel;


import android.content.Context;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;


public class ConnecttoGet {

    public static String callWeb(Context context, String benutzer, String pass, String seite, String item, String saison) {

        String urlstring="https://tipp.gozillabiene.net/androidAPI/"+seite+"/"+seite+".php?item="+item+"&ben="+benutzer+"&pass="+pass+"&saison="+saison;
        String data = null;

        try {
            URL url = new URL(urlstring);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(1000 /* milliseconds */);
            conn.setConnectTimeout(5000 /* milliseconds */);
            conn.setRequestMethod("GET");

            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            InputStream stream = conn.getInputStream();

            data = convertStreamToString(stream);

            stream.close();

            conn.disconnect();

        } catch (SocketTimeoutException e) {

            Toast.makeText(context,"Sorry, network error",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}