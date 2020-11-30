<?php
    /**
     * @author Marvin Vissers
     */

    // Requireing the user controller and user model
    require("../functions/controller/userController.php");
    require("../functions/model/user.php");

    $userCtrl = new userController();

    // Checking if feedback on login is given
    $loginError = null;

    if (isset($_GET['error'])) {
        $loginError = $_GET['error'];
    }

    // Checking if button is pressed
    if (isset($_POST["btnSubmit"])) {
        // Getting the values of the input types
        $username = htmlspecialchars($_POST["txtUsername"]);
        $password = htmlspecialchars($_POST["txtPassword"]);

        // Checking if all fields are filled in
        if (!empty($username) || !empty($password)) {
            // Putting the variabels in the User model
            $userModel = new User(null, $username, $password);

            // Sending the model to the login function in the controller
            $userCtrl->userLogin($userModel);
        } else {
            // Reloading the page with an error
            header("Location: login?error=1");
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <?php
            // Including the head
            require("include/head.php");
        ?>
    </head>

    <body>
        <main class="grid">
            <section class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h1 class="grid__title">Grid - Selected name</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        Show grid or something
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        Add Boebot or something
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-6">
                        Show connected Boebots
                    </div>

                    <div class="col-sm-6">
                        Show log
                    </div>
                </div>
            </section>
        </main>

        <!-- Linking to javascript -->
        <script src="../assets/script/validation.js"></script>
    </body>
</html>