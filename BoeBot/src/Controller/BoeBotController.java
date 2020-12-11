package Controller;

import Model.Node;
import TI.BoeBot;

import java.util.ArrayList;
import java.util.List;

public class BoeBotController {
    
    public void KnipperLinks(){
        BoeBot.digitalWrite(15, true);
        BoeBot.wait(200);
        BoeBot.digitalWrite(15, false);
        BoeBot.wait(200);
    }

    public void KnipperRechts(){
        BoeBot.digitalWrite(2, true);
        BoeBot.wait(200);
        BoeBot.digitalWrite(2, false);
        BoeBot.wait(200);
    }

    public void ObstacleDetect(){
        BoeBot.freqOut(0,1000,1000);
    }

    public void Astar() {
        Node initialNode = new Node(0, 0);
        Node finalNode = new Node(2, 5);
        int rows = 6;
        int cols = 7;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        //blocksArray = new int[][]{{1, 3}, {2, 3}, {3, 3}};
        aStar.setBlocks(//iets);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            System.out.println(node);
        }
    }
}
