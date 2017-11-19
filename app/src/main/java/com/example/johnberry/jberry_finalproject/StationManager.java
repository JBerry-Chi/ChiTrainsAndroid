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
        System.out.println("Running in Station Manager; Creating new Maps!!");
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

    public static String getStationID(String lineColor, String stationName){
        String requestedID = "";
        switch (lineColor.toUpperCase()) {
            case "RED":
                requestedID = redLineMap.get(stationName);
                break;
            case "BROWN":
                requestedID = brownLineMap.get(stationName);
                break;
            case "PURPLE":
                requestedID = purpleLineMap.get(stationName);
                break;
            case "YELLOW":
                requestedID = yellowLineMap.get(stationName);
                break;
            case "GREEN":
                requestedID = greenLineMap.get(stationName);
                break;
            case "BLUE":
                requestedID = blueLineMap.get(stationName);
                break;
            case "PINK":
                requestedID = pinkLineMap.get(stationName);
                break;
            case "ORANGE":
                requestedID = orangeLineMap.get(stationName);
                break;
        }
        return requestedID;
    }

    public static ArrayList<String> getAllStationsForLine(String lineColor) {
        ArrayList<String> requestedStations = new ArrayList<String>();
        switch (lineColor) {
            case "RED":
                for (String station : redLineMap.keySet())
                    requestedStations.add(station);
                break;
            case "BROWN":
                for (String station : brownLineMap.keySet())
                    requestedStations.add(station);
                break;
            case "PURPLE":
                for (String station : purpleLineMap.keySet())
                    requestedStations.add(station);
                break;
            case "YELLOW":
                for (String station : yellowLineMap.keySet())
                    requestedStations.add(station);
                break;
            case "GREEN":
                for (String station : greenLineMap.keySet())
                    requestedStations.add(station);
                break;
            case "BLUE":
                for (String station : blueLineMap.keySet())
                    requestedStations.add(station);
                break;
            case "PINK":
                for (String station : pinkLineMap.keySet())
                    requestedStations.add(station);
                break;
            case "ORANGE":
                for (String station : orangeLineMap.keySet())
                    requestedStations.add(station);
                break;
        }
        return requestedStations;
    }

    private static void setupStationMaps(){
        setupRedLineMap();
        setupBrownLineMap();
        setupPurpleLineMap();
        setupPinkLineMap();
        setupYellowLineMap();
        setupOrangeLineMap();
        setupGreenLineMap();
        setupBluelineMap();
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
        brownLineMap.put("Kimball", "41290");
        brownLineMap.put("Kedzie", "41180");
        brownLineMap.put("Francisco", "40870");
        brownLineMap.put("Rockwell", "41010");
        brownLineMap.put("Western", "41480");
        brownLineMap.put("Damen", "40090");
        brownLineMap.put("Montrose", "41500");
        brownLineMap.put("Irving Park", "41460");
        brownLineMap.put("Addison", "41440");
        brownLineMap.put("Paulina", "41310");
        brownLineMap.put("Southport", "40360");
        brownLineMap.put("Belmont", "41320");
        brownLineMap.put("Wellington", "41210");
        brownLineMap.put("Diversey", "40530");
        brownLineMap.put("Fullerton", "41220");
        brownLineMap.put("Armitage", "40660");
        brownLineMap.put("Sedgwick", "40800");
        brownLineMap.put("Chicago", "40710");
        brownLineMap.put("Merchandise Mart", "40460");
        brownLineMap.put("Washington/Wells", "40730");
        brownLineMap.put("Quincy", "40040");
        brownLineMap.put("LaSalle/Van Buren", "40160");
        brownLineMap.put("Adams/Wabash", "40680");
        brownLineMap.put("Randolph/Wabash", "40200");
        brownLineMap.put("State/Lake", "40260");
        brownLineMap.put("Clark/Lake", "40380");
    }
    private static void setupPurpleLineMap(){
        purpleLineMap.put("Linden", "41050");
        purpleLineMap.put("Central","41250");
        purpleLineMap.put("Noyes","40400");
        purpleLineMap.put("Foster","40520");
        purpleLineMap.put("Davis","40050");
        purpleLineMap.put("Dempster","40690");
        purpleLineMap.put("Main","40270");
        purpleLineMap.put("South Blvd","40840");
        purpleLineMap.put("Howard","40900");
        purpleLineMap.put("Wilson","40540");
        purpleLineMap.put("Sheridan","40080");
        purpleLineMap.put("Belmont","41320");
        purpleLineMap.put("Wellington","41210");
        purpleLineMap.put("Diversey","40530");
        purpleLineMap.put("Fullerton","41220");
        purpleLineMap.put("Armitage","40660");
        purpleLineMap.put("Sedgwick","40800");
        purpleLineMap.put("Chicago","40710");
        purpleLineMap.put("Merchandise Mart","40460");
        purpleLineMap.put("Clark/Lake","40380");
        purpleLineMap.put("State/Lake","40260");
        purpleLineMap.put("Randolph/Wabash","40200");
        purpleLineMap.put("Adams/Wabash","40680");
        purpleLineMap.put("Harold Washington Library-State/Van Buren","40850");
        purpleLineMap.put("LaSalle/Van Buren","40160");
        purpleLineMap.put("Quincy","40040");
        purpleLineMap.put("Washington/Wells","40730");
    }
    private static void setupPinkLineMap(){
        pinkLineMap.put("54th/Cermak","40580");
        pinkLineMap.put("Cicero","40420");
        pinkLineMap.put("Kostner","40600");
        pinkLineMap.put("Pulaski","40150");
        pinkLineMap.put("Central Park","40780");
        pinkLineMap.put("Kedzie","41040");
        pinkLineMap.put("California","40440");
        pinkLineMap.put("Western","40740");
        pinkLineMap.put("Damen","40210");
        pinkLineMap.put("18th","40830");
        pinkLineMap.put("Polk","41030");
        pinkLineMap.put("Ashland","40170");
        pinkLineMap.put("Morgan","41510");
        pinkLineMap.put("Clinton","41160");
        pinkLineMap.put("Clark/Lake","40380");
        pinkLineMap.put("State/Lake","40260");
        pinkLineMap.put("Randolph/Wabash","40200");
        pinkLineMap.put("Adams/Wabash","40680");
        pinkLineMap.put("Harold Washington Library-State/Van Buren","40850");
        pinkLineMap.put("LaSalle/Van Buren","40160");
        pinkLineMap.put("Quincy","40040");
        pinkLineMap.put("Washington/Wells","40730");
    }
    private static void setupYellowLineMap(){
        yellowLineMap.put("Dempster-Skokie","40140");
        yellowLineMap.put("Oakton-Skokie","41680");
        yellowLineMap.put("Howard","40900");
    }
    private static void setupOrangeLineMap(){
        orangeLineMap.put("Midway","40930");
        orangeLineMap.put("Pulaski","40960");
        orangeLineMap.put("Kedzie","41070");
        orangeLineMap.put("Western","40310");
        orangeLineMap.put("35th/Archer","40120");
        orangeLineMap.put("Ashland","41060");
        orangeLineMap.put("Halsted","41130");
        orangeLineMap.put("Roosevelt","41400");
        orangeLineMap.put("Harold Washington Library-State/Van Buren","40850");
        orangeLineMap.put("LaSalle/Van Buren","40160");
        orangeLineMap.put("Quincy","40040");
        orangeLineMap.put("Washington/Wells","40730");
        orangeLineMap.put("Clark/Lake","40380");
        orangeLineMap.put("State/Lake","40260");
        orangeLineMap.put("Randolph/Wabash","40200");
        orangeLineMap.put("Adams/Wabash","40680");
    }
    private static void setupGreenLineMap(){
        greenLineMap.put("Harlem/Lake","40020");
        greenLineMap.put("Oak Park","41350");
        greenLineMap.put("Ridgeland","40610");
        greenLineMap.put("Austin","41260");
        greenLineMap.put("Central","40280");
        greenLineMap.put("Laramie","40700");
        greenLineMap.put("Cicero","40480");
        greenLineMap.put("Pulaski","40030");
        greenLineMap.put("Conservatory-Central Park Drive","41670");
        greenLineMap.put("Kedzie","41070");
        greenLineMap.put("California","41360");
        greenLineMap.put("Ashland","40170");
        greenLineMap.put("Morgan","41510");
        greenLineMap.put("Clinton","41160");
        greenLineMap.put("Clark/Lake","40380");
        greenLineMap.put("State/Lake","40260");
        greenLineMap.put("Randolph/Wabash","40200");
        greenLineMap.put("Adams/Wabash","40680");
        greenLineMap.put("Roosevelt","41400");
        greenLineMap.put("Cermack-McCormick Place","41690");
        greenLineMap.put("35th-Bronzeville-IIT","41120");
        greenLineMap.put("Indiana","40300");
        greenLineMap.put("43rd","41270");
        greenLineMap.put("47th","41080");
        greenLineMap.put("51st","40130");
        greenLineMap.put("Garfield","40510");
        greenLineMap.put("King Drive","41140");
        greenLineMap.put("Cottage Grove","40720");
        greenLineMap.put("Halsted","40940");
        greenLineMap.put("Ashland/63rd","40290");
    }
    private static void setupBluelineMap(){
        blueLineMap.put("O'Hare","40890");
        blueLineMap.put("Rosemont","40820");
        blueLineMap.put("Cumberland","40230");
        blueLineMap.put("Harlem (Blue - O'Hare Branch)","40750");
        blueLineMap.put("Jefferson Park","41280");
        blueLineMap.put("Montrose","41330");
        blueLineMap.put("Irving Park","40550");
        blueLineMap.put("Addison","41240");
        blueLineMap.put("Belmont","40060");
        blueLineMap.put("Logan Square","41020");
        blueLineMap.put("California","40570");
        blueLineMap.put("Western (Blue - O'Hare Branch)","40670");
        blueLineMap.put("Damen","40590");
        blueLineMap.put("Division","40320");
        blueLineMap.put("Chicago","41410");
        blueLineMap.put("Grand","40490");
        blueLineMap.put("Clark/Lake","40380");
        blueLineMap.put("Washington","40370");
        blueLineMap.put("Monroe","40790");
        blueLineMap.put("Jackson","40070");
        blueLineMap.put("LaSalle","41340");
        blueLineMap.put("Clinton","40430");
        blueLineMap.put("UIC-Halsted","40350");
        blueLineMap.put("Racine","40470");
        blueLineMap.put("Illinois Medical District","40810");
        blueLineMap.put("Western (Blue - Forest Park Branch)","40220");
        blueLineMap.put("Kedzie-Homan","40250");
        blueLineMap.put("Pulaski","40920");
        blueLineMap.put("Cicero","40970");
        blueLineMap.put("Austin","40010");
        blueLineMap.put("Oak Park","40180");
        blueLineMap.put("Harlem (Blue - Forest Park Branch)","40980");
        blueLineMap.put("Forest Park","40390");
    }
}
