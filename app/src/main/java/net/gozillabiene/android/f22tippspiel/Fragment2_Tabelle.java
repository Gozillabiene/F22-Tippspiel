package net.gozillabiene.android.f22tippspiel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.concurrent.ExecutionException;


public class Fragment2_Tabelle extends Fragment {
    public String item;
    public ScrollView layMain;
    public TableGenerator mTable;


    public View fraglayoutv2;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fraglayoutv2 = inflater.inflate(R.layout.frag2_tabelle_layout, null);

        String ben=((MainActivity)getActivity()).benutzername();
        String pass= FunktionenAllgemein.md5(((MainActivity)getActivity()).passwort());

        if(FunktionenAllgemein.isURLReachable(getActivity())) {

            try {
                datenSenden(ben, pass, "tabelle", "m", FunktionenAllgemein.getSaison(getActivity()));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return fraglayoutv2;
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
        result[0]=result[0].replaceAll("[\\D]","");


        mTable = new TableGenerator(getActivity());
        layMain = (ScrollView)fraglayoutv2.findViewById(R.id.table);

        Integer i = 0;
        String[] Row0 = {"Pl.", "Mannschaft", "Sp.", "S", "U", "N", "Tore", "diff.", "Pkt." };
        String[] Row1 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row2 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row3 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row4 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row5 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row6 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row7 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row8 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row9 = {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row10= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row11= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row12= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row13= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row14= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row15= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row16= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row17= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };i+=9;
        String[] Row18= {result[i], result[1+i], result[2+i], result[3+i], result[4+i], result[5+i], result[6+i], result[7+i], result[8+i] };

        mTable.addRow(Row0,0);
        mTable.addRow(Row1,2);
        mTable.addRow(Row2,1);
        mTable.addRow(Row3,2);
        mTable.addRow(Row4,1);
        mTable.addRow(Row5,2);
        mTable.addRow(Row6,1);
        mTable.addRow(Row7,2);
        mTable.addRow(Row8,1);
        mTable.addRow(Row9,2);
        mTable.addRow(Row10,1);
        mTable.addRow(Row11,2);
        mTable.addRow(Row12,1);
        mTable.addRow(Row13,2);
        mTable.addRow(Row14,1);
        mTable.addRow(Row15,2);
        mTable.addRow(Row16,1);
        mTable.addRow(Row17,2);
        mTable.addRow(Row18,1);

        layMain.removeAllViews();
        layMain.addView(mTable.getTable());
    }

}