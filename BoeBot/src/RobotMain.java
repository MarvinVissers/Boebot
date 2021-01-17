import Controller.RouteController;
import Controller.LogController;
import Controller.ObstacleController;
import Controller.BoeBotController;
import Model.Node;
import Model.Obstacle;
import Model.Log;
import Model.Route;
import TI.BoeBot;
import TI.Servo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

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

        sLinks.update(iNormalSpeed - iRaceSpeed);
        sRechts.update(iNormalSpeed + iRaceSpeed);

        while (true) {
            // Checking if the Boebot is busy
            if (bBusy) {
                if (sAction.toUpperCase().contains("TEST")) {
                    // Test all test functions
                    CompletableFuture.runAsync(() -> {
                        // Posting to the log to start the test
                        logCtrl.postLog("Attempting to test all");
                    });

                    // Executing the test
                    boebotCtrl.testAll();

                    CompletableFuture.runAsync(() -> {
                        // Posting the the log that the test is done
                        logCtrl.postLog("Succeeded");
                    });

                    // Setting the boebot open for new actions
                    bBusy = false;
                }
            } else {
                BoeBot.wait(5000);

                sAction = logCtrl.checkLogAction(logCtrl.getLastLog());

                // Checkking the action of the log
                if (!sAction.isEmpty() && !sAction.toUpperCase().matches("NONE")) {
                    bBusy = true;
                    System.out.println(sAction);
                } else {
                    System.out.println("No action");
                    BoeBot.wait(1000);
                }
            }

            BoeBot.wait(1);
        }
    }
}
