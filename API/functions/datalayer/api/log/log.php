<?php
    /**
     * @author Marvin Vissers
     */

    // Require needed file
    require("../../ApiRequest.php");

    class LogAPI implements ApiRequest {

        // Making a variable for the connection
        private $conn;

        public function __construct($conn) {
            $database = new Database();
            $this->conn = $database->getConnection();
        }

        /**
         * Getting all obstacles in the database
         *
         * @return obstacleList a array with all obstacle in the database
         */
        public function getAll() {
            // Making a list to fill later
            $logList = array();

            // Query to get all the products
            $query = "SELECT * FROM log ORDER BY id DESC";
            $stmt = $this->conn->prepare($query);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Looping through the results
                foreach ($result as $log) {
                    // Filling the log model
                    $logModel = array("id" => $log->id, "boebot" => $log->boebot, "text" => $log->text);
                    array_push($logList, $logModel);
                }
            }
            // Returning the filled list
            return $logList;
        }

        public function post($logModel) {
            // Creating variables for in the query
            $boebot = $logModel->getBoebot();
            $text = $logModel->getText();

            // Query to add obstacle to the database
            $query = "INSERT INTO log(Boebot, text) VALUES(?, ?)";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $boebot);
            $stmt->bindParam(2, $text);
            if ($stmt->execute()) {
                // Sending good feedback if row is added
                return array("feedback" => "row added");
            } else {
                // Sending bad feedback if row is not added
                return array("feedback" => "row is not added");
            }
        }

        public function getLast($logModel) {
            // Making a list to fill later
            $action = array();

            // Creating variable for the query
            $boebot = $logModel->getBoebot();

            // Query to get all the products
            $query = "SELECT * FROM log WHERE boebot = ? ORDER BY id DESC LIMIT 1";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $boebot);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetch(PDO::FETCH_OBJ);

                // Filling the obstacle model
                $obstacleModel = array("id" => $result->id, "boebot" => $result->boebot, "text" => $result->text);
                array_push($action, $obstacleModel);
            }
            // Returning the filled list
            return $action;
        }
    }
?>