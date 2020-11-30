<?php
    /**
     * @author Marvin Vissers
     */
    class validateAPI {
        // Creating variables for the selector and the token
        private $selector;
        private $token;
        private $conn;
       
        /**
         * Constructor for the class
         * 
         * @param selector the selector in the database
         * @param token the token in the database
         * @param conn the connection to the database
         */
        public function __construct($selector, $token, $conn) {
           // Linking the given selector and token to the private versions of them
           $this->selector = $selector;
           $this->token = $token;
           $this->conn = $conn;
        }

        /**
         * Function to check if token and selector exist in the database
         * 
         * @return true if selector and token exist in the database
         * @return false if the selector and or token does not exist in the databse
         */
        public function checkSelecorTokenExist() {
            // Query to get all token equal to the selector
            $query = "SELECT * FROM api_acces WHERE selector = ?";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $this->selector);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetch(PDO::FETCH_OBJ);

                // Getting the token of the database
                $tokenDB = $result->token;

                // Converting given token
                $tokenBin = hex2bin($this->token);

                // Checking if token is equal to the database token
                if (password_verify($tokenBin, $tokenDB)) return true;
                // If token is not equal to the database token return false
                else return false;
            } else {
                // Returning with error
                return false;
            }
        }
    }
?>