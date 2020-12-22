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

        public function post($obstacleModel) {
            // Creating variables for in the query
            $gridId = $obstacleModel->getGridId();
            $row1 = $obstacleModel->getRow1();
            $column1 = $obstacleModel->getColumn1();
            $row2 = $obstacleModel->getRow2();
            $column2 = $obstacleModel->getColumn2();

            // Query to add obstacle to the database
            $query = "INSERT INTO obstacle(gridId, row1, column1, row2, column2) VALUES(?, ?, ?, ?, ?)";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $gridId);
            $stmt->bindParam(2, $row1);
            $stmt->bindParam(3, $column1);
            $stmt->bindParam(4, $row2);
            $stmt->bindParam(5, $column2);
            if ($stmt->execute()) {
                // Sending good feedback if row is added
                return array("feedback" => "row added");
            } else {
                // Sending bad feedback if row is not added
                return array("feedback" => "row is not added");
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