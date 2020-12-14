package Controller;

import Model.Obstacle;
import javafx.collections.ObservableList;
import json.Json;
import json.JsonArray;
import json.JsonObject;
import json.JsonValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;

public class ObstacleController extends ApiRequest {
    /**
     * Constructor for the Controller.ApiRequest
     *
     * @param sMap the map of the api
     */
    public ObstacleController(String sMap) throws UnknownHostException {
        super(sMap);
    }

    /**
     * Function to get the content of the API map
     *
     * @return List with objects of the API map
     */
    @Override
    public ArrayList<Object> get() {
        // Creating the array for the Obstacles
        ArrayList<Object> listObstacles = new ArrayList<>();

        try {
            // Setting to URL to get from
            URL oracle = new URL(this.baseURL + "&action=get");
            // Opening the file
            URLConnection yc = oracle.openConnection();
            // Reading the file
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            // String for the given JSON
            String inputLine;
            // Looping through the JSON
            while ((inputLine = in.readLine()) != null) {
                // Creating an array of the result
                JsonArray json = Json.parse(inputLine).asArray();

                // Looping through the result
                for (Object o : json) {
                    // Converting the array to an Object
                    JsonObject obj = (JsonObject) o;

                    // Getting the values
                    int iId = Integer.parseInt(removeQuotes(obj.get("id")));
                    int iGridId = Integer.parseInt(removeQuotes(obj.get("gridId")));
                    int iRow1 = Integer.parseInt(removeQuotes(obj.get("row1")));
                    int iColumn1 = Integer.parseInt(removeQuotes(obj.get("column1")));
                    int iRow2 = Integer.parseInt(removeQuotes(obj.get("row2")));
                    int iColumn2 = Integer.parseInt(removeQuotes(obj.get("column2")));

                    // Filling the Obstacle model
                    Obstacle obstacle = new Obstacle(iId, iGridId, iRow1, iColumn1, iRow2, iColumn2);

                    // Adding the Model to the array
                    listObstacles.add(obstacle);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Returning the filled Array
        return listObstacles;
    }

    /**
     * Function to post to the map of the API
     *
     * @param obj model of the object of the API map
     */
    @Override
    public void post(Object obj) {

    }

    /**
     * Function to remove quotes from the JsonValue String
     *
     * @param sJsonValue the gotton value of the API
     */
    @Override
    public String removeQuotes(JsonValue sJsonValue) {
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

    public ArrayList<int[]> createObstacleList(ArrayList<Obstacle> obstacles) {
        // Creating an array to fill later
        ArrayList<int[]> obstacleList = new ArrayList<>();

        for (Object o : obstacles) {
            // Converting obstacles to the obstacle class
            Obstacle obstacle = Obstacle.class.cast(o);

            // Filling the obstacle list array with coordinates
            obstacleList.add(new int[]{obstacle.getRow1(), obstacle.getColumn1()});
            obstacleList.add(new int[]{obstacle.getRow2(), obstacle.getColumn2()});
        }

        return obstacleList;
    }


}