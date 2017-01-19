package net.gozillabiene.android.f22tippspiel;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment100_nichts  extends Fragment {
    public View fraglayoutv100;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fraglayoutv100 = inflater.inflate(R.layout.frag100_nichts, null);

        Activity activity = getActivity();
        if(activity instanceof MainActivity) {
            MainActivity myactivity = (MainActivity) activity;
            myactivity.fragmanager = getFragmentManager();
            myactivity.fragtrans = myactivity.fragmanager.beginTransaction();
            myactivity.fragtrans.replace(R.id.HauptLayout, myactivity.fragment99);
            myactivity.fragtrans.commit();
        }


        return fraglayoutv100;
    }
}
