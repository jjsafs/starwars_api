package correia.felipe.cortana_app;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import correia.felipe.cortana_app.Connection.Get_Name_API;
import correia.felipe.cortana_app.Connection.Get_Title_API;
import correia.felipe.cortana_app.Connection.HttpConnection;
import correia.felipe.cortana_app.models.Film;
import correia.felipe.cortana_app.models.Starship;

public class Activity_Starship extends AppCompatActivity {

    //public static final String URL = "https://swapi.co/api/starships/15/";

    TextView tvname, tvmodel, tvmanufacturer, tvcost, tvlength, tvmaxatm, tvcrew, tvpassengers, tvcargo,
             tvconsumables, tvhyperdrive, tvmglt, tvstarhipclass;
    ListView lvpilots, lvfilms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starship);

        tvname = (TextView) findViewById(R.id.tv_name_starship);
        tvmodel = (TextView) findViewById(R.id.tv_model_starship);
        tvmanufacturer = (TextView) findViewById(R.id.tv_manufacturer_starship);
        tvcost = (TextView) findViewById(R.id.tv_cost_in_credits_starship);
        tvlength = (TextView) findViewById(R.id.tv_length_starship);
        tvmaxatm = (TextView) findViewById(R.id.tv_max_atmosphering_speed_starship);
        tvcrew = (TextView) findViewById(R.id.tv_crew_starship);
        tvpassengers = (TextView) findViewById(R.id.tv_passengers_starship);
        tvcargo = (TextView) findViewById(R.id.tv_cargo_capacity_starship);
        tvconsumables = (TextView) findViewById(R.id.tv_consumables_starship);
        tvhyperdrive = (TextView) findViewById(R.id.tv_hyperdrive_rating);
        tvmglt = (TextView) findViewById(R.id.tv_mglt);
        tvstarhipclass = (TextView) findViewById(R.id.tv_starship_class);

        lvpilots = (ListView) findViewById(R.id.lv_pilots_starship);
        lvfilms = (ListView) findViewById(R.id.lv_films_starship);

        final String URL = getIntent().getStringExtra("api");


        new Thread() {
            public void run() {
                try {
                    String response = HttpConnection.getSetDataWeb(URL);
                    Log.d("ACTIVITY", "Resposta: " + response);
                    populate(response);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    String response = HttpConnection.getSetDataWeb(URL);
                    Log.d("ACTIVITY", "Resposta: " + response);
                    populateList(response);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void populate(String response) throws JSONException, IOException {
        JSONObject jsonO = new JSONObject(response);

        final Starship item = new Starship();

        final String name = jsonO.getString("name");
        Log.d("Parser", "JSONObject name: " + name);
        final String model = jsonO.getString("model");
        Log.d("Parser", "JSONObject model: " + model);
        final String manufacturer = jsonO.getString("manufacturer");
        Log.d("Parser", "JSONObject manufacturer: " + manufacturer);
        final String cost_in_credits = jsonO.getString("cost_in_credits");
        Log.d("Parser", "JSONObject cost_in_credits: " + cost_in_credits);
        final String length = jsonO.getString("length");
        Log.d("Parser", "JSONObject length: " + length);
        final String max_atmosphering_speed = jsonO.getString("max_atmosphering_speed");
        Log.d("Parser", "JSONObject max_atmosphering_speed: " + max_atmosphering_speed);
        final String crew = jsonO.getString("crew");
        Log.d("Parser", "JSONObject crew: " + crew);
        final String passengers = jsonO.getString("passengers");
        Log.d("Parser", "JSONObject passengers: " + passengers);
        final String cargo_capacity = jsonO.getString("cargo_capacity");
        Log.d("Parser", "JSONObject cargo_capacity: " + cargo_capacity);
        final String consumables = jsonO.getString("consumables");
        Log.d("Parser", "JSONObject consumables: " + consumables);
        final String hyperdrive_rating = jsonO.getString("hyperdrive_rating");
        Log.d("Parser", "JSONObject hyperdrive_rating: " + hyperdrive_rating);
        final String MGLT = jsonO.getString("MGLT");
        Log.d("Parser", "JSONObject MGLT: " + MGLT);
        final String starship_class = jsonO.getString("starship_class");
        Log.d("Parser", "JSONObject starship_class: " + starship_class);



        item.setName(name);
        item.setModel(model);
        item.setManufacturer(manufacturer);
        item.setCost_in_credits(cost_in_credits);
        item.setLength(length);
        item.setMax_atmosphering_speed(max_atmosphering_speed);
        item.setCrew(crew);
        item.setPassengers(passengers);
        item.setCargo_capacity(cargo_capacity);
        item.setConsumables(consumables);
        item.setHyperdrive_rating(hyperdrive_rating);
        item.setMGLT(MGLT);
        item.setStarship_class(starship_class);




        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Parser thread", "THREAD");
                setPopulate(tvname.getText().toString(),item.getName(),tvname);
                setPopulate(tvmodel.getText().toString(),item.getModel(),tvmodel);
                setPopulate(tvmanufacturer.getText().toString(),item.getManufacturer(),tvmanufacturer);
                setPopulate(tvcost.getText().toString(),item.getCost_in_credits(),tvcost);
                setPopulate(tvlength.getText().toString(),item.getLength(),tvlength);
                setPopulate(tvmaxatm.getText().toString(),item.getMax_atmosphering_speed(),tvmaxatm);
                setPopulate(tvcrew.getText().toString(),item.getCrew(),tvcrew);
                setPopulate(tvpassengers.getText().toString(),item.getPassengers(),tvpassengers);
                setPopulate(tvcargo.getText().toString(),item.getCargo_capacity(),tvcargo);
                setPopulate(tvconsumables.getText().toString(),item.getConsumables(),tvconsumables);
                setPopulate(tvhyperdrive.getText().toString(),item.getHyperdrive_rating(),tvhyperdrive);
                setPopulate(tvmglt.getText().toString(),item.getMGLT(),tvmglt);
                setPopulate(tvstarhipclass.getText().toString(),item.getStarship_class(),tvstarhipclass);


            }
        });
    }

    private void setPopulate(String text_1, String text_2, TextView tvtxt){
        String now;
        now = text_1 + text_2;
        tvtxt.setText(now);
    }

    private void populateList(String response) throws JSONException, IOException {
        JSONObject jsonO = new JSONObject(response);
        final ArrayList<String> items_pilots = new ArrayList<String>();
        final JSONArray pilots = jsonO.getJSONArray("pilots");
        Log.d("Parser", "JSONArray pilots: " + pilots);
        for(int i = 0; i< pilots.length(); i++){
            String json_data = pilots.getString(i);
            Log.d("Parser", "JSONArray pilots: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray pilots_name: " + name);
            items_pilots.add(name);
        }

        final ArrayList<String> items_films = new ArrayList<String>();
        final JSONArray films = jsonO.getJSONArray("films");
        Log.d("Parser", "JSONArray films: " + films);
        for(int i = 0; i< films.length(); i++){
            String json_data = films.getString(i);
            Log.d("Parser", "JSONArray films: " + json_data);
            String title = Get_Title_API.getTitle(json_data);
            Log.d("Parser", "JSONArray films_title: " + title);
            items_films.add(title);
        }


        final ArrayAdapter<String> mArrayAdapter_pilots = new ArrayAdapter<String>(this,
                R.layout.list_item, items_pilots);
        final ArrayAdapter<String> mArrayAdapter_films = new ArrayAdapter<String>(this,
                R.layout.list_item, items_films);

        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                lvpilots.setAdapter(mArrayAdapter_pilots);
                lvfilms.setAdapter(mArrayAdapter_films);
            }
        });
    }
}


