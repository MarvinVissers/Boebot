import Controller.ObstacleController;
import TI.BoeBot;
import TI.PinMode;

public class RobotMain {

    public static void main(String[] args) {

        ObstacleController obstacleCtrl = new ObstacleController("obstacle");

        while (true) {
            BoeBot.wait(250);
        }
    }
}
