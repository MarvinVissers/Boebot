<?php
    /**
     * @author Marivn Vissers
     */

    // Linking to classes in the database layer
    require_once("../functions/datalayer/database/routeDB.php");
    // Linking to the validation class
    require_once("../functions/helper/validateInput.php");

    class RouteController {
        // Variabels for the needed BoeBotDB en Validate classes
        private $routeDB;
        private $validate;

        public function __construct() {
            $this->routeDB = new RouteDB();
            $this->validate = new Validate();
        }

        /**
         * @param logModel the model of the log
         */
        public function addRouteBoebot($routeModel) {
            // Sending the variabels to the datalayer function
            $this->routeDB->addRouteBoebot($routeModel);
        }

        /**
         * Funciton to remove all routes when a new grid is set
         */
        public function removeAllRoutes() {
            // Sending the variabels to the datalayer function
            $this->routeDB->removeAllRoutes();
        }
    }
?>