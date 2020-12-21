<?php
    /**
     * @author Marvin Vissers
     */

    // Linking to classes in the datalayer
    require_once("../functions/datalayer/database.php");

    class ObstacleDB  {
        // Creating variabels for in the class
        private $conn;

        /* Creating a new instance of UserDB */
        public function __construct() {
            // Making a connection with the database
            $database = new Database();
            $this->conn = $database->getConnection();
        }

        /**
         * Function to add an obstacle to the database
         * @param obstacleModel the model of the obstacle
         */
        public function addObstacle($obstacleModel) {
            // Creating variabels for the query
            $row1 = $obstacleModel->getRow1();
            $column1 = $obstacleModel->getColumn1();
            $row2 = $obstacleModel->getRow2();
            $column2 = $obstacleModel->getColumn2();

            // Query to add an obstacle to the database
            $query = "INSERT INTO obstacle(gridId, row1, column1, row2, column2) VALUES (1, ?, ?, ?, ?)";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $row1);
            $stmt->bindParam(2, $column1);
            $stmt->bindParam(3, $row2);
            $stmt->bindParam(4, $column2);
            $stmt->execute();
        }

        /**
         * Function to remove an obstacle to the database
         * @param obstacleModel the model of the obstacle
         */
        public function removeObstacle($obstacleModel) {
            $row1 = $obstacleModel->getRow1();
            $column1 = $obstacleModel->getColumn1();
            $row2 = $obstacleModel->getRow2();
            $column2 = $obstacleModel->getColumn2();

            // Query to remove obstacle from the database
            $query = "DELETE FROM obstacle WHERE gridId = 1 AND row1 = ? AND column1 = ? AND row2 = ? AND column2 = ?";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $row1);
            $stmt->bindParam(2, $column1);
            $stmt->bindParam(3, $row2);
            $stmt->bindParam(4, $column2);
            $stmt->execute();
        }

        /**
         * Getting all the obstacles
         * @return array a list with all obstacles
         */
        public function getObstacles() {
            // Creating an array to fill it later
            $obstacleList = array();

            // Query to get all grids from the database
            $query = "SELECT * FROM obstacle";
            $stmt = $this->conn->prepare($query);
            if ($stmt->execute()) {
                // Getting the grids
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Filling the array with the grid object
                foreach ($result as $obstacle) {
                    $obstacleModel = new Obstacle($obstacle->id, $obstacle->gridId, $obstacle->row1, $obstacle->column1, $obstacle->row2, $obstacle->column2);
                    array_push($obstacleList, $obstacleModel);
                }
            }

            // Returning the array
            return $obstacleList;
        }

        /**
         * Function to remove all obstacles when a new grid is made
         */
        public function removeAllObstacles() {
            // Query to remove obstacle from the database
            $query = "DELETE FROM obstacle WHERE gridId = 1";
            $stmt = $this->conn->prepare($query);
            $stmt->execute();
        }
    }
?>