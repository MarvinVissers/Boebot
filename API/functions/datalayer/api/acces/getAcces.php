<?php
    /**
     * File for getting al the products
     * 
     * @author Marvin Vissers
     */

    // Require the needed files
    require("../../database.php");

    // Making the database connection
    $database = new Database();
    $db = $database->getConnection();

    // Query to get all the products
    $query = "SELECT * FROM api_acces";
    $stmt = $db->prepare($query);
    if ($stmt->execute()) {
        // Getting the results
        $result = $stmt->fetchAll(PDO::FETCH_OBJ); // Krijgen van alle resultaten omdat ik meerdere rijen verwacht

        foreach($result as $apiAcces) {
            echo "Selector = " . $apiAcces->selector . " en token = " . $apiAcces->token . "<br><br>";
        }
    }
?>