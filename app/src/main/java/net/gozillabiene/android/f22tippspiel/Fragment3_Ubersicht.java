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

import java.util.Arrays;
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
                        if(FunktionenAllgemein.isURLReachable(getActivity())) {

                            datenSenden(ben, pass, "ubersicht", item, FunktionenAllgemein.getSaison(getActivity()).replaceAll("[\\D]", ""));
                        }
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

    public String punkte(String spiel,String tipp){
        String ergReturn="";
        String[] spielerg=null;
        spielerg = spiel.replaceAll(" ", "").split(":");
        if(spielerg.length>1) {
            int ergHeim = Integer.parseInt(spielerg[0]);
            int ergGast = Integer.parseInt(spielerg[1]);

            String[] qw1 = tipp.split(" ");
            String[] qw2 = qw1[0].split("\\(");
            String[] qw3 = qw1[2].split("\\)");
            int tippHeim = Integer.parseInt(qw2[1]);
            int tippGast = Integer.parseInt(qw3[0]);

            if(tippHeim != tippGast && ergHeim != ergGast) {
                if      (tippHeim >  tippGast && ergHeim >  ergGast){ergReturn = "1";}
                else if (tippHeim <  tippGast && ergHeim <  ergGast){ergReturn = "1";}
                else if (tippHeim <  tippGast && ergHeim <= ergGast){ergReturn = "1";}
                else if (tippHeim <= tippGast && ergHeim <  ergGast){ergReturn = "1";}
                else if (tippHeim >  tippGast && ergHeim >= ergGast){ergReturn = "1";}
                else if (tippHeim >= tippGast && ergHeim >  ergGast){ergReturn = "1";}
            }
            if (ergHeim == ergGast && tippHeim == tippGast){
                ergReturn = "2";
            }
            if(ergHeim - ergGast != 0) {
                if (tippHeim - ergHeim == tippGast - ergGast) {
                    ergReturn = "3";
                }
            }
            if (tippHeim == ergHeim && tippGast == ergGast) {
                ergReturn = "5";
                if(qw3.length>1){ergReturn="7";}
            }

        }

        return ergReturn;
    }
    public static int [] removeDuplicates (int ... as) {
        boolean[] isDuplicate = new boolean[as.length];
        int numDeleted = 0;
        for(int i = 0; i < as.length -1; i++) {
            for(int j = i+1; j < as.length; j++) {
                if (as[i] == as[j] && ! isDuplicate[j]) {
                    numDeleted++;
                    isDuplicate[j] = true;
                }
            }
        }
        int[] result = new int[as.length - numDeleted];
        for(int i = 0, j=0; i < as.length; i++) {
            if (! isDuplicate[i]) {
                result[j++] = as[i];
            }
        }
        return result;
    }
    private void showTable(String input) {


        String[] result=null;
        result = input.split("#@@#");

        int [] si = new int[10];int [] un = new int[10];int [] ni = new int[10];int[] gesPunkte = new int[35];
        String [] sun = new String[10];
        sun[0]="";
        sun[1]="";
        sun[2]="";
        sun[3]="";
        sun[4]="";
        sun[5]="";
        sun[6]="";
        sun[7]="";
        sun[8]="";

        String punkte;
        int a1;int a2;
        int zz =10; // ergebnis erstes spiel
        int yy =20; // beginn erster Tipp
        for(int i=0;i<=35;i++) {
            if((yy+13 < result.length) && (result[yy+13] != null)) {

                for (int q = 0; q < 9; q++) {
                    String[] qw1 = result[yy + q].split(" ");
                    String[] qw2 = qw1[0].split("\\(");
                    String[] qw3 = qw1[2].split("\\)");

                    if(!qw2[1].equals("-") || !qw3[0].equals("-")) {
                        a1 = Integer.parseInt(qw2[1]);
                        a2 = Integer.parseInt(qw3[0]);

                        if (a1 == a2) {
                            un[q]++;
                        } else if (a1 > a2) {
                            si[q]++;
                        } else if (a1 < a2) {
                            ni[q]++;
                        }
                        sun[q]="";
                        sun[q] = si[q] + "-" + un[q] + "-" + ni[q];

                        punkte=punkte(result[zz+q],result[yy+q]);

                        if (!punkte.equals("")) {
                            gesPunkte[i] += Integer.parseInt(punkte);
                        }
                        String[] ee = result[yy+q].split("\\)");

                        if(ee.length==2){
                            result[yy+q] = ee[0]+") " + punkte+"**";
                        }else {
                            result[yy + q] = result[yy + q] + " " + punkte;
                        }
                    }
                }
            }
            yy=yy+14;
        }

        mTable1 = new TableGenerator(getActivity());
        layMain1 = (HorizontalScrollView)fraglayoutv3.findViewById(R.id.table1);


        String[] row1 = {"Spiel",result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9]," "," "," "," "};mTable1.addRow(row1,0);
        String[] row2 = {"Erg.",result[10],result[11],result[12],result[13],result[14],result[15],result[16],result[17],result[18]," "," "," "," "};mTable1.addRow(row2,0);
        String[] row3 = {"SUN",sun[0],sun[1],sun[2],sun[3],sun[4],sun[5],sun[6],sun[7],sun[8], "Pl.", "Pkt.", " ", " " };mTable1.addRow(row3,0);

        int xx=19;
        int farbe = 1;
        String [] [] row = new String[55] [14];

//        int[] sortGesPunkte={1,2,3,4,5};//gesPunkte;

        int[] sortGesPunkte = Arrays.copyOf(gesPunkte,gesPunkte.length);

        Arrays.sort(sortGesPunkte);

        int temp;
        for( int k = 0; k < sortGesPunkte.length/2; ++k )
        {
            temp = sortGesPunkte[k];
            sortGesPunkte[k] = sortGesPunkte[sortGesPunkte.length - k - 1];
            sortGesPunkte[sortGesPunkte.length - k - 1] = temp;
        }
        int[] ergPlatz = removeDuplicates(sortGesPunkte);
        String platzfarbe;
        for(int i=0;i<=35;i++) {
            if((xx+13 < result.length) && (result[xx+13] != null)) {
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

                for (int j = 0; j < gesPunkte.length; j++){
                    if (gesPunkte[i] == sortGesPunkte[j]) {
                        if(j-1>=0) {
                            if (gesPunkte[i] != sortGesPunkte[j - 1]) {
                                result[xx + 10] = String.valueOf(j + 1);
                            }
                        }else{
                            result[xx + 10] = String.valueOf(j + 1);
                        }
                    }
                }
                if(result[xx+10].equals("1")){platzfarbe="##1";
                }else if(result[xx+10].equals("2")){platzfarbe="##2";
                }else if(result[xx+10].equals("3")){platzfarbe="##3";
                }else{platzfarbe="";}

                row[i][10] = result[xx+10]+platzfarbe;
                row[i][11] = String.valueOf(gesPunkte[i])+platzfarbe; //result[xx+11];
                row[i][12] = "";//result[xx+12];
                row[i][13] = "";//result[xx+13];
                xx = xx + 14;
                mTable1.addRow(row[i], farbe);
                if(farbe==1){farbe=2;}else{farbe=1;}
            }
        }

        layMain1.removeAllViews();
        layMain1.addView(mTable1.getTable());
    }

}