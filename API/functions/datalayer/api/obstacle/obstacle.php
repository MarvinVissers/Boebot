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

        /**
         * Getting all obstacles in the database
         * 
         * @return obstacleList a array with all obstacle in the database
         */
        public function getAll() {
            // Making a list to fill later
            $obstacleList = array();

            // Query to get all the products
            $query = "SELECT * FROM obstacle";
            $stmt = $this->conn->prepare($query);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Looping through the results
                foreach ($result as $obstacle) {
                    // Filling the obstacle model
                    $obstacleModel = array("id" => $obstacle->id, "gridId" => $obstacle->gridId, "row1" => $obstacle->row1, "column1" => $obstacle->column1, "row2" => $obstacle->row2, "column2" => $obstacle->column2);
                    array_push($obstacleList, $obstacleModel); 
                }
            }
            // Returning the filled list
            return $obstacleList;
        }

        public function getDetails($id) {
            
        }

        public function post($obstacleModel) {
            // Creating variables for in the query
            $gridId = $obstacleModel->getGridId();
            $length = $obstacleModel->getLength();
            $height = $obstacleModel->getHeight();

            // Query to add obstacle to the database
            $query = "INSERT INTO obstacle(gridId, length, height) VALUES(?, ?, ?)";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $gridId);
            $stmt->bindParam(2, $length);
            $stmt->bindParam(3, $height);
            if ($stmt->execute()) {
                // Sending good feedback if row is added
                return array("feedback" => "row added");
            } else {
                // Sending bad feedback if row is not added
                return array("feedback" => "row is not added");
            }
        }

        public function put($obstacleModel) {
            // Creating variables for in the query
            $id = $obstacleModel->getId();
            $gridId = $obstacleModel->getGridId();
            $length = $obstacleModel->getLength();
            $height = $obstacleModel->getHeight();

            // Query to add obstacle to the database
            $query = "UPDATE obstacle SET gridId = ?, length = ?, height = ? WHERE id = ?";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $gridId);
            $stmt->bindParam(2, $length);
            $stmt->bindParam(3, $height);
            $stmt->bindParam(4, $id);
            if ($stmt->execute()) {
                // Sending good feedback if row is added
                return array("feedback" => "Obstacle updated");
            } else {
                // Sending bad feedback if row is not added
                return array("feedback" => "Obstacle could not be updated");
            }
        }

        public function delete($id) {
            
        }

        /**
         * Function to get the obstacles of 1 grid
         *
         * @param gridId the id of the grid
         * @return obstacleList list with the obstacles in the grid
         */
        public function getGridObstacles($gridId) {
            // Making a list to fill later
            $obstacleList = array();

            // Query to get all the products
            $query = "SELECT * FROM obstacle WHERE gridId = ?";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $gridId);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Looping through the results
                foreach ($result as $obstacle) {
                    // Filling the obstacle model
                    $obstacleModel = array("id" => $obstacle->id, "gridId" => $obstacle->gridId, "row1" => $obstacle->row1, "column1" => $obstacle->column1, "row2" => $obstacle->row2, "column2" => $obstacle->column2);
                    array_push($obstacleList, $obstacleModel); 
                }
            }
            // Returning the filled list
            return $obstacleList;
        }
    }
?>