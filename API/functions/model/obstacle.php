<?php
    /**
     * @author Marvin Vissers
     */
    class Obstacle  {
        // Creating the variables
        private $id;
        private $gridId;
        private $length;
        private $height;

        /**
         * Creates a new instance of Obstacle
         * 
         * @param pid id of the product
         * @param product product name
         */
        public function __construct($id, $gridId, $length, $height) {
            // Setting the given values equal to the variables in the class
            $this->id = $id;
            $this->gridId = $gridId;
            $this->length = $length;
            $this->height = $height;
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