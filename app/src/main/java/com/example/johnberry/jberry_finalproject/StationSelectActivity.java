package com.example.johnberry.jberry_finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class StationSelectActivity extends ListActivity {
    private static ArrayList<String> STATIONS_TO_DISPLAY;
    StationManager stationManager = StationManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String line_selection = getIntent().getStringExtra("LINE_SELECTION");
        System.out.println("Received Line selection " + line_selection);
        STATIONS_TO_DISPLAY = stationManager.getAllStationsForLine(line_selection);

        final ArrayAdapter myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, STATIONS_TO_DISPLAY);

        final ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                String station_id = stationManager.getStationID(line_selection, STATIONS_TO_DISPLAY.get(position));
                System.out.println("Selected station: " + STATIONS_TO_DISPLAY.get(position) + " ID: " + station_id);
            }
        });

    }

}
