package net.gozillabiene.android.f22tippspiel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class Fragment7_Zusatztipp extends Fragment implements View.OnClickListener{
    public String item;
    public TableGenerator mTable1;
    public ScrollView layMain1;
    Button btn_speichern;
    Spinner[] spinner = new Spinner[19];

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fraglayoutv7 = inflater.inflate(R.layout.frag7_zusatztipp_layout, null);

        btn_speichern = (Button) fraglayoutv7.findViewById(R.id.btn_speichern);
        btn_speichern.setOnClickListener(this);

        Spinner sp = (Spinner) fraglayoutv7.findViewById(R.id.spinner_zusatz);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( getActivity() , R.array.spn_zusatz, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);
        //sp.setSelection(((MainActivity)getActivity()).aktuellerSpieltag);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id) {
                item = ""+position;


                ScrollView sv2 =(ScrollView)getActivity().findViewById(R.id.sv2);
                ScrollView sv3 =(ScrollView)getActivity().findViewById(R.id.sv3);
                if(item.equals("0")){
                    sv2.setVisibility(View.INVISIBLE);
                    sv3.setVisibility(View.INVISIBLE);
                }else{
                    sv2.setVisibility(View.VISIBLE);
                    sv3.setVisibility(View.VISIBLE);
                }

                //loescheFelder();
                try {
                    String ben=((MainActivity)getActivity()).benutzername();
                    String pass= FunktionenAllgemein.md5(((MainActivity)getActivity()).passwort());

                    if(FunktionenAllgemein.isURLReachable(getActivity())) {

                        datenSenden(ben, pass, "zusatz", item, FunktionenAllgemein.getSaison(getActivity()).replaceAll("[\\D]", ""));
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

        return fraglayoutv7;
    }


    public void datenSenden(String benutzer,String pass,String url,String item,String saison) throws ExecutionException, InterruptedException {

        String output=null;
        output = new AuslesenWeb()
                .execute(benutzer,pass,url,item,saison)
                .get();

        if(!item.equals("0")){
            datenAufSplitten(output);
        }

    }

    public void datenAufSplitten(String input){

        String[] result = input.split("#@@#");
        int laenge = result.length;
        String[] vereine = new String[19];
        vereine[0]="Bitte Auswählen.";

        for(int i=0;i<18;i++){
            vereine[i+1]=result[i];
        }

        ScrollView sv2 =(ScrollView)getActivity().findViewById(R.id.sv2);
        HorizontalScrollView sv3 =(HorizontalScrollView)getActivity().findViewById(R.id.hsv1);

        if(result[36].equals("true")) {

            sv2.setVisibility(View.VISIBLE);
            sv3.setVisibility(View.GONE);

            spinner[1] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz1);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[1].setAdapter(adapter1);
            spinner[2] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz2);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[2].setAdapter(adapter2);
            spinner[3] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz3);
            ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[3].setAdapter(adapter3);
            spinner[4] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz4);
            ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[4].setAdapter(adapter4);
            spinner[5] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz5);
            ArrayAdapter<String> adapter5 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[5].setAdapter(adapter5);
            spinner[6] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz6);
            ArrayAdapter<String> adapter6 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[6].setAdapter(adapter6);
            spinner[7] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz7);
            ArrayAdapter<String> adapter7 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[7].setAdapter(adapter7);
            spinner[8] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz8);
            ArrayAdapter<String> adapter8 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[8].setAdapter(adapter8);
            spinner[9] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz9);
            ArrayAdapter<String> adapter9 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[9].setAdapter(adapter9);
            spinner[10] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz10);
            ArrayAdapter<String> adapter10 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[10].setAdapter(adapter10);
            spinner[11] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz11);
            ArrayAdapter<String> adapter11 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[11].setAdapter(adapter11);
            spinner[12] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz12);
            ArrayAdapter<String> adapter12 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[12].setAdapter(adapter12);
            spinner[13] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz13);
            ArrayAdapter<String> adapter13 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[13].setAdapter(adapter13);
            spinner[14] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz14);
            ArrayAdapter<String> adapter14 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[14].setAdapter(adapter14);
            spinner[15] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz15);
            ArrayAdapter<String> adapter15 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[15].setAdapter(adapter15);
            spinner[16] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz16);
            ArrayAdapter<String> adapter16 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[16].setAdapter(adapter16);
            spinner[17] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz17);
            ArrayAdapter<String> adapter17 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[17].setAdapter(adapter17);
            spinner[18] = (Spinner) getActivity().findViewById(R.id.spinner_zusatz18);
            ArrayAdapter<String> adapter18 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, vereine);
            adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner[18].setAdapter(adapter18);

            for (int i = 1; i < 19; i++) {
                if (result[18].equals(vereine[i])) {spinner[1].setSelection(i);kontrolle(i,1);}
                if (result[19].equals(vereine[i])) {spinner[2].setSelection(i);}
                if (result[20].equals(vereine[i])) {spinner[3].setSelection(i);}
                if (result[21].equals(vereine[i])) {spinner[4].setSelection(i);}
                if (result[22].equals(vereine[i])) {spinner[5].setSelection(i);}
                if (result[23].equals(vereine[i])) {spinner[6].setSelection(i);}
                if (result[24].equals(vereine[i])) {spinner[7].setSelection(i);}
                if (result[25].equals(vereine[i])) {spinner[8].setSelection(i);}
                if (result[26].equals(vereine[i])) {spinner[9].setSelection(i);}
                if (result[27].equals(vereine[i])) {spinner[10].setSelection(i);}
                if (result[28].equals(vereine[i])) {spinner[11].setSelection(i);}
                if (result[29].equals(vereine[i])) {spinner[12].setSelection(i);}
                if (result[30].equals(vereine[i])) {spinner[13].setSelection(i);}
                if (result[31].equals(vereine[i])) {spinner[14].setSelection(i);}
                if (result[32].equals(vereine[i])) {spinner[15].setSelection(i);}
                if (result[33].equals(vereine[i])) {spinner[16].setSelection(i);}
                if (result[34].equals(vereine[i])) {spinner[17].setSelection(i);}
                if (result[35].equals(vereine[i])) {spinner[18].setSelection(i);}
            }
        }else{
            sv2.setVisibility(View.GONE);
            sv3.setVisibility(View.VISIBLE);

            String [] [] row = new String[55] [20];
            int xx = 37;
            int farbe = 1;
            mTable1 = new TableGenerator(getActivity());
            layMain1 = (ScrollView)getActivity().findViewById(R.id.sv3);

            String[] row1 = {"Platz","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",""};mTable1.addRow(row1,0);
            String[] row2 = {"Name"," "," ","","","","","","",""," "," "," "," ","","","","","","Pkt."};mTable1.addRow(row2,0);

            for(int i=0;i<=35;i++) {
                if((xx+19 < result.length) && (result[xx+19] != null)) {
                    row[i][0] = result[xx];
                    row[i][1] = result[xx + 1];
                    row[i][2] = result[xx + 2];
                    row[i][3] = result[xx + 3];
                    row[i][4] = result[xx + 4];
                    row[i][5] = result[xx + 5];
                    row[i][6] = result[xx + 6];
                    row[i][7] = result[xx + 7];
                    row[i][8] = result[xx + 8];
                    row[i][9] = result[xx + 9];
                    row[i][10] = result[xx + 10];
                    row[i][11] = result[xx + 11];
                    row[i][12] = result[xx + 12];
                    row[i][13] = result[xx + 13];
                    row[i][14] = result[xx + 14];
                    row[i][15] = result[xx + 15];
                    row[i][16] = result[xx + 16];
                    row[i][17] = result[xx + 17];
                    row[i][18] = result[xx + 18];
                    row[i][19] = result[xx + 19];
                    xx = xx + 20;
                    mTable1.addRow(row[i], farbe);
                    if(farbe==1){farbe=2;}else{farbe=1;}
                }
            }
            String[] row3 = {"Tab.",result[laenge-18],result[laenge-17],result[laenge-16],result[laenge-15],result[laenge-14],result[laenge-13],result[laenge-12],result[laenge-11],result[laenge-10],result[laenge-9],result[laenge-8],result[laenge-7],result[laenge-6],result[laenge-5],result[laenge-4],result[laenge-3],result[laenge-2],result[laenge-1],""};mTable1.addRow(row3,0);

            layMain1.removeAllViews();
            layMain1.addView(mTable1.getTable());


        }
    }

    public void kontrolle(int item,int spinn){
        for (int i = 1; i < 19; i++) {
            //spinner[spinn].

        }

    }


    public void speichern(){

        Spinner sp0 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz);String text0 = sp0.getSelectedItem().toString();
        Spinner sp1 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz1);String text1 = sp1.getSelectedItem().toString();
        Spinner sp2 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz2);String text2 = sp2.getSelectedItem().toString();
        Spinner sp3 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz3);String text3 = sp3.getSelectedItem().toString();
        Spinner sp4 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz4);String text4 = sp4.getSelectedItem().toString();
        Spinner sp5 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz5);String text5 = sp5.getSelectedItem().toString();
        Spinner sp6 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz6);String text6 = sp6.getSelectedItem().toString();
        Spinner sp7 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz7);String text7 = sp7.getSelectedItem().toString();
        Spinner sp8 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz8);String text8 = sp8.getSelectedItem().toString();
        Spinner sp9 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz9);String text9 = sp9.getSelectedItem().toString();
        Spinner sp10 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz10);String text10 = sp10.getSelectedItem().toString();
        Spinner sp11 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz11);String text11 = sp11.getSelectedItem().toString();
        Spinner sp12 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz12);String text12 = sp12.getSelectedItem().toString();
        Spinner sp13 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz13);String text13 = sp13.getSelectedItem().toString();
        Spinner sp14 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz14);String text14 = sp14.getSelectedItem().toString();
        Spinner sp15 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz15);String text15 = sp15.getSelectedItem().toString();
        Spinner sp16 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz16);String text16 = sp16.getSelectedItem().toString();
        Spinner sp17 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz17);String text17 = sp17.getSelectedItem().toString();
        Spinner sp18 = (Spinner) getActivity().findViewById(R.id.spinner_zusatz18);String text18 = sp18.getSelectedItem().toString();


        String sep = "aasepaa";
        String punkt = "aapunktaa";
        String space = "aaspaceaa";
        String auml = "aaaumlaa";
        String ouml = "aaoumlaa";
        String uuml = "aauumlaa";

        String output1 = text0+sep+text1+sep+text2+sep+text3+sep+text4+sep+text5+sep+text6+sep+text7+sep+text8+sep+text9+sep+text10+sep;
        String output2 = output1+text11+sep+text12+sep+text13+sep+text14+sep+text15+sep+text16+sep+text17+sep+text18+sep;

        String newString1 = output2.replace(" ",space);
        String newString2 = newString1.replace(".",punkt);
        String newString3 = newString2.replace("ä",auml);
        String newString4 = newString3.replace("ö",ouml);
        String newString5 = newString4.replace("ü",uuml);

        String output=null;
        try {
            if(FunktionenAllgemein.isURLReachable(getActivity())) {
                String ben=((MainActivity)getActivity()).benutzername();
                String pass= FunktionenAllgemein.md5(((MainActivity)getActivity()).passwort());

                output = new AuslesenWeb()
                        .execute(ben, pass, "zusatz_speichern", newString5, FunktionenAllgemein.getSaison(getActivity()).replaceAll("[\\D]", ""))
                        .get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(output.replaceAll("[\\D]", "").equals("1")) {
            Toast.makeText(getActivity(), "Ok, gespeichert", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(), "Fehler, nicht gespeichert", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        int cid = v.getId();

        switch(cid) {
            case R.id.btn_speichern:
                // it was the first button
                speichern();
                break;
        }

    }
}
