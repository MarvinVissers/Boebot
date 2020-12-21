<?php
/**
 * @author Marvin Vissers
 */
class GridLine {
    // Creating the variables
    private $x1;
    private $y1;
    private $x2;
    private $y2;

    /**
     * Creates a new instance of GridLine
     *
     * @param x1 the starting row
     * @param y1 the starting column
     * @param x2 the ending row
     * @param y2 the ending row
     */
    public function __construct($x1, $y1, $x2, $y2) {
        // Setting the given values equal to the variables in the class
        $this->x1 = $x1;
        $this->y1 = $y1;
        $this->x2 = $x2;
        $this->y2 = $y2;
    }

    public function getX1() {
        return $this->x1;
    }

    public function setX1($x1) {
        $this->id = $x1;
    }

    public function getY1() {
        return $this->y1;
    }

    public function setY1($y1) {
        $this->y1 = $y1;
    }

    public function getX2() {
        return $this->x2;
    }

    public function setX2($x2) {
        $this->x2 = $x2;
    }

    public function getY2() {
        return $this->y2;
    }

    public function setY2($y2) {
        $this->y2 = $y2;
    }
}
?>