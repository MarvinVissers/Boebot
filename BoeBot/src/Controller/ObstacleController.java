package Controller;

import json.JsonValue;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class ObstacleController extends ApiRequest {
    /**
     * Constructor for the Controller.ApiRequest
     *
     * @param sMap the map of the api
     */
    public ObstacleController(String sMap) throws UnknownHostException {
        super(sMap);
//        System.out.println(baseURL);
//        System.out.println(sIpAdres);
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

    }

    /**
     * Function to delete a row of the API map
     *
     * @param obj model of the object of the API map
     */
//    @Override
//    void delete(Object obj) {
//
//    }

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
}
