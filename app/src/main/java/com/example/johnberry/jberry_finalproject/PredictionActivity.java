package com.example.johnberry.jberry_finalproject;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
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
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PredictionActivity extends AppCompatActivity {
    private static String stationID;
    private static String lineColor;

    private ListView southList;
    private ListView northList;
    private ArrayAdapter<String> northAdapter;
    private ArrayAdapter<String> southAdapter;

    private ArrayList<String> northData;
    private ArrayList<String> southData;

    private ArrayList<ArrivalPrediction> newArrivalPredictions;
    private JSONObject predictionDataFromWeb;

    private ArrayList<ArrivalPrediction> northBoundTrains;
    private ArrayList<ArrivalPrediction> southBoundTrains;

    private long threadStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stationID = getIntent().getStringExtra("STATION_ID");
        lineColor = getIntent().getStringExtra("LINE_COLOR");
        new AsyncNetworkCall().execute();
        System.out.println("Running rest of Main Thread");
        setContentView(R.layout.prediction_activity);

        northData = new ArrayList<String>();
        southData = new ArrayList<String>();

        northData.add("Loading...");
        northData.add("Loading...");
        southData.add("Loading...");
        southData.add("Loading...");

        northList = (ListView)findViewById(R.id.northListView);
        southList = (ListView)findViewById(R.id.southListView);
        northAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, northData);
        southAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, southData);

        northList.setAdapter(northAdapter);
        southList.setAdapter(southAdapter);
        ListUtils.setDynamicHeight(northList);
        ListUtils.setDynamicHeight(southList);
        System.out.println("Finish Running Main Thread");
    }

   private class AsyncNetworkCall extends AsyncTask<Void, Void, Void> {
        URL url;
        HttpURLConnection urlConnection = null;

        public AsyncNetworkCall(){}

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                System.out.println("Started executing in background");
                threadStart = System.currentTimeMillis();
                southBoundTrains = new ArrayList<ArrivalPrediction>();
                northBoundTrains = new ArrayList<ArrivalPrediction>();

                String base_url = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=2ef142eb986f42cb9b087645f68e65d2&mapid=";
                String json_url_specs = "&max=25&outputType=JSON";
                url = new URL(base_url + stationID + json_url_specs);
                urlConnection = (HttpURLConnection) url
                        .openConnection();

                BufferedReader isw = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                JSONTokener data = new JSONTokener(isw.readLine());
                predictionDataFromWeb = new JSONObject(data);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                    long endNetwork = System.currentTimeMillis();
                    System.out.println("Network Task ran for " + (endNetwork - threadStart));
                }
            }

            try {
                long parseStart = System.currentTimeMillis();
                Parser JSONParser = new Parser();
                newArrivalPredictions = JSONParser.parsePrediction(predictionDataFromWeb, lineColor);
                long parseEnd = System.currentTimeMillis();
                long parseTime = parseEnd - parseStart;
                System.out.println("Parser ran for " + parseTime + " mills");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            for(ArrivalPrediction p : newArrivalPredictions) {
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

                northData.clear();
                southData.clear();
                for(ArrivalPrediction p: northBoundTrains){
                    if(p.getWaitTimeMins().equals("Due")){
                        northData.add(p.getWaitTimeMins());
                    }
                    else{
                        String waitTime = p.getWaitTimeMins() + " Mins";
                        northData.add(waitTime);
                    }
                }
                for(ArrivalPrediction p: southBoundTrains){
                    if(p.getWaitTimeMins().equals("Due")){
                        southData.add(p.getWaitTimeMins());
                    }
                    else{
                        String waitTime = p.getWaitTimeMins() + " Mins";
                        southData.add(waitTime);
                    }
                }
            long threadEnd = System.currentTimeMillis();
            long threadTime = threadEnd - threadStart;
            System.out.println("Entire Task ran for " + threadTime + "milliseconds");
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            northAdapter.notifyDataSetChanged();
            southAdapter.notifyDataSetChanged();
        }
    }

    /* HELPER CLASS TO SIZE LISTVIEWS APPROPRIATELY */
    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
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

}


