<?php
    /**
     * @author Marvin Vissers
     */

    // Require needed file
    require("../../ApiRequest.php");

    class RouteAPI {

        // Making a variable for the connection
        private $conn;

        public function __construct($conn) {
            $database = new Database();
            $this->conn = $database->getConnection();
        }

        /**
         * Function to get the grid details
         */
        public function getLastRoute($boebot) {
            // Making a list to fill later
            $routeList = array();

            // Query to get all the products
            $query = "SELECT * FROM route WHERE boebot = ? ORDER BY id DESC LIMIT 1";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $boebot);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Looping through the results
                foreach ($result as $route) {
                    // Filling the log model
                    $routeModel = array("id" => $route->id, "boebot" => $route->boebot, "row1" => $route->row1, "column1" => $route->column1, "row2" => $route->row2, "column2" => $route->column2);
                    array_push($routeList, $routeModel);
                }
            }
            // Returning the filled list
            return $routeList;
        }
    }
?>