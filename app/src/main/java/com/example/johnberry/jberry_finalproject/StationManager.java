package com.example.johnberry.jberry_finalproject;

/*
   StationManager is a Singleton that handles station information requests
   when a user selects a given train line.
 */

import java.util.ArrayList;
import java.util.HashMap;

public final class StationManager {

    private static StationManager instance;
    private static HashMap<String, String> redLineMap;
    private static HashMap<String, String> brownLineMap;
    private static HashMap<String, String> purpleLineMap;
    private static HashMap<String, String> yellowLineMap;
    private static HashMap<String, String> blueLineMap;
    private static HashMap<String, String> greenLineMap;
    private static HashMap<String, String> pinkLineMap;
    private static HashMap<String, String> orangeLineMap;
    static{
        redLineMap = new HashMap<String, String>();
        brownLineMap = new HashMap<String, String>();
        purpleLineMap = new HashMap<String, String>();
        yellowLineMap = new HashMap<String, String>();
        blueLineMap = new HashMap<String, String>();
        greenLineMap = new HashMap<String, String>();
        pinkLineMap = new HashMap<String, String>();
        orangeLineMap = new HashMap<String, String>();
        setupStationMaps();
    }


    private StationManager(){};

    public static StationManager getInstance(){
        if(instance==null){
            instance = new StationManager();
            return instance;
        }
        return instance;
    }

    public static ArrayList<String> getAllStationsForLine(String lineColor) {

        ArrayList<String> requestedStations = new ArrayList<String>();

        switch (lineColor) {
            case "RED":
                for (String station : redLineMap.keySet()) {
                    requestedStations.add(station);
                }
                break;
        }
        return requestedStations;
    }

    private static void setupStationMaps(){
        //Possible Threading? Thread Pool for each?

        setupRedLineMap();

        /*setupBrownLineMap();
        setupPurpleLineMap();
        setupPinkLineMap();
        setupYellowLineMap();
        setupOrangeLineMap();
        setupGreenLineMap();
        setupBluelineMap(); */
    }
    private static void setupRedLineMap(){
        redLineMap.put("Howard", "40900");
        redLineMap.put("Jarvis", "41190");
        redLineMap.put("Morse", "40100");
        redLineMap.put("Loyola", "41300");
        redLineMap.put("Granville", "40760");
        redLineMap.put("Thorndale", "40880");
        redLineMap.put("Bryn Mawr", "41380");
        redLineMap.put("Berwyn", "40340");
        redLineMap.put("Argyle", "41200");
        redLineMap.put("Lawrence", "40770");
        redLineMap.put("Wilson", "40540");
        redLineMap.put("Sheridan", "40080");
        redLineMap.put("Addison", "41420");
        redLineMap.put("Belmont", "41320");
        redLineMap.put("Fullerton", "41220");
        redLineMap.put("North & Clybourn", "40650");
        redLineMap.put("Clark & Division", "41320");
        redLineMap.put("Chicago", "41450");
        redLineMap.put("Grand", "40330");
        redLineMap.put("Lake", "41660");
        redLineMap.put("Monroe", "41090");
        redLineMap.put("Jackson", "40560");
        redLineMap.put("Harrison", "41490");
        redLineMap.put("Roosevelt", "41400");
        redLineMap.put("Cermak-Chinatown", "41000");
        redLineMap.put("Sox-35th", "40190");
        redLineMap.put("47th", "41230");
        redLineMap.put("Garfield", "41170");
        redLineMap.put("63rd", "40910");
        redLineMap.put("69th", "40990");
        redLineMap.put("79th", "40240");
        redLineMap.put("87th", "41430");
        redLineMap.put("95th/Dan Ryan", "40450");
    }

    private static void setupBrownLineMap(){

    }

    private static void setupPurpleLineMap(){

    }
    private static void setupPinkLineMap(){

    }
    private static void setupYellowLineMap(){

    }
    private static void setupOrangeLineMap(){

    }
    private static void setupGreenLineMap(){

    }
    private static void setupBluelineMap(){

    }
}
