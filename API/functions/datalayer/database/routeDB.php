<?php
    /**
     * @author Marvin Vissers
     */

    // Linking to classes in the datalayer
    require_once("../functions/datalayer/database.php");

    class RouteDB {
        // Creating variabels for in the class
        private $conn;

        /* Creating a new instance of GridDB */
        public function __construct() {
            // Making a connection with the database
            $database = new Database();
            $this->conn = $database->getConnection();
        }

        /**
         * @param logModel the model of the Log
         */
        public function addRouteBoebot($routeModel) {
            // Creating variables for the query
            $boebot = $routeModel->getBoebot();
            $row1 = $routeModel->getRow1();
            $column1 = $routeModel->getColumn1();
            $row2 = $routeModel->getRow2();
            $column2 = $routeModel->getColumn2();

            // Query to add the Boebot to the database
            $query = "INSERT INTO route(boebot, row1, column1, row2, column2) VALUES (?, ?, ?, ?, ?)";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $boebot);
            $stmt->bindParam(2, $row1);
            $stmt->bindParam(3, $column1);
            $stmt->bindParam(4, $row2);
            $stmt->bindParam(5, $column2);
            $stmt->execute();
        }

        /**
         * Funciton to remove all routes when a new grid is set
         */
        public function removeAllRoutes() {
            // Query to remove all routes from the database
            $query = "DELETE FROM route";
            $stmt = $this->conn->prepare($query);
            $stmt->execute();
        }
    }
?>