package com.example.johnberry.jberry_finalproject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class StationSelectActivity extends ListActivity {
    StationManager stationManager = StationManager.getInstance();

    private static ArrayList<String> STATIONS_TO_DISPLAY;
    private AlertDialog.Builder builder;
    private String line_selection;
    private String station_selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        builder = new AlertDialog.Builder(this);
        line_selection = getIntent().getStringExtra("LINE_SELECTION");
        STATIONS_TO_DISPLAY = stationManager.getAllStationsForLine(line_selection);

        final ArrayAdapter myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, STATIONS_TO_DISPLAY);

        final ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                if(!isNetworkAvailable()){
                    builder.setMessage("Please connect and try again.")
                            .setTitle("No network connection detected");
                    builder.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog networkAlert = builder.create();
                    networkAlert.show();
                }
                else {
                    station_selection = STATIONS_TO_DISPLAY.get(position);
                    String station_id = stationManager.getStationID(line_selection, station_selection);
                    transitionToPrediction(station_id);
                }
            }
        });
    }

    private void transitionToPrediction(String stationID){
        Intent detailIntent = new Intent(getApplicationContext(), PredictionActivity.class);
        detailIntent.putExtra("STATION_ID", stationID);
        detailIntent.putExtra("LINE_COLOR", line_selection);
        detailIntent.putExtra("STATION_NAME", station_selection);
        detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(detailIntent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
