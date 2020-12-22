        <?php
            // Checking if the modal button is pressed
            if (isset($_POST["btnRemoveObstacle"])) {
                // Getting the values of the form
                $row1 = $_POST["txtRemoveStartX"];
                $column1 = $_POST["txtRemoveStartY"];
                $row2 = $_POST["txtRemoveEndX"];
                $column2 = $_POST["txtRemoveEndY"];
                // Text for the log
                $text = "Remove obstacle on starting point (" . $row1 . "," . $column1 . ") and ending point (" . $row2 . "," . $column2 . ")";

                // Filling the route model
                $obstacleModel = new Obstacle(null, 1, $row1, $column1, $row2, $column2);
                // Filling the log model
                $logModel = new Log(null, "Obstacle", $text);

                // Adding the route to the database
                $logCtrl->addLogItem($logModel);
                $obstacleCtrl->removeObstacle($obstacleModel);
            }
        ?>

        <!-- The modal add or remove an obstacle -->
        <div class="modal fade" id="removeObstacle">
            <form action="" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Remove obstacle</h4>
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
                                            <input type="number" name="txtRemoveStartX" id="removeStartX" class="form-control form--label" min="0" maxlength="2" required readonly>
                                            <span id="startXFeedback" class="form__feedback"></span>
                                        </div>

                                        <div class="col-sm-6">
                                            <input type="number" name="txtRemoveStartY" id="removeStartY" class="form-control form--label" min="0" maxlength="2" required readonly>
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
                                            <input type="number" name="txtRemoveEndX" id="removeEndX" class="form-control form--label" min="0" maxlength="2" required readonly>
                                            <span id="EndXFeedback" class="form__feedback"></span>
                                        </div>

                                        <div class="col-sm-6">
                                            <input type="number" name="txtRemoveEndY" id="removeEndY" class="form-control form--label" min="0" maxlength="2" required readonly>
                                            <span id="EndYFeedback" class="form__feedback"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <input type="submit" value="Remove obstacle" name="btnRemoveObstacle" id="btnRemoveObstacle" class="btn btn-primary">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>