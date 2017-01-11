package net.gozillabiene.android.f22tippspiel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.concurrent.ExecutionException;


public class Fragment3_Ubersicht extends Fragment {
    public HorizontalScrollView layMain1;
    public TableGenerator mTable1;
    public String item;

    public View fraglayoutv3;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        fraglayoutv3=inflater.inflate(R.layout.frag3_ubersicht_layout, null);


        Spinner sp = (Spinner) fraglayoutv3.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( getActivity() , R.array.spn_spieltag, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setSelection(((MainActivity)getActivity()).aktuellerSpieltag);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id) {
                item = ""+position;


                ScrollView sv1 =(ScrollView)getActivity().findViewById(R.id.sv_oben);
                if(item.equals("0")){sv1.setVisibility(View.INVISIBLE);}else{sv1.setVisibility(View.VISIBLE);}

//                loescheFelder();
                try {
                    String ben=((MainActivity)getActivity()).benutzername();
                    String pass= FunktionenAllgemein.md5(((MainActivity)getActivity()).passwort());

                    if(position >= 1) {
                        datenSenden(ben, pass, "ubersicht", item, getString(R.string.saison));
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



        return fraglayoutv3;
    }

    public void datenSenden(String benutzer,String pass,String url,String item,String saison) throws ExecutionException, InterruptedException {

        String output=null;
        output = new AuslesenWeb()
                .execute(benutzer,pass,url,item,saison)
                .get();

        showTable(output);

    }

    private void showTable(String input) {

        String[] result=null;
        result = input.split("#@@#");
//        result[0]=result[0].replaceAll("[\\D]","");
System.out.println(input);

        mTable1 = new TableGenerator(getActivity());
        layMain1 = (HorizontalScrollView)fraglayoutv3.findViewById(R.id.table1);

        String[] row1 = {"Spiel",result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9]," "," "," "," "};mTable1.addRow(row1,0);
        String[] row2 = {"Erg.",result[10],result[11],result[12],result[13],result[14],result[15],result[16],result[17],result[18]," "," "," "," "};mTable1.addRow(row2,0);
        String[] row3 = {" ", " "," "," "," "," "," "," "," "," ", "Pl.", "Pkt.", "Pl.", "Pkt." };mTable1.addRow(row3,0);

        int xx=19;
        int farbe = 1;
        String [] [] row = new String[55] [14];

        for(int i=0;i<=35;i++) {
            if((xx+13 < result.length) && (result[xx+13] != null)) {
                row[i][0] = result[xx];
                row[i][1] = result[xx+1];
                row[i][2] = result[xx+2];
                row[i][3] = result[xx+3];
                row[i][4] = result[xx+4];
                row[i][5] = result[xx+5];
                row[i][6] = result[xx+6];
                row[i][7] = result[xx+7];
                row[i][8] = result[xx+8];
                row[i][9] = result[xx+9];
                row[i][10] = result[xx+10];
                row[i][11] = result[xx+11];
                row[i][12] = result[xx+12];
                row[i][13] = result[xx+13];
                xx = xx + 14;
                mTable1.addRow(row[i], farbe);
                if(farbe==1){farbe=2;}else{farbe=1;}
            }
        }

        layMain1.removeAllViews();
        layMain1.addView(mTable1.getTable());
    }

}