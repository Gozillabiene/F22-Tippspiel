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
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class Fragment1_Tipps extends Fragment {
public String item;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fraglayoutv1 = inflater.inflate(R.layout.frag1_tipps_layout, null);


        Spinner sp = (Spinner) fraglayoutv1.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( getActivity() , R.array.spn_spieltag, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);
        sp.setSelection(((MainActivity)getActivity()).aktuellerSpieltag);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id) {
                item = ""+position;


                ScrollView sv1 =(ScrollView)getActivity().findViewById(R.id.sv1);
                if(item.equals("0")){sv1.setVisibility(View.INVISIBLE);}else{sv1.setVisibility(View.VISIBLE);}

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






        return fraglayoutv1;
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


    public void datenAufSplitten(String input){

        String[] result = input.split("#@@#");

//        for (int x=0; x<result.length; x++)
//            System.out.println(x+"->"+result[x]);


        int xx = 0;

        Button btn = (Button)getActivity().findViewById(R.id.btn_weg);

        RadioButtonGroup radioButtonGroup = new RadioButtonGroup();
        RadioButton btn1 = (RadioButton)getActivity().findViewById(R.id.rbj1);
        RadioButton btn2 = (RadioButton)getActivity().findViewById(R.id.rbj2);
        RadioButton btn3 = (RadioButton)getActivity().findViewById(R.id.rbj3);
        RadioButton btn4 = (RadioButton)getActivity().findViewById(R.id.rbj4);
        RadioButton btn5 = (RadioButton)getActivity().findViewById(R.id.rbj5);
        RadioButton btn6 = (RadioButton)getActivity().findViewById(R.id.rbj6);
        RadioButton btn7 = (RadioButton)getActivity().findViewById(R.id.rbj7);
        RadioButton btn8 = (RadioButton)getActivity().findViewById(R.id.rbj8);
        RadioButton btn9 = (RadioButton)getActivity().findViewById(R.id.rbj9);

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

        TextView tve1 =(TextView)getActivity().findViewById(R.id.erg1);
        TextView tve2 =(TextView)getActivity().findViewById(R.id.erg2);
        TextView tve3 =(TextView)getActivity().findViewById(R.id.erg3);
        TextView tve4 =(TextView)getActivity().findViewById(R.id.erg4);
        TextView tve5 =(TextView)getActivity().findViewById(R.id.erg5);
        TextView tve6 =(TextView)getActivity().findViewById(R.id.erg6);
        TextView tve7 =(TextView)getActivity().findViewById(R.id.erg7);
        TextView tve8 =(TextView)getActivity().findViewById(R.id.erg8);
        TextView tve9 =(TextView)getActivity().findViewById(R.id.erg9);

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


        radioButtonGroup.addRadioButton(btn1, false);
        radioButtonGroup.addRadioButton(btn2, false);
        radioButtonGroup.addRadioButton(btn3, false);
        radioButtonGroup.addRadioButton(btn4, false);
        radioButtonGroup.addRadioButton(btn5, false);
        radioButtonGroup.addRadioButton(btn6, false);
        radioButtonGroup.addRadioButton(btn7, false);
        radioButtonGroup.addRadioButton(btn8, false);
        radioButtonGroup.addRadioButton(btn9, false);

        int zaehler=0;
        String rest="0";
        tvz1.setText(FunktionenAllgemein.getDate(result[xx]));xx++;     // Zeit
        tvh1.setText(result[xx]);xx++;                                  // Heimmannschaft
        tvg1.setText(result[xx]);xx++;                                  // Gastmannschaft
        if(result[xx].equals("-")){result[xx]="";}
        edh1.setText(result[xx]);xx++;                                  // Tipp Heim
        if(result[xx].equals("-")){result[xx]="";}
        edg1.setText(result[xx]);xx++;                                  // Tipp Gast
        xx++;
        tve1.setText(result[xx-1]+" : "+result[xx]);                    // erg Spiel
        xx++;
        if(result[xx].equals("1")){rest="1";}                           // restliche Joker sperren
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh1.setEnabled(false);edg1.setEnabled(false);btn1.setEnabled(false);}else{edh1.setEnabled(true);edg1.setEnabled(true);btn1.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn1.setChecked(true);}else{btn1.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn1.setEnabled(false);}else{btn1.setEnabled(true);}
        xx++;

        tvz2.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh2.setText(result[xx]);xx++;
        tvg2.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh2.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg2.setText(result[xx]);xx++;
        xx++;
        tve2.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh2.setEnabled(false);edg2.setEnabled(false);btn2.setEnabled(false);}else{edh2.setEnabled(true);edg2.setEnabled(true);btn2.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn2.setChecked(true);}else{btn2.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn2.setEnabled(false);}else{btn2.setEnabled(true);}
        xx++;

        tvz3.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh3.setText(result[xx]);xx++;
        tvg3.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh3.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg3.setText(result[xx]);xx++;
        xx++;
        tve3.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh3.setEnabled(false);edg3.setEnabled(false);btn3.setEnabled(false);}else{edh3.setEnabled(true);edg3.setEnabled(true);btn3.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn3.setChecked(true);}else{btn3.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn3.setEnabled(false);}else{btn3.setEnabled(true);}
        xx++;

        tvz4.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh4.setText(result[xx]);xx++;
        tvg4.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh4.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg4.setText(result[xx]);xx++;
        xx++;
        tve4.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh4.setEnabled(false);edg4.setEnabled(false);btn4.setEnabled(false);}else{edh4.setEnabled(true);edg4.setEnabled(true);btn4.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn4.setChecked(true);}else{btn4.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn4.setEnabled(false);}else{btn4.setEnabled(true);}
        xx++;

        tvz5.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh5.setText(result[xx]);xx++;
        tvg5.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh5.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg5.setText(result[xx]);xx++;
        xx++;
        tve5.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh5.setEnabled(false);edg5.setEnabled(false);btn5.setEnabled(false);}else{edh5.setEnabled(true);edg5.setEnabled(true);btn5.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn5.setChecked(true);}else{btn5.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn5.setEnabled(false);}else{btn5.setEnabled(true);}
        xx++;

        tvz6.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh6.setText(result[xx]);xx++;
        tvg6.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh6.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg6.setText(result[xx]);xx++;
        xx++;
        tve6.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh6.setEnabled(false);edg6.setEnabled(false);btn6.setEnabled(false);}else{edh6.setEnabled(true);edg6.setEnabled(true);btn6.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn6.setChecked(true);}else{btn6.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn6.setEnabled(false);}else{btn6.setEnabled(true);}
        xx++;

        tvz7.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh7.setText(result[xx]);xx++;
        tvg7.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh7.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg7.setText(result[xx]);xx++;
        xx++;
        tve7.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh7.setEnabled(false);edg7.setEnabled(false);btn7.setEnabled(false);}else{edh7.setEnabled(true);edg7.setEnabled(true);btn7.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn7.setChecked(true);}else{btn7.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn7.setEnabled(false);}else{btn7.setEnabled(true);}
        xx++;

        tvz8.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh8.setText(result[xx]);xx++;
        tvg8.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh8.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg8.setText(result[xx]);xx++;
        xx++;
        tve8.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh8.setEnabled(false);edg8.setEnabled(false);btn8.setEnabled(false);}else{edh8.setEnabled(true);edg8.setEnabled(true);btn8.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn8.setChecked(true);}else{btn8.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn8.setEnabled(false);}else{btn8.setEnabled(true);}
        xx++;

        tvz9.setText(FunktionenAllgemein.getDate(result[xx]));xx++;
        tvh9.setText(result[xx]);xx++;
        tvg9.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edh9.setText(result[xx]);xx++;
        if(result[xx].equals("-")){result[xx]="";}
        edg9.setText(result[xx]);xx++;
        xx++;
        tve9.setText(result[xx-1]+" : "+result[xx]);
        xx++;
        if(result[xx].equals("1")){rest="1";}
        xx++;
        if(result[xx].equals("1") ){zaehler++;edh9.setEnabled(false);edg9.setEnabled(false);btn9.setEnabled(false);}else{edh9.setEnabled(true);edg9.setEnabled(true);btn9.setEnabled(true);}
        xx++;
        if(result[xx].equals("1")){btn9.setChecked(true);}else{btn9.setChecked(false);}
        if(rest.equals("1") || result[xx-1].equals("1")){btn9.setEnabled(false);}else{btn9.setEnabled(true);}
        xx++;

        if(zaehler==9){
            btn.setEnabled(false);
        }else{
            btn.setEnabled(true);
        }
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
