package com.example.johnberry.jberry_finalproject;

import java.text.ParseException;

public class ArrivalPrediction {

    private String timeStamp;
    private String stationID;
    private String stationName;
    private String serviceDirection;
    private String trainColor;
    private boolean isDelayed;

    private String predictedArrivalTime;
    private String waitTimeMins;

    public ArrivalPrediction(String stationIDIn, String stationNameIn,
                             String serviceDirectionIn, String trainColorIn,
                             String predictedArrivalTimeIn, String waitTimeMinsIn, boolean isDelayedIn){
        stationID = stationIDIn;
        stationName = stationNameIn;
        serviceDirection = serviceDirectionIn;
        trainColor = trainColorIn;
        predictedArrivalTime = predictedArrivalTimeIn;
        waitTimeMins = waitTimeMinsIn;
        isDelayed = isDelayedIn;
    }

    public String getStationID(){
        return stationID;
    }
    public String getServiceDirection(){
        return serviceDirection;
    }
    public String getTrainColor(){
        return trainColor;
    }
    public String getStationName(){
        return stationName;
    }
    public String getPredictedArrivalTime(){
        return predictedArrivalTime;
    }
    public boolean getDelayedStatus(){
        return isDelayed;
    }
    public String getWaitTimeMins(){return waitTimeMins;};

    public String updateWaitTimeMins() throws ParseException {
        String updatedWaitTime = Parser.calculateWaitTimeMins(this.predictedArrivalTime);
        this.waitTimeMins = updatedWaitTime;
        return this.waitTimeMins;
    }
}
