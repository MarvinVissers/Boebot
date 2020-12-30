package Controller;

import Model.Node;
import Model.Route;
import Model.SFNodes;
import json.Json;
import json.JsonArray;
import json.JsonObject;
import json.JsonValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * @author Rick Deurloo
 * @author Marvin Vissers
 */

public class RouteController {

    public RouteController () {

    }

    /**
     * Function to set the new coordinates for the boebot to drive to
     * @param route the Route object
     * @return the new Route object
     */
    public Route DriveRoute(Route route) {
        // Checking if there is a new point for the Boebot to drive to
        if (route.getOffset() < route.getListCoordinates().size()) {
            // Setting the new point to drive to
            Node routeOffset = route.getListCoordinates().get(route.getOffset());

            // Last 0,0 New = 1,0
            // Checking whether the row or column changes for the boebots direction
            if (route.getLastCoordinates().getRow() < routeOffset.getRow()) {
                // Going a row up
                // Checking if direction is chancing
                if (!switchDirection(route.getDirection(), "Right")) {
                    // Setting the new direction based on its current direction
                    switch (route.getDirection()) {
                        case "Left":
                            route.setDirection("Up");
                            break;
                        case "Up":
                            route.setDirection("Left");
                            break;
                        case "Down":
                            route.setDirection("Right");
                            break;
                        default:
                            System.out.println("error");
                    }
                }
            } else if (route.getLastCoordinates().getRow() > routeOffset.getRow()) {
                // Going a row down
                // Checking if direction is chancing
                if (!switchDirection(route.getDirection(), "Left")) {
                    // Setting the new direction based on its current direction
                    switch (route.getDirection()) {
                        case "Up":
                            route.setDirection("Left");
                            break;
                        case "Down":
                            route.setDirection("Right");
                            break;
                        case "Right":
                            route.setDirection("Down");
                            break;
                        default:
                            System.out.println("error");
                    }
                }
            } else if (route.getLastCoordinates().getCol() < routeOffset.getCol()) {
                // Going a column up
                // Checking if direction is chancing
                if (!switchDirection(route.getDirection(), "Up")) {
                    // Setting the new direction based on its current direction
                    switch (route.getDirection()) {
                        case "Down":
                            route.setDirection("Up");
                            break;
                        case "Right":
                            route.setDirection("Left");
                            break;
                        case "Left":
                            route.setDirection("Right");
                            break;
                        default:
                            System.out.println("error");
                    }
                }
            } else if (route.getLastCoordinates().getCol() > routeOffset.getCol()) {
                // Going a column down
                // Checking if direction is chancing
                if (!switchDirection(route.getDirection(), "Down")) {
                    // Setting the new direction based on its current direction
                    switch (route.getDirection()) {
                        case "Right":
                            route.setDirection("Left");
                            break;
                        case "Left":
                            route.setDirection("Right");
                            break;
                        case "Up":
                            route.setDirection("Down");
                            break;
                        default:
                            System.out.println("error");
                    }
                }
            }

            // Updating the offset
            int iOffset = route.getOffset() + 1;
            route.setOffset(iOffset);

            // Updating the last coordinates
            route.setLastCoordinates(routeOffset);
        } else {
            // Setting the result to done
            route.setResult(1);
        }

        // Giving back the route
        return route;
    }

    private boolean switchDirection(String sDirection, String sNewDirection) {
        if (sDirection.matches(sNewDirection)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function to get grid size from the API
     * @return an object with the size of the grid
     */
    public Node getGridSize() {
        try {
            // Setting to URL to post to
            URL apiLink = new URL("https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/grid/?selector=ae026dd58cd57fd2&validator=4424bdd85905aa88646327911b6893598a279abb4f82466dca61a988041afb08");
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
                    int iRows = Integer.parseInt(removeQuotes(obj.get("rows")));
                    int iColumns = Integer.parseInt(removeQuotes(obj.get("columns")));

                    // Filling the Node object
                    Node grid = new Node(iRows, iColumns);

                    // Returning thr node
                    return grid;
                }
            }
        } catch (Exception e) {
            // Printing the error
            System.out.println(e);
        }

        // Returning nothing
        return null;
    }

    public SFNodes GetSFNodes() {
        try {
            // Setting to URL to post to
            InetAddress inetAddress = InetAddress.getLocalHost();
            URL apiLink = new URL("https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/route/?selector=ae026dd58cd57fd2&validator=4424bdd85905aa88646327911b6893598a279abb4f82466dca61a988041afb08&boebot="+inetAddress.getHostName());
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
                    int iRows = Integer.parseInt(removeQuotes(obj.get("row1")));
                    int iColumns = Integer.parseInt(removeQuotes(obj.get("column1")));
                    int iRows2 = Integer.parseInt(removeQuotes(obj.get("row2")));
                    int iColumns2 = Integer.parseInt(removeQuotes(obj.get("column2")));

                    SFNodes SfNodes = new SFNodes(iRows,iColumns,iRows2,iColumns2);

                    // Returning thr node
                    return SfNodes;
                }
            }
        } catch (Exception e) {
            // Printing the error
            System.out.println(e);
        }

        // Returning nothing
        return null;
    }


    /**
     * Function to remove quotes from the JsonValue String
     * @param sJsonValue the gotton value of the API
     */
    public String removeQuotes(JsonValue sJsonValue) {
        // Remvoing the quotes from the JsonValue
        String sStringJsonValue = sJsonValue.toString().replace("\"", "");

        return sStringJsonValue;
    }
}
