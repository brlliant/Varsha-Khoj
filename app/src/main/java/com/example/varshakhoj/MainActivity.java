package com.example.varshakhoj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button get_city_ID, use_city_ID, use_name;
    public EditText et_textview;
    public ListView lv_weather_report;
    private ArrayList<String> weatherData;
    private ArrayAdapter<String> adapter;
    private final String API_key = "68a9686d39f344a89a121652243005";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign values to each control on the layout.
        get_city_ID = findViewById(R.id.btn_get_city_ID);
        use_city_ID = findViewById(R.id.btn_use_City_ID);
        use_name = findViewById(R.id.btn_use_name);

        et_textview = findViewById(R.id.et_data_input);
        lv_weather_report = findViewById(R.id.lv_weather_reports);

        //Array to store weather data
        weatherData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, weatherData);
        lv_weather_report.setAdapter(adapter);
        //Set onClickListener
        use_city_ID.setOnClickListener(v1 ->
        {

        });
        use_name.setOnClickListener(v13 ->
        {
            String city = et_textview.getText().toString();
            getWeatherInfo(city);
        });
        et_textview.setOnClickListener(v12 ->
        {

        });

    }
    private void getWeatherInfo(String city) {
        String url = "https://api.weatherapi.com/v1/current.json?key=" + API_key + "&q=" + city;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseAndDisplayWeather(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        weatherData.clear();
                        weatherData.add("Error: " + error.toString());
                        adapter.notifyDataSetChanged();
                    }
                });

        queue.add(stringRequest);
    }

    private void parseAndDisplayWeather(String response) {
        try {
            weatherData.clear();

            JSONObject jsonObject = new JSONObject(response);
            JSONObject location = jsonObject.getJSONObject("location");
            String cityName = location.getString("name");
            String country = location.getString("country");
            String region = location.getString("region");

            JSONObject current = jsonObject.getJSONObject("current");
            String tempC = current.getString("temp_c");
            String condition = current.getJSONObject("condition").getString("text");
            String humidity = current.getString("humidity");
            String tempF = current.getString("temp_f");

            weatherData.add("City: " + cityName);
            weatherData.add("Country: " + country);
            weatherData.add("Region: " + region);
            weatherData.add("Temperature: " + tempC + "°C");
            weatherData.add("Condition: " + condition);
            weatherData.add("Humidity: " + humidity + "%");
            weatherData.add("Temperature F: " + tempF + "°F");

            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
            weatherData.clear();
            weatherData.add("Error parsing data");
            adapter.notifyDataSetChanged();
        }
    }
}