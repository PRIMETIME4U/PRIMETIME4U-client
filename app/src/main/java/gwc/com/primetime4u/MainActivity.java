package gwc.com.primetime4u;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class MainActivity extends FragmentActivity {



    //VERSIONE CONDIVISA SU GITHUB, 07.12.2014 ore 13.51
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        Fragment button = new ButtonLista();
        if (savedInstanceState == null) {
            //inserisco il fragment del bottone nel layout container (inserirò altri fragment)
            ft.add(R.id.container, button);
            ft.addToBackStack(null);
            ft.commit();
        }

        //CHIEDI AL SERVER LISTA FILM VISTI -> ACTIVITY LISTA FILM

        //CHIEDI AL SERVER PROGRAMMAZIONE DI STASERA

        //VISUALIZZA FILM DI STASERA -> FRAGMENTS?

        //ASSOCIA A OGNI FRAGMENT LISTENER -> activity film cliccato -> lo guardo/non lo guardo




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
