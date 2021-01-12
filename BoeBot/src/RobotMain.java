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
        String sAction = "none";

        // Buiten de while loop
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
        int iSensitivity = 200;

        BoeBot.digitalWrite(2, false);
        BoeBot.digitalWrite(14, false);

        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");
        LogController logCtrl = new LogController("log");
        RouteController routeCtrl = new RouteController();
        BoeBotController boebotCtrl = new BoeBotController();

        Thread logThread = new Thread(logCtrl);

        /**
         * Actions with obstacles
         */

        // Filling the obstacle list
        ArrayList<Obstacle> obstacles = new ArrayList(obstacleCtrl.get());
        // Filling an array with the obstacle coordinates
        ArrayList<int[]> obstacleCoordinates = new ArrayList(obstacleCtrl.createObstacleList(obstacles));
        logCtrl.postLog("Booting up BoeBot");

        ArrayList<Node> AstarRoute = boebotCtrl.Astar(obstacleCoordinates,routeCtrl.getGridSize(), routeCtrl.GetSFNodes());
        Route route = routeCtrl.DriveRoute(new Route(AstarRoute, new Node(0, 0), "Right", 0, 0));

        sLinks.update(iRaceSpeed);
        sRechts.update(iRaceSpeed);

        while (true) {
            boebotCtrl.KnipperLinks(false);
            boebotCtrl.KnipperRechts(false);


            /**
             * Checking the action that the boebot should be doing
             * After that is a switch statement with the action
             */

            // Checking if the Boebot is busy
            if (bBusy) {
                // Switch to get do the action of the Boebot
                switch (sAction) {
                   // Test left left
                    case "testLightLeft":
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test lights left");

                        // Executing the test
                        boebotCtrl.KnipperLinks(true);
                        BoeBot.wait(1000);
                        boebotCtrl.KnipperLinks(false);

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
                        boebotCtrl.KnipperRechts(true);
                        BoeBot.wait(1000);
                        boebotCtrl.KnipperRechts(false);

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

                    // Reading out the line followers
                    int iSensorLeft = BoeBot.analogRead(analogPin1);
                    int iSensorMiddle = BoeBot.analogRead(analogPin2);
                    int iSensorRight = BoeBot.analogRead(analogPin3);

                    // Checking if all 3 line followers see black wich means crossroad and the route is not yet completed
                    if ((iSensorLeft >= iSensitivity) && (iSensorMiddle >= iSensitivity) && (iSensorRight >= iSensitivity) && route.getResult() == 0) {
                        // Playing some sound
                        BoeBot.freqOut(0,1500,1000);

                        try {
                            // Getting the coordinates for the log
                            Node newCoordinates = route.getListCoordinates().get(route.getOffset());

                            // Sending it to the log
                            logCtrl.postLog("Found a crossroad. Drive from (" + route.getLastCoordinates().getRow() + "," + route.getLastCoordinates().getCol() + ") to (" + newCoordinates.getRow() + "," + newCoordinates.getCol() + ")");
                        } catch (Exception e) {
                            System.out.println("Offset te groot");
                            logCtrl.postLog("Route completed");
                            bBusy = false;
                            sAction = "none";

                            boebotCtrl.emergencyBrake();
                        }

                        switch (route.getDirection()) {
                            case "Right":
                                if (!routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    switch (sDirection) {
                                        case "Up":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperLinks(true);
                                            boebotCtrl.turnDegrees(90, -25);

                                            break;
                                        case "Down":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperRechts(true);
                                            boebotCtrl.turnDegrees(90, 25);
                                            break;
                                    }
                                }
                                break;
                            case "Left":
                                if (!routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    switch (sDirection) {
                                        case "Up":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperRechts(true);
                                            boebotCtrl.turnDegrees(90, 25);
                                            break;
                                        case "Down":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperLinks(true);
                                            boebotCtrl.turnDegrees(90, -25);
                                            break;
                                    }
                                }
                                break;
                            case "Up":
                                if (!routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    switch (sDirection) {
                                        case "Right":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperLinks(true);
                                            boebotCtrl.turnDegrees(90, -25);
                                            break;
                                        case "Left":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperRechts(true);
                                            boebotCtrl.turnDegrees(90, 25);
                                            break;
                                    }
                                }
                                break;
                            case "Down":
                                if (!routeCtrl.switchDirection(sDirection, route.getDirection())) {
                                    switch (sDirection) {
                                        case "Right":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperLinks(true);
                                            boebotCtrl.turnDegrees(90, 25);
                                            break;
                                        case "Left":
                                            boebotCtrl.emergencyBrake();
                                            boebotCtrl.KnipperRechts(true);
                                            boebotCtrl.turnDegrees(90, -25);
                                            break;
                                    }
                                }
                                break;
                        }

                        // Updating the direction
                        sDirection = route.getDirection();

                        // Checking if the route has been completed
                        if (route.getResult() == 1) {
                            logCtrl.postLog("Route completed");
                            bBusy = false;
                            sAction = "none";
                        } else {
                            // Bringing the boebot back to speed
                            sLinks.update(iNormalSpeed - iRaceSpeed);
                            sRechts.update(iNormalSpeed + iRaceSpeed);
                            route = routeCtrl.DriveRoute(route);
                        }
                    } else if (((iSensorLeft >= iSensitivity) && (iSensorMiddle <= iSensitivity) && (iSensorRight <= iSensitivity)) || ((iSensorLeft >= iSensitivity) && (iSensorMiddle >= iSensitivity) && (iSensorRight <= iSensitivity)) && route.getResult() == 0) {
                        sLinks.update(iNormalSpeed);
                        sRechts.update(iNormalSpeed + iRaceSpeed);
                    } else if (((iSensorLeft <= iSensitivity) && (iSensorMiddle <= iSensitivity) && (iSensorRight >= iSensitivity)) || ((iSensorLeft <= iSensitivity) && (iSensorMiddle >= iSensitivity) && (iSensorRight >= iSensitivity)) && route.getResult() == 0) {
                        sLinks.update(iNormalSpeed - iRaceSpeed);
                        sRechts.update(iNormalSpeed);
                    } else if ((iSensorLeft <= iSensitivity) && (iSensorMiddle >= iSensitivity) && (iSensorRight <= iSensitivity) && route.getResult() == 0) {
                        sLinks.update(iNormalSpeed - iRaceSpeed);
                        sRechts.update(iNormalSpeed + iRaceSpeed);
                    }
                }
            } else {
                try {
                    logThread.start();
                    logThread.join();

                    sAction = logCtrl.getLogText();

                    System.out.println(sAction);

                    // Checkking the action of the log
                    if (!sAction.isEmpty() && !sAction.toUpperCase().matches("NONE")) {
                        bBusy = true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            BoeBot.wait(1);
        }
    }
}
