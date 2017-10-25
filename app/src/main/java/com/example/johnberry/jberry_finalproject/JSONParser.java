package com.example.johnberry.jberry_finalproject;

import org.json.JSONObject;

/**
 * Created by John Berry on 10/25/2017.
 */

public class JSONParser {

    public JSONParser(){}

    public void parsePrediction(JSONObject prediction){
        System.out.println("Json Parser running here is the returned data: ");
        System.out.println(prediction);
    }
}
