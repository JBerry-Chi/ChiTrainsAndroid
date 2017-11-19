package com.example.johnberry.jberry_finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StationSelectActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String line_selection = getIntent().getStringExtra("LINE_SELECTION");

        System.out.println("Received Line selection " + line_selection);
       // setContentView(R.layout.activity_station_select);

        //NEED TO SELECT LIST OF STOPS FOR EACH COLOR
        //REFERENCE THEIR ID NUMBER TO PASS TO URL

        final ArrayAdapter myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, STATIONS);

        final ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        setListAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                //transitionDetail(CTA_TRAIN_LINES[position]);
            }
        });
    }


    private static final String[] STATIONS = {

            //CODE TO GET STATIONS FOR A COLOR LINE
            //USE COLOR NAME PASSED FROM OTHER VIEW AS KEY


        "Belmont"


    };
}
