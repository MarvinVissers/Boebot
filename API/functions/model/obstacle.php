<?php
    /**
     * @author Marvin Vissers
     */
    class Obstacle  {
        // Creating the variables
        private $pid;
        private $product;

        /**
         * Creates a new instance of Time
         * 
         * @param pid id of the product
         * @param product product name
         */
        public function __construct($pid, $product) {
            // Setting the given values equal to the variables in the class
            $this->pid = $pid;
            $this->product = $product;
        }

        public function getPid() {
            return $this->pid;
        }
    
        public function setPid($pid) {
            $this->pid = $pid;
        }

        public function getProduct() {
            return $this->product;
        }
    
        public function setProduct($product) {
            $this->product = $product;
        }
    }  
?>