<?php
    /**
     * @author Marivn Vissers
     */

    // Linking to classes in the database layer
    require_once("../functions/datalayer/database/userDB.php");
    // Linking to the validation class
    require_once("../functions/helper/validateInput.php");

    class UserController {
        // Variabels for the needed UserDB en Validate classes
        private $userDB;
        private $validate;

        public function __construct() {
            $this->userDB = new UserDB;
            $this->validate = new Validate();
        }

        /**
         * Function to log user in
         * When the function is completed the user will be logged in
         * 
         * @param userModel model with user login information
         */
        public function userLogin($userModel) {
             // Validating the input on back-end
             $validateUsername = $this->validate->validateInput($userModel->getUsername(), "/^[a-zA-Z0-9_.-]*$/", 1, 50);
             $validatePassword = $this->validate->validateInput($userModel->getPassword(), "/^[a-zA-Z0-9@+_.!?|]*$/", 8, 20);
 
            // Checking if they all return true
            if ($validateUsername && $validatePassword) {
                // Sending the data to the datalayer login function
                $userID = $this->userDB->userLogin($userModel);
                $userModel->setId($userID);

                echo "Jouw id is = " . $userID . "<br>";

                if ($userModel->getID() != null) {
                    // Starting the session
                    session_start();
                    $_SESSION['userId'] = $userModel->getId();

                    // Sending the user to the home page
                    header("Location: index");
                } else {
                    // Reloading the page with an error
                    header("Location: login?error=2");
                }
            } else {
                // Reloading the page with an error
                 header("Location: login?error=1");
            }
        }
    }
?>