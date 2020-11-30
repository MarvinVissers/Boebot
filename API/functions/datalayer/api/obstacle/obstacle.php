<?php
    /**
     * @author Marvin Vissers
     */

    // Require needed file
    require("../../ApiRequest.php"); 

    class ObstacleAPI implements ApiRequest {

        // Making a variable for the connection
        private $conn;

        public function __construct($conn) {
            $this->conn = $conn;
        }

        public function getAll() {
            // Making a list to fill laten
            $obstacleList = array();

            // Query to get all the products
            $query = "SELECT * FROM product";
            $stmt = $this->conn->prepare($query);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Looping through the results
                foreach ($result as $obstacle) {
                    // Filling the obstacle model
                    $obstacleModel = array("pid" => $obstacle->pid, "product" => $obstacle->Product);
                    array_push($obstacleList, $obstacleModel); 
                }

                // Returning the filled list
                return $obstacleList;
            }
        }

        public function getDetails($id) {
            
        }

        public function post($array) {
            
        }

        public function put($array) {
            
        }

        public function delete($id) {
            
        }
    }
?>