package com.example.johnberry.jberry_finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LineSelectActivity extends ListActivity {
    StationManager stationManager = StationManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ArrayAdapter myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, CTA_TRAIN_LINES);
        final ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                transitionDetail(CTA_TRAIN_LINES[position]);
            }
        });
    }

    public void transitionDetail(String selection){
        switch(selection) {
            case "Red": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
            case "Brown": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
            case "Purple": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
            case "Yellow": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
            case "Orange": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
            case "Blue": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
            case "Green": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
            case "Pink": {
                Intent detailIntent = new Intent(getApplicationContext(), StationSelectActivity.class);
                detailIntent.putExtra("LINE_SELECTION", selection.toUpperCase());
                detailIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(detailIntent);
                break;
            }
        }
    }
            private static final String[] CTA_TRAIN_LINES = {
            "Red",
            "Brown",
            "Purple",
            "Yellow",
            "Orange",
            "Blue",
            "Green",
            "Pink"
    };
}
