<?php
    /**
     * @author Marvin Vissers
     */

    // Requireing the user controller and user model
    require("../functions/controller/userController.php");
    require("../functions/controller/gridController.php");
    require("../functions/controller/BoeBotController.php");
    require("../functions/controller/logController.php");
    require("../functions/controller/routeController.php");
    require("../functions/controller/obstacleController.php");
    require("../functions/model/user.php");
    require("../functions/model/grid.php");
    require("../functions/model/BoeBot.php");
    require("../functions/model/log.php");
    require("../functions/model/route.php");
    require("../functions/model/obstacle.php");
    require("../functions/model/gridLine.php");

    // Making instances of the controllers
    $userCtrl = new UserController();
    $gridCtrl = new GridController();
    $boebotCtrl = new BoeBotController();
    $logCtrl = new LogController();
    $routeCtrl = new RouteController();
    $obstacleCtrl = new ObstacleController();

    // Variable for userId to fill later
    $userID = null;

    session_start();
    // Checking if user is logged in
    if (isset($_SESSION['userId'])) {
        $userID = $_SESSION['userId'];
    } else {
        header("Location: login");
    }

    // Getting the grid
    $gridModel = $gridCtrl->getGrid();
    $obstacleList = $obstacleCtrl->getObstacles();

    // Setting the rows and columns of the grid
    // Minis 1 because else the grid is 1 size to big
    $rows = $gridModel->getRows();
    $columns = $gridModel->getColumns();

    if ($rows >= 1) {
        $rows--;
    }

    if ($columns >= 1) {
        $columns--;
    }

    // Getting the connected Boebots
    $conenctedBoebots = $boebotCtrl->getBoebots();

    // Checking if the user want to connect a new Boebt
    if (isset($_POST["btnSubmitConnect"])) {
        // Getting the values from the form
        $boebotName = htmlspecialchars($_POST["txtBoeBot"]);

        // Filling the Boebot model
        $boebot = new BoeBot(null, $boebotName, null);

        // Sending the variables to a function to add the BoeBot to the database
        $boebotCtrl->addBoebot($boebot);
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
        <script>
            // Calling a function to fill the log
            fillLog();
        </script>

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
                                                                // Setting the line coordinates
                                                                $gridLineTop = new GridLine($x0, $y0, $x0, $y1);

                                                                // Checking if the column has a obstacle, if so display in pink and trigger remove modal instead of add modal
                                                                if ($obstacleCtrl->checkObstacleGrid($gridLineTop, $obstacleList)) {
                                                                    ?>
                                                                    <span class="grid__line grid__line--top grid--obstacle" id="top<?php echo $x0 . $y0; ?>" onclick="toggleObstacle('top', <?php echo $x0; ?>, <?php echo $y0; ?>, <?php echo $x0; ?>, <?php echo $y1; ?>)" data-toggle="modal" data-target="#removeObstacle"></span>
                                                                    <?php
                                                                } else {
                                                                    ?>
                                                                    <span class="grid__line grid__line--top" id="top<?php echo $x0 . $y0; ?>" onclick="toggleObstacle('top', <?php echo $x0; ?>, <?php echo $y0; ?>, <?php echo $x0; ?>, <?php echo $y1; ?>)" data-toggle="modal" data-target="#addObstacle"></span>
                                                                    <?php
                                                                }
                                                                ?>
                                                                <input type="hidden" name="txtCoordinatesTop<?php echo $i . $j; ?>" id="coordinatesTop<?php echo $i . $j; ?>" value="<?php echo $x0 . $y0 . $x0 . $y1; ?>">
                                                                <?php
                                                            }

                                                            // Checking if its the first row
                                                            if ($i == 0) {
                                                                // Setting the line coordinates
                                                                $gridLineLeft = new GridLine($x0, $y0, $x1, $y0);

                                                                // Checking if the column has a obstacle, if so display in pink and trigger remove modal instead of add modal
                                                                if ($obstacleCtrl->checkObstacleGrid($gridLineLeft, $obstacleList)) {
                                                                    ?>
                                                                    <span class="grid__line grid__line--left grid--obstacle" id="left<?php echo $x0 . $y0; ?>" onclick="toggleObstacle('left', <?php echo $x0; ?>, <?php echo $y0; ?>, <?php echo $x1; ?>, <?php echo $y0; ?>)" data-toggle="modal" data-target="#removeObstacle"></span>
                                                                    <?php
                                                                }
                                                                ?>
                                                                    <span class="grid__line grid__line--left" id="left<?php echo $x0 . $y0; ?>" onclick="toggleObstacle('left', <?php echo $x0; ?>, <?php echo $y0; ?>, <?php echo $x1; ?>, <?php echo $y0; ?>)" data-toggle="modal" data-target="#addObstacle"></span>
                                                                    <input type="hidden" name="txtCoordinatesLeft<?php echo $i . $j; ?>" id="coordinatesLeft<?php echo $i . $j; ?>" value="<?php echo $x0 . $y0 . $x1 . $y0; ?>">
                                                                <?php
                                                            }

                                                        // Setting the line coordinates
                                                        $gridLineBottom = new GridLine($x1, $y0, $x1, $y1);

                                                        // Checking if the column has a obstacle, if so display in pink and trigger remove modal instead of add modal
                                                        if ($obstacleCtrl->checkObstacleGrid($gridLineBottom, $obstacleList)) {
                                                            ?>
                                                            <span class="grid__line grid__line--bottom grid--obstacle" id="bottom<?php echo $x1 . $y0; ?>" onclick="toggleObstacle('bottom', <?php echo $x1; ?>, <?php echo $y0; ?>, <?php echo $x1; ?>, <?php echo $y1; ?>)" data-toggle="modal" data-target="#removeObstacle"></span>
                                                            <?php
                                                        } else {
                                                            ?>
                                                            <span class="grid__line grid__line--bottom" id="bottom<?php echo $x1 . $y0; ?>" onclick="toggleObstacle('bottom', <?php echo $x1; ?>, <?php echo $y0; ?>, <?php echo $x1; ?>, <?php echo $y1; ?>)" data-toggle="modal" data-target="#addObstacle"></span>
                                                            <?php
                                                        }

                                                        // Setting the line coordinates
                                                        $gridLineRight = new GridLine($x0, $y1, $x1, $y1);

                                                        // Checking if the column has a obstacle, if so display in pink and trigger remove modal instead of add modal
                                                        if ($obstacleCtrl->checkObstacleGrid($gridLineRight, $obstacleList)) {
                                                            ?>
                                                            <span class="grid__line grid__line--right grid--obstacle" id="right<?php echo $x0 . $y1; ?>" onclick="toggleObstacle('right', <?php echo $x0; ?>, <?php echo $y1; ?>, <?php echo $x1; ?>, <?php echo $y1; ?>)" data-toggle="modal" data-target="#removeObstacle"></span>
                                                            <?php
                                                        } else {
                                                            ?>
                                                            <span class="grid__line grid__line--right" id="right<?php echo $x0 . $y1; ?>" onclick="toggleObstacle('right', <?php echo $x0; ?>, <?php echo $y1; ?>, <?php echo $x1; ?>, <?php echo $y1; ?>)" data-toggle="modal" data-target="#addObstacle"></span>
                                                            <?php
                                                        }
                                                        ?>
                                                        <input type="hidden" name="txtCoordinatesBottom<?php echo $i . $j; ?>" id="coordinatesBottom<?php echo $i . $j; ?>" value="<?php echo $x1 . $y0 . $x1 . $y1; ?>">
                                                        <input type="hidden" name="txtCoordinatesRight<?php echo $i . $j; ?>" id="coordinatesRight<?php echo $i . $j; ?>" value="<?php echo $x0 . $y1 . $x1 . $y1; ?>">
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
                        <div class="col-sm-4">
                            <form action="" method="post" class="connect">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <h2 class="connect__title">Connect new Boebot</h2>
                                    </div>

                                    <div class="col-sm-12">
                                        <label for="boebotIP">BoeBot name</label>
                                        <input type="text" name="txtBoeBot" class="form-control" id="boebotIP" minlength="1" maxlength="25" placeholder="Like ada-rbp-14" required>
                                        <span id="boebotNameFeedback" class="form__feedback"></span>
                                    </div>

                                    <div class="col-sm-12">
                                        <input type="submit" name="btnSubmitConnect" value="Add Boebot" class="btn btn-primary">
                                    </div>
                                </div>
                            </form>

                            <h2 class="connected">Connected Boebots</h2>

                            <table class="table">
                                <thead>
                                <tr class="table__row">
                                    <th class="table__head">BoeBot</th>
                                    <th class="table__head table--right">Actions</th>
                                </tr>
                                </thead>

                                <tbody>
                                <?php
                                    foreach ($conenctedBoebots as $boebot) {
                                        ?>
                                        <tr class="table__row">
                                            <td class="table__td"><?php echo $boebot->getName(); ?></td>
                                            <td class="table__td table--right">
                                                <a href="javascript:void(0)" class="table__link talbe--actions" onclick="setBoebotInModal('<?php echo $boebot->getName(); ?>')" data-toggle="modal" data-target="#testBoebot">Test BoeBot</a>
                                                <a href="javascript:void(0)" class="table__link talbe--actions" onclick="setBoebotInModal('<?php echo $boebot->getName(); ?>')" data-toggle="modal" data-target="#chooseBoebot">Choose route</a>
                                            </td>
                                        </tr>
                                        <?php
                                    }
                                ?>
                                </tbody>
                            </table>
                        </div>

                        <div class="col-md-8" id="logPart">
                            <h2 class="log">Log</h2>

                            <div class="log__container">
                                <div class="log__actions">
                                    <a href="javascript:void(0)" class="log__refresh" onclick="refreshLog()">Refresh log</a>
                                </div>

                                <div class="log__text" id="logText">
                                    <!-- Paragraph for the API log -->
                                    <p id="log"></p>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
        </main>

        <?php
            // Including the modals
            require("include/addObstacleModal.php");
            require("include/removeObstacleModal.php");
            require("include/testBoebotModal.php");
            require("include/routeBoebotModal.php");
        ?>

        <!-- Linking to javascript -->
        <script src="../assets/script/main.js"></script>
    </body>
</html>