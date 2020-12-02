<?php
/**
 * @author Marivn Vissers
 */

// Linking to classes in the database layer
require_once("../functions/datalayer/database/gridDB.php");
// Linking to the validation class
require_once("../functions/helper/validateInput.php");

class GridController {
    // Variabels for the needed UserDB en Validate classes
    private $gridDB;
    private $validate;

    public function __construct() {
        $this->gridDB = new GridDB();
        $this->validate = new Validate();
    }

    /**
     * @return gridList an array with all grid in the database
     */
    public function getGrids() {
        // Creating an array to fill later
        $gridList = array();

        // Making an array and filling it with the grids from the database
        $gridList = $this->gridDB->getGrids();

        // Returning the filled array
        return $gridList;
    }
}
?>