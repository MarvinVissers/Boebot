package Controller;

import Model.Node;
import Model.SFNodes;
import TI.BoeBot;
import TI.Servo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rick Deurloo
 * @author Marvin Vissers
 */

public class BoeBotController {

    private Servo sLinks;
    private Servo sRechts;

    public BoeBotController() {
        this.sLinks = new Servo(12);
        this.sRechts = new Servo(13);
    }
    
    public void KnipperLinks(int iLoops){
//        BoeBot.digitalWrite(15, true);
//        BoeBot.wait(200);
//        BoeBot.digitalWrite(15, false);
//        BoeBot.wait(200);
        controlLed(14, 200, iLoops);
    }

    public void KnipperRechts(int iLoops){
//        BoeBot.digitalWrite(2, true);
//        BoeBot.wait(200);
//        BoeBot.digitalWrite(2, false);
//        BoeBot.wait(200);
        controlLed(2, 200, iLoops);
    }

    /**
     * Hee Rick Marvin hier met een super onnodige functie die misschien wel handig is
     * @param iLed de led die aan en uit moet
     * @param iWait de wachttijd tussen het knipperen
     * @param iLoops hoevaak de loop wordt uitgevoerd
     */
    private void controlLed(int iLed, int iWait, int iLoops) {
       for (int i = 0; i < iLoops; i++) {
           BoeBot.digitalWrite(iLed, true);
           BoeBot.wait(iWait);
           BoeBot.digitalWrite(iLed, false);
           BoeBot.wait(iWait);
       }
    }

//    public void ObstacleDetect(){
//        BoeBot.freqOut(0,1000,1000);
//         // TODO post naar api
//    }

    /**
     * Function to go past every test function
     */
    public void testAll() {
        // Setting a waiting time between the test functions
        int iWait = 500;

        // The lights
        KnipperLinks(5);
        BoeBot.wait(iWait);
        KnipperRechts(5);
        BoeBot.wait(iWait);

        // Driving
        toSpeed(50);
        BoeBot.wait(iWait);
        toSpeed(-50);
        BoeBot.wait(iWait);

        // Turning
        turnDegrees(360, 50);
        BoeBot.wait(iWait);
        turnDegrees(360, -50);
        BoeBot.wait(iWait);
    }

    /**
     * Function to test the Boebot driving forward and backward
     * @param iSpeed the speed to test with
     */
    public void toSpeed(int iSpeed) {
        // Default speed
        int iDefault = 1500;

        // Checking if the speed is more or less than 0
        if (iSpeed > 0) {
            // Speeding up the boebot forwards
            for (int i = 0; i < iSpeed; i++) {
                // Setting the new speed
                this.sLinks.update(iDefault - i);
                this.sRechts.update(iDefault + i);
                BoeBot.wait(20);
            }
            // Activating the emergency brakes
            emergencyBrake();
        } else if (iSpeed < 0) {
            // Speeding up the boebot backwards
            for (int i = 0; i > iSpeed; i--) {
                // Setting the new speed
                this.sLinks.update(iDefault - i);
                this.sRechts.update(iDefault + i);
                BoeBot.wait(20);
            }
            // Activating the emergency brakes
            emergencyBrake();
        }
    }

    public void turnDegrees(int degrees, int turningSpeed) {
        // kijken of het getal groter is dan 0
        if (turningSpeed > 0){
            // Tijd om te draaien bereken
            int timePerDegree = 400 / turningSpeed;

            // Boebot laten draaien
            this.sLinks.update(1500 + turningSpeed);
            this.sRechts.update(1500 + turningSpeed);
            // Boebot de berekende tijd laten wachten
            BoeBot.wait(timePerDegree * degrees);

            // Boebot stoppen
            emergencyBrake();
        } else if (turningSpeed < 0) {
            // Negatieve snelheid omdraaien naar positieve snelheid
            turningSpeed = Math.abs(turningSpeed);

            // Tijd om te draaien bereken
            int timePerDegree = 400 / turningSpeed;

            // Boebot laten draaien
            this.sLinks.update(1500 - turningSpeed);
            this.sRechts.update(1500 - turningSpeed);
            // Boebot de berekende tijd laten wachten
            BoeBot.wait(timePerDegree * degrees);

            // Boebot stoppen
            emergencyBrake();
        }
    }

    public void emergencyBrake() {
        // Boebot stil laten staan
        this.sLinks.update(1500);
        this.sRechts.update(1500);

        BoeBot.wait(250);
    }
}
