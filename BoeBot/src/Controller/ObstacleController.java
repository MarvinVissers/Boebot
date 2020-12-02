package Controller;

import java.util.ArrayList;

public class ObstacleController extends ApiRequest {
    /**
     * Constructor for the Controller.ApiRequest
     *
     * @param sMap the map of the api
     */
    public ObstacleController(String sMap) {
        super(sMap);
        System.out.println(baseURL);
    }

    /**
     * Function to get the content of the API map
     *
     * @return List with objects of the API map
     */
    @Override
    ArrayList<Object> get() {
        return null;
    }

    /**
     * Function to post to the map of the API
     *
     * @param obj model of the object of the API map
     */
    @Override
    void post(Object obj) {

    }

    /**
     * Function to delete a row of the API map
     *
     * @param obj model of the object of the API map
     */
    @Override
    void delete(Object obj) {

    }
}
