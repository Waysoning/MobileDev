package edu.neu.madcourse.numad21fa_sihengwei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";

    private final ArrayList<ItemWeather> itemWeathers = new ArrayList<>();

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private EditText etRequest;
    private ProgressBar pbLoading;

    public static final int ITEM_CONTENT_NUMBER = 4;
    public static final int NUMBER_OF_DAYS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        etRequest = findViewById(R.id.et_request);
        pbLoading = findViewById(R.id.pb_loading);
    }

    public void requestForData(View view){

        // close the soft keyboard
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etRequest.getWindowToken(), 0);

        //set the loading
        pbLoading.setVisibility(View.VISIBLE);

        WebServiceTask task = new WebServiceTask();
        task.execute(etRequest.getText().toString());


    }

    @SuppressLint("StaticFieldLeak")
    private class WebServiceTask extends AsyncTask<String, Integer, String[][]>{
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String[][] doInBackground(String... strings) {

            String[][] results = new String[NUMBER_OF_DAYS + 1][ITEM_CONTENT_NUMBER];
            URL urlLocation;
            URL urlWeather;
            try {
                String locationId = null;

                String city = (strings.length == 0 || strings[0] == null || "".equals(strings[0])) ? "San Jose" : strings[0];
                urlLocation = new URL("https://www.metaweather.com/api/location/search/?query=" + city);
                HttpsURLConnection connLocation = (HttpsURLConnection) urlLocation.openConnection();
                connLocation.setRequestMethod("GET");
                connLocation.setDoInput(true);
                connLocation.connect();
                // Read response.
                InputStream inputStreamLocation = connLocation.getInputStream();
                final String respLocation = convertStreamToString(inputStreamLocation);
                JSONArray location = new JSONArray(respLocation);
                if(location.length() != 0){
                    JSONObject locationJSON = location.getJSONObject(0);
                    locationId = locationJSON.getString("woeid");
                }

                locationId = (locationId == null || "".equals(locationId)) ? "2488042" : locationId;
                urlWeather = new URL("https://www.metaweather.com/api/location/" + locationId);
                HttpsURLConnection connWeather = (HttpsURLConnection) urlWeather.openConnection();
                connWeather.setRequestMethod("GET");
                connWeather.setDoInput(true);
                connWeather.connect();
                // Read response.
                InputStream inputStreamWeather = connWeather.getInputStream();
                final String respWeather = convertStreamToString(inputStreamWeather);


                JSONObject weather = new JSONObject(respWeather);
                JSONArray consolidatedWeather = weather.getJSONArray("consolidated_weather");

                for(int i = 0; i < NUMBER_OF_DAYS; i++){
                    JSONObject jsonObject = consolidatedWeather.getJSONObject(i);
                    results[i][0] = jsonObject.getString("applicable_date").substring(jsonObject.getString("applicable_date").indexOf('-') + 1);
                    results[i][1] = jsonObject.getString("weather_state_abbr");
                    results[i][2] = jsonObject.getString("min_temp").substring(0, jsonObject.getString("min_temp").indexOf('.'));
                    results[i][3] = jsonObject.getString("max_temp").substring(0, jsonObject.getString("max_temp").indexOf('.'));
                }
                results[NUMBER_OF_DAYS][0] = weather.getString("title");
                return results;

            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG,"JSONException");
                e.printStackTrace();
            }
            Toast.makeText(
                    getApplicationContext(), "Someting went wrong", Toast.LENGTH_SHORT).show();
            return(results);
        }

        @Override
        protected void onPostExecute(String[][] strings) {
            super.onPostExecute(strings);
            etRequest.setText(strings[NUMBER_OF_DAYS][0]);

            itemWeathers.clear();
            for(int i = 0; i < NUMBER_OF_DAYS && i < strings.length - 1; i++){
                itemWeathers.add(new ItemWeather(strings[i][0], strings[i][1], strings[i][2], strings[i][3]));
            }

            layoutManager = new LinearLayoutManager(getBaseContext());
            recyclerView = findViewById(R.id.rv_weather);
            recyclerView.setHasFixedSize(true);
            weatherAdapter = new WeatherAdapter(itemWeathers);
            recyclerView.setAdapter(weatherAdapter);
            recyclerView.setLayoutManager(layoutManager);

            //close the loading
            pbLoading.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Helper function
     */
    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }

}