import Controller.BoeBotController;
import Controller.LogController;
import Controller.ObstacleController;
import Controller.LogController;
import Model.Obstacle;
import Model.Log;

import TI.BoeBot;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class RobotMain {

    public static void main(String[] args) throws UnknownHostException {
        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");
        LogController logCtrl = new LogController("log");

        // Filling the obstacle list
        ArrayList<Obstacle> obstacles = new ArrayList(obstacleCtrl.get());
        // Filling an array with the obstacle coordinates
        ArrayList<int[]> obstacleCoordinates = new ArrayList(obstacleCtrl.createObstacleList(obstacles));
        System.out.println(obstacleCoordinates.size());
        Log log = new Log(null, "Opstarten Boebot");
        logCtrl.post(log);
        BoeBotController BoeController = new BoeBotController();
        BoeController.Astar();


        while (true) {
            BoeController.KnipperLinks();
            BoeController.KnipperRechts();
            BoeController.ObstacleDetect();
        }
    }
}
