<?php
    /**
     * File for generating api acces keys
     * 
     * @author Marvin Vissers
     */

    // Require the needed files
    require("../../database.php");

    // Making the database connection
    $database = new Database();
    $db = $database->getConnection();

    // Even vooraf, te token die ik hier laat zien zijn zoals ze in de url moeten komen
    $selector = bin2hex(random_bytes(8));               // Selector genereren
    $token = random_bytes(32);                          // Token genereren
    $tokenDB = password_hash($token, PASSWORD_DEFAULT); // Token hashen voor de database
    $tokenURL = bin2hex($token);                        // token omzetten voor de url

    // Setting the token in the databse
    $query = "INSERT INTO api_acces(selector, token) VALUES(?, ?)";
    $stmt = $db->prepare($query);
    $stmt->bindParam(1, $selector);
    $stmt->bindParam(2, $tokenDB);
    if ($stmt->execute()) {
        echo "API acces key opgeslagen <br>";
        echo "Selector = " . $selector . "<br>";
        echo "Token = " . $tokenURL . "<br>";
        // Gebruik validator in plaats van token zodat het is 'geheimer' is
        echo "Url om de API te benaderen is = https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/obstacle/?selector=" . $selector . "&validator=" . $tokenURL;
    }
?>