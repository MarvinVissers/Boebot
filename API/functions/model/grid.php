<?php
    /**
     * @author Marvin Vissers
     */
    class Grid {
        // Creating the variables
        private $id;
        private $name;
        private $rows;
        private $columns;

        /**
         * Creates a new instance of Grid
         *
         * @param id id of the grid
         * @param name name of the grid
         * @param rows the rows of the grid
         * @param columns the columns of the grid
         */
        public function __construct($id, $name, $rows, $columns) {
            // Setting the given values equal to the variables in the class
            $this->id = $id;
            $this->name = $name;
            $this->rows = $rows;
            $this->columns = $columns;
        }

        public function getId() {
            return $this->id;
        }

        public function setId($id) {
            $this->id = $id;
        }

        public function getName() {
            return $this->name;
        }

        public function setName($name) {
            $this->name = $name;
        }

        public function getRows() {
            return $this->rows;
        }

        public function setRows($rows) {
            $this->rows = $rows;
        }

        public function getColumns() {
            return $this->columns;
        }

        public function setColumns($columns) {
            $this->columns = $columns;
        }
    }
?>