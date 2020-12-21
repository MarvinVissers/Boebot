<?php
    /**
     * @author Marvin Vissers
     */

    // Require needed file
    require("../../ApiRequest.php");

    class GridAPI {

        // Making a variable for the connection
        private $conn;

        public function __construct($conn) {
            $database = new Database();
            $this->conn = $database->getConnection();
        }

        /**
         * Function to get the grid details
         */
        public function getGrid() {
            // Making a list to fill later
            $gridList = array();

            // Query to get all the products
            $query = "SELECT * FROM grid";
            $stmt = $this->conn->prepare($query);
            if ($stmt->execute()) {
                // Getting the results
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Looping through the results
                foreach ($result as $grid) {
                    // Filling the log model
                    $gridModel = array("id" => $grid->id, "rows" => $grid->rows, "columns" => $grid->columns);
                    array_push($gridList, $gridModel);
                }
            }
            // Returning the filled list
            return $gridList;
        }
    }
?>