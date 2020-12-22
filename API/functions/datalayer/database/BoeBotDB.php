<?php
    /**
     * @author Marvin Vissers
     */

    // Linking to classes in the datalayer
    require_once("../functions/datalayer/database.php");

    class BoeBotDB {
        // Creating variabels for in the class
        private $conn;

        /* Creating a new instance of GridDB */
        public function __construct() {
            // Making a connection with the database
            $database = new Database();
            $this->conn = $database->getConnection();
        }

        /**
         * @return array with boebot objects
         */
        public function getBoebots() {
            // Creating a variable to fill later
            $listBoebots = array();

            // Query to get the BoeBots from the database
            $query = "SELECT * FROM boebot ORDER BY status ASC, name DESC";
            $stmt = $this->conn->prepare($query);
            if ($stmt->execute()) {
                // Getting the grids
                $result = $stmt->fetchAll(PDO::FETCH_OBJ);

                // Filling the array with the boebot object
                foreach ($result as $boebot) {
                    $boebotModel = new BoeBot($boebot->id, $boebot->name, $boebot->status);
                    array_push($listBoebots, $boebotModel);
                }
            }

            // Returning the filled array
            return $listBoebots;
        }

        /**
         * @param $boebotModel the model of the Boebot
         */
        public function addBoebot($boebotModel) {
            // Creating variables for the query
            $name = $boebotModel->getName();

            // Query to add the Boebot to the database
            $query = "INSERT INTO boebot(name) VALUES (?)";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $name);
            $stmt->execute();
        }
    }
?>