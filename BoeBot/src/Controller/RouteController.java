package Controller;

import Model.Node;
import Model.Route;

import java.util.ArrayList;
import java.util.Arrays;

public class RouteController {



    public RouteController() {

    }

//    public int DriveRoute(ArrayList<int[]> Route, int iLastX, int iLastY, int iOffSet){
//        if(iOffSet < Route.size()){
//            String RouteOffSet = Arrays.toString(Route.get(iOffSet));
//            System.out.println(RouteOffSet);
//
//            int iEnd = Route.indexOf(",");
//            iLastX = Integer.parseInt(RouteOffSet.substring(1, iEnd));
//            System.out.println(iLastX);
//
//            iOffSet++;
//            return DriveRoute(Route, iLastX, iLastY, iOffSet);
//        } else {
//            return 1;
//        }
//    }

    public Route DriveRoute(Route route){
        if(route.getOffset() < route.getListCoordinates().size()) {
            Node routeOffset = route.getListCoordinates().get(route.getOffset());

//            Route route = new Route(Route, routeOffset, sDirection, iOffSet, 0);
            System.out.println("Nieuwe route"+ routeOffset);
            System.out.println("Oude route" + route.getLastCoordinates());

            String sNewDirection;
            // Last 0,0 New = 1,0
            if (route.getLastCoordinates().getRow() < routeOffset.getRow()) {
                System.out.println("Right");
                sNewDirection = "Right";
                route.setDirection(sNewDirection);
            } else if (route.getLastCoordinates().getRow() > routeOffset.getRow()) {
                System.out.println("Links");
                sNewDirection = "Left";
                route.setDirection(sNewDirection);
            } else if (route.getLastCoordinates().getCol() < routeOffset.getCol()) {
                System.out.println("Ga omhoog");
                sNewDirection = "Up";
                route.setDirection(sNewDirection);
            } else if (route.getLastCoordinates().getCol() > routeOffset.getCol()) {
                System.out.println("Down");
                sNewDirection = "Down";
                route.setDirection(sNewDirection);
            } else {
                System.out.println("Ik weet het niet meer");
            }

            int iOffset = route.getOffset() + 1;
            route.setOffset(iOffset);
            return route;
        } else {
            route.setResult(1);
            return route;
        }
    }
}
