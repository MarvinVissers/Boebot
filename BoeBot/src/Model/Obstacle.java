package Model;

public class Obstacle {
    private int iid, gridId, length, height;

    public Obstacle(int iid, int gridId, int length, int height) {
        this.iid = iid;
        this.gridId = gridId;
        this.length = length;
        this.height = height;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
