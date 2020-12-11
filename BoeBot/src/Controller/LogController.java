package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Model.Log;

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
            URL oracle = new URL(this.baseURL + "&action=post&text=" + sText);
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

    /**
     * Function to remove quotes from the JsonValue String
     *
     * @param sJsonValue the gotton value of the API
     */
    @Override
    public String removeQuotes(JsonValue sJsonValue) {
        // Remvoing the quotes from the JsonValue
        String sNewJsonValue = sJsonValue.toString().replace("\"", "");

        return sNewJsonValue;
    }

    /**
     * Function to make a String URL friendly
     *
     * @param sText the String that needs to be made URL friendly
     * @return the String that is URL friendly
     */
    @Override
    public String makeUrlFriendly(String sText) {
        try {
            // Converting the String to url safe
            String url = URLEncoder.encode(sText, "UTF-8");
            return url;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            return null;
        }
    }
}
