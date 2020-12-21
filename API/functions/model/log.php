<?php
    /**
     * @author Marvin Vissers
     */
    class Log  {
        // Creating the variables
        private $id;
        private $boebot;
        private $text;

        /**
         * Creates a new instance of Log
         *
         * @param id id of the log
         * @param boebot the id of the Boebot
         * @param text text of the log
         */
        public function __construct($id, $boebot, $text) {
            // Setting the given values equal to the variables in the class
            $this->id = $id;
            $this->boebot = $boebot;
            $this->text = $text;
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

        public function getText() {
            return $this->text;
        }

        public function setText($text) {
            $this->text = $text;
        }
    }
?>