package com.example.johnberry.jberry_finalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    private String iconColor;
    private static String stationName;

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
    private String northHeader;
    private String southHeader;

    private AsyncNetworkCall networkTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stationID = getIntent().getStringExtra("STATION_ID");
        stationName = getIntent().getStringExtra("STATION_NAME");
        lineColor = getIntent().getStringExtra("LINE_COLOR");

        setContentView(R.layout.prediction_activity);
        this.setTitle(stationName);

        if(!(networkTask == null)) {
            if (networkTask.getStatus() == AsyncTask.Status.RUNNING) {
                networkTask.cancel(true);
            }
        }
        networkTask = new AsyncNetworkCall();
        networkTask.execute();

        northData = new ArrayList<String>();
        southData = new ArrayList<String>();
        northData.add("Loading...");
        northData.add("Loading...");
        northData.add("Loading...");
        northData.add("Loading...");
        northData.add("Loading...");
        southData.add("Loading...");
        southData.add("Loading...");
        southData.add("Loading...");
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
    }

   private class AsyncNetworkCall extends AsyncTask<Void, Void, Void> {
       ArrayList<String> tempNorthData;
       ArrayList<String> tempSouthData;
        URL url;
        HttpURLConnection urlConnection = null;

        public AsyncNetworkCall(){}

        @Override
        protected Void doInBackground(Void... voids) {
            tempNorthData = new ArrayList<String>();
            tempSouthData = new ArrayList<String>();
            southBoundTrains = new ArrayList<>();
            northBoundTrains = new ArrayList<>();

            if(!(newArrivalPredictions==null))
                newArrivalPredictions.clear();

            String base_url = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=2ef142eb986f42cb9b087645f68e65d2&mapid=";
            String json_url_specs = "&max=25&outputType=JSON";

            try {
                url = new URL(base_url + stationID + json_url_specs);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader isw = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                JSONTokener data = new JSONTokener(isw.readLine());
                predictionDataFromWeb = new JSONObject(data);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            try {
                Parser JSONParser = new Parser();
                newArrivalPredictions = JSONParser.parsePrediction(predictionDataFromWeb, lineColor);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(newArrivalPredictions.size() > 0) {
                for (ArrivalPrediction p : newArrivalPredictions) {
                    iconColor = p.getTrainColor();
                    switch (p.getServiceDirection()) {
                        case "95th/Dan Ryan":
                            southHeader = "95th/Dan Ryan";
                            southBoundTrains.add(p);
                            break;
                        case "Forest Park":
                            southHeader = "Forest Park";
                            southBoundTrains.add(p);
                            break;
                        case "Loop":
                            southHeader = "Loop";
                            southBoundTrains.add(p);
                            break;
                        case "Harlem/Lake":
                            southHeader = "Harlem/Lake";
                            southBoundTrains.add(p);
                            break;
                        case "Linden":
                            northHeader = "Linden";
                            northBoundTrains.add(p);
                            break;
                        case "Howard":
                            northHeader = "Howard";
                            northBoundTrains.add(p);
                            break;
                        case "Skokie":
                            northHeader = "Skokie";
                            northBoundTrains.add(p);
                            break;
                        case "O'Hare":
                            northHeader = "O'Hare";
                            northBoundTrains.add(p);
                            break;
                        case "Kimball":
                            northHeader = "Kimball";
                            northBoundTrains.add(p);
                            break;
                        case "54th/Cermak":
                            northHeader = "54th/Cermak";
                            northBoundTrains.add(p);
                            break;
                        case "Midway":
                            northHeader = "Midway";
                            northBoundTrains.add(p);
                            break;
                        case "Ashland/63rd":
                            northHeader = "Ashland/63rd";
                            northBoundTrains.add(p);
                            break;
                        case "Cottage Grove":
                            northHeader = "Cottage Grove";
                            northBoundTrains.add(p);
                            break;
                        case "63rd Street":
                            northHeader = "63rd Street";
                            northBoundTrains.add(p);
                            break;
                    }
                }

                if (northBoundTrains.size() == 0) {
                    northHeader = "";
                    tempNorthData.add("No arrival times.");
                } else {
                    for (ArrivalPrediction p : northBoundTrains) {
                        if (p.getWaitTimeMins().equals("Due")) {
                            tempNorthData.add(p.getWaitTimeMins());
                        } else {
                            String waitTime = p.getWaitTimeMins() + " Mins";
                            tempNorthData.add(waitTime);
                        }
                    }
                }

                if (southBoundTrains.size() == 0) {
                    tempSouthData.add("No arrival times.");
                    southHeader = "";
                } else {
                    for (ArrivalPrediction p : southBoundTrains) {
                        if (p.getWaitTimeMins().equals("Due")) {
                            tempSouthData.add(p.getWaitTimeMins());
                        } else {
                            String waitTime = p.getWaitTimeMins() + " Mins";
                            tempSouthData.add(waitTime);
                        }
                    }
                }
            }
            else{
                tempNorthData.add("No arrival times.");
                tempSouthData.add("No arrival times.");
                southHeader = "";
                northHeader = "";
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
                northData.clear();
                southData.clear();
                southData.addAll(tempSouthData);
                northData.addAll(tempNorthData);
                TextView northText = (TextView) findViewById(R.id.northHeaderText);
                TextView southText = (TextView) findViewById(R.id.southHeaderText);

                northText.setText("Service to " + northHeader);
                southText.setText("Service to " + southHeader);
                setIconColor(iconColor);
                northAdapter.notifyDataSetChanged();
                southAdapter.notifyDataSetChanged();
            }
        }

        private void setIconColor(String color){
            ImageView northIconImg = (ImageView) findViewById(R.id.northIcon);
            ImageView southIconImg = (ImageView) findViewById(R.id.southIcon);
            switch (color){
                case "RED":
                    northIconImg.setImageResource(R.drawable.red);
                    southIconImg.setImageResource(R.drawable.red);
                    break;
                case "BROWN":
                    northIconImg.setImageResource(R.drawable.brown);
                    southIconImg.setImageResource(R.drawable.brown);
                    break;
                case "PURPLE":
                    northIconImg.setImageResource(R.drawable.purple);
                    southIconImg.setImageResource(R.drawable.purple);
                    break;
                case "YELLOW":
                    northIconImg.setImageResource(R.drawable.yellow);
                    southIconImg.setImageResource(R.drawable.yellow);
                    break;
                case "BLUE":
                    northIconImg.setImageResource(R.drawable.blue);
                    southIconImg.setImageResource(R.drawable.blue);
                    break;
                case "GREEN":
                    northIconImg.setImageResource(R.drawable.green);
                    southIconImg.setImageResource(R.drawable.green);
                    break;
                case "PINK":
                    northIconImg.setImageResource(R.drawable.pink);
                    southIconImg.setImageResource(R.drawable.pink);
                    break;
                case "ORANGE":
                    northIconImg.setImageResource(R.drawable.orange);
                    southIconImg.setImageResource(R.drawable.orange);
                    break;
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


