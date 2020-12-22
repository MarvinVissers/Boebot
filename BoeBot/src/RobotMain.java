import Controller.BoeBotController;
import Controller.LogController;
import Controller.ObstacleController;
import Controller.RouteController;
import Model.Node;
import Model.Obstacle;
import Model.Log;
import Model.Route;
import TI.BoeBot;
import TI.Servo;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class RobotMain {

    public static void main(String[] args) throws UnknownHostException {
        /**
         * Setting the variabeles that will be used
         */
        // Status if the Boebot is busy
        boolean bBusy = false;
        // Action for the boebot
        String sAction;

        // Pins for the line followers
        int analogPin1 = 0;  // Richt
        int analogPin2 = 1;  // Middle
        int analogPin3 = 2;  // Left

        // Setting the servo wheels
        Servo Slink = new Servo(12);
        Servo Srechts = new Servo(13);

        // Setting the speeds for servo
        int iNormalSpeed = 1500;
        int iRaceSpeed = 50;

        // Setting the sensor sensitivity
        int iSensitivity = 400;

        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");
        LogController logCtrl = new LogController("log");
        RouteController routeCtrl = new RouteController();
        BoeBotController BoeController = new BoeBotController();
        BoeBot.digitalWrite(15,false);
        BoeBot.digitalWrite(2,false);

        /**
         * Getting obstacles and creating list with obstacle coordinates for Rick
         */
//        System.out.println("Obstacle functions");
//        // Filling the obstacle list
//        System.out.println("Obstacle get");
//        ArrayList<Obstacle> obstacles = new ArrayList(obstacleCtrl.get());
//        System.out.println(obstacles);
//        // Filling an array with the obstacle coordinates
//        System.out.println("Obstacle coordinates");
//        ArrayList<int[]> obstacleCoordinates = new ArrayList(obstacleCtrl.createObstacleList(obstacles));
//        System.out.println(obstacleCoordinates.size());
//        System.out.println(obstacleCoordinates);

        /**
         * Actions with Log
         */
//        System.out.println("Post log");
//        Log log = new Log(null, null, "Boebot online");
//        logCtrl.post(log);
//        System.out.println("De laatste log was: " + logCtrl.getLastLog());

        /**
         * Actions with obstacles
         */
        // Filling the obstacle list
        ArrayList<Obstacle> obstacles = new ArrayList(obstacleCtrl.get());
        // Filling an array with the obstacle coordinates
        ArrayList<int[]> obstacleCoordinates = new ArrayList(obstacleCtrl.createObstacleList(obstacles));
        Log log = new Log(null, null, "Opstarten Boebot");
        logCtrl.post(log);
//        ArrayList<int[]> Route = BoeController.Astar(obstacleCoordinates);
        ArrayList<Node> Route = BoeController.Astar(obstacleCoordinates);

//        BoeBot.wait(10000);
        Slink.update(iNormalSpeed - iRaceSpeed);
        Srechts.update(iNormalSpeed + iRaceSpeed);
        Route route = routeCtrl.DriveRoute(new Route(Route, new Node(0, 0), "Right", 1, 0));

        while (true) {
           // BoeController.detectObject();
            /**
             * Checking the action that the boebot should be doing
             * After that is a switch statement with the action
             */
            // Getting the last log item
            String sLogText = logCtrl.getLastLog();
            // Setting the log text to an action
            sAction = logCtrl.checkLogAction(sLogText);

            // Checking if the Boebot is busy
            if (bBusy) {
                // Switch to get do the action of the Boebot
                switch (sAction) {
                   // Test left left
                    case "testLightLeft":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test lights left");

                        // Executing the test
                        BoeController.KnipperLinks(5);

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                    // Test left right
                    case "testLightRight":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test lights right");

                        // Executing the test
                        BoeController.KnipperRechts(5);

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                    // Test drive foward
                    case "testDriveForward":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test drive forward");

                        // Executing the test
                        BoeController.toSpeed(50);

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                    // Test drive backward
                    case "testDriveBackward":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test drive backward");

                        // Executing the test
                        BoeController.toSpeed(-50);

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                    // Test turn left
                    case "testTurnLeft":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test turn left");

                        // Executing the test
                        BoeController.turnDegrees(360, 50);

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                    // Test turn right
                    case "testTurnRight":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test turn right");

                        // Executing the test
                        BoeController.turnDegrees(360, -50);

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                    // Test all test functions
                    case "testAll":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test all");

                        // Executing the test
                        BoeController.testAll();

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                }

                if (sAction.contains("route")) {
                    // Route code
                }
            } else {
                // Checkking the action of the log
                if (sAction != null) {
                    System.out.println(sAction);
                    bBusy = true;
                } else {
                    System.out.println(logCtrl.getLastLog());
                }
            }

            /**
             * Drive route logic
             */
            // Getting the fastest route
            BoeController.Astar(obstacleCoordinates);
            BoeBot.wait(1000);

            // Reading out the line followers
            int iSensorLeft = BoeBot.analogRead(analogPin1);
            int iSensorMiddle = BoeBot.analogRead(analogPin2);
            int iSensorRight = BoeBot.analogRead(analogPin3);

            // Checking if all 3 line followers see black wich means crossroad and the route is not yet completed
            if ((iSensorLeft >= iSensitivity) && (iSensorMiddle >= iSensitivity) && (iSensorRight >= iSensitivity) && route.getResult() == 0) {
//                System.out.println("Alvast voor een kruispunt");
                // Playing some sound
//                BoeBot.freqOut(0,1500,1000);

//                try {
//                    // Getting the coordinates for the log
//                    Node newCoordinates = route.getListCoordinates().get(route.getOffset());
//
//                    // Sending it to the log
//                    logCtrl.postLog("Found a crossroad. Turning " + route.getDirection() + " from (" + route.getLastCoordinates().getRow() + "," + route.getLastCoordinates().getCol() + ") to (" + newCoordinates.getRow() + "," + newCoordinates.getCol() + ")");
//                } catch (Exception e) {
//                    System.out.println("Offset te groot");
//                }
//
//                switch (route.getDirection()) {
//                    case "Right":
//                        boebotCtrl.turnDegrees(90, 50);
//                        System.out.println("Draai naar rechts");
//                        break;
//                    case "Left":
//                        boebotCtrl.turnDegrees(90, -50);
//                        System.out.println("Draai naar links");
//                        break;
//                    case "Up":
//                        boebotCtrl.turnDegrees(90, 50);
//                        System.out.println("Draai naar boven");
//                        break;
//                    case "Down":
//                        boebotCtrl.turnDegrees(90, -50);
//                        System.out.println("Draai naar beneden");
//                        break;
//                }
//
//                // Waiting for a bit
//                BoeBot.wait(250);
//
//                // Checking if the route has been completed
//                if (route.getResult() == 1) {
//                    logCtrl.postLog("Route completed");
//                } else {
//                    // Bringing the boebot back to speed
//                    sLeft.update(iNormalSpeed - iRaceSpeed);
//                    sRight.update(iNormalSpeed + iRaceSpeed);
//                    route = routeCtrl.DriveRoute(route);
//                }
            } else {
                // IDK, do something
            }
        }
    }
}
