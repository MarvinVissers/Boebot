<?php
    /**
     * @author Marvin Vissers
     */

    // Requireing the user controller and user model
    require("../functions/controller/userController.php");
    require("../functions/controller/gridController.php");
    require("../functions/controller/logController.php");
    require("../functions/controller/obstacleController.php");
    require("../functions/controller/routeController.php");
    require("../functions/model/user.php");
    require("../functions/model/grid.php");
    require("../functions/model/log.php");

    // Making instances of the controllers
    $userCtrl = new UserController();
    $gridCtrl = new GridController();
    $logCtrl = new LogController();
    $obstacleCtrl = new ObstacleController();
    $routeCtrl = new RouteController();

    $userID = null;

    session_start();
    // Checking if user is logged in
    if (isset($_SESSION['userId'])) {
        $userID = $_SESSION['userId'];
    } else {
        header("Location: login");
    }

     // Emptying the log
    $logCtrl->deleteLog();

    // Checking if button of form is pressed
    if (isset($_POST["btnSubmit"])) {
        // Getting the values from the form
        $rows = $_POST["txtRows"];
        $columns = $_POST["txtColumns"];

        // Filling the grid model
        $gridModel = new Grid(1, null, $rows, $columns);
        // Filling the log model
        $logModel = new Log(null, "Grid", "Rezise grid to " . $rows . " rows and " . $columns . " columns");

        // Emptying the routes
        $routeCtrl->removeAllRoutes();
        // Emptying the obstacles
        $obstacleCtrl->removeAllObstacles();
        // Adding the log
        $logCtrl->addLogItem($logModel);
        // Updating the grid
        $gridCtrl->updateGrid($gridModel);
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
        <main class="index">
            <section class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h1 class="index__title">Create grid</h1>
                    </div>
                </div>

                <form action="" method="post">
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="rows">Rows</label>
                            <input type="number" name="txtRows" id="rows" class="form-control" maxlength="2" minlength="1" min="1" max="99" required>
                            <span class="form__feedback"></span>
                        </div>
                        <div class="col-sm-6">
                            <label for="columns">Columns</label>
                            <input type="number" name="txtColumns" id="columns" class="form-control" maxlength="2" minlength="1" min="1" max="99" required>
                            <span class="form__feedback"></span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <input type="submit" name="btnSubmit" value="Grid maken" class="btn btn-primary">
                        </div>
                    </div>
                </form>
            </section>
        </main>

        <!-- Linking to javascript -->
        <script src="../assets/script/validation.js"></script>
    </body>
</html>