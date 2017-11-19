package com.example.johnberry.jberry_finalproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by John Berry on 10/25/2017.
 */

public class Parser {

    private ArrayList<ArrivalPrediction> requestedPredictions;

    boolean isDelayed;

    public Parser(){
        requestedPredictions = new ArrayList<ArrivalPrediction>();
    }

    public ArrayList<ArrivalPrediction> parsePrediction(JSONObject prediction) throws JSONException {
        JSONObject outerDict = (JSONObject) prediction.get("ctatt");
        String timeStamp = outerDict.get("tmst").toString();
        JSONArray predictionData = (JSONArray) outerDict.get("eta");
        System.out.println("Received # " + predictionData.length() + " predictions");

        for(int i=0; i<predictionData.length(); i++) {
            JSONObject currentPrediction = (JSONObject) predictionData.get(i);

            System.out.println(currentPrediction);

            String trainColor = currentPrediction.get("rt").toString();
            System.out.println("Retrieved Train Color: " + trainColor);

            String stationID = currentPrediction.get("staId").toString();
            System.out.println("Retrieved Station ID: " + stationID);

            String stationName = currentPrediction.get("staNm").toString();
            System.out.println("Retrieved Station name: " + stationName);

            String destinationDirection = currentPrediction.get("destNm").toString();
            System.out.println("Retrieved Service towards: " + destinationDirection);

            String predictedDepartureTime = currentPrediction.get("prdt").toString();
            String predictedArrivalTime = currentPrediction.get("arrT").toString();

            if (currentPrediction.get("isDly").equals("0")) {
                boolean isDelayed = false;
            } else {
                boolean isDelayed = true;
            }
           // ArrivalPrediction newPrediction = new ArrivalPrediction(timeStamp, stationID, stopID, stationName,
           //         serviceDirection, trainColor, predictedDepartureTime, predictedArrivalTime, isDelayed);

            ArrivalPrediction newPrediction = new ArrivalPrediction();
            requestedPredictions.add(newPrediction);
        }
        /*for(ArrivalPrediction predict : requestedPredictions){

            System.out.println("--------------Requested Prediction------------------");
            System.out.println(predict);
        }*/
        return requestedPredictions;
    }
}
