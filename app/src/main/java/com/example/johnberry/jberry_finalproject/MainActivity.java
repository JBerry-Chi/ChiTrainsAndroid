package com.example.johnberry.jberry_finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread.start();
    }

    Thread thread = new Thread(new Runnable(){
        URL url;
        HttpURLConnection urlConnection = null;

        @Override
        public void run() {
            try {
                Parser currentParser = new Parser();

                String base_url = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=2ef142eb986f42cb9b087645f68e65d2&mapid=";
                String json_url_specs = "&max=50&outputType=JSON";
                String stationID = "41420"; //ADDISON RED LINE FOR TESTING
                url = new URL(base_url + stationID + json_url_specs);
                urlConnection = (HttpURLConnection) url
                        .openConnection();

                BufferedReader isw = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                JSONTokener data = new JSONTokener(isw.readLine());
                JSONObject returnedData = new JSONObject(data);
                ArrivalPrediction newArrival = currentParser.parsePrediction(returnedData);

                System.out.println("Time Stamp of call: " + newArrival.getTimeStamp());
                System.out.println("Station ID: " + newArrival.getStationID());
                System.out.println("Station Name: " + newArrival.getStationName());
                System.out.println("Service Direction: " + newArrival.getServiceDirection());
                System.out.println("Train Color: " + newArrival.getTrainColor());
                System.out.println("Predicted Departure Time: " + newArrival.getPredictedDepartureTime());
                System.out.println("Predicted Arrival Time: " + newArrival.getPredictedArrivalTime());
                System.out.println("Delayed: " + newArrival.getDelayedStatus());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    });
}
