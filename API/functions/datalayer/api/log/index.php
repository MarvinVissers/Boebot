<?php
    /**
     * @author Marvin Vissers
     */
    // Setting the page as json
    header("Content-Type: application/json");

    // Require the needed files
    require("log.php");
    require("../../database.php");
    require("../../../model/log.php");
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
            $logAPI = new LogAPI($conn);
            // Creating an array to fill later
            $result = array();

            // Switch for the actions
            switch ($action) {
                case "post":
                    // Filling the obstacle model
                    $logModel = new Log(null, $_GET["boebot"], $_GET["text"]);
                    // Adding the log to the database
                    $result = $logAPI->post($logModel);
                    break;
                case "getLast":
                    // Filling the obstacle model
                    $logModel = new Log(null, $_GET["boebot"], null);
                    // Filling the log array with the last log item of the Boebot
                    $result = $logAPI->getLast($logModel);
                    break;
                default:
                    // Filling the obstacle array with all log items
                    $result = $logAPI->getAll();
                    break;
            };

            // Converting the array to json
            echo json_encode($result);
        } else echo json_encode("Ongeldige acces key");
    } else echo json_encode("Ja die link he, die is niet geldig");
?>