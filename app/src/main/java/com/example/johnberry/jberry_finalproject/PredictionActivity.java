package com.example.johnberry.jberry_finalproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONTokener;

public class PredictionActivity extends ListActivity {

    private static final String[] PREDICTION_DATA = {};
    private static String stationID;
    private static String lineColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.prediction_activity);
        stationID = getIntent().getStringExtra("STATION_ID");
        lineColor = getIntent().getStringExtra("LINE_COLOR");

        System.out.println("PredictionActivity received line color " + lineColor);

        thread.start();

        final ArrayAdapter myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, LOADING_TEXT);
        final ListView listView = getListView();
        setListAdapter(myAdapter);

    }

    Thread thread = new Thread(new Runnable(){
        URL url;
        HttpURLConnection urlConnection = null;

        @Override
        public void run() {
            try {
                Parser currentParser = new Parser();

                String base_url = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=2ef142eb986f42cb9b087645f68e65d2&mapid=";
                String json_url_specs = "&max=25&outputType=JSON";
                url = new URL(base_url + stationID + json_url_specs);
                urlConnection = (HttpURLConnection) url
                        .openConnection();

                BufferedReader isw = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                JSONTokener data = new JSONTokener(isw.readLine());
                JSONObject predictionDataFromWeb = new JSONObject(data);
                String selectedLineColor = lineColor;

                ArrayList<ArrivalPrediction> newArrivalpredictions = currentParser.parsePrediction(predictionDataFromWeb, selectedLineColor);

                /*  for(ArrivalPrediction p : newArrivalpredictions) {
                    System.out.println("Time Stamp of call: " + p.getTimeStamp());
                    System.out.println("Station ID: " + p.getStationID());
                    System.out.println("Station Name: " + p.getStationName());
                    System.out.println("Service Direction: " + p.getServiceDirection());
                    System.out.println("Train Color: " + p.getTrainColor());
                    System.out.println("Predicted Departure Time: " + p.getPredictedDepartureTime());
                    System.out.println("Predicted Arrival Time: " + p.getPredictedArrivalTime());
                    System.out.println("Delayed: " + p.getDelayedStatus());
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(" ");
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    });


    private final String[] LOADING_TEXT = {
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading"
    };

}


