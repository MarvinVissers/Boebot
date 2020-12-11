package Controller;

import Model.Node;
import Model.Obstacle;
import TI.BoeBot;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoeBotController {
    
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

    public ArrayList<int[]> Astar( ArrayList<int[]> obstacleCoordinates) throws UnknownHostException {
        ArrayList<int[]> Cordinaten = new ArrayList<>();
       // System.out.println(Arrays.deepToString(obstacleCoordinates.toArray()));
        Node initialNode = new Node(0, 0);
        Node finalNode = new Node(6, 5);
        int rows = 10;
        int cols = 10;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        //blocksArray = new int[][]{{1, 3}, {2, 3}, {3, 3}};
        aStar.setBlocks(obstacleCoordinates);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            Cordinaten.add(new int[]{node.getRow(), node.getCol()});
        }
        return Cordinaten;
    }
}
