package Controller;

import Model.Node;
import Model.Obstacle;
import TI.BoeBot;
import TI.Servo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoeBotController {

    private Servo sLinks;
    private Servo sRechts;

    public BoeBotController() {
        this.sLinks = new Servo(12);
        this.sRechts = new Servo(13);
    }
    
//    public void KnipperLinks(){
//        BoeBot.digitalWrite(15, true);
//        BoeBot.wait(200);
//        BoeBot.digitalWrite(15, false);
//        BoeBot.wait(200);
//    }
//
//    public void KnipperRechts(){
//        BoeBot.digitalWrite(2, true);
//        BoeBot.wait(200);
//        BoeBot.digitalWrite(2, false);
//        BoeBot.wait(200);
//    }
//
//    public void ObstacleDetect(){
//        BoeBot.freqOut(0,1000,1000);
//    }

    public ArrayList<Node> Astar( ArrayList<int[]> obstacleCoordinates) throws UnknownHostException {
        ArrayList<Node> Cordinaten = new ArrayList<>();
        // System.out.println(Arrays.deepToString(obstacleCoordinates.toArray()));
        Node initialNode = new Node(0, 0);
        Node finalNode = new Node(10, 12);
        int rows = 15;
        int cols = 15;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        //blocksArray = new int[][]{{1, 3}, {2, 3}, {3, 3}};
        aStar.setBlocks(obstacleCoordinates);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            Cordinaten.add(new Node(node.getRow(), node.getCol()));
        }
        return Cordinaten;
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
