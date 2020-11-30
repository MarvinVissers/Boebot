<?php
    /**
     * @author Marvin Vissers
     */
    // Setting the page as json
    header("Content-Type: application/json");

    // Require the needed files
    require("obstacle.php");
    require("../../database.php");
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
            $obstacleAPI = new ObstacleAPI($conn);
            // Filling the obstacle array
            $listObstacles = $obstacleAPI->getAll();

            // Converting the array to json
            echo json_encode(array("products" => $listObstacles));
        }
    } else echo "Ja die link he, die is niet geldig";
?>