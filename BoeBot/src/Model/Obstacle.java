package Model;

public class Obstacle {
    private Integer id;
    private int gridId, row1, column1, row2, column2;

    public Obstacle(Integer id, int gridId, int row1, int column1, int row2, int column2) {
        this.id = id;
        this.gridId = gridId;
        this.row1 = row1;
        this.column1 = column1;
        this.row2 = row2;
        this.column2 = column2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public int getRow1() {
        return row1;
    }

    public void setRow1(int row1) {
        this.row1 = row1;
    }

    public int getColumn1() {
        return column1;
    }

    public void setColumn1(int column1) {
        this.column1 = column1;
    }

    public int getRow2() {
        return row2;
    }

    public void setRow2(int row2) {
        this.row2 = row2;
    }

    public int getColumn2() {
        return column2;
    }

    public void setColumn2(int column2) {
        this.column2 = column2;
    }

    @Override
    public String toString() {
        return "Obstacle{" +
                "id=" + id +
                ", gridId=" + gridId +
                ", row1=" + row1 +
                ", column1=" + column1 +
                ", row2=" + row2 +
                ", column2=" + column2 +
                '}';
    }
}
