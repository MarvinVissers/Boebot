import Controller.BoeBotController;
import Controller.LogController;
import Controller.ObstacleController;
import Controller.LogController;
import Model.Obstacle;
import Model.Log;

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

    public static void main(String[] args) throws UnknownHostException {
        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");
        LogController logCtrl = new LogController("log");



        // Filling the obstacle list
        ArrayList<Obstacle> obstacles = new ArrayList(obstacleCtrl.get());
        // Filling an array with the obstacle coordinates
        ArrayList<int[]> obstacleCoordinates = new ArrayList(obstacleCtrl.createObstacleList(obstacles));
        Log log = new Log(null, "Opstarten Boebot");
        logCtrl.post(log);
        BoeBotController BoeController = new BoeBotController();
        ArrayList<int[]> Route = BoeController.Astar(obstacleCoordinates);
        while (true) {
           // System.out.println(Arrays.deepToString(BoeController.Astar(obstacleCoordinates).toArray()));
            //BoeController.Astar(obstacleCoordinates);
            BoeBot.wait(1000);
            for (int i = 0; i < Route.size(); i++) {
                //print de cordinaten
               System.out.println(Arrays.toString(Route.get(i)));
                BoeBot.wait(1000);
                //cordinaten naar een string.
                StringBuilder strNum = new StringBuilder();
                for (int num : Route.get(i))
                {
                    strNum.append(num);
                }
                int finalInt = Integer.parseInt(strNum.toString());
                System.out.println(finalInt);
            }
        }
    }
}
