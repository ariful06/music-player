package com.javarank.magicapp.WeatherApp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javarank.magicapp.R;
import com.javarank.magicapp.WeatherApp.Helper.Helper;
import com.javarank.magicapp.WeatherApp.Model.OpenWeatherMap;
import com.javarank.magicapp.WeatherApp.Model.Weather;
import com.javarank.magicapp.WeatherApp.common.Common;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class WeatherActivity extends AppCompatActivity implements LocationListener {

    TextView tvCity, tvLastUpdate, tvDescription, tvHumidity, tvTime, tvCelsius;
    ImageView imageView;
    LocationManager locationManager;
    String provider;
    static double lat, lng;

    Toolbar toolbar;
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();
    int MY_PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Weather Forecast");

        tvCity = findViewById(R.id.text_view_city);
        tvLastUpdate = findViewById(R.id.text_view_last_update);
        tvDescription = findViewById(R.id.text_view_description);
        tvHumidity = findViewById(R.id.text_view_humidity);
        tvTime = findViewById(R.id.text_view_time);
        tvCelsius = findViewById(R.id.text_view_celsius);
        imageView = findViewById(R.id.image_view);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null) {
            Log.e("TAG", "No Location");
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        lat = location.getLatitude();
        lng = location.getLongitude();
        new GetWeather().execute(Common.apiRequest(String.valueOf(lat), String.valueOf(lng)));

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, MY_PERMISSION);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, MY_PERMISSION);
        }
        locationManager.removeUpdates(this);
    }


    private class GetWeather extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(WeatherActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please Wait...");
            pd.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            Helper httpData = new Helper();
            stream = httpData.getHTTPData(urlString);
            return stream;
        }

        @SuppressLint("DefaultLocale")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null && s.contains("Error: Not found city")) {
                pd.dismiss();
                return;
            }
            Gson gson = new Gson();

            Type myType = new TypeToken<OpenWeatherMap>() {
            }.getType();
            openWeatherMap = gson.fromJson(s, myType);
            pd.dismiss();

            tvCity.setText(String.format("%s , %s", openWeatherMap.getName(), openWeatherMap.get_sys().getCountry()));
            tvLastUpdate.setText(String.format("Last Update: %s", Common.getCurrentDate()));
            tvDescription.setText(String.format("%s", openWeatherMap.getWeather().get(0).getDescription()));
            tvHumidity.setText(String.format("Humidity: %d%%", openWeatherMap.getMain().getHumidity()));
            tvTime.setText(String.format("Time: %s/%s", Common.unixTimeStampToDateTime(openWeatherMap.get_sys().getSunrise()), Common.unixTimeStampToDateTime(openWeatherMap.get_sys().getSunset())));
            tvCelsius.setText(String.format("Temp: %.2f °C", openWeatherMap.getMain().getTemp() - 273));
            Picasso.get().load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon())).into(imageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
