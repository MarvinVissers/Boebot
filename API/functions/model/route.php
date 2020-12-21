<?php
    /**
     * @author Marvin Vissers
     */
    class Route  {
        // Creating the variables
        private $id;
        private $boebot;
        private $row1;
        private $column1;
        private $row2;
        private $column2;

        /**
         * Creates a new instance of Route
         *
         * @param id id of the log
         * @param boebot the id of the Boebot
         * @param row1 starting row of the Boebot
         * @param column1 starting column of the Boebot
         * @param row2 ending row of the Boebot
         * @param column2 ending column of the Boebot
         */
        public function __construct($id, $boebot, $row1, $column1, $row2, $column2) {
            // Setting the given values equal to the variables in the class
            $this->id = $id;
            $this->boebot = $boebot;
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

        public function getBoebot() {
            return $this->boebot;
        }

        public function setBoebot($boebot) {
            $this->boebot = $boebot;
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