import TI.BoeBot;
import TI.PinMode;

public class RobotMain {

    public static void main(String[] args) {

        boolean state = true;

        while (true) {
            state = !state;
            BoeBot.digitalWrite(0, state);
            BoeBot.wait(250);
        }
    }
}
