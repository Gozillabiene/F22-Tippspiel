package net.gozillabiene.android.f22tippspiel;


import android.os.AsyncTask;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class AuslesenWeb extends AsyncTask<String,Integer,String> {


    protected String doInBackground(String... params) {

        String benutzer=params[0];
        String pass=params[1];
        String url=params[2];
        String item = params[3];
        String saison = params[4];

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://tipp.gozillabiene.net/androidAPI/"+url+"/"+url+".php?item="+item+"&ben="+benutzer+"&pass="+pass+"&saison="+saison);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        try {
            String response_str = client.execute(request, responseHandler);

            return response_str;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String result) {
        //super.onPostExecute(result);


    }

}