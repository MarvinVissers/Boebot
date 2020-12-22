<?php
    /**
     * @author Marvin Vissers
     */
    class Obstacle  {
        // Creating the variables
        private $id;
        private $gridId;
        private $row1;
        private $column1;
        private $row2;
        private $column2;

        /**
         * Creates a new instance of Obstacle
         * 
         * @param id id of the obstacle
         * @param gridId id of the grid
         * @param row1 the starting column of the obstacle
         * @param column1 the start row of the obstacle
         * @param row2 the ending row of the obstacle
         * @param column the ending column of the obstacle
         */
        public function __construct($id, $gridId, $row1, $column1, $row2, $column2) {
            // Setting the given values equal to the variables in the class
            $this->id = $id;
            $this->gridId = $gridId;
            $this->row1 = $row1;
            $this->column1 = $column1;
            $this->row2 = $row2;
            $this->column2 = $column2;
        }

        public function getId() {
            return $this->id;
        }
    
        public function setId($id) {
            $this->id = $id;
        }

        public function getGridId() {
            return $this->gridId;
        }
    
        public function setGridId($gridId) {
            $this->gridId = $gridId;
        }

        public function getRow1() {
            return $this->row1;
        }

        public function setRow1($row1) {
            $this->row1 = $row1;
        }

        public function getColumn1() {
            return $this->column1;
        }
    
        public function setColumn1($column1) {
            $this->column1 = $column1;
        }

        public function getRow2() {
            return $this->row2;
        }

        public function setRow2($row2) {
            $this->row1 = $row2;
        }

        public function getColumn2() {
            return $this->column2;
        }

        public function setColumn2($column2) {
            $this->column1 = $column2;
        }
    }  
?>