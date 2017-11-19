package com.example.johnberry.jberry_finalproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class Parser {

    private ArrayList<ArrivalPrediction> requestedPredictions;
    private static String requestedWaitTime;

    boolean isDelayed;

    public Parser(){
        requestedPredictions = new ArrayList<ArrivalPrediction>();
    }

    public ArrayList<ArrivalPrediction> parsePrediction(JSONObject prediction, String lineColor) throws JSONException, ParseException {
        String selectedLineColor = lineColor.toUpperCase();
        JSONObject outerDict = (JSONObject) prediction.get("ctatt");

        JSONArray predictionData = (JSONArray) outerDict.get("eta");
        System.out.println("Received # " + predictionData.length() + " predictions");

        for(int i=0; i<predictionData.length(); i++) {
            JSONObject currentPrediction = (JSONObject) predictionData.get(i);
            String trainColor = currentPrediction.get("rt").toString().toUpperCase();

            switch(trainColor){
                case "BRN":
                    trainColor = "BROWN";
                    break;
                case "G":
                    trainColor = "GREEN";
                    break;
                case "P":
                    trainColor = "PURPLE";
                    break;
                case "Y":
                    trainColor = "YELLOW";
                    break;
                case "ORG":
                    trainColor = "ORANGE";
                    break;
            }

            if(!trainColor.equals(selectedLineColor)){
                System.out.println("Color is " + lineColor.toUpperCase() + " Parsed " + trainColor.toUpperCase());
                System.out.println("Skipping " + i);
                continue;
                }

            String stationID = currentPrediction.get("staId").toString();
            String stationName = currentPrediction.get("staNm").toString();
            String destinationDirection = currentPrediction.get("destNm").toString();
            String predictedArrivalTime = currentPrediction.get("arrT").toString();

            String calculatedWaitTime = calculateWaitTimeMins(predictedArrivalTime);

            if (currentPrediction.get("isDly").equals("0")) {
                boolean isDelayed = false;
            } else {
                boolean isDelayed = true;
            }

            ArrivalPrediction newPrediction = new ArrivalPrediction(stationID, stationName, destinationDirection, trainColor,
                                                    predictedArrivalTime,calculatedWaitTime, isDelayed);
            requestedPredictions.add(newPrediction);
        }
        System.out.println("Requested Predictions Len " + requestedPredictions.size());
        return requestedPredictions;
    }

    public static String calculateWaitTimeMins(String arrivalTime) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        Date currentSystemDate = format.parse(format.format(new Date()));

        String timeRemovedDate = arrivalTime.replaceAll(".*T", "");
        Date arrivalTimeDate = format.parse(timeRemovedDate);

        long waitTimeMillis = arrivalTimeDate.getTime() - currentSystemDate.getTime();
        double waitTimeSeconds = waitTimeMillis/1000.00;

        if(waitTimeSeconds <= 60){
            requestedWaitTime = "Due";
        }
        else{
            double waitTimeRoundedMinutes = Math.ceil((waitTimeSeconds/60));
            Integer waitTimeMinutesInt = (int) waitTimeRoundedMinutes;
            requestedWaitTime = waitTimeMinutesInt.toString();
        }
        return requestedWaitTime;
    }
}
