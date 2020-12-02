<?php
    /**
     * @author Marvin Vissers
     */
    class Grid {
        // Creating the variables
        private $id;
        private $name;
        private $length;
        private $height;

        /**
         * Creates a new instance of Grid
         *
         * @param id id of the grid
         * @param name name of the grid
         * @param length the length of the grid
         * @param height the height of the grid
         */
        public function __construct($id, $name, $length, $height) {
            // Setting the given values equal to the variables in the class
            $this->id = $id;
            $this->name = $name;
            $this->length = $length;
            $this->height = $height;
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

        public function getLength() {
            return $this->length;
        }

        public function setLength($length) {
            $this->length = $length;
        }

        public function getHeight() {
            return $this->height;
        }

        public function setHeight($height) {
            $this->height = $height;
        }
    }
?>