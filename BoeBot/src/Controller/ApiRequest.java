package Controller;

import java.util.ArrayList;

abstract class ApiRequest {
    // Variable for the base url of the API
    protected String baseURL, sMap;
    protected String sSelector = "ae026dd58cd57fd2";
    protected String sToken = "4424bdd85905aa88646327911b6893598a279abb4f82466dca61a988041afb0";

    /**
     * Constructor for the Controller.ApiRequest
     * @param sMap the map of the api
     */
    public ApiRequest(String sMap) {
        // Filling the variabeels with the given values
        this.sMap = sMap;
        // Setting the base URL with the validation parameter filled in
        this.baseURL = "https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/" + sMap + "/?selector=" + this.sSelector + "&validator=" + this.sToken;
    }

    /**
     * Function to get the content of the API map
     * @return List with objects of the API map
     */
    abstract ArrayList<Object> get();

    /**
     * Function to post to the map of the API
     * @param obj model of the object of the API map
     */
    abstract void post(Object obj);

    /**
     * Function to update a row of the API map
     * @param obj model of the object of the API map
     */
//    abstract void put(Object obj);

    /**
     * Function to delete a row of the API map
     * @param obj model of the object of the API map
     */
    abstract void delete(Object obj);
}

