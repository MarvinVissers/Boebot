import Controller.ObstacleController;
import Model.Obstacle;
import TI.BoeBot;

public class RobotMain {

    public static void main(String[] args) {
        // Creating instances of the needed classes
        ObstacleController obstacleCtrl = new ObstacleController("obstacle");

        while (true) {
            BoeBot.wait(250);
        }
    }
}
