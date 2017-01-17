package net.gozillabiene.android.f22tippspiel;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateApp extends AsyncTask<String,Integer,Void> {
    ProgressDialog mProgressDialog;


    private Context context;
    public void setContext(Context contextf){
        context = contextf;
    }

    @Override
    protected Void doInBackground(String... arg0) {
        try {
            URL url = new URL(arg0[0]+arg0[1]+".apk");
            String app = arg0[1];

            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            String PATH = Environment.getExternalStorageDirectory().getPath()+"/Download/";
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, app+".apk");
            if(outputFile.exists()){
                outputFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();
            int fileLength = c.getContentLength();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            long total = 0;

            while ((len1 = is.read(buffer)) != -1) {
                total += len1;

                if (fileLength > 0){
                    publishProgress((int) (total * 100 / fileLength));
                }

                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(PATH+app+".apk")), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            context.startActivity(intent);


        } catch (Exception e) {
//            Toast.makeText(context,"Update Fehler : "+ e.getMessage(),Toast.LENGTH_LONG).show();
            Log.e("UpdateAPP", "Update error! " + e.getMessage());
        }
        mProgressDialog.dismiss();
        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        // Increment Progress Dialog with the Update
        // from the doInBackgroundMethod
        mProgressDialog.setProgress(values[0]);
        System.out.println(">>>>>>>>"+values[0]);
    }
    @Override
    protected void onPreExecute() {
        // Setup Progress Dialog
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("neue version : ");
        mProgressDialog.setTitle("Download");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mProgressDialog.setProgress(30);
    }
}