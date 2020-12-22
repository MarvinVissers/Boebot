<?php
/**
 * @author Marvin Vissers
 */

// Linking to classes in the datalayer
require_once("../functions/datalayer/database.php");

class GridDB  {
    // Creating variabels for in the class
    private $conn;

    /* Creating a new instance of GridDB */
    public function __construct() {
        // Making a connection with the database
       $database = new Database();
       $this->conn = $database->getConnection();
    }

   public function getGrids() {
       // Creating an array to fill it later
       $gridList = array();

       // Query to get all grids from the database
       $query = "SELECT * FROM grid";
       $stmt = $this->conn->prepare($query);
       if ($stmt->execute()) {
           // Getting the grids
           $result = $stmt->fetchAll(PDO::FETCH_OBJ);

           // Filling the array with the grid object
           foreach ($result as $grid) {
               $gridModel = new Grid($grid->id, $grid->name, $grid->length, $grid->height);
               array_push($gridList, $gridModel);
           }
       }
       // Returning the array
       return $gridList;
   }

    /**
     * @return gridModel a model of the grid
     */
    public function getGrid(){
        $gridModel = null;

        // Query to get the grid from the database
        $query = "SELECT * FROM grid WHERE id = 1";
        $stmt = $this->conn->prepare($query);
        if ($stmt->execute()) {
            // Getting the grids
            $result = $stmt->fetch(PDO::FETCH_OBJ);

            // Filling the model
            $gridModel = new Grid(1, $result->name, $result->rows, $result->columns);
        }

        // Returning the filled model
        return $gridModel;
    }

   public function updateGrid($gridModel) {
        // Creating variabels for the query
        $rows = $gridModel->getRows();
        $columns = $gridModel->getColumns();

        // Query to update the grid in the database
        $query = "UPDATE grid SET rows = ?, columns = ? WHERE id = 1";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $rows);
        $stmt->bindParam(2, $columns);
        $stmt->execute();
   }
}
?>