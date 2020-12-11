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
        System.out.println("De laatste log was: " + logCtrl.getLastLog());

        /**
         * Actions with obstacles
         */
//        System.out.println("Obstacle post");
//        Obstacle obstacle = new Obstacle(null, 1, 2, 4, 2, 5);
//        obstacleCtrl.post(obstacle);

        while (true) {
            BoeBot.wait(250);
        }
    }
}
