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
}
?>