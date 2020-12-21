<?php
    /**
     * @author Marvin Vissers
     */
    class User  {
        // Creating the variables
        private $id;
        private $username;
        private $password;

        /**
         * Creates a new instance of user
         * 
         * @param id id of the user
         * @param username username of the user
         * @param password password of the user
         */
        public function __construct($id, $username, $password) {
            // Setting the given values equal to the variables in the class
            $this->id = $id;
            $this->username = $username;
            $this->password = $password;
        }

        public function getId() {
            return $this->id;
        }
    
        public function setId($id) {
            $this->id = $id;
        }

        public function getUsername() {
            return $this->username;
        }
    
        public function setUsername($username) {
            $this->username = $username;
        }

        public function getPassword() {
            return $this->password;
        }

        public function setPassword($password) {
            $this->password = $password;
        }
    }  
?>