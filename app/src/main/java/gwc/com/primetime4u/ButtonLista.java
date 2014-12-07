package gwc.com.primetime4u;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Giovanni on 06/12/2014.
 */
public class ButtonLista extends Fragment {
    //fragment che contiene un bottone
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //il fragment viene definito graficamente nel layout fragment_main
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button bt = (Button) view.findViewById(R.id.listafilm);
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ActivityListaFilm.class);
                startActivity(i);
            }
        });
        return view;
    }

    /*
     * Just an helper method to write the value received from the intent/action
     * into the TextView in the layout
     */
    public void setDescriptionIntoFragment(String s)
    {

    }

    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }
}
