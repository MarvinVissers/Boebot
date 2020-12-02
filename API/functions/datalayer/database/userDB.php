<?php
    /**
     * @author Marvin Vissers
     */

    // Linking to classes in the datalayer
    require_once("../functions/datalayer/database.php");

    class UserDB  {
        // Creating variabels for in the class
        private $conn;

        /* Creating a new instance of UserDB */
        public function __construct() {
            // Making a connection with the database
            $database = new Database();
            $this->conn = $database->getConnection();
        }

        /**
         * Function to log user in
         * 
         * @return userID the ID of the user account found
         */
        public function userLogin($userModel) {
            // Making a variable for the username
            $username = $userModel->getUsername();
            $password = $userModel->getPassword();

            $query = "SELECT ID, password FROM user WHERE username = ?";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $username);
            if ($stmt->execute()) {
                // Getting the user
                $result = $stmt->fetch(PDO::FETCH_OBJ);

                // Checking if passwords match
                if (password_verify($password, $result->password)) {
                    // Gettint the userID and returning it
                    $userID = $result->ID;

                    return $userID;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }
?>