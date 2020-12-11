import Controller.LogController;
import Controller.ObstacleController;
import Controller.LogController;
import Model.Obstacle;
import Model.Log;

import TI.BoeBot;

import java.net.UnknownHostException;

public class RobotMain {

    public static void main(String[] args) throws UnknownHostException {
        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");
        LogController logCtrl = new LogController("log");

        Log log = new Log(null, "Opstarten Boebot");
        logCtrl.post(log);

        while (true) {
            BoeBot.wait(250);
        }
    }
}
