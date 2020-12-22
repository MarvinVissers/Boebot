<?php
    /**
     * @author Marvin Vissers
     */
    // Setting the page as json
    header("Content-Type: application/json");

    // Require the needed files
    require("grid.php");
    require("../../database.php");
    require("../../../model/grid.php");
    require("../../../helper/validateAPI.php");

    // Making the database connection
    $database = new Database();
    $conn = $database->getConnection();

    // Checking if selector and token are set
    if (isset($_GET["selector"]) && isset($_GET["validator"])) {
        $selector = $_GET["selector"];
        $token = $_GET["validator"];

        // Linking to validateAPI with the acces key
        $validateAPI = new validateAPI($selector, $token, $conn);
        $validAccesKey = $validateAPI->checkSelecorTokenExist();

        // Checking if selector and token exist in the database
        if ($validAccesKey) {
            // Calling the obstacleAPI class
            $gridAPI = new GridAPI($conn);
            // Creating an array to fill later
            $result = array();

            $result = $gridAPI->getGrid();

            // Converting the array to json
            echo json_encode($result);
        } else echo json_encode("Ongeldige acces key");
    } else echo json_encode("Ja die link he, die is niet geldig");
?>