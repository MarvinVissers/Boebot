import Controller.*;
import Controller.LogController;
import Model.Node;
import Model.Obstacle;
import Model.Log;

import Model.Route;
import TI.BoeBot;
import TI.Servo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

public class RobotMain {

    static int AnalogPin1 = 0;  // Rechts
    static int AnalogPin2 = 1;  // Midde
    static int AnalogPin3 = 2;  // Links

    static Servo Slink = new Servo(12);
    static Servo Srechts = new Servo(13);

    static int NormaleSnelheid = 1500;
    //kleine afweiking omdat me servo stom doet
    static int NormaleSnelheid2 = 1500;
    static int RaceSnelHeid = 50;
    //zodat hij niet onnodig iets oppakt
    static int Gevoeligheid = 400;

    public static void main(String[] args) throws UnknownHostException {
        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");
        LogController logCtrl = new LogController("log");
        RouteController routeCtrl = new RouteController();

        // Filling the obstacle list
        ArrayList<Obstacle> obstacles = new ArrayList(obstacleCtrl.get());
        // Filling an array with the obstacle coordinates
        ArrayList<int[]> obstacleCoordinates = new ArrayList(obstacleCtrl.createObstacleList(obstacles));
        Log log = new Log(null, "Opstarten Boebot");
        logCtrl.post(log);
        BoeBotController BoeController = new BoeBotController();
//        ArrayList<int[]> Route = BoeController.Astar(obstacleCoordinates);
        ArrayList<Node> Route = BoeController.testStar(obstacleCoordinates);

//        routeCtrl.DriveRoute(Route, 0, 0, 0);

       // BoeBot.wait(10000);
        Slink.update(NormaleSnelheid - RaceSnelHeid);
        Srechts.update(NormaleSnelheid2 + RaceSnelHeid);
        Route route = routeCtrl.DriveRoute(new Route(Route, new Node(0, 0), "Right", 1, 0));

        while (true) {
           // System.out.println(Arrays.deepToString(BoeController.Astar(obstacleCoordinates).toArray()));
            //BoeController.Astar(obstacleCoordinates);
            BoeBot.wait(1000);

            int sensor1 = BoeBot.analogRead(AnalogPin1);
            int sensor2 = BoeBot.analogRead(AnalogPin2);
            int sensor3 = BoeBot.analogRead(AnalogPin3);

            // Functie voor kruispunte
            if ((sensor1 >= Gevoeligheid) && (sensor2 >= Gevoeligheid) && (sensor3 >= Gevoeligheid)) {
                System.out.println("Alvast voor een kruispunt");
                BoeBot.freqOut(0,1500,1000);
                switch (route.getDirection()) {
                    case "Right":
                        BoeController.turnDegrees(90, 50);
                        break;
                    case "Left":
                        BoeController.turnDegrees(90, 50);
                        break;
                    case "Up":
                        BoeController.turnDegrees(90, 50);
                        break;
                    case "Down":
                        BoeController.turnDegrees(90, 50);
                        break;
                }

                Slink.update(NormaleSnelheid - RaceSnelHeid);
                Srechts.update(NormaleSnelheid2 + RaceSnelHeid);
                route = routeCtrl.DriveRoute(route);

                if (route.getResult() == 1) {
                    System.out.println("Route geslaagd");
                }
            }
        }
    }
}
