package net.gozillabiene.android.f22tippspiel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class Fragment0_Home extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fraglayoutv0 = inflater.inflate(R.layout.frag0_home_layout, null);


        String ben=((MainActivity)getActivity()).benutzername();
        String pass= FunktionenAllgemein.md5(((MainActivity)getActivity()).passwort());

        if(FunktionenAllgemein.isURLReachable(getActivity())) {
            String output = null;
            try {
                output = FunktionenAllgemein.datenSenden(ben,pass,"naechstes_spiel","1",getString(R.string.saison));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] result = output.split("#@@#");
            TextView tv = (TextView) fraglayoutv0.findViewById(R.id.textView8);
            tv.setText("Nächstes Spiel beginnt am : \n"+FunktionenAllgemein.getDate(result[0]));

        }

            return fraglayoutv0;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

}
