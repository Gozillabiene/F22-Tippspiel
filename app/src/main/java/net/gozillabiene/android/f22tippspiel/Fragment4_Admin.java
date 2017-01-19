package net.gozillabiene.android.f22tippspiel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.ExecutionException;


public class Fragment4_Admin extends Fragment {
    public View fraglayoutv4;
    public String item;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fraglayoutv4 = inflater.inflate(R.layout.frag4_admin_layout, null);

        // prüfen ob admin
        if(FunktionenAllgemein.isURLReachable(getActivity())){

            String ben=((MainActivity)getActivity()).benutzername();
            String pass= FunktionenAllgemein.md5(((MainActivity)getActivity()).passwort());

            String output=null;
            try {
                output = new AuslesenWeb()
                        .execute(ben,pass,"ist_admin",item,getString(R.string.saison))
                        .get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            String[] result = output.split("#@@#");

            if(result[1].replaceAll("[\\D]", "").equals("1")){
                ScrollView sv1 =(ScrollView)fraglayoutv4.findViewById(R.id.sv1);
                sv1.setVisibility(View.VISIBLE);
                LinearLayout lo0 =(LinearLayout)fraglayoutv4.findViewById(R.id.lo0);
                lo0.setVisibility(View.VISIBLE);
                TextView tv =(TextView)fraglayoutv4.findViewById(R.id.zugriff);
                tv.setText("");
            }else{
                ScrollView sv1 =(ScrollView)fraglayoutv4.findViewById(R.id.sv1);
                sv1.setVisibility(View.INVISIBLE);
                LinearLayout lo0 =(LinearLayout)fraglayoutv4.findViewById(R.id.lo0);
                lo0.setVisibility(View.INVISIBLE);
                TextView tv =(TextView)fraglayoutv4.findViewById(R.id.zugriff);
                tv.setText("Zugriff Verweigert !");
            }

        }


        Spinner sp = (Spinner) fraglayoutv4.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( getActivity() , R.array.spn_spieltag, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);
        sp.setSelection(((MainActivity)getActivity()).aktuellerSpieltag);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id) {
                item = ""+position;


                ScrollView sv1 =(ScrollView)getActivity().findViewById(R.id.sv1);
//                if(item.equals("0")){sv1.setVisibility(View.INVISIBLE);}else{sv1.setVisibility(View.VISIBLE);}

                loescheFelder();
                try {
                    String ben=((MainActivity)getActivity()).benutzername();
                    String pass= FunktionenAllgemein.md5(((MainActivity)getActivity()).passwort());

                    if(FunktionenAllgemein.isURLReachable(getActivity())) {

                        datenSenden(ben, pass, "tipp_lesen", item, getString(R.string.saison));
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        return fraglayoutv4;
    }






    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }
    public void datenSenden(String benutzer,String pass,String url,String item,String saison) throws ExecutionException, InterruptedException {

        String output=null;
        output = new AuslesenWeb()
                .execute(benutzer,pass,url,item,saison)
                .get();

        if(!item.equals("0")){
            datenAufSplitten(output);
        }
//        TextView tv =(TextView)getActivity().findViewById(R.id.time1);
//        tv.setText(output);
    }
    public void datenAufSplitten(String input) {

        String[] result = input.split("#@@#");
        int xx=0;

        TextView tvz1 =(TextView)getActivity().findViewById(R.id.time1);
        TextView tvz2 =(TextView)getActivity().findViewById(R.id.time2);
        TextView tvz3 =(TextView)getActivity().findViewById(R.id.time3);
        TextView tvz4 =(TextView)getActivity().findViewById(R.id.time4);
        TextView tvz5 =(TextView)getActivity().findViewById(R.id.time5);
        TextView tvz6 =(TextView)getActivity().findViewById(R.id.time6);
        TextView tvz7 =(TextView)getActivity().findViewById(R.id.time7);
        TextView tvz8 =(TextView)getActivity().findViewById(R.id.time8);
        TextView tvz9 =(TextView)getActivity().findViewById(R.id.time9);

        TextView tvh1 =(TextView)getActivity().findViewById(R.id.tv_heim1);
        TextView tvh2 =(TextView)getActivity().findViewById(R.id.tv_heim2);
        TextView tvh3 =(TextView)getActivity().findViewById(R.id.tv_heim3);
        TextView tvh4 =(TextView)getActivity().findViewById(R.id.tv_heim4);
        TextView tvh5 =(TextView)getActivity().findViewById(R.id.tv_heim5);
        TextView tvh6 =(TextView)getActivity().findViewById(R.id.tv_heim6);
        TextView tvh7 =(TextView)getActivity().findViewById(R.id.tv_heim7);
        TextView tvh8 =(TextView)getActivity().findViewById(R.id.tv_heim8);
        TextView tvh9 =(TextView)getActivity().findViewById(R.id.tv_heim9);

        TextView tvg1 =(TextView)getActivity().findViewById(R.id.tv_gast1);
        TextView tvg2 =(TextView)getActivity().findViewById(R.id.tv_gast2);
        TextView tvg3 =(TextView)getActivity().findViewById(R.id.tv_gast3);
        TextView tvg4 =(TextView)getActivity().findViewById(R.id.tv_gast4);
        TextView tvg5 =(TextView)getActivity().findViewById(R.id.tv_gast5);
        TextView tvg6 =(TextView)getActivity().findViewById(R.id.tv_gast6);
        TextView tvg7 =(TextView)getActivity().findViewById(R.id.tv_gast7);
        TextView tvg8 =(TextView)getActivity().findViewById(R.id.tv_gast8);
        TextView tvg9 =(TextView)getActivity().findViewById(R.id.tv_gast9);

        EditText edh1 =(EditText)getActivity().findViewById(R.id.et_heim1);
        EditText edh2 =(EditText)getActivity().findViewById(R.id.et_heim2);
        EditText edh3 =(EditText)getActivity().findViewById(R.id.et_heim3);
        EditText edh4 =(EditText)getActivity().findViewById(R.id.et_heim4);
        EditText edh5 =(EditText)getActivity().findViewById(R.id.et_heim5);
        EditText edh6 =(EditText)getActivity().findViewById(R.id.et_heim6);
        EditText edh7 =(EditText)getActivity().findViewById(R.id.et_heim7);
        EditText edh8 =(EditText)getActivity().findViewById(R.id.et_heim8);
        EditText edh9 =(EditText)getActivity().findViewById(R.id.et_heim9);

        EditText edg1 =(EditText)getActivity().findViewById(R.id.et_gast1);
        EditText edg2 =(EditText)getActivity().findViewById(R.id.et_gast2);
        EditText edg3 =(EditText)getActivity().findViewById(R.id.et_gast3);
        EditText edg4 =(EditText)getActivity().findViewById(R.id.et_gast4);
        EditText edg5 =(EditText)getActivity().findViewById(R.id.et_gast5);
        EditText edg6 =(EditText)getActivity().findViewById(R.id.et_gast6);
        EditText edg7 =(EditText)getActivity().findViewById(R.id.et_gast7);
        EditText edg8 =(EditText)getActivity().findViewById(R.id.et_gast8);
        EditText edg9 =(EditText)getActivity().findViewById(R.id.et_gast9);

        int zaehler=0;
        int zeitSpanne = 60*60*24*2; // 2 Tage
        int aktZeit = Integer.parseInt(String.valueOf(System.currentTimeMillis()/1000L));
        int anfangSpiel = Integer.parseInt(result[xx]);
        int endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh1.setEnabled(false);edg1.setEnabled(false);}else{edh1.setEnabled(true);edg1.setEnabled(true);}
        tvz1.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh1.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg1.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh1.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg1.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh2.setEnabled(false);edg2.setEnabled(false);}else{edh2.setEnabled(true);edg2.setEnabled(true);}
        tvz2.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh2.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg2.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh2.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg2.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh3.setEnabled(false);edg3.setEnabled(false);}else{edh3.setEnabled(true);edg3.setEnabled(true);}
        tvz3.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh3.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg3.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh3.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg3.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh4.setEnabled(false);edg4.setEnabled(false);}else{edh4.setEnabled(true);edg4.setEnabled(true);}
        tvz4.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh4.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg4.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh4.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg4.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh5.setEnabled(false);edg5.setEnabled(false);}else{edh5.setEnabled(true);edg5.setEnabled(true);}
        tvz5.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh5.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg5.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh5.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg5.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh6.setEnabled(false);edg6.setEnabled(false);}else{edh6.setEnabled(true);edg6.setEnabled(true);}
        tvz6.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh6.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg6.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh6.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg6.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh7.setEnabled(false);edg7.setEnabled(false);}else{edh7.setEnabled(true);edg7.setEnabled(true);}
        tvz7.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh7.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg7.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh7.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg7.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh8.setEnabled(false);edg8.setEnabled(false);}else{edh8.setEnabled(true);edg8.setEnabled(true);}
        tvz8.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh8.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg8.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh8.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg8.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        anfangSpiel = Integer.parseInt(result[xx]);
        endZeit = anfangSpiel + (zeitSpanne);
        if(aktZeit < anfangSpiel || aktZeit > endZeit){zaehler++;edh9.setEnabled(false);edg9.setEnabled(false);}else{edh9.setEnabled(true);edg9.setEnabled(true);}
        tvz9.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh9.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg9.setText(result[xx]);xx++;xx++;xx++;                        // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh9.setText(result[xx]);xx++;                                  // Erg. Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg9.setText(result[xx]);xx++;xx++;xx++;xx++;                   // Erg. Gast

        Button btn_weg = (Button)fraglayoutv4.findViewById(R.id.btn_weg);
        if(zaehler==9){btn_weg.setEnabled(false);}else{btn_weg.setEnabled(true);}
    }

    public void loescheFelder(){

        TextView tvz1 =(TextView)getActivity().findViewById(R.id.time1);tvz1.setText("");
        TextView tvz2 =(TextView)getActivity().findViewById(R.id.time2);tvz2.setText("");
        TextView tvz3 =(TextView)getActivity().findViewById(R.id.time3);tvz3.setText("");
        TextView tvz4 =(TextView)getActivity().findViewById(R.id.time4);tvz4.setText("");
        TextView tvz5 =(TextView)getActivity().findViewById(R.id.time5);tvz5.setText("");
        TextView tvz6 =(TextView)getActivity().findViewById(R.id.time6);tvz6.setText("");
        TextView tvz7 =(TextView)getActivity().findViewById(R.id.time7);tvz7.setText("");
        TextView tvz8 =(TextView)getActivity().findViewById(R.id.time8);tvz8.setText("");
        TextView tvz9 =(TextView)getActivity().findViewById(R.id.time9);tvz9.setText("");

        TextView tvh1 =(TextView)getActivity().findViewById(R.id.tv_heim1);tvh1.setText("");
        TextView tvh2 =(TextView)getActivity().findViewById(R.id.tv_heim2);tvh2.setText("");
        TextView tvh3 =(TextView)getActivity().findViewById(R.id.tv_heim3);tvh3.setText("");
        TextView tvh4 =(TextView)getActivity().findViewById(R.id.tv_heim4);tvh4.setText("");
        TextView tvh5 =(TextView)getActivity().findViewById(R.id.tv_heim5);tvh5.setText("");
        TextView tvh6 =(TextView)getActivity().findViewById(R.id.tv_heim6);tvh6.setText("");
        TextView tvh7 =(TextView)getActivity().findViewById(R.id.tv_heim7);tvh7.setText("");
        TextView tvh8 =(TextView)getActivity().findViewById(R.id.tv_heim8);tvh8.setText("");
        TextView tvh9 =(TextView)getActivity().findViewById(R.id.tv_heim9);tvh9.setText("");

        TextView tvg1 =(TextView)getActivity().findViewById(R.id.tv_gast1);tvg1.setText("");
        TextView tvg2 =(TextView)getActivity().findViewById(R.id.tv_gast2);tvg2.setText("");
        TextView tvg3 =(TextView)getActivity().findViewById(R.id.tv_gast3);tvg3.setText("");
        TextView tvg4 =(TextView)getActivity().findViewById(R.id.tv_gast4);tvg4.setText("");
        TextView tvg5 =(TextView)getActivity().findViewById(R.id.tv_gast5);tvg5.setText("");
        TextView tvg6 =(TextView)getActivity().findViewById(R.id.tv_gast6);tvg6.setText("");
        TextView tvg7 =(TextView)getActivity().findViewById(R.id.tv_gast7);tvg7.setText("");
        TextView tvg8 =(TextView)getActivity().findViewById(R.id.tv_gast8);tvg8.setText("");
        TextView tvg9 =(TextView)getActivity().findViewById(R.id.tv_gast9);tvg9.setText("");

        EditText eth1 =(EditText)getActivity().findViewById(R.id.et_heim1);eth1.setText("");
        EditText eth2 =(EditText)getActivity().findViewById(R.id.et_heim2);eth2.setText("");
        EditText eth3 =(EditText)getActivity().findViewById(R.id.et_heim3);eth3.setText("");
        EditText eth4 =(EditText)getActivity().findViewById(R.id.et_heim4);eth4.setText("");
        EditText eth5 =(EditText)getActivity().findViewById(R.id.et_heim5);eth5.setText("");
        EditText eth6 =(EditText)getActivity().findViewById(R.id.et_heim6);eth6.setText("");
        EditText eth7 =(EditText)getActivity().findViewById(R.id.et_heim7);eth7.setText("");
        EditText eth8 =(EditText)getActivity().findViewById(R.id.et_heim8);eth8.setText("");
        EditText eth9 =(EditText)getActivity().findViewById(R.id.et_heim9);eth9.setText("");

        EditText etg1 =(EditText)getActivity().findViewById(R.id.et_gast1);etg1.setText("");
        EditText etg2 =(EditText)getActivity().findViewById(R.id.et_gast2);etg2.setText("");
        EditText etg3 =(EditText)getActivity().findViewById(R.id.et_gast3);etg3.setText("");
        EditText etg4 =(EditText)getActivity().findViewById(R.id.et_gast4);etg4.setText("");
        EditText etg5 =(EditText)getActivity().findViewById(R.id.et_gast5);etg5.setText("");
        EditText etg6 =(EditText)getActivity().findViewById(R.id.et_gast6);etg6.setText("");
        EditText etg7 =(EditText)getActivity().findViewById(R.id.et_gast7);etg7.setText("");
        EditText etg8 =(EditText)getActivity().findViewById(R.id.et_gast8);etg8.setText("");
        EditText etg9 =(EditText)getActivity().findViewById(R.id.et_gast9);etg9.setText("");

    }

}

