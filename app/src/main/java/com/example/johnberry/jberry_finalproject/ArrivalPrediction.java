package com.example.johnberry.jberry_finalproject;

/**
 * Created by John Berry on 10/25/2017.
 */

public class ArrivalPrediction {

    private String timeStamp;
    private String stationID;
    private String stopID;
    private String stationName;
    private String serviceDirection;
    private int runNumber;
    private String trainColor;
    private String nextStopID;

    private String predictedDepartureTime; //CHANGE TO TIMESTAMP OBJECT?
    private String predictedArrivalTime; //CHANGE TO TIMESTAMP OBJECT?

    private boolean isDelayed;

    //ADD VARIABLE TO HOLD EXPECTED WAIT TIME IN MINS;


    public ArrivalPrediction(String timeStamp, String stationID, String stopID, String stationName,
                             String serviceDirection, String trainColor, String predictedDepartureTime,
                             String predictedArrivalTime, boolean isDelayed){

        timeStamp = timeStamp;
        stationID = stationID;
        stopID = stopID;
        stationName = stationName;
        serviceDirection = serviceDirection;
        trainColor = trainColor;
        predictedDepartureTime = predictedDepartureTime;
        predictedArrivalTime = predictedArrivalTime;
        isDelayed = isDelayed;

    }

    public String getTimeStamp(){
        return timeStamp;
    }
    public String getStationID(){
        return stationID;
    }
    public String getStopID(){
        return stopID;
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
    public String getPredictedDepartureTime(){
        return predictedDepartureTime;
    }
    public String getPredictedArrivalTime(){
        return predictedArrivalTime;
    }
    public boolean getDelayedStatus(){
        return isDelayed;
    }
}
