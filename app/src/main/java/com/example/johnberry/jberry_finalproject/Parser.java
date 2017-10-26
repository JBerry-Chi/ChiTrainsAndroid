package com.example.johnberry.jberry_finalproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by John Berry on 10/25/2017.
 */

public class Parser {

    boolean isDelayed;

    public Parser(){}

    public ArrivalPrediction parsePrediction(JSONObject prediction) throws JSONException {
        JSONObject outerDict = (JSONObject) prediction.get("ctatt");
        String timeStamp = outerDict.get("tmst").toString();

        /// PREDICTION DATA DICTIONARY

        //CANNOT CAST TO JSON ARRAY???
        JSONObject predictionData = (JSONObject) outerDict.get("eta");

        /// PREDICTION DATA ATTRS
        String stationID = predictionData.get("staID").toString();
        String stopID = predictionData.get("stpID").toString();
        String stationName = predictionData.get("staNm").toString();
        String serviceDirection = predictionData.get("stpDe").toString();
        String trainColor = predictionData.get("rt").toString();
        String predictedDepartureTime = predictionData.get("prdt").toString();
        String predictedArrivalTime = predictionData.get("arrT").toString();

        if(predictionData.get("isDly").equals("0")){
            boolean isDelayed = false;
        }
        else{
            boolean isDelayed = true;
        }

        ArrivalPrediction newPrediction = new ArrivalPrediction(timeStamp, stationID, stopID, stationName,
                serviceDirection, trainColor, predictedDepartureTime, predictedArrivalTime, isDelayed);

        return newPrediction;
    }
}
