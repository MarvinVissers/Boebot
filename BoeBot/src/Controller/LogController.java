package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;

import Model.Log;

import Model.Obstacle;
import json.*;

public class LogController extends ApiRequest {
    /**
     * Constructor for the Controller.ApiRequest
     *
     * @param sMap the map of the api
     */
    public LogController(String sMap) throws UnknownHostException {
        super(sMap);
    }

    /**
     * Function to get the content of the API map
     *
     * @return List with objects of the API map
     */
    @Override
    public ArrayList<Object> get() {
        return null;
    }

    /**
     * Function to post to the map of the API
     *
     * @param obj model of the object of the API map
     */
    @Override
    public void post(Object obj) {
        // Converting the object to Log
        Log log = Log.class.cast(obj);

        // Making the text URL safe
        String sText = makeUrlFriendly(log.getText());

        try {
            // Setting to URL to post to
            URL oracle = new URL(this.baseURL + "&action=post&boebot=" + this.sIpAdres + "&text=" + sText);
            // Opening the file
            URLConnection yc = oracle.openConnection();
            // Reading the file
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            // String for the given JSON
            String inputLine;
            // Looping through the JSON
            while ((inputLine = in.readLine()) != null) {
                // Outputting the result
                System.out.println(inputLine);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getLastLog() {
        try {
            // Setting to URL to post to
            URL apiLink = new URL(this.baseURL + "&action=get&boebot=" + this.sIpAdres);
            // Opening the file
            URLConnection apiConnection = apiLink.openConnection();
            // Reading the file
            BufferedReader result = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

            System.out.println(apiLink);

            // String for the given JSON
            String inputLine;
            // Looping through the JSON
            while ((inputLine = result.readLine()) != null) {
                // Creating an array of the result
                JsonArray json = Json.parse(inputLine).asArray();

                // Looping through the result
                for (Object o : json) {
                    // Converting the array to an Object
                    JsonObject obj = (JsonObject) o;

                    // Getting the values
                    int iId = Integer.parseInt(removeQuotes(obj.get("id")));
                    String sText = (removeQuotes(obj.get("text")));

                    // Filling the Log model
                    Log log = new Log(iId, this.sIpAdres, sText);

                    System.out.println(sText);

                    // Returning the last log
                    return log.getText();
                }
            }
        } catch (Exception e) {
            // Printing the error
            System.out.println(e);
        }
        // Returning nothing
        return null;
    }
}
