package Model;

import java.util.ArrayList;

public class Route {

    private ArrayList<Node> listCoordinates;
    private Node lastCoordinates;
    private String direction;
    private int offset, result;

    public Route(ArrayList<Node> listCoordinates, Node lastCoordinates, String direction, int offset, int result) {
        this.listCoordinates = listCoordinates;
        this.lastCoordinates = lastCoordinates;
        this.direction = direction;
        this.offset = offset;
        this.result = result;
    }

    public ArrayList<Node> getListCoordinates() {
        return listCoordinates;
    }

    public void setListCoordinates(ArrayList<Node> listCoordinates) {
        this.listCoordinates = listCoordinates;
    }

    public Node getLastCoordinates() {
        return lastCoordinates;
    }

    public void setLastCoordinates(Node lastCoordinates) {
        this.lastCoordinates = lastCoordinates;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "listCoordinates=" + listCoordinates +
                ", lastCoordinates=" + lastCoordinates +
                ", direction='" + direction + '\'' +
                ", offset=" + offset +
                ", result=" + result +
                '}';
    }
}
