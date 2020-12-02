<?php
    /**
     * @author Marvin Vissers
     */

    // Requireing the user controller and user model
    require("../functions/controller/userController.php");
    require("../functions/controller/gridController.php");
    require("../functions/model/user.php");
    require("../functions/model/grid.php");

    // Making instances of the controllers
    $userCtrl = new UserController();
    $gridCtrl = new GridController();

    // Getting all the grids
    $gridList = $gridCtrl->getGrids();
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
                        <h1 class="index__title">Grid selection</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table__grid">
                            <tr class="table__row table__head"> <th>Name</th> <th>Size</th> <th>Actions</th> </tr>
                            <?php
                                foreach ($gridList as $grid) {
                                    ?>
                                    <tr class="table__row">
                                        <td class="table__td"><?php echo $grid->getName(); ?></td>
                                        <td class="table__td"><?php echo $grid->getLength(); ?> by <?php echo $grid->getHeight(); ?></td>
                                        <td class="table__td"><a href="grid?gridId=<?php echo $grid->getId(); ?>" class="table__link table--details">Details</a></td>
                                    </tr>
                                    <?php
                                }
                            ?>
                        </table>
                    </div>
                </div>
            </section>
        </main>

        <!-- Linking to javascript -->
        <script src="../assets/script/validation.js"></script>
    </body>
</html>