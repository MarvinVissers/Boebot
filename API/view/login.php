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
        <main class="login">
            <section class="container">
                <div class="row justify-content-center">
                    <div class="col-sm-8">
                        <h1 class="login__title">Login</h1>
                    </div>

                    <div class="col-sm-8">
                        <form action="" method="post" class="form">
                            <div class="form__input">
                                <label for="username" class="form__label">Username</label>
                                <input type="text" name="txtUsername" id="username" class="form-control" min="1" max="50" title="Username of your account" onkeyup="loginUsernameValidation()">
                                <span id="usernameFeedback" class="form__feedback"></span>
                            </div>

                            <div class="form__input">
                                <label for="password" class="form__label">Password</label>
                                <input type="password" name="txtPassword" id="password" class="form-control" min="1" max="20" title="Password of your account" onkeyup="loginPasswordValidation()">
                                <span id="passwordFeedback" class="form__feedback"></span>
                            </div>

                            <input type="submit" value="Login" name="btnSubmit" id="btnForm" class="btn btn-primary">
                            <?php
                                // Checking if an error was given 
                                if (isset($_GET["error"])) {
                                    $error = $_GET["error"];

                                    // Displaying the error
                                    switch ($error) {
                                        case "1":
                                            ?>
                                                <span class="form__feedback">Please fill in all fields. </span>
                                            <?php
                                            break;
                                        
                                        case "2":
                                            ?>
                                                <span class="form__feedback">Now user found with this username and password. </span>
                                            <?php
                                            break;
                                        
                                        default:
                                            ?>
                                                <span class="form__feedback">Somehting went wrong, please try again now or later. </span>
                                            <?php
                                            break;
                                    }
                                }

                                // echo password_hash("wachtwoord", PASSWORD_DEFAULT);
                            ?>
                        </form>
                    </div>
                </div>
            </section>
        </main>

        <!-- Linking to javascript -->
        <script src="../assets/script/validation.js"></script>
    </body>
</html>