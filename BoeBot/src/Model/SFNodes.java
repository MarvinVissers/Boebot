package Model;

import java.util.ArrayList;

public class SFNodes {

    private int StartNodes;
    private int StartColumn;
    private int FinishNodes;
    private int FinishColumn;

    public SFNodes(int startNodes, int startColumn, int finishNodes, int finishColumn) {
        StartNodes = startNodes;
        StartColumn = startColumn;
        FinishNodes = finishNodes;
        FinishColumn = finishColumn;
    }

    public int getStartNodes() {
        return StartNodes;
    }

    public void setStartNodes(int startNodes) {
        StartNodes = startNodes;
    }

    public int getStartColumn() {
        return StartColumn;
    }

    public void setStartColumn(int startColumn) {
        StartColumn = startColumn;
    }

    public int getFinishNodes() {
        return FinishNodes;
    }

    public void setFinishNodes(int finishNodes) {
        FinishNodes = finishNodes;
    }

    public int getFinishColumn() {
        return FinishColumn;
    }

    public void setFinishColumn(int finishColumn) {
        FinishColumn = finishColumn;
    }

    @Override
    public String toString() {
        return "SFNodes{" +
                "StartNodes=" + StartNodes +
                ", StartColumn=" + StartColumn +
                ", FinishNodes=" + FinishNodes +
                ", FinishColumn=" + FinishColumn +
                '}';
    }
}
