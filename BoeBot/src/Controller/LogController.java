package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Model.Log;

import json.*;

/**
 * @author Marvin Vissers
 */

public class LogController extends ApiRequest implements Runnable {
    private volatile String sLogAction = "none";

    /**
     * Constructor for the Controller.ApiRequest
     *
     * @param sMap the map of the api
     */
    public LogController(String sMap) throws UnknownHostException {
        super(sMap);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            // Getting the last log
            String sLogText = getLastLog();

            // Getting its action
            this.sLogAction = checkLogAction(sLogText);

            // Sleeeping for a bit
            Thread.sleep(5000);
            run();
        } catch (Exception e) {
            e.printStackTrace();

            this.sLogAction = "none";
        }
    }

    public String getLogText() {
        return this.sLogAction;
    }

    /**
     * Function to get the content of the API map
     *
     * @return List with objects of the API map
     */
    @Override
    public ArrayList<Object> get() {
        // Creating an ArrayList to fill
        ArrayList<Object> listLogs = new ArrayList<>();

        try {
            // Setting to URL to post to
            URL apiLink = new URL(this.baseURL + "&action=get&boebot=" + this.sIpAdres);
            // Opening the file
            URLConnection apiConnection = apiLink.openConnection();
            // Reading the file
            BufferedReader result = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

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

                    // Filling the listLogs array
                    listLogs.add(new Log(iId, this.sIpAdres, sText));
                }
            }
        } catch (Exception e) {
            // Printing the error
            e.printStackTrace();
        }

        // Returning list with all logs items
        return listLogs;
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
            URL apiLink = new URL(this.baseURL + "&action=post&boebot=" + this.sIpAdres + "&text=" + sText);

            new Thread(() -> {
                try {
                    // Opening the file
                    URLConnection conn = apiLink.openConnection();
                    // Reading the file
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function so the log model doesn't need te be given in order to post a log
     * @param sText the text of the log item
     */
    public void postLog(String sText) {
        // Creating an log object
        Log log = new Log(null, this.sIpAdres, sText);

        // Posting the log
        post(log);
    }

    /**
     * Getting the last log items given for a Boebot
     * @return the last log text in the API
     */
    public String getLastLog() {
        try {
            // Setting to URL to post to
            URL apiLink = new URL(this.baseURL + "&action=getLast&boebot=" + this.sIpAdres);
            // Opening the file
            URLConnection apiConnection = apiLink.openConnection();
            // Reading the file
            BufferedReader result = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

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
                    String sText = (removeQuotes(obj.get("text")));
                    // Giving back the test
                    return sText;
                }
            }
        } catch (Exception e) {
            // Printing the error
            e.printStackTrace();
        }
        // Returning nothing
        return "none";
    }

    /**
     * Function to check the action of the last log item
     * @param sLogText the text of the the last log item
     * @return the action of the last log item
     */
    public String checkLogAction(String sLogText) {
        try {
            // Setting the log text to uppercase to better check. test will be TEST
            String sLogTextUpper = sLogText.toUpperCase();

            // Checking if log text contains the words test, or drive from
            // Drive from for driving route
            if (sLogTextUpper.contains("TEST")) {
                // Seeing wich element(s) need to be tested
                return checkTestAction(sLogTextUpper);
            } else if (sLogTextUpper.contains("DRIVE FROM")) {
                return "route";
            } else {
                return "none";
            }
        } catch (Exception e) {
            return "none";
        }
    }

    /**
     * Function to check wich test needs to be done
     * @param sLogText the text of the log
     * @return the string with the text
     */
    private String checkTestAction(String sLogText) {
        try {
            // Checking wich test needs to be done
            if (sLogText.contains("TEST ALL")) {
                return "testAll";
            } else if (sLogText.contains("TURN RIGHT")) {
                return "testTurnRight";
            } else if (sLogText.contains("TURN LEFT")) {
                return "testTurnLeft";
            } else if (sLogText.contains(("DRIVE BACKWARD"))) {
                return "testDriveBackward";
            } else if (sLogText.contains("DRIVE FORWARD")) {
                return "testDriveForward";
            } else if (sLogText.contains("LIGHT RIGHT")) {
                return "testLightRight";
            } else {
                return "testLightLeft";
            }
        } catch (Exception e) {
            // Returning nothing if nothing is found
            return "none";
        }
    }
}
