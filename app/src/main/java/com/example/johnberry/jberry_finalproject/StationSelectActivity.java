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
import android.widget.Toast;

import java.util.ArrayList;

public class StationSelectActivity extends ListActivity {
    private static ArrayList<String> STATIONS_TO_DISPLAY;
    private AlertDialog.Builder builder;
    public String line_selection;
    StationManager stationManager = StationManager.getInstance();

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
                    System.out.println("No network connection; try again");
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
                    String station_id = stationManager.getStationID(line_selection, STATIONS_TO_DISPLAY.get(position));
                    transitionToPrediction(station_id);
                }
            }
        });
    }

    private void transitionToPrediction(String stationID){
        Intent detailIntent = new Intent(getApplicationContext(), PredictionActivity.class);
        detailIntent.putExtra("STATION_ID", stationID);
        detailIntent.putExtra("LINE_COLOR", line_selection);
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
