<?php
    /**
     * @author Marvin Vissers
     */

    // Requireing the user controller and user model
    require("../functions/controller/userController.php");
    require("../functions/controller/gridController.php");
    require("../functions/controller/BoeBotController.php");
    require("../functions/model/user.php");
    require("../functions/model/grid.php");
    require("../functions/model/BoeBot.php");

    // Making instances of the controllers
    $userCtrl = new UserController();
    $gridCtrl = new GridController();
    $boebotCtrl = new BoeBotController();

    // Variable for userId to fill later
    $userID = null;

    //    // Checking if user is logged in
    //    if (isset($_SESSION['userId'])) {
    //        $userID = $_SESSION['userId'];
    //    } else {
    //        header("Location: login");
    //    }

    // Getting the grid
    $gridModel = $gridCtrl->getGrid();

    // Setting the rows and columns of the grid
    // Minis 1 because else the grid is 1 size to big
    $rows = $gridModel->getRows() - 1;
    $columns = $gridModel->getColumns() - 1;

    // Getting the connected Boebots
    $conenctedBoebots = $boebotCtrl->getBoebots();

    // Checking if the user want to connect a new Boebt
    if (isset($_POST["btnSubmitConnect"])) {
        // Getting the values from the form
        $boebotName = htmlspecialchars($_POST["txtBoeBot"]);

        // Filling the Boebot model
        $boebot = new BoeBot(null, $boebotName, null);

        // Sending the variables to a function to add the BoeBot to the database

    }

    if (isset($_GET['error'])) {
        $loginError = $_GET['error'];
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
                        <h1 class="grid__title">Grid</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="grid__map">
                            <?php
                                // Looping through the length of the grid
                                for ($i = 0; $i < $rows; $i++) {
                                    // Creating variables for the javascript functies
                                    $x1 = $i;
                                    $x0 = $x1++;
                                    ?>
                                        <div class="grid__row">
                                            <?php
                                                // Looping through the height of the grid
                                                for ($j = 0; $j < $columns; $j++) {
                                                    // Creating variables for the javascript functies
                                                    $y1 = $j;
                                                    $y0 = $y1++;

                                                    ?>
                                                    <div class="grid__column" id="<?php echo $i . $j; ?>">
                                                        <?php
                                                            // Checking if its the first column
                                                            if ($j == 0) {
                                                                ?>
                                                                <span class="grid__line grid__line--top" id="top<?php echo $x0 . $y0; ?>" onclick="toggleObstacle('top', <?php echo $x0; ?>, <?php echo $y0; ?>, <?php echo $x0; ?>, <?php echo $x1; ?>)"></span>
                                                                <?php
                                                            }

                                                            // Checking if its the first row
                                                            if ($i == 0) {
                                                                ?>
                                                                    <span class="grid__line grid__line--left" id="left<?php echo $x0 . $y0; ?>" onclick="toggleObstacle('left', <?php echo $x0; ?>, <?php echo $y0; ?>, <?php echo $x1; ?>, <?php echo $y0; ?>)"></span>
                                                                <?php
                                                            }
                                                        ?>
                                                        <span class="grid__line grid__line--bottom" id="bottom<?php echo $x1 . $y0; ?>" onclick="toggleObstacle('bottom', <?php echo $x1; ?>, <?php echo $y0; ?>, <?php echo $x1; ?>, <?php echo $y1; ?>)"></span>
                                                        <span class="grid__line grid__line--right" id="right<?php echo $x0 . $y1; ?>" onclick="toggleObstacle('right', <?php echo $x0; ?>, <?php echo $y1; ?>, <?php echo $x1; ?>, <?php echo $y1; ?>)"></span>
                                                    </div>
                                                <?php
                                                }
                                            ?>
                                        </div>
                                    <?php
                                }
                            ?>
                        </div>
                    </div>
                </div>

                <section class="actions">
                    <div class="row">
                        <div class="col-sm-6">
                            <form action="" method="post" class="connect">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <h2 class="connect__title">Connect new Boebot</h2>
                                    </div>

                                    <div class="col-sm-12">
                                        <label for="boebotIP">BoeBot name</label>
                                        <input type="text" name="txtBoeBot" class="form-control" id="boebotIP" minlength="1" maxlength="25" placeholder="Like rpb-ada14" required>
                                        <span id="usernameFeedback" class="form__feedback"></span>
                                    </div>

                                    <div class="col-sm-12">
                                        <input type="submit" name="btnSubmitConnect" value="Add Boebot" class="btn-primary">
                                    </div>
                                </div>
                            </form>

                            <h2 class="connected">Connected Boebots</h2>

                            <table class="table table-hover">
                                <thead>
                                <tr class="table__row">
                                    <th class="table__head">BoeBot</th>
                                    <th class="table__head">Status</th>
                                    <th class="table__head">Actions</th>
                                </tr>
                                </thead>

                                <tbody>
                                <?php
                                    foreach ($conenctedBoebots as $boebot) {
                                        ?>
                                        <tr class="table__row">
                                            <td class="table__td"><?php echo $boebot->getName(); ?></td>
                                            <td class="table__td"><?php echo $boebot->getStatus(); ?></td>
                                            <td class="table__td"></td>
                                        </tr>
                                        <?php
                                    }
                                ?>
                                </tbody>
                            </table>
                        </div>

                        <div class="col-sm-6">
                            <h2 class="log">log</h2>

                            <div class="log__container">
                                <div class="log__text" id="logText">
                                    <!-- TODO gekke API calls -->
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
        </main>

        <!-- Linking to javascript -->
        <script src="../assets/script/validation.js"></script>

        <script>
            /**
             * Function to set a obstacle on the grid and save it in the database
             * @param direction the direction on the grid
             * @param x1 the starting point from top to bottom
             * @param y1 the starting point from left to right
             * @param x2 the end point from top to bottom
             * @param y2 the end point from left to right
             */
            // function toggleObstacle(direction, x1, y1, x2, y2) {
            //     // Setting the line active
            //     document.getElementById(direction + x1 + y1).classList.toggle("grid--obstacle");
            //
            //     // Filling the url
            //     var url = "https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/obstacle/?selector=ae026dd58cd57fd2&validator=4424bdd85905aa88646327911b6893598a279abb4f82466dca61a988041afb08&action=post&row1=" + x1 + "&column1=" + y1 + "&row2=" + x2 + "&column2=" + y2;
            //     console.log(url);
            //
            //     // Posting the data to the API
            //     var xhttp = new XMLHttpRequest();
            //     xhttp.open("POST", url, true);
            //     xhttp.setRequestHeader("Content-type", "application/json");
            //     xhttp.send();
            // }
        </script>
    </body>
</html>