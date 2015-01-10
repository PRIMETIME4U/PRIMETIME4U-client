package gwc.com.primetime4u;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import primetime4u.adapter.CustomListAdapter;
import primetime4u.app.AppController;
import primetime4u.model.Movie;

import com.android.volley.toolbox.JsonObjectRequest;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.model.BasicButtonsCard;
import com.dexafree.materialList.model.BasicImageButtonsCard;
import com.dexafree.materialList.model.BasicListCard;
import com.dexafree.materialList.model.BigImageButtonsCard;
import com.dexafree.materialList.model.BigImageCard;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.model.SmallImageCard;
import com.dexafree.materialList.model.WelcomeCard;
import com.dexafree.materialList.view.IMaterialView;
import com.dexafree.materialList.view.MaterialListView;
import com.dexafree.materialList.view.MaterialStaggeredGridView;


public class MainActivity extends FragmentActivity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://hale-kite-786.appspot.com/schedule/free/today";
    private ProgressDialog pDialog;
    private List<Movie> movieList;
    private ListView listView;
    private CustomListAdapter adapter;
    private Context mContext;
    private IMaterialView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        movieList = new ArrayList<Movie>();

        //settaggio della listview delle card
        View view = findViewById(R.id.material_listview);


        if(view instanceof MaterialListView) {
            mListView = (MaterialListView) view;
        } else {
            mListView = (MaterialStaggeredGridView) view;
        }
        mListView.setCardAnimation(MaterialListView.CardAnimation.SWING_BOTTOM_IN);

        /*mListView.setOnDismissCallback(new OnDismissCallback() {
            @Override
            public void onDismiss(Card card, int position) {
                // QUANDO SWIPPIAMO VIA LA CARD EQUIVALE A UN NON MI PIACE
            }
        });*/

        //creazioni card

        Card card = new WelcomeCard();
        card.setDescription("Tutorial");
        card.setTitle("Welcome to PRIMETIME4U");


        Drawable icon = getResources().getDrawable(R.drawable.ic_launcher);
        card.setBitmap(icon);

        mListView.add(card);


        // Creating volley request obj
        new JsonRequest().execute(url);



    }
    private void drawResult(){
        System.out.println("Grandezza lista film: " + movieList.size());
        for (int i = 0; i < movieList.size(); i++) {
            Card currentcard = new SmallImageCard();
            Movie currentmovie = movieList.get(i);
            String title = currentmovie.getTitle();
            if (!currentmovie.getOriginalTitle().equals("null"))
                currentcard.setDescription("Titolo originale: " + currentmovie.getOriginalTitle() + "\n" + currentmovie.getTime() + "\n" + currentmovie.getChannel());
            else
                currentcard.setDescription(currentmovie.getTime() + "\n" + currentmovie.getChannel());

            currentcard.setTitle(title);
            Drawable icon2 = getResources().getDrawable(R.drawable.ic_launcher);
            currentcard.setBitmap(icon2);
            currentcard.setDismissible(false);
            mListView.add(currentcard);

        }




    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
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

    class JsonRequest extends AsyncTask<String,Void,List<Movie>>{
        List<Movie> movieList2 = new ArrayList<Movie>();
        @Override
        protected List<Movie> doInBackground(String... params) {


            final String TAG =  MainActivity.class.getSimpleName();
            JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET, params[0], null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());




                            try {

                                JSONObject obj = response.getJSONObject("data");
                                JSONArray obj2 = obj.getJSONArray("schedule");
                                for (int j=0;j<obj2.length();j++) {
                                    JSONObject obj3 = obj2.getJSONObject(j);
                                    Movie movie = new Movie();
                                    movie.setTitle(obj3.getString("title"));
                                    movie.setOriginalTitle(obj3.getString("originalTitle"));
                                    movie.setChannel(obj3.getString(("channel")));
                                    movie.setTime(obj3.getString("time"));
                                    movieList2.add(movie);

                                }

                                movieList=movieList2;
                                drawResult();
                                //movie.setRating(((Number) obj.get("rating"))
                                //        .doubleValue());
                                //movie.setYear(obj.getInt("releaseYear"));

                                // Genre is json array
                                /*JSONArray genreArry = obj.getJSONArray("genre");
                                ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < genreArry.length(); j++) {
                                    genre.add((String) genreArry.get(j));
                                }
                                movie.setGenre(genre);*/

                                // adding movie to movies array


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();

                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(movieReq);
            return movieList2;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            hidePDialog();

        }
    }

}

