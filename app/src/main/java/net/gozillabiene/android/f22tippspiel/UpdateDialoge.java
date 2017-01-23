package net.gozillabiene.android.f22tippspiel;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class UpdateDialoge {

    public static void neueHauptversion(final Context context, String version,final int neueVersion, int a,int c,int d,final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE){

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Warnung !!");
        alertDialogBuilder
                .setMessage("Diese Version ist zu alt.\n\n" +
                        "Bitte besorge dir eine aktuelle Version.\n\n" +
                        "Deine Version : \t" + version + "\n" +
                        "Neue Version : \t" + a + "." + c + "." +d +"\n\n" +
                        "")
                .setIcon(R.drawable.ic_error_outline_black_24dp)
                .setCancelable(false)
                .setPositiveButton("Download",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity ma = (MainActivity) context;
                                FunktionenAllgemein.checkPermissions(context,MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                                UpdateApp atualizaApp = new UpdateApp();
                                atualizaApp.setContext(ma);
                                atualizaApp.execute("https://tipp.gozillabiene.net/androidAPI/version/","F22-Tippspiel_v"+neueVersion);

                            }
                        }
                );
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public static void neueVersion(final Context context, String version, final int neueVersion, int a, int c, int d, final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Information");
        alertDialogBuilder
                .setMessage("Es gibt eine neuere Version.\n\n" +
                        "du kannst dir die neue Version runterladen.\n\n" +
                        "Deine Version : \t" + version + "\n" +
                        "Neue Version : \t" + a + "." + c + "." +d +"\n\n")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setCancelable(false)
                .setPositiveButton("Download",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity ma = (MainActivity) context;
                        FunktionenAllgemein.checkPermissions(context,MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                        UpdateApp atualizaApp = new UpdateApp();
                        atualizaApp.setContext(ma);
                        atualizaApp.execute("https://tipp.gozillabiene.net/androidAPI/version/","F22-Tippspiel_v"+neueVersion);

                    }
                })
                .setNegativeButton("jetzt nicht",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("Cancel");
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public static void changeLog(final Context context, String version){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Changelog");
        alertDialogBuilder
                .setMessage("Neuerungen in der Version "+ version +"\n\n" +
                        "- Dieser Dialog :-)\n" +
                        "- Farben der Platzierungen in der Übersicht\n" +
                        "- kleine Fehler behoben\n")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setCancelable(true)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }



}
