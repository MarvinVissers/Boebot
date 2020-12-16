package Controller;

import Model.Node;
import Model.Route;

import java.util.ArrayList;
import java.util.Arrays;

public class RouteController {

    public RouteController() {

    }

    /**
     * Function to set the new coordinates for the boebot to drive to
     * @param route the Route object
     * @return the new Route object
     */
    public Route DriveRoute(Route route) {
        // Checking if there is a new point for the Boebot to drive to
        if(route.getOffset() < route.getListCoordinates().size()) {
            // Setting the new point to drive to
            Node routeOffset = route.getListCoordinates().get(route.getOffset());

            // Last 0,0 New = 1,0
            // Checking wether the row or column changes for the boebots direction
            if (route.getLastCoordinates().getRow() < routeOffset.getRow()) {
                // Setting the new direction
                route.setDirection("Right");
            } else if (route.getLastCoordinates().getRow() > routeOffset.getRow()) {
                // Setting the new direction
                route.setDirection("Left");
            } else if (route.getLastCoordinates().getCol() < routeOffset.getCol()) {
                // Setting the new direction
                route.setDirection("Up");
            } else if (route.getLastCoordinates().getCol() > routeOffset.getCol()) {
                // Setting the new direction
                route.setDirection("Down");
            } else {
                System.out.println("Oof");
            }

            // Updating the offset
            int iOffset = route.getOffset() + 1;
            route.setOffset(iOffset);

            // Updating the last coordinates
            route.setLastCoordinates(routeOffset);

            // Giving back the new route
            return route;
        } else {
            // Setting the result to done
            route.setResult(1);

            // Giving back the route
            return route;
        }
    }
}
