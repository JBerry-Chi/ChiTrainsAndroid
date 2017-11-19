package com.example.johnberry.jberry_finalproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONTokener;

public class PredictionActivity extends AppCompatActivity {

    private ListView southList;
    private ListView northList;

    private static final String[] PREDICTION_DATA = {};
    private static String stationID;
    private static String lineColor;
    private ArrayList<ArrivalPrediction> northBoundTrains;
    private ArrayList<ArrivalPrediction> southBoundTrains;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prediction_activity);
        stationID = getIntent().getStringExtra("STATION_ID");
        lineColor = getIntent().getStringExtra("LINE_COLOR");

        thread.start();

        String[] testData = {"5 mins","6 mins"};

        //final ArrayAdapter myAdapter = new ArrayAdapter<>(this,
          //      android.R.layout.simple_list_item_1, LOADING_TEXT);

        northList = (ListView)findViewById(R.id.northListView);
        southList = (ListView)findViewById(R.id.southListView);

        northList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testData));
        southList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testData));

        ListUtils.setDynamicHeight(northList);
        ListUtils.setDynamicHeight(southList);
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
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

                long startTime = System.currentTimeMillis();
                ArrayList<ArrivalPrediction> newArrivalpredictions = currentParser.parsePrediction(predictionDataFromWeb, selectedLineColor);
                long endTime = System.currentTimeMillis();
                long waitTime = endTime - startTime;
                System.out.println("Parser ran for " + waitTime + " milliseconds");

                southBoundTrains = new ArrayList<ArrivalPrediction>();
                northBoundTrains = new ArrayList<ArrivalPrediction>();

                 for(ArrivalPrediction p : newArrivalpredictions) {
                    switch(p.getServiceDirection()){
                        case "95th/Dan Ryan":
                            southBoundTrains.add(p);
                            break;
                        case "Forest Park":
                            southBoundTrains.add(p);
                            break;
                        case "Loop":
                            southBoundTrains.add(p);
                            break;
                        case "Skokie":
                            northBoundTrains.add(p);
                            break;
                        case "Harlem/Lake":
                            southBoundTrains.add(p);
                            break;
                        case "Linden":
                            northBoundTrains.add(p);
                            break;
                        case "Howard":
                            northBoundTrains.add(p);
                            break;
                        case "O'Hare":
                            northBoundTrains.add(p);
                            break;
                        case "Kimball":
                            northBoundTrains.add(p);
                            break;
                        case "54th/Cermak":
                            northBoundTrains.add(p);
                            break;
                        case "Midway":
                            northBoundTrains.add(p);
                            break;
                        case "Ashland/63rd":
                            northBoundTrains.add(p);
                            break;
                        case "Cottage Grove":
                            northBoundTrains.add(p);
                            break;
                        case "63rd Street":
                            northBoundTrains.add(p);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            System.out.println("Done Processing Data");
            System.out.println("Northbound Count " + northBoundTrains.size());
            System.out.println("Southbound Count " + southBoundTrains.size());
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


