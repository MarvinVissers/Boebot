import Controller.*;
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
        /*
         * Setting the variabeles that will be used
         */
        // Status if the Boebot is busy
        boolean bBusy = false;
        // Action for the boebot
        String sAction;
        String sDirection = "Right";

        // Pins for the line followers
        int analogPin1 = 0;  // Richt
        int analogPin2 = 1;  // Middle
        int analogPin3 = 2;  // Left

        // Setting the servo wheels
        Servo sLinks = new Servo(12);
        Servo sRechts = new Servo(13);

        // Setting the speeds for servo
        int iNormalSpeed = 1500;
        int iRaceSpeed = 50;

        // Setting the sensor sensitivity
        int iSensitivity = 400;

        BoeBot.digitalWrite(2, false);
        BoeBot.digitalWrite(14, false);

        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");
        LogController logCtrl = new LogController("log");
        RouteController routeCtrl = new RouteController();
        BoeBotController boebotCtrl = new BoeBotController();

        ArrayList<Obstacle> obstacleList = new ArrayList(obstacleCtrl.get());
        Node startPoint = new Node(0, 0);
        Node endPoint = new Node(7, 4);

        /**
         * Actions with obstacles
         */
        // Filling the obstacle list
        ArrayList<Obstacle> obstacles = new ArrayList(obstacleCtrl.get());
        // Filling an array with the obstacle coordinates
        ArrayList<int[]> obstacleCoordinates = new ArrayList(obstacleCtrl.createObstacleList(obstacles));

        Log log = new Log(null, null, "booting up Boebot");
        logCtrl.post(log);

        ArrayList<Node> AstarRoute = boebotCtrl.Astar(obstacleCoordinates,routeCtrl.getGridSize(), routeCtrl.GetSFNodes());
        Route route = routeCtrl.DriveRoute(new Route(AstarRoute, new Node(0, 0), "Right", 1, 0));

        while (true) {
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
                        boebotCtrl.KnipperLinks(5);

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
                        boebotCtrl.KnipperRechts(5);

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
                        boebotCtrl.toSpeed(50);

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
                        boebotCtrl.toSpeed(-50);

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
                        boebotCtrl.turnDegrees(360, 50);

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
                        boebotCtrl.turnDegrees(360, -50);

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
                        boebotCtrl.testAll();

                        // Posting the the log that the test is done
                        logCtrl.postLog("Test succeeded");

                        // Setting the boebot open for new actions
                        bBusy = false;
                        break;
                }

                if (sAction.contains("route")) {
                    /**
                     * Drive route logic
                     */

                    BoeBot.wait(1000);

                    // Reading out the line followers
                    int iSensorLeft = BoeBot.analogRead(analogPin1);
                    int iSensorMiddle = BoeBot.analogRead(analogPin2);
                    int iSensorRight = BoeBot.analogRead(analogPin3);

                    // Checking if all 3 line followers see black wich means crossroad and the route is not yet completed
                    if ((iSensorLeft >= iSensitivity) && (iSensorMiddle >= iSensitivity) && (iSensorRight >= iSensitivity) && route.getResult() == 0) {
//                        System.out.println("Alvast voor een kruispunt");
//                        // Playing some sound
//                        BoeBot.freqOut(0,1500,1000);

                        try {
                            // Getting the coordinates for the log
                            Node newCoordinates = route.getListCoordinates().get(route.getOffset());

                            // Sending it to the log
                            logCtrl.postLog("Found a crossroad. Turning " + route.getDirection() + " from (" + route.getLastCoordinates().getRow() + "," + route.getLastCoordinates().getCol() + ") to (" + newCoordinates.getRow() + "," + newCoordinates.getCol() + ")");
                        } catch (Exception e) {
                            System.out.println("Offset te groot");
                        }

                        switch (route.getDirection()) {
                            case "Right":
                                if (routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    boebotCtrl.turnDegrees(90, 50);
                                }
                                System.out.println("Draai naar rechts");
                                break;
                            case "Left":
                                if (routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    boebotCtrl.turnDegrees(90, -50);
                                }
                                System.out.println("Draai naar links");
                                break;
                            case "Up":
                                if (routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    boebotCtrl.turnDegrees(90, -50);
                                }
                                System.out.println("Draai naar boven");
                                break;
                            case "Down":
                                if (routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    boebotCtrl.turnDegrees(90, -50);
                                }
                                System.out.println("Draai naar beneden");
                                break;
                        }

                        // Updating the direction
                        sDirection = route.getDirection();

                        // Waiting for a bit
                        BoeBot.wait(250);

                        // Checking if the route has been completed
                        if (route.getResult() == 1) {
                            logCtrl.postLog("Route completed");
                        } else {
                            // Bringing the boebot back to speed
                            sLinks.update(iNormalSpeed - iRaceSpeed);
                            sRechts.update(iNormalSpeed + iRaceSpeed);
                            route = routeCtrl.DriveRoute(route);
                        }
                    }
                }
            } else {
                // Checkking the action of the log
                if (sAction != null) {
                    System.out.println(sAction);
                    bBusy = true;
                }
            }
        }
    }
}
