        <?php
            // Checking if the modal button is pressed
            if (isset($_POST["btnAddObstacle"])) {
                // Getting the values of the form
                $row1 = $_POST["txtAddStartX"];
                $column1 = $_POST["txtAddStartY"];
                $row2 = $_POST["txtAddEndX"];
                $column2 = $_POST["txtAddEndY"];
                // Text for the log
                $text = "Add obstacle on starting point (" . $row1 . "," . $column1 . ") and ending point (" . $row2 . "," . $column2 . ")";

                // Filling the route model
                $obstacleModel = new Obstacle(null, 1, $row1, $column1, $row2, $column2);
                // Filling the log model
                $logModel = new Log(null, "Obstacle", $text);

                // Adding the route to the database
                $logCtrl->addLogItem($logModel);
                $obstacleCtrl->addObstacle($obstacleModel);
            }
        ?>

        <!-- The modal add or remove an obstacle -->
        <div class="modal fade" id="addObstacle">
            <form action="" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Add obstacle</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="col-sm-12">
                                        <label for="">Starting point</label>
                                    </div>

                                    <div class="form--inline">
                                        <div class="col-sm-6">
                                            <input type="number" name="txtAddStartX" id="addStartX" class="form-control form--label" min="0" maxlength="2" required readonly>
                                            <span id="startXFeedback" class="form__feedback"></span>
                                        </div>

                                        <div class="col-sm-6">
                                            <input type="number" name="txtAddStartY" id="addStartY" class="form-control form--label" min="0" maxlength="2" required readonly>
                                            <span id="startYFeedback" class="form__feedback"></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <div class="col-sm-12">
                                        <label for="">Ending point</label>
                                    </div>

                                    <div class="form--inline">
                                        <div class="col-sm-6">
                                            <input type="number" name="txtAddEndX" id="addEndX" class="form-control form--label" min="0" maxlength="2" required readonly>
                                            <span id="EndXFeedback" class="form__feedback"></span>
                                        </div>

                                        <div class="col-sm-6">
                                            <input type="number" name="txtAddEndY" id="addEndY" class="form-control form--label" min="0" maxlength="2" required readonly>
                                            <span id="EndYFeedback" class="form__feedback"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <input type="submit" value="Add obstacle" name="btnAddObstacle" id="btnAddObstacle" class="btn btn-primary">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>