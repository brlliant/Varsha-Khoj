package com.example.varshakhoj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public Button get_city_ID, use_city_ID, use_name;
    public EditText et_textview;
    public ListView lv_weather_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_city_ID = findViewById(R.id.btn_get_city_ID);
        use_city_ID = findViewById(R.id.btn_use_City_ID);
        use_name = findViewById(R.id.btn_use_name);

        et_textview = findViewById(R.id.et_data_input);
        lv_weather_report = findViewById(R.id.lv_weather_reports);

    }
}