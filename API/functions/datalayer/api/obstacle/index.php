<?php
    /**
     * @author Marvin Vissers
     */
    // Setting the page as json
    header("Content-Type: application/json");

    // Require the needed files
    require("obstacle.php");
    require("../../database.php");
    require("../../../model/obstacle.php");
    require("../../../helper/validateAPI.php");

    // Making the database connection
    $database = new Database();
    $conn = $database->getConnection();

    // Checking if selector and token are set
    if (isset($_GET["selector"]) && isset($_GET["validator"])) {
        $selector = $_GET["selector"];
        $token = $_GET["validator"];
        $action = $_GET["action"];
 
        // Linking to validateAPI with the acces key
        $validateAPI = new validateAPI($selector, $token, $conn);
        $validAccesKey = $validateAPI->checkSelecorTokenExist();

        // Checking if selector and token exist in the database
        if ($validAccesKey) {
            // Calling the obstacleAPI class
            $obstacleAPI = new ObstacleAPI($conn);
            // Creating an array to fill later
            $result = array();

            // Switch for the actions
            switch ($action) {
                case "post":
                    // Filling the obstacle model
                    $obstacleModel = new Obstacle(null, $_GET["gridId"], $_GET["length"], $_GET["height"]);
                    // Adding the obstacle to the database
                    $result = $obstacleAPI->post($obstacleModel);
                    break;
                case "put":
                    // Filling the obstacle model
                    $obstacleModel = new Obstacle($_GET["id"], $_GET["gridId"], $_GET["length"], $_GET["height"]);
                    // Updating the obstacle to the database
                    $result = $obstacleAPI->put($obstacleModel);
                    break;
                case "delete":
                    break;
                case "getObstaclesGrid":
                    // Filling the obstacle array with all obstacles of 1 grid
                    $result = $obstacleAPI->getGridObstacles($_GET["gridId"]);
                    break;
                default:
                    // Filling the obstacle array with all obstacles
                    $result = $obstacleAPI->getAll();
                    break;
            };

            // Converting the array to json
            echo json_encode(array("obstacle" => $result));
        } else echo json_encode("Ongeldige acces key");
    } else echo json_encode("Ja die link he, die is niet geldig");
?>