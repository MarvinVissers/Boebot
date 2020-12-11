<?php
/**
 * @author Marivn Vissers
 */

// Linking to classes in the database layer
require_once("../functions/datalayer/database/gridDB.php");
// Linking to the validation class
require_once("../functions/helper/validateInput.php");

class GridController {
    // Variabels for the needed GridDB en Validate classes
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

    /**
     * @return gridModel a model of the grid
     */
    public function getGrid() {
        // Filling the model with the data from the the database
        $gridModel = $this->gridDB->getGrid();

        // Returning the filled array
        return $gridModel;
    }

    /**
     * Function to update the grid
     * @param $gridModel model of the grid
     */
    public function updateGrid($gridModel) {
        // Sending the model the datalayer
        $this->gridDB->updateGrid($gridModel);

        // Sending user to grid page
         header("Location: grid");
    }
}
?>