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

    public ArrayList<Node> Astar(ArrayList<int[]> obstacleCoordinates, Node gridSize, SFNodes sfNodes) {
        ArrayList<Node> Cordinaten = new ArrayList<>();
        // System.out.println(Arrays.deepToString(obstacleCoordinates.toArray()));
        Node initialNode = new Node(sfNodes.getStartNodes(),sfNodes.getStartColumn());
        Node finalNode = new Node(sfNodes.getFinishNodes(), sfNodes.getFinishColumn());
        int rows = gridSize.getRow();
        int cols = gridSize.getCol();
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        //blocksArray = new int[][]{{1, 3}, {2, 3}, {3, 3}};
        aStar.setBlocks(obstacleCoordinates);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            Cordinaten.add(new Node(node.getRow(), node.getCol()));
        }
        return Cordinaten;
    }
    
    public void KnipperLinks(boolean state){
        controlLed(14, state);
    }

    public void KnipperRechts(boolean state){
        controlLed(2,  state);
    }

    /**
     * Hee Rick Marvin hier met een super onnodige functie die misschien wel handig is
     * @param iLed de led die aan en uit moet
     * @param state the state of the led
     */
    private void controlLed(int iLed, boolean state) {
       BoeBot.digitalWrite(iLed, state);
    }

    /**
     * Function to go past every test function
     */
    public void testAll() {
        // Setting a waiting time between the test functions
        int iWait = 500;

        // The lights
        KnipperLinks(true);
        BoeBot.wait(iWait);
        KnipperLinks(false);
        BoeBot.wait(iWait);

        KnipperRechts(true);
        BoeBot.wait(iWait);
        KnipperRechts(false);
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
