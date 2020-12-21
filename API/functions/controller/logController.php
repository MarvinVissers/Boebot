<?php
    /**
     * @author Marivn Vissers
     */

    // Linking to classes in the database layer
    require_once("../functions/datalayer/database/logDB.php");
    // Linking to the validation class
    require_once("../functions/helper/validateInput.php");

    class LogController {
        // Variabels for the needed logDB en Validate classes
        private $logDB;
        private $validate;

        public function __construct() {
            $this->logDB = new LogDB();
            $this->validate = new Validate();
        }

        /**
         * @return array with log Objects
         */
        public function getLogItems() {
            // Creating a variable to fill later
            $listLogItems = array();

            // Filling the list with items from the database
            $listLogItems = $this->logDB->getBoebots();

            // Returning the filled array
            return $listLogItems;
        }

        /**
         * @param logModel the model of the log
         */
        public function addLogItem($logModel) {
            // Sending the variabels to the datalayer function
            $this->logDB->addLogTestItem($logModel);
        }

        /**
         * Function to delete all log items on start grid
         */
        public function deleteLog() {
            // Doing the function in the database layer
            $this->logDB->deleteLog();
        }
    }
?>