<?php
    /**
     * @author Marivn Vissers
     */

    // Linking to classes in the database layer
    require_once("../functions/datalayer/database/BoeBotDB.php");
    // Linking to the validation class
    require_once("../functions/helper/validateInput.php");

    class BoeBotController {
        // Variabels for the needed BoeBotDB en Validate classes
        private $boebotDB;
        private $validate;

        public function __construct() {
            $this->boebotDB = new BoeBotDB();
            $this->validate = new Validate();
        }

        /**
         * @return array with Boebot objects
         */
        public function getBoebots() {
            // Creating a variable to fill later
            $listBoebots = array();

            // Filling the list with items from the database
            $listBoebots = $this->boebotDB->getBoebots();

            // Returning the filled array
            return $listBoebots;
        }

        /**
         * @param $boebotModel the model of the BoeBot
         */
        public function addBoebot($boebotModel) {
            // Sending the variabels to the datalayer function
            $this->boebotDB->addBoebot($boebotModel);
        }
    }
?>