package Controller;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import json.JsonValue;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author Marvin Vissers
 */

abstract class ApiRequest {
    // Variable for the base url of the API
    protected String baseURL, sMap;
    protected String sSelector = "ae026dd58cd57fd2";
    protected String sToken = "4424bdd85905aa88646327911b6893598a279abb4f82466dca61a988041afb08";

    // Variable for the ip-adres of the BoeBot
    protected String sIpAdres = InetAddress.getLocalHost().getHostName();

    /**
     * Constructor for the Controller.ApiRequest
     * @param sMap the map of the api
     */
    public ApiRequest(String sMap) throws UnknownHostException {
        // Filling the variabels with the given values
        this.sMap = sMap;
        // Setting the base URL with the validation parameter filled in
        this.baseURL = "https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/" + sMap + "/?selector=" + this.sSelector + "&validator=" + this.sToken;
    }

    /**
     * Function to get the content of the API map
     * @return List with objects of the API map
     */
    public abstract ArrayList<Object> get();

    /**
     * Function to post to the map of the API
     * @param obj model of the object of the API map
     */
    public abstract void post(Object obj);

    /**
     * Function to remove quotes from the JsonValue String
     * @param sJsonValue the gotton value of the API
     */
    public String removeQuotes(JsonValue sJsonValue) {
        // Remvoing the quotes from the JsonValue
        String sStringJsonValue = sJsonValue.toString().replace("\"", "");

        return sStringJsonValue;
    }

    /**
     * Function to make a String URL friendly
     * @param sText the String that needs to be made URL friendly
     * @return the String that is URL friendly
     */
    public String makeUrlFriendly(String sText) {
        try {
            // Converting the String to url safe
            String sUrlSafeText = URLEncoder.encode(sText, "UTF-8");
            return sUrlSafeText;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            return null;
        }
    }
}

