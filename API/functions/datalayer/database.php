<?php
    class Database {
        
        // Variable for the connection
        public $conn;

        // Database login information
        private $host = 'localhost';
        private $db_name = 'bp6_helloworldbot';
        private $username = 'helloworldbot';
        private $password = 'Kz#tz596';

        /**
         * Function to get database connection
         * 
         * @return conn the variable for the connection
         */
        public function getConnection() {
            $this->conn = null;

            // Checking if connection can be made, else show error
            try {
                $this->conn = new PDO('mysql:host=' . $this->host . ';dbname=' . $this->db_name . ';port=3306', $this->username, $this->password);
            } catch (\PDOException $exception) {
                echo 'Connection error: ' . $exception->getMessage();
            }
            // Connectie teruggeven
            return $this->conn;
        }
    }
?>
